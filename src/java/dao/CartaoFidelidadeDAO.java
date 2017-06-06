package dao;

import controle.C;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.CartaoFidelidade;
import modelo.Pessoa;
import modelo.QrCode;

public class CartaoFidelidadeDAO {

    private PreparedStatement pst;
    private ResultSet rs;
    private Connection con;
    private String sql;

    public boolean inserirCartaoFidelidade(CartaoFidelidade cf) {
        sql = "INSERT INTO cartao_fidelidade (data_ativacao, qrcode_id_qrcode, id_pessoa) VALUES (DATE(NOW()),?,?);";
        try {
            if (cf != null) {                      
                con = C.conectaBanco();
                pst = con.prepareStatement(sql); 
                pst.setInt(1, cf.getQrCode().getId_qrcode());
                pst.setInt(2, cf.getPessoa().getId_pessoa());
                pst.execute();
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


    public List<CartaoFidelidade> getCartoes() throws ClassNotFoundException, SQLException {
        sql = "SELECT * FROM cartao_fidelidade Join qrcode, pessoa where cartao_fidelidade.qrcode_id_qrcode = qrcode.id_qrcode and cartao_fidelidade.id_pessoa = pessoa.id_pessoa;";
        List<CartaoFidelidade> cfs = new ArrayList<>();
        CartaoFidelidade cf = null;
        QrCode code = null;
        Pessoa p = null;
        con = C.conectaBanco();
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        while (rs.next()) {
            cf = new CartaoFidelidade();
            code = new QrCode();
            p = new Pessoa();
            code.setId_qrcode(rs.getInt("id_qrcode"));
            p.setId_pessoa(rs.getInt("id_pessoa"));
            cf.setId_cartao(rs.getInt("id_cartao_fidelidade"));
            cf.setData_ativacao(rs.getDate("data_ativacao"));
            cf.setQrCode(code);
            cf.setPessoa(p);

            cfs.add(cf);
        }
        C.desconectaBanco();
        return cfs;
    }
    
    public int getCartoesPessoa(int id){
        sql = "SELECT count(id_pessoa) AS total FROM cartao_fidelidade where id_pessoa = ? and status = 'ativo' ;";                
        int total = 0;        
        try {
            con = C.conectaBanco();
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {            
                total = rs.getInt("total");
            }
        } catch (SQLException | ClassNotFoundException ex) {        
            Logger.getLogger(CartaoFidelidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }
    
    public boolean atualizaCartao(CartaoFidelidade cf){
        sql = "UPDATE cartao_fidelidade SET status = ? where id_pessoa = ? and status = 'ativo' ORDER BY id_cartao_fidelidade LIMIT 10";
        try {
            con = C.conectaBanco();
            pst = con.prepareStatement(sql);
            pst.setString(1, cf.getStatus());
            pst.setInt(2, cf.getPessoa().getId_pessoa());
            pst.executeUpdate();
            pst.close();
            C.desconectaBanco();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }


}
