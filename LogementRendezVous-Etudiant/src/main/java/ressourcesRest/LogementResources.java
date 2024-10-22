package ressourcesRest;

import entities.Logement;
import metiers.LogementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
@Path("logements")
public class LogementResources {

    public  static LogementBusiness logB = new LogementBusiness();
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response addlogements(Logement l){
        if(logB.addLogement(l))
            return Response.status(Response.Status.CREATED).build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listlogements(@QueryParam(value = "delegation") String delegation,
                         @QueryParam(value = "reference") String reference){
        List list =new ArrayList<Logement>();
        if(delegation==null && reference==null){
            list = logB.getLogements();

        }
        if(delegation!=null && reference==null){
            list=logB.getLogementsByDeleguation(delegation);


        }
        if(delegation==null && reference!=null){
            list=logB.getLogementsListeByref(Integer.parseInt(reference));
        }

        if(list.size()!=0)
            return Response.status(Response.Status.OK).entity(list).build();
        return Response.status(Response.Status.NOT_FOUND).build();

    }
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    @Path("{id}")
    public Response modifielogements(@PathParam("id") int reference,Logement logement){
        if(logB.updateLogement(reference,logement))
            return Response.status(Response.Status.OK).build();
        return Response.status(Response.Status.NOT_FOUND).build();

    }
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{ref}")

    public Response deletelogements(@PathParam("ref") int reference){
        if(logB.deleteLogement(reference))
            return Response.status(Response.Status.OK).entity("success").build();
        return Response.status(Response.Status.NOT_FOUND).entity("echec").build();
    }



}
