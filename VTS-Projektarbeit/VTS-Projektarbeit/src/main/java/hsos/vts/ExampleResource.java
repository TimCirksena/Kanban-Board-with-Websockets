package hsos.vts;

import io.quarkus.runtime.Startup;
import io.zonky.test.db.postgres.embedded.EmbeddedPostgres;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

@Path("/hello")
public class ExampleResource {

    @Startup
    public void startDB(){
        try {
            EmbeddedPostgres pg = EmbeddedPostgres.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Adresse postAdress(Adresse a){
        Adresse moin = new Adresse();
        moin.plz = a.plz;
        moin.persist();
        return moin;
    }

    @GET
    @Path("/adress")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<Adresse> getAll(){

        return Adresse.findAll().list();
    }
}