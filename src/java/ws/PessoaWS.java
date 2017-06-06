/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import dao.PessoaDAO;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import modelo.Pessoa;

/**
 * REST Web Service
 *
 * @author Lucas
 */
@Path("pessoa")
public class PessoaWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PessoaWS
     */
    public PessoaWS() {
    }

    /**
     * Retrieves representation of an instance of ws.PessoaWS
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("recuperaPessoas")
    public String getPessoas() throws ClassNotFoundException, SQLException{
        Gson g =  new Gson();
        PessoaDAO pDao = new PessoaDAO();
        List<Pessoa> pessoas = pDao.getPessoa();
        
        return g.toJson(pessoas);        
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("recuperaPessoaId/{id}")
    public String verificaLogin(@PathParam("id") int id) throws ClassNotFoundException, SQLException{
        Gson g =  new Gson();
        PessoaDAO pDao = new PessoaDAO();
        Pessoa p = new Pessoa();
        p = pDao.getPessoaId(id);
        
        return g.toJson(p);
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("verificaLogin/{login}/{senha}")
    public String verificaLogin(@PathParam("login") String login, @PathParam("senha") String senha) throws ClassNotFoundException, SQLException{
        Gson g =  new Gson();
        PessoaDAO pDao = new PessoaDAO();
        Pessoa p = new Pessoa();
        p.setLogin(login);
        p.setSenha(senha);
        p = pDao.verificaLogin(p);
        
        return g.toJson(p);
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/inserirPessoa")
    public boolean inserirPessoa(String content) throws ClassNotFoundException{
        Gson g = new Gson();
        Pessoa p = (Pessoa) g.fromJson(content, Pessoa.class);
        PessoaDAO pessoaDao = new PessoaDAO();            
            return pessoaDao.inserirPessoa(p);
    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/atualizarPessoa")
    public boolean atualizarPessoa(String content) throws ClassNotFoundException{
        Gson g = new Gson();
        Pessoa p = (Pessoa) g.fromJson(content, Pessoa.class);
        PessoaDAO pessoaDao = new PessoaDAO();            
            return pessoaDao.atualizarUsuario(p); 
    }
  
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/excluirPessoa/{id}")
    public String excluirPessoa(@PathParam("id") int id) throws ClassNotFoundException, SQLException{
        Gson g = new Gson();
        PessoaDAO pDao = new PessoaDAO();        
        return g.toJson(pDao.deletarUsuario(id));            
            
    }   
    /**
     * PUT method for updating or creating an instance of PessoaWS
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
