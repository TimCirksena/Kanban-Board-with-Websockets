package hsos.vts.boundary.acl.logindto;


import hsos.vts.entity.Kunde;

public class ReturnKundeDTO {
    public Long id;
    public String username;
    public String role;

    public ReturnKundeDTO(){}
    public ReturnKundeDTO(Kunde kunde){
        id = kunde.id;
        username = kunde.getUsername();
        role = kunde.getRole();
    }
}
