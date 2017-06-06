/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import dao.SugestaoDAO;
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
import modelo.Sugestao;

/**
 * REST Web Service
 *
 * @author Lucas
 */
@Path("sugestao")
public class SugestaoWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SugestaoWS
     */
    public SugestaoWS() {
    }

    /**
     * Retrieves representation of an instance of ws.SugestaoWS
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/verificacheckin/{id}")
    public String verificaCheckIn(@PathParam("id") int id){
        Gson g =  new Gson();
        SugestaoDAO sugestaoDAO = new SugestaoDAO();
        return g.toJson(sugestaoDAO.verificaCheckIn(id));
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/verificaatendimento/{id}")
    public String verificaAtendimento(@PathParam("id") int idPessoa){
        Gson g =  new Gson();
        SugestaoDAO sugestaoDAO = new SugestaoDAO();
        return g.toJson(sugestaoDAO.verificaAtendimento(idPessoa));
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/inserirSugestaoCozinha/")
    public boolean inserirSugestaoCozinha(String content) throws ClassNotFoundException{
        Gson g = new Gson();
        Sugestao sugestao = (Sugestao) g.fromJson(content, Sugestao.class);
        SugestaoDAO sugestaoDAO = new SugestaoDAO();            
            return sugestaoDAO.inserirSugestaoCozinha(sugestao);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/inserirSugestaoAtendimento/")
    public boolean inserirSugestaoAtendimento(String content) throws ClassNotFoundException{
        Gson g = new Gson();
        Sugestao sugestao = (Sugestao) g.fromJson(content, Sugestao.class);
        SugestaoDAO sugestaoDAO = new SugestaoDAO();            
            return sugestaoDAO.inserirSugestaoAtendimento(sugestao);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/inserirSugestaoComida/")
    public boolean inserirSugestaoComida(String content) throws ClassNotFoundException{
        Gson g = new Gson();
        Sugestao sugestao = (Sugestao) g.fromJson(content, Sugestao.class);
        SugestaoDAO sugestaoDAO = new SugestaoDAO();            
            return sugestaoDAO.inserirSugestaoComida(sugestao);
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/inserirSugestaoAmbiente/")
    public boolean inserirSugestaoAmbiente(String content) throws ClassNotFoundException{
        Gson g = new Gson();
        Sugestao sugestao = (Sugestao) g.fromJson(content, Sugestao.class);
        SugestaoDAO sugestaoDAO = new SugestaoDAO();            
            return sugestaoDAO.inserirSugestaoAmbiente(sugestao);
    }

    /**
     * PUT method for updating or creating an instance of SugestaoWS
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
