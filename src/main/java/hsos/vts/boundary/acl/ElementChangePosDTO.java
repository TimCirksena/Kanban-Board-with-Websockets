package hsos.vts.boundary.acl;

public class ElementChangePosDTO {

    public long listeId;
    public long elementId;

    public ElementChangePosDTO() {
    }

    public ElementChangePosDTO(long listeId, long elementId) {
        this.listeId = listeId;
        this.elementId = elementId;
    }
}
