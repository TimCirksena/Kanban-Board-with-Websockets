package hsos.vts.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Cacheable
public class BoardKanban extends PanacheEntity {

    private String titel;
    @OneToMany
    private List<ListeKanban> kanbanListen;

    public BoardKanban(){}

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public List<ListeKanban> getKanbanListen() {
        return kanbanListen;
    }

    public void setKanbanListen(List<ListeKanban> kanbanListen) {
        this.kanbanListen = kanbanListen;
    }
}
