package hsos.vts.boundary.acl;

import hsos.vts.entity.ElementKanban;

public class StubElementDTO {
    public long listeId;
    public long elementId;
    public String titel;

    public StubElementDTO() {
    }

    public StubElementDTO(long listeId, long elementId, String titel) {
        this.listeId = listeId;
        this.elementId = elementId;
        this.titel = titel;
    }
    public StubElementDTO(ElementKanban elementKanban) {
        this.elementId = elementKanban.getElementId();
        this.titel = elementKanban.getTitel();
    }
}
