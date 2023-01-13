package hsos.vts.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Cacheable
public class KanbanListe extends PanacheEntity {
    private int position;
    private String titel;
    @OneToMany
    private List<KanbanElement> kanbanElementList;

    public KanbanListe(){}

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public List<KanbanElement> getKanbanElementList() {
        return kanbanElementList;
    }

    public void setKanbanElementList(List<KanbanElement> kanbanElementList) {
        this.kanbanElementList = kanbanElementList;
    }
}
