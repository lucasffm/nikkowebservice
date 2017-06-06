/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import dao.AgendaDAO;
import dao.CartaoFidelidadeDAO;
import dao.QrCodeDAO;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import modelo.Agenda;
import modelo.CartaoFidelidade;

/**
 * REST Web Service
 *
 * @author Lucas
 */
@Path("agenda")
public class AgendaWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AgendaWS
     */
    public AgendaWS() {
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/inseriragenda")
    public boolean inserirAgenda(String content) throws ClassNotFoundException{
       Gson g = new Gson();
        Agenda ag = (Agenda) g.fromJson(content, Agenda.class);
        AgendaDAO agdao = new AgendaDAO();            
        try {
            return agdao.inserirAgenda(ag);
        } catch (ParseException ex) {
            Logger.getLogger(AgendaWS.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
            
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/recuperaAgendas")
    public String recuperaAgendas() throws ClassNotFoundException, SQLException{
        Gson g =  new Gson();
        AgendaDAO agendaDAO = new AgendaDAO();
        List<Agenda> agendas = agendaDAO.getAgendas();
        
        return g.toJson(agendas);        
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/atualizaAgenda")
    public boolean atualizaQrCode(String content) throws ClassNotFoundException, ParseException{
        Gson g = new Gson();
        Agenda ag = (Agenda) g.fromJson(content, Agenda.class);
        AgendaDAO agendaDAO = new AgendaDAO();            
            return agendaDAO.atualizaAgenda(ag); 
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAgenda/{id}")
    public String getAgendaID(@PathParam("id") int id) throws ClassNotFoundException, SQLException{
        Gson g =  new Gson();
        AgendaDAO agendaDAO = new AgendaDAO();       
        return g.toJson(agendaDAO.getAgendaID(id));        
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAgendaData/")
    public String getAgendaData() throws ClassNotFoundException, SQLException{
        Gson g =  new Gson();
        AgendaDAO agendaDAO = new AgendaDAO();       
        return g.toJson(agendaDAO.getAgendaData());        
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/excluirAgenda/{id}")
    public String excluirPessoa(@PathParam("id") int id) throws ClassNotFoundException, SQLException{
        Gson g = new Gson();
        AgendaDAO agendaDAO = new AgendaDAO();        
        return g.toJson(agendaDAO.deletarAgenda(id));            
            
    }

    /**
     * Retrieves representation of an instance of ws.AgendaWS
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of AgendaWS
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
