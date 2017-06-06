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
import java.text.SimpleDateFormat;
import java.util.Date;
import modelo.GraficoAvaliacao;

/**
 *
 * @author Lucas
 */
public class GraficoAvaliacaoDAO {
    
    private PreparedStatement pst,pst2,pst3;
    private ResultSet rs;
    private Connection con;
    private String sql,sql2,sql3;
    
    
    public GraficoAvaliacao getAvaliacao(String data){
        GraficoAvaliacao avaliacao = new GraficoAvaliacao();
        sql = "SELECT count(descricao) as negativas FROM avaliacao WHERE data_avaliacao  = ? AND descricao < 2;";
        sql2 = "SELECT count(descricao) as imparciais FROM avaliacao WHERE data_avaliacao  = ? AND descricao >= 2 AND descricao <= 3.5;";
        sql3 = "SELECT count(descricao) as positivas FROM avaliacao WHERE data_avaliacao  = ? AND descricao > 3.5;";        
        try {
            con = C.conectaBanco();
            pst = con.prepareStatement(sql);            
            pst2 = con.prepareStatement(sql2);
            pst3 = con.prepareStatement(sql3);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date dateUtil = formatter.parse(data);
            java.sql.Date dateDB = new java.sql.Date(dateUtil.getTime());
            pst.setDate(1, dateDB);
            rs = pst.executeQuery();
            if(rs.next()){
                avaliacao.setNotaNegativa(rs.getInt("negativas"));
            }
            pst2.setDate(1, dateDB);
            rs = pst2.executeQuery();
            if(rs.next()){
                avaliacao.setNotaImparcial(rs.getInt("imparciais"));
            }
            pst3.setDate(1, dateDB);
            rs = pst3.executeQuery();
            if(rs.next()){
                avaliacao.setNotaPositiva(rs.getInt("positivas"));
            }
        } catch (Exception e) {
            System.out.println("Ex" + e.getMessage());            
        }
        
        return avaliacao;
        
        
        
        
    }
    
}
