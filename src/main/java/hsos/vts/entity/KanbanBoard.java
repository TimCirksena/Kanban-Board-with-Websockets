package hsos.vts.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Cacheable
public class KanbanBoard extends PanacheEntity {

    private String titel;
    @OneToMany
    private List<KanbanListe> kanbanListen;

    public KanbanBoard(){}

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public List<KanbanListe> getKanbanListen() {
        return kanbanListen;
    }

    public void setKanbanListen(List<KanbanListe> kanbanListen) {
        this.kanbanListen = kanbanListen;
    }
}
