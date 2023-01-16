package hsos.vts.boundary.acl;

import hsos.vts.entity.ElementKanban;

public class StubElementDTO {
    public long id;
    public String titel;

    public StubElementDTO() {
    }

    public StubElementDTO(long id, String titel) {
        this.id = id;
        this.titel = titel;
    }
    public StubElementDTO(ElementKanban elementKanban) {
        this.id = elementKanban.getElementId();
        this.titel = elementKanban.getTitel();
    }
}
