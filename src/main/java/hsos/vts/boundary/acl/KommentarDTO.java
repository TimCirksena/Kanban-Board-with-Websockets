package hsos.vts.boundary.acl;

import hsos.vts.entity.Kommentar;

public class KommentarDTO {
    public long id;
    public String nachricht;
    public String ersteller;

    public KommentarDTO(Kommentar kommentar) {
        this.id = kommentar.getKommentarId();
        this.nachricht = kommentar.getNachricht();
        this.ersteller = kommentar.getErsteller();
    }

    public KommentarDTO() {
    }
}
