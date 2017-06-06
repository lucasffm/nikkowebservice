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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Endereco;
import modelo.Pessoa;

/**
 *
 * @author Lucas
 */
public class PessoaDAO {

    private PreparedStatement pst, pst2;
    private ResultSet rs;
    private Connection con;
    private String sql;
    
    public Pessoa getPessoaId(int id) throws ClassNotFoundException{
        sql = "SELECT * FROM pessoa WHERE id_pessoa = ?";
        String sql2 = "SELECT * FROM endereco WHERE id_pessoa = ?";
        Pessoa p = null; 
        Endereco e = null;
        try {
            con = C.conectaBanco();
            pst = con.prepareStatement(sql);            
            pst2 = con.prepareStatement(sql2);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if(rs.next()){
                p = new Pessoa();           
                p.setId_pessoa(rs.getInt("id_pessoa"));
                p.setNome(rs.getString("nome"));
                p.setLogin(rs.getString("login"));
                p.setSenha(rs.getString("senha"));
                p.setCpf(rs.getString("cpf"));
                p.setTipoUsuario(rs.getInt("tipo_usuario")); 
            }
            pst2.setInt(1, id);
            rs = pst2.executeQuery();
            if(rs.next()){
                e = new Endereco(); 
                e.setCep(rs.getInt("cep"));
                e.setEstado(rs.getString("estado"));
                e.setCidade(rs.getString("cidade"));
                e.setComplemento(rs.getString("complemento"));
                p.setEndereco(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return p;
        
        
    }

    public List<Pessoa> getPessoa() throws ClassNotFoundException, SQLException {
        sql = "SELECT pessoa.nome, pessoa.login, pessoa.senha, pessoa.cpf, pessoa.tipo_usuario, endereco.cep, endereco.estado, endereco.cidade, endereco.complemento FROM pessoa Join endereco WHERE pessoa.id_pessoa = endereco.id_pessoa";
        List<Pessoa> pessoas = new ArrayList<>();
        Pessoa p = null;
        Endereco e = null;
        con = C.conectaBanco();
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        while (rs.next()) {
            p = new Pessoa();
            e = new Endereco();

            p.setNome(rs.getString("nome"));
            p.setLogin(rs.getString("login"));
            p.setSenha(rs.getString("senha"));
            p.setCpf(rs.getString("cpf"));   
            p.setTipoUsuario(rs.getInt("tipo_usuario"));
            
            e.setCep(rs.getInt("cep"));
            e.setEstado(rs.getString("estado"));
            e.setCidade(rs.getString("cidade"));
            e.setComplemento(rs.getString("complemento"));
            p.setEndereco(e);

            pessoas.add(p);
        }
        C.desconectaBanco();
        return pessoas;
    }

    public boolean inserirPessoa(Pessoa p) throws ClassNotFoundException {
        String sqlPessoa = "INSERT INTO pessoa (nome,login,senha,cpf) VALUES (?,?,?,?);";
        String sqlEndereco = "INSERT INTO endereco (cep, estado, cidade, complemento,id_pessoa) VALUES (?,?,?,?,LAST_INSERT_ID())";
        try {
            con = C.conectaBanco();
            pst = con.prepareStatement(sqlPessoa);
            pst2 = con.prepareStatement(sqlEndereco);

            pst.setString(1, p.getNome());
            pst.setString(2, p.getLogin());
            pst.setString(3, p.getSenha());
            pst.setString(4, p.getCpf());
            pst2.setInt(1, p.getEndereco().getCep());
            pst2.setString(2, p.getEndereco().getEstado());
            pst2.setString(3, p.getEndereco().getCidade());
            pst2.setString(4, p.getEndereco().getComplemento());

            pst.execute();
            pst2.execute();
            pst.close();
            pst2.close();
            C.desconectaBanco();           

            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean deletarUsuario(int id) {
        String sqlExcluirEndereco = "DELETE FROM endereco WHERE id_pessoa = ?";
        String sqlExcluirPessoa = "DELETE FROM pessoa WHERE id_pessoa = ?";
        try {
            con = C.conectaBanco();
            pst = con.prepareStatement(sqlExcluirEndereco);
            pst2 = con.prepareStatement(sqlExcluirPessoa);
            pst.setInt(1, id);
            pst2.setInt(1, id);
            pst.execute();
            pst2.execute();
            pst.close();
            pst2.close();
            C.desconectaBanco();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean atualizarUsuario(Pessoa p) {
        String sqlPessoa = "UPDATE pessoa SET nome = ?,login = ?, senha = ?, cpf = ? WHERE pessoa.id_pessoa = ?";
        String sqlEndereco = "UPDATE endereco SET cep = ?,estado = ?, cidade = ?, complemento = ? WHERE endereco.id_pessoa = ?";
        try {
            con = C.conectaBanco();
            pst = con.prepareStatement(sqlPessoa);
            pst2 = con.prepareStatement(sqlEndereco);
            pst.setString(1, p.getNome());
            pst.setString(2, p.getLogin());
            pst.setString(3, p.getSenha());
            pst.setString(4, p.getCpf());
            pst.setInt(5, p.getId_pessoa());
            
            pst2.setInt(1, p.getEndereco().getCep());
            pst2.setString(2, p.getEndereco().getEstado());
            pst2.setString(3, p.getEndereco().getCidade());
            pst2.setString(4, p.getEndereco().getComplemento());
            pst2.setInt(5, p.getId_pessoa());

            pst.executeUpdate();
            pst2.executeUpdate();
            pst.close();
            pst2.close();
            C.desconectaBanco();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Pessoa verificaLogin(Pessoa p) {
        sql = "SELECT id_pessoa, login, senha, tipo_usuario FROM pessoa WHERE login = ? AND senha = ?";
        Pessoa retorno = null;
        try {
            con = C.conectaBanco();
            pst = con.prepareStatement(sql);
            pst.setString(1, p.getLogin());
            pst.setString(2, p.getSenha());
            rs = pst.executeQuery();
            if (rs.next()) {
                retorno = new Pessoa();
                retorno.setId_pessoa(rs.getInt("id_pessoa"));
                retorno.setLogin(rs.getString("login"));
                retorno.setSenha(rs.getString("senha"));
                retorno.setTipoUsuario(rs.getInt("tipo_usuario"));
            }
            pst.close();
            C.desconectaBanco();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

}
