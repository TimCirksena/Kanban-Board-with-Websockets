package hsos.vts.entity;

import hsos.vts.boundary.acl.logindto.ReturnKundeDTO;

import java.util.List;

public interface KundenCatalog {
    ReturnKundeDTO addKunde(String username, String password, String role);
    Boolean deleteKunde(String username);
    List<ReturnKundeDTO> getAllKunden();
    /**
     * diese methode darf nur mit dem namen des angemeldeten users aufgerufen werden, sonst entsteht eine sicherheitslücke
     * */
    Long getKundenIdByUsername(String username);
}
