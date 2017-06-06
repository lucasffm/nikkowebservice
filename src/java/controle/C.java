package controle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucas
 */
public class C {
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USER = "root";
    private static final String SENHA = "root";
    
    private static Connection con;
    
    public static Connection conectaBanco() throws ClassNotFoundException{
        
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, SENHA);
            System.out.println("Conectado ao MySQL");
        } catch (SQLException ex) {
            Logger.getLogger(C.class.getName()).log(Level.SEVERE, null, ex);
        }return con;
    }
    
    
    public static void desconectaBanco(){
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(C.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
