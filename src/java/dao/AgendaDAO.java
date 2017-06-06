/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controle.C;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Agenda;
import modelo.Carro;

/**
 *
 * @author Lucas
 */
public class AgendaDAO {

    private PreparedStatement pst;
    private ResultSet rs;
    private Connection con;
    private String sql;

    public boolean inserirAgenda(Agenda ag) throws ParseException {
        sql = "INSERT INTO agenda (latitude, longitude, data, hora_inicio, hora_fim, id_carro) VALUES (?,?,?,?,?,?);";
        try {
            if (ag != null) {
                con = C.conectaBanco();
                pst = con.prepareStatement(sql);

                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String data = formatter.format(ag.getData());
                java.util.Date dateStr = formatter.parse(data);
                java.sql.Date dateDB = new java.sql.Date(dateStr.getTime());

                SimpleDateFormat formata_hora = new SimpleDateFormat("HH:mm");
                String hora_inicio = formata_hora.format(ag.getHora_inicio());
                String hora_fim = formata_hora.format(ag.getHora_fim());

                java.util.Date horaStr = formata_hora.parse(hora_inicio);
                java.util.Date horaFimStr = formata_hora.parse(hora_fim);

                pst.setDouble(1, ag.getLatitude());
                pst.setDouble(2, ag.getLongitude());
                pst.setDate(3, dateDB);
                pst.setTime(4, new Time(horaStr.getTime()));
                pst.setTime(5, new Time(horaFimStr.getTime()));
                pst.setInt(6, ag.getCarro().getId_carro());
                pst.executeUpdate();
                pst.close();
                C.desconectaBanco();
                return true;
            } else {
                return false;
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CartaoFidelidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Agenda> getAgendas() throws ClassNotFoundException, SQLException {
        sql = "SELECT * FROM mydb.agenda Join carro where agenda.id_carro = carro.id_carro;";
        List<Agenda> agendas = new ArrayList<>();
        Agenda a;
        Carro c;
        con = C.conectaBanco();
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        while (rs.next()) {
            a = new Agenda();
            c = new Carro();
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String data = formatter.format(rs.getDate("data"));
                Date date = (Date) formatter.parse(data);

                SimpleDateFormat formata_hora = new SimpleDateFormat("HH:mm");
                String hora_inicio = formata_hora.format(rs.getTime("hora_inicio"));
                String hora_fim = formata_hora.format(rs.getTime("hora_fim"));

                java.util.Date horaStr = formata_hora.parse(hora_inicio);
                java.util.Date horaFimStr = formata_hora.parse(hora_fim);

                a.setId_agenda(rs.getInt("id_agenda"));
                a.setLatitude(rs.getDouble("latitude"));
                a.setLongitude(rs.getDouble("longitude"));
                a.setData(date);
                a.setHora_inicio(horaStr);
                a.setHora_fim(horaFimStr);

                c.setId_carro(rs.getInt("id_carro"));
                c.setTipo_comida(rs.getString("tipo_comida"));
                a.setCarro(c);
                agendas.add(a);
            } catch (ParseException ex) {
                Logger.getLogger(AgendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        pst.close();
        C.desconectaBanco();
        return agendas;
    }

    public boolean atualizaAgenda(Agenda ag) throws ParseException {
        String sqlAgenda = "UPDATE agenda SET agenda.data = ?, agenda.hora_inicio = ?, agenda.hora_fim = ? WHERE agenda.id_agenda = ?";
        try {
            con = C.conectaBanco();
            pst = con.prepareStatement(sqlAgenda);
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String data = formatter.format(ag.getData());
            java.util.Date dateStr = formatter.parse(data);
            java.sql.Date dateDB = new java.sql.Date(dateStr.getTime());

            SimpleDateFormat formata_hora = new SimpleDateFormat("HH:mm");
            String hora_inicio = formata_hora.format(ag.getHora_inicio());
            String hora_fim = formata_hora.format(ag.getHora_fim());

            java.util.Date horaStr = formata_hora.parse(hora_inicio);
            java.util.Date horaFimStr = formata_hora.parse(hora_fim);

            pst.setDate(1, dateDB);
            pst.setTime(2, new Time(horaStr.getTime()));
            pst.setTime(3, new Time(horaFimStr.getTime()));
            pst.setInt(4, ag.getId_agenda());
            pst.executeUpdate();
            pst.close();
            C.desconectaBanco();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Agenda getAgendaData() {
        sql = "SELECT * FROM agenda WHERE data = DATE(NOW())";
        Agenda agenda = null;
        try {
            con = C.conectaBanco();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                agenda = new Agenda();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

                String dataRs = formatter.format(rs.getDate("data"));
                Date date = (Date) formatter.parse(dataRs);
                SimpleDateFormat formata_hora = new SimpleDateFormat("HH:mm");
                String hora_inicio = formata_hora.format(rs.getTime("hora_inicio"));
                String hora_fim = formata_hora.format(rs.getTime("hora_fim"));
                java.util.Date horaStr = formata_hora.parse(hora_inicio);
                java.util.Date horaFimStr = formata_hora.parse(hora_fim);
                agenda.setId_agenda(rs.getInt("id_agenda"));
                agenda.setLatitude(rs.getDouble("latitude"));
                agenda.setLongitude(rs.getDouble("longitude"));
                agenda.setData(date);
                agenda.setHora_inicio(horaStr);
                agenda.setHora_fim(horaFimStr);
                
            }

        } catch (ClassNotFoundException | SQLException | ParseException ex) {
            Logger.getLogger(AgendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }   
        C.desconectaBanco();
       
        return agenda;
        
     
    }

    public Agenda getAgendaID(int id) {
        sql = "SELECT * FROM agenda WHERE id_agenda = ?";
        Agenda ag = null;
        try {
            con = C.conectaBanco();
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String data = formatter.format(rs.getDate("data"));
                Date date = (Date) formatter.parse(data);

                SimpleDateFormat formata_hora = new SimpleDateFormat("HH:mm");
                String hora_inicio = formata_hora.format(rs.getTime("hora_inicio"));
                String hora_fim = formata_hora.format(rs.getTime("hora_fim"));

                java.util.Date horaStr = formata_hora.parse(hora_inicio);
                java.util.Date horaFimStr = formata_hora.parse(hora_fim);

                ag = new Agenda();
                ag.setId_agenda(rs.getInt("id_agenda"));
                ag.setLatitude(rs.getDouble("latitude"));
                ag.setLongitude(rs.getDouble("longitude"));
                ag.setData(date);
                ag.setHora_inicio(horaStr);
                ag.setHora_fim(horaFimStr);

            }
        } catch (ClassNotFoundException | SQLException | ParseException ex) {
            Logger.getLogger(AgendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        C.desconectaBanco();
        return ag;

    }

    public boolean deletarAgenda(int id) {
        sql = "DELETE FROM agenda WHERE id_agenda = ?";
        try {
            con = C.conectaBanco();
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.execute();
            pst.close();
            C.desconectaBanco();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
