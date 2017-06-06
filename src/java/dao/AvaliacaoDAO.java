/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.sun.istack.internal.logging.Logger;
import controle.C;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import modelo.Avaliacao;
import modelo.MediaAvaliacao;

/**
 *
 * @author Lucas
 */
public class AvaliacaoDAO {
    
    private PreparedStatement pst, pst2, pst3, pst4;
    private ResultSet rs;
    private Connection con;
    private String sql,sql2,sql3,sql4;
    
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
    public boolean verificaExistenciaAvaliacao(int idAtendimento) {
        sql = "SELECT * FROM avaliacao WHERE id_atendimento = ?";
        boolean check = false;
        try {
            con = C.conectaBanco();
            pst = con.prepareStatement(sql);
            pst.setInt(1, idAtendimento);
            rs = pst.executeQuery();
            check = rs.next();    
            C.desconectaBanco();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro" + ex.getMessage());
        }
        return check;
    }
    
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
    
    public boolean inserirAvaliacaoCozinha(Avaliacao avaliacao){
        String sqlAreaCozinha = "Insert into avaliacao (data_avaliacao, descricao, id_area_servico, id_atendimento) VALUES (DATE(NOW()), ?, 1, ?)";          
        try {
            con = C.conectaBanco();
            pst = con.prepareStatement(sqlAreaCozinha);
            pst.setFloat(1, avaliacao.getDescricao());
            pst.setInt(2, avaliacao.getAtendimento().getIdAtendimento());  
            pst.execute();
            pst.close();
            C.desconectaBanco();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean inserirAvaliacaoAtendimento(Avaliacao avaliacao){
        String sqlAreaCozinha = "Insert into avaliacao (data_avaliacao, descricao, id_area_servico, id_atendimento) VALUES (DATE(NOW()), ?, 2, ?)";          
        try {
            con = C.conectaBanco();
            pst = con.prepareStatement(sqlAreaCozinha);
            pst.setFloat(1, avaliacao.getDescricao());
            pst.setInt(2, avaliacao.getAtendimento().getIdAtendimento());  
            pst.execute();
            pst.close();
            C.desconectaBanco();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean inserirAvaliacaoComida(Avaliacao avaliacao){
        String sqlAreaCozinha = "Insert into avaliacao (data_avaliacao, descricao, id_area_servico, id_atendimento) VALUES (DATE(NOW()), ?, 3, ?)";          
        try {
            con = C.conectaBanco();
            pst = con.prepareStatement(sqlAreaCozinha);
            pst.setFloat(1, avaliacao.getDescricao());
            pst.setInt(2, avaliacao.getAtendimento().getIdAtendimento());  
            pst.execute();
            pst.close();
            C.desconectaBanco();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean inserirAvaliacaoAmbiente(Avaliacao avaliacao){
        String sqlAreaCozinha = "Insert into avaliacao (data_avaliacao, descricao, id_area_servico, id_atendimento) VALUES (DATE(NOW()), ?, 4, ?)";          
        try {
            con = C.conectaBanco();
            pst = con.prepareStatement(sqlAreaCozinha);
            pst.setFloat(1, avaliacao.getDescricao());
            pst.setInt(2, avaliacao.getAtendimento().getIdAtendimento());  
            pst.execute();
            pst.close();
            C.desconectaBanco();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public MediaAvaliacao getMediaAvaliacoes(){
        MediaAvaliacao avaliacao = new MediaAvaliacao();
        sql = "SELECT avg(descricao) as mediaCozinha from avaliacao where id_area_servico = 1;";
        sql2 = "SELECT avg(descricao) as mediaAtendimento from avaliacao where id_area_servico = 2;";
        sql3 = "SELECT avg(descricao) as mediaComida from avaliacao where id_area_servico = 3;";        
        sql4 = "SELECT avg(descricao) as mediaAmbiente from avaliacao where id_area_servico = 4;";        
        try {
            con = C.conectaBanco();
            pst = con.prepareStatement(sql);            
            pst2 = con.prepareStatement(sql2);
            pst3 = con.prepareStatement(sql3);
            pst4 = con.prepareStatement(sql4);
            rs = pst.executeQuery();
            if(rs.next()){
                avaliacao.setMediaCozinha(rs.getFloat("mediaCozinha"));
            }
            rs = pst2.executeQuery();
            if(rs.next()){
                avaliacao.setMediaAtendimento(rs.getFloat("mediaAtendimento"));
            }
            rs = pst3.executeQuery();
            if(rs.next()){
                avaliacao.setMediaComida(rs.getFloat("mediaComida"));
            }
            rs = pst4.executeQuery();
            if(rs.next()){
                avaliacao.setMediaAmbiente(rs.getFloat("mediaAmbiente"));
            }
        } catch (Exception e) {
            System.out.println("Ex" + e.getMessage());            
        }
        
        return avaliacao;
    }
    
}
