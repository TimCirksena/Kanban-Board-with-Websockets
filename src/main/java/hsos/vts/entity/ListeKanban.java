package hsos.vts.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Cacheable
public class ListeKanban extends PanacheEntity {
    private int position;
    private String titel;
    @OneToMany
    private List<ElementKanban> kanbanElementList;

    public ListeKanban(){}

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

    public List<ElementKanban> getKanbanElementList() {
        return kanbanElementList;
    }

    public void setKanbanElementList(List<ElementKanban> kanbanElementList) {
        this.kanbanElementList = kanbanElementList;
    }
}
