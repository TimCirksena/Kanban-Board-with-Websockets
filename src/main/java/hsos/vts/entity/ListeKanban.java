package hsos.vts.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Cacheable
public class ListeKanban extends PanacheEntityBase {

    @Id
    @SequenceGenerator(
            name = "ListeKanbanSequence",
            sequenceName = "ListeKanban_id_seq",
            allocationSize = 1,
            initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ListeKanbanSequence")
    @Basic(optional = false)
    private long listeId;
    @Column
    private int position;
    @Column
    private String titel;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Column
    private List<ElementKanban> kanbanElementList;

    public ListeKanban(){}

    public ListeKanban(String titel) {
        this.titel = titel;
        this.kanbanElementList = new ArrayList<>();
    }

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

    public long getListeId() {
        return listeId;
    }

    public void setListeId(long listeId) {
        this.listeId = listeId;
    }
}
