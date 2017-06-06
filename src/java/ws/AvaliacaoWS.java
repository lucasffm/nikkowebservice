/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import dao.AvaliacaoDAO;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import modelo.Avaliacao;

/**
 * REST Web Service
 *
 * @author Lucas
 */
@Path("avaliacao")
public class AvaliacaoWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AvaliacaoResource
     */
    public AvaliacaoWS() {
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/verificacheckin/{id}")
    public String verificaCheckIn(@PathParam("id") int id){
        Gson g =  new Gson();
        AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
        return g.toJson(avaliacaoDAO.verificaCheckIn(id));
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/verificaExistenciaAvaliacao/{id}")
    public String verificaExistenciaAvaliacao(@PathParam("id") int idAtendimento){
        Gson g =  new Gson();
        AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
        return g.toJson(avaliacaoDAO.verificaExistenciaAvaliacao(idAtendimento));
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/verificaatendimento/{id}")
    public String verificaAtendimento(@PathParam("id") int idPessoa){
        Gson g =  new Gson();
        AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
        return g.toJson(avaliacaoDAO.verificaAtendimento(idPessoa));
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/inserirAvaliacaoCozinha/")
    public boolean inserirAvaliacaoCozinha(String content) throws ClassNotFoundException{
        Gson g = new Gson();
        Avaliacao avaliacao = (Avaliacao) g.fromJson(content, Avaliacao.class);
        AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();            
            return avaliacaoDAO.inserirAvaliacaoCozinha(avaliacao);
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/inserirAvaliacaoAtendimento/")
    public boolean inserirAvaliacaoAtendimento(String content) throws ClassNotFoundException{
        Gson g = new Gson();
        Avaliacao avaliacao = (Avaliacao) g.fromJson(content, Avaliacao.class);
        AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();            
            return avaliacaoDAO.inserirAvaliacaoAtendimento(avaliacao);
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/inserirAvaliacaoComida/")
    public boolean inserirAvaliacaoComida(String content) throws ClassNotFoundException{
        Gson g = new Gson();
        Avaliacao avaliacao = (Avaliacao) g.fromJson(content, Avaliacao.class);
        AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();            
            return avaliacaoDAO.inserirAvaliacaoComida(avaliacao);
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/inserirAvaliacaoCozinhaAmbiente/")
    public boolean inserirAvaliacaoAmbiente(String content) throws ClassNotFoundException{
        Gson g = new Gson();
        Avaliacao avaliacao = (Avaliacao) g.fromJson(content, Avaliacao.class);
        AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();            
            return avaliacaoDAO.inserirAvaliacaoAmbiente(avaliacao);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getMediaAvaliacoes/")
    public String verificaExistenciaAvaliacao(){
        Gson g =  new Gson();
        AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
        return g.toJson(avaliacaoDAO.getMediaAvaliacoes());
    }

    /**
     * Retrieves representation of an instance of ws.AvaliacaoResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of AvaliacaoResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
