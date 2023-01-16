package hsos.vts.boundary.acl;

import hsos.vts.entity.ElementKanban;
import hsos.vts.entity.ListeKanban;

import java.util.ArrayList;
import java.util.List;

public class ListKanbanDTO {


    public List<StubElementDTO> stubElementDTOS;
    public String titel;
    public long listeId;

    public ListKanbanDTO() {
    }

    public ListKanbanDTO(ListeKanban listeKanban) {
        this.titel = listeKanban.getTitel();
        this.listeId = listeKanban.getListeId();
        this.stubElementDTOS = new ArrayList<>();
        for (ElementKanban elementKanban : listeKanban.getKanbanElementList()) {
            this.stubElementDTOS.add(new StubElementDTO(elementKanban));
        }
    }
}
