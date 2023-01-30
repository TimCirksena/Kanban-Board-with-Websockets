package hsos.vts.boundary.acl;

import hsos.vts.entity.ElementKanban;
import hsos.vts.entity.ListeKanban;

import java.util.ArrayList;
import java.util.List;

public class ListeKanbanDTO {


    public List<StubElementDTO> stubElementDTOS;
    public String titel;
    public long listeId;
    public String color;

    public ListeKanbanDTO() {
    }

    public ListeKanbanDTO(ListeKanban listeKanban) {
        this.titel = listeKanban.getTitel();
        this.listeId = listeKanban.getListeId();
        this.color = listeKanban.getFarbe();
        this.stubElementDTOS = new ArrayList<>();
        for (ElementKanban elementKanban : listeKanban.getKanbanElementList()) {
            this.stubElementDTOS.add(new StubElementDTO(elementKanban));
        }
    }

    @Override
    public String toString() {
        return "ListeKanbanDTO{" +
                "stubElementDTOS=" + stubElementDTOS +
                ", titel='" + titel + '\'' +
                ", listeId=" + listeId +
                '}';
    }
}
