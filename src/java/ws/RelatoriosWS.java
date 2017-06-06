/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import dao.AgendaDAO;
import dao.GraficoAvaliacaoDAO;
import java.sql.SQLException;
import java.util.Date;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import modelo.GraficoAvaliacao;

/**
 * REST Web Service
 *
 * @author Lucas
 */
@Path("relatorios")
public class RelatoriosWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RelatoriosWS
     */
    public RelatoriosWS() {
    }

    /**
     * Retrieves representation of an instance of ws.RelatoriosWS
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
    @Path("/getAvaliacoes/{data}")
    public String getRelatorioAvaliacao(@PathParam("data") String data) throws ClassNotFoundException, SQLException{
        Gson g =  new Gson();
        GraficoAvaliacaoDAO avaliacaoDAO = new GraficoAvaliacaoDAO(); 
        GraficoAvaliacao avaliacao = new GraficoAvaliacao();
        avaliacao = avaliacaoDAO.getAvaliacao(data);
        return g.toJson(avaliacao);        
    }

    /**
     * PUT method for updating or creating an instance of RelatoriosWS
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
