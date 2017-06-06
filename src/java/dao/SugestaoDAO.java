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
import modelo.Sugestao;

/**
 *
 * @author Lucas
 */
public class SugestaoDAO {
    
    private PreparedStatement pst, pst2, pst3, pst4;
    private ResultSet rs;
    private Connection con;
    private String sql;
    
    public int verificaAtendimento(int idPessoa){
        sql = "select id_atendimento from atendimento where pessoa_id_pessoa = ? order by id_atendimento desc limit 1";
        int idAtendimento = 0;
        try {
            con = C.conectaBanco();
            pst = con.prepareStatement(sql);
            pst.setInt(1, idPessoa);
            rs = pst.executeQuery();
            if(rs.next()){
                idAtendimento = rs.getInt("id_atendimento");
            }
            C.desconectaBanco();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro" + e.getMessage());
        }
        return idAtendimento;
    }
    public boolean verificaCheckIn(int idPessoa) {
        sql = "SELECT * FROM atendimento WHERE data_atendimento > (DATE(NOW()) - 7) and data_atendimento <= (DATE(NOW())) and pessoa_id_pessoa = ?";
        boolean check = false;
        try {
            con = C.conectaBanco();
            pst = con.prepareStatement(sql);
            pst.setInt(1, idPessoa);
            rs = pst.executeQuery();
            check = rs.next();    
            C.desconectaBanco();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro" + ex.getMessage());
        }
        return check;
    }
    
    public boolean inserirSugestaoCozinha(Sugestao sugestao){
        String sqlAreaCozinha = "Insert into sugestao (sugestao.data_sugestao, sugestao.descricao, sugestao.id_area_servico, sugestao.id_atendimento) VALUES (DATE(NOW()), ?, 1, ?)";          
        try {
            con = C.conectaBanco();
            pst = con.prepareStatement(sqlAreaCozinha);
            pst.setString(1, sugestao.getDescricao());
            pst.setInt(2, sugestao.getAtendimento().getIdAtendimento());  
            pst.execute();
            pst.close();
            C.desconectaBanco();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean inserirSugestaoAtendimento(Sugestao sugestao){
        String sqlAreaAtendimento = "Insert into sugestao (data_sugestao, descricao, id_area_servico, id_atendimento) VALUES (DATE(NOW()), ?, 2, ?)";          
        try {
            con = C.conectaBanco();
            pst = con.prepareStatement(sqlAreaAtendimento);
            pst.setString(1, sugestao.getDescricao());
            pst.setInt(2, sugestao.getAtendimento().getIdAtendimento());  
            pst.execute();
            pst.close();
            C.desconectaBanco();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean inserirSugestaoComida(Sugestao sugestao){
        String sqlAreaComida = "Insert into sugestao (data_sugestao, descricao, id_area_servico, id_atendimento) VALUES (DATE(NOW()), ?, 3, ?)";          
        try {
            con = C.conectaBanco();
            pst = con.prepareStatement(sqlAreaComida);
            pst.setString(1, sugestao.getDescricao());
            pst.setInt(2, sugestao.getAtendimento().getIdAtendimento());  
            pst.execute();
            pst.close();
            C.desconectaBanco();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean inserirSugestaoAmbiente(Sugestao sugestao){
        String sqlAreaAmbiente = "Insert into sugestao (data_sugestao, descricao, id_area_servico, id_atendimento) VALUES (DATE(NOW()), ?, 4, ?)";          
        try {
            con = C.conectaBanco();
            pst = con.prepareStatement(sqlAreaAmbiente);
            pst.setString(1, sugestao.getDescricao());
            pst.setInt(2, sugestao.getAtendimento().getIdAtendimento());  
            pst.execute();
            pst.close();
            C.desconectaBanco();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
