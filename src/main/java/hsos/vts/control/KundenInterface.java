package hsos.vts.control;

import hsos.vts.boundary.acl.logindto.ReturnKundeDTO;

import java.util.List;

public interface KundenInterface {

    ReturnKundeDTO addKunde(String username, String password, String role);
    Boolean deleteKunde(String username);
    List<ReturnKundeDTO> getAllKunden();
    Long getKundenIdByUsername(String username);
}
