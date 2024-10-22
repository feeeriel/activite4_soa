package ressourcesRest;

import entities.RendezVous;
import metiers.RendezVousBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
@Path("rendezvous")

public class RendezVousResources {
    public  static RendezVousBusiness rendB = new RendezVousBusiness();
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addrendezvous(RendezVous r){
        if(rendB.addRendezVous(r))
            return Response.status(Response.Status.CREATED).build();
        return Response.status(Response.Status.NOT_FOUND).build();

    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listrendezvous(@QueryParam(value = "refLogement") String reference){

        List list =new ArrayList<RendezVous>();

        if( reference==null){
            list = rendB.getListeRendezVous();

        }
        if(reference!=null){
            list=rendB.getListeRendezVousByLogementReference(Integer.parseInt(reference));

        }

        if(list.size()!=0)
            return Response.status(Response.Status.OK).entity(list).build();
        return Response.status(Response.Status.NOT_FOUND).build();

    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response modifierendezvous(@PathParam("id") int id,RendezVous rendezVous){
        if(rendB.updateRendezVous(id,rendezVous))
            return Response.status(Response.Status.OK).build();
        return Response.status(Response.Status.NOT_FOUND).build();

    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public  Response getRendezVousbyId(@PathParam("id") int id){
        if(rendB.getRendezVousById(id)!=null)
            return Response.status(Response.Status.OK).entity(rendB.getRendezVousById(id)).build();


        return Response.status(Response.Status.NOT_FOUND).build();

    }
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")

    public Response deleterendezvous(@PathParam("id") int id){
        if(rendB.deleteRendezVous(id))
            return Response.status(Response.Status.OK).build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }


}
