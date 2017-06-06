package ws;

import com.google.gson.Gson;
import dao.CartaoFidelidadeDAO;
import dao.QrCodeDAO;
import java.sql.SQLException;
import java.util.List;
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
import modelo.CartaoFidelidade;
import modelo.QrCode;

/**
 * REST Web Service
 *
 * @author Lucas
 */
@Path("cartaofidelidade")
public class CartaoFidelidadeWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CartaoFidelidadeWS
     */
    public CartaoFidelidadeWS() {
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/inserirqrcode/{inserir}")
    public String inserirQrCode(@PathParam("inserir") String content) throws ClassNotFoundException{
        Gson g = new Gson();
        //QrCode qrCode = (QrCode) g.fromJson(content, QrCode.class);
        QrCodeDAO codeDAO = new QrCodeDAO();
        if (codeDAO.inserirQrCode(content).equals("Inserido")) {
            return codeDAO.inserirQrCode(content);
        }else{
            return "Não Inserido";
        }
            
    }    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/recuperaQrCode/{codigo}")
    public String getQrCode(@PathParam("codigo") String codigo) throws ClassNotFoundException, SQLException{
        Gson g =  new Gson();
        QrCodeDAO codeDAO = new QrCodeDAO();
        QrCode code = new QrCode();
        code = codeDAO.getQrCode(codigo);        
        return g.toJson(code);
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/recuperaCartoes")
    public String getCartoes() throws ClassNotFoundException, SQLException{
        Gson g =  new Gson();
        CartaoFidelidadeDAO cfdao = new CartaoFidelidadeDAO();
        List<CartaoFidelidade> cfs = cfdao.getCartoes();
        
        return g.toJson(cfs);        
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getCartoesPessoa/{id}")
    public String getCartoesPessoa(@PathParam("id") int id) throws ClassNotFoundException, SQLException{
        Gson g =  new Gson();
        CartaoFidelidadeDAO cfdao = new CartaoFidelidadeDAO();       
        return g.toJson(cfdao.getCartoesPessoa(id));        
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/inserirCartao")
    public boolean inserirCartao(String content) throws ClassNotFoundException, SQLException{
        Gson g = new Gson();
        CartaoFidelidade cf = (CartaoFidelidade) g.fromJson(content, CartaoFidelidade.class);
        CartaoFidelidadeDAO cfdao = new CartaoFidelidadeDAO();            
            return cfdao.inserirCartaoFidelidade(cf);
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/atualizaQrcode")
    public boolean atualizaQrCode(String content) throws ClassNotFoundException{
        Gson g = new Gson();
        QrCode code = (QrCode) g.fromJson(content, QrCode.class);
        QrCodeDAO codeDAO = new QrCodeDAO();            
            return codeDAO.atualizaQrCode(code); 
    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/atualizaCartao")
    public boolean atualizaCartao(String content) throws ClassNotFoundException{
        Gson g = new Gson();
        CartaoFidelidade cf = (CartaoFidelidade) g.fromJson(content, CartaoFidelidade.class);
        CartaoFidelidadeDAO cfdao = new CartaoFidelidadeDAO();            
            return cfdao.atualizaCartao(cf); 
    }

    /**
     * Retrieves representation of an instance of ws.CartaoFidelidadeWS
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of CartaoFidelidadeWS
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
