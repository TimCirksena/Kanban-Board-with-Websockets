package hsos.vts.control;

import hsos.vts.boundary.acl.logindto.ReturnKundeDTO;
import hsos.vts.entity.KundenCatalog;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
@ApplicationScoped
public class KundenManagement implements KundenInterface{
    @Inject
    KundenCatalog kundenCatalog;

    @Override
    public ReturnKundeDTO addKunde(String username, String password, String role) {
        return kundenCatalog.addKunde(username, password, role);
    }

    @Override
    public Boolean deleteKunde(String username) {
        return kundenCatalog.deleteKunde(username);
    }

    @Override
    public List<ReturnKundeDTO> getAllKunden() {
        return kundenCatalog.getAllKunden();
    }

    @Override
    public Long getKundenIdByUsername(String username) {
        return kundenCatalog.getKundenIdByUsername(username);
    }
}
