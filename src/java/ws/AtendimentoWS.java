/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import dao.AtendimentoDAO;
import java.text.ParseException;
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
import modelo.Atendimento;

/**
 * REST Web Service
 *
 * @author Lucas
 */
@Path("atendimento")
public class AtendimentoWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AtendimentoResource
     */
    public AtendimentoWS() {
        
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/inseriratendimento")
    public boolean inserirAtendimento(String content) throws ClassNotFoundException{
       Gson g = new Gson();
       Atendimento atendimento = (Atendimento) g.fromJson(content, Atendimento.class);
        AtendimentoDAO atendimentoDAO = new AtendimentoDAO();            
        return atendimentoDAO.inserirAtendimento(atendimento);
            
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/checkagenda/{id}")
    public String checkAgenda(@PathParam("id") int id){
        Gson g =  new Gson();
        AtendimentoDAO atendimentoDAO = new AtendimentoDAO();
        return g.toJson(atendimentoDAO.getAgendaData(id));
    }
    /**
     * Retrieves representation of an instance of ws.AtendimentoResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of AtendimentoResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
