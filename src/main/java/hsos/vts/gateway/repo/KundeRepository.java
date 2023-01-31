package hsos.vts.gateway.repo;

import hsos.vts.Adresse;
import hsos.vts.boundary.acl.logindto.ReturnKundeDTO;
import hsos.vts.entity.Kunde;
import hsos.vts.entity.KundenCatalog;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ApplicationScoped
public class KundeRepository implements KundenCatalog {
    @Transactional
    public void loadUsers(@Observes StartupEvent evt) {
        // reset and load all test users
        Kunde.deleteAll();
        addKunde("admin", "admin", "admin");
        addKunde("user", "user", "kunde");
        addKunde("lol","lol","kunde");
    }

    @Override
    public List<ReturnKundeDTO> getAllKunden() {
        Collection<Kunde> kunden = Kunde.findAll().list();
        List<ReturnKundeDTO> returnKundeDTOList = new ArrayList<>();
        for(Kunde k : kunden){
            returnKundeDTOList.add(new ReturnKundeDTO(k));
        }
        return returnKundeDTOList;
    }
    /**
     * Adds a new user to the database
     * @param username the username
     * @param password the unencrypted password (it will be encrypted with bcrypt)
     * @param role the comma-separated roles
     */
    @Override
    public ReturnKundeDTO addKunde(String username, String password, String role) {
        Kunde kunde = new Kunde();
        kunde.setUsername(username);
        kunde.setPassword(BcryptUtil.bcryptHash(password));
        kunde.setRole(role);
        kunde.persistAndFlush();
        System.out.println(kunde.getUsername());
        System.out.println(password);

        return new ReturnKundeDTO(kunde);
    }
    /**
     * returns true if at least 1 kunde has been deleted
     * */
    @Override
    public Boolean deleteKunde(String username) {
        long amountDeleted = Kunde.delete("username", username);
        return amountDeleted > 0;
    }

    public Kunde findKundeById(long id){
        return Kunde.findById(id);
    }

    /**
     * gibt -1 in long zurück wenn kunde nicht vorhanden
     * */
    @Override
    public Long getKundenIdByUsername(String username) {
        Kunde k = Kunde.find("username", username).firstResult();
        try{
            return k.id;
        } catch (NullPointerException n){
            return -1L;
        }

    }
}
