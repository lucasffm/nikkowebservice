package dao;

import controle.C;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.QrCode;

/**
 *
 * @author Lucas
 */
public class QrCodeDAO {

    private PreparedStatement pst, pst2;
    private ResultSet rs;
    private Connection con;
    private String sql;

    public String inserirQrCode(String inserir) throws ClassNotFoundException {
        sql = "INSERT INTO qrcode (id_qrcode,numero_qrcode,status) VALUES (DEFAULT,?,DEFAULT);";
        try {
            if (inserir.equals("true")) {
                con = C.conectaBanco();
                pst = con.prepareStatement(sql);

                for (int i = 0; i < 25; i++) {
                    String codigo = UUID.randomUUID().toString().replaceAll("-", "");
                    pst.setString(1, "Nikko" + codigo);
                    pst.addBatch();
                    pst.executeBatch();
                }
                pst.close();
                C.desconectaBanco();
                return "Inserido";
            } else {
                return "Não inserido";
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return "Não Inserido";
        }
    }

    public QrCode getQrCode(String codigo) throws ClassNotFoundException {
        sql = "SELECT * FROM qrcode WHERE numero_qrcode = ?";
        QrCode code = null;
        try {
            con = C.conectaBanco();
            pst = con.prepareStatement(sql);
            pst.setString(1, codigo);
            rs = pst.executeQuery();
            if (rs.next()) {
                code = new QrCode();
                code.setId_qrcode(rs.getInt("id_qrcode"));
                code.setNumero_qrcode(rs.getString("numero_qrcode"));
                code.setStatus(rs.getString("status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return code;
    }
    
    public boolean atualizaQrCode(QrCode code){
        sql = "UPDATE qrcode SET status = ? WHERE id_qrcode = ?;";
        try {
            con = C.conectaBanco();
            pst = con.prepareStatement(sql);
            pst.setString(1, code.getStatus());
            pst.setInt(2, code.getId_qrcode());
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
