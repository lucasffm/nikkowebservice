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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Agenda;
import modelo.Atendimento;

/**
 *
 * @author Lucas
 */
public class AtendimentoDAO {
    
    private PreparedStatement pst;
    private ResultSet rs;
    private Connection con;
    private String sql;
    
    public boolean inserirAtendimento(Atendimento atendimento) {
        sql = "INSERT INTO atendimento (data_atendimento, carro_id_carro, pessoa_id_pessoa) VALUES (DATE(NOW()),?,?);";
        try {
            if (atendimento != null) {
                con = C.conectaBanco();
                pst = con.prepareStatement(sql);                

                pst.setInt(1, atendimento.getCarro().getId_carro());
                pst.setInt(2, atendimento.getPessoa().getId_pessoa());
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
    
    public boolean getAgendaData(int idPessoa) {
        sql = "SELECT * FROM atendimento WHERE data_atendimento = DATE(NOW()) AND pessoa_id_pessoa = ?";
        boolean check = false;
        try {
            con = C.conectaBanco();
            pst = con.prepareStatement(sql);
            pst.setInt(1, idPessoa);
            rs = pst.executeQuery();
            check = rs.next();    
            C.desconectaBanco();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AgendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }
    
}
