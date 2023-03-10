package hsos.vts.boundary.rest;

import hsos.vts.boundary.acl.logindto.PostKundeDTO;
import hsos.vts.control.kundeKanban.KundenInterface;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/register")
@ApplicationScoped
public class RegisterResource {
    @Inject
    KundenInterface kundenInterface;

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerKunde(PostKundeDTO postKundeDTO){
        Long bestehendeKundenId = kundenInterface.getKundenIdByUsername(postKundeDTO.username);
        //wenn kunde nicht existiert
        if(bestehendeKundenId == -1){
            return Response.ok(kundenInterface.addKunde(postKundeDTO.username, postKundeDTO.password, "kunde")).build();
        } else {
            return Response.status(422).build();
        }


    }
}
