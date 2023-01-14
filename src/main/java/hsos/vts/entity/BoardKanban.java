package hsos.vts.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.util.List;

@Entity
@Cacheable
public class BoardKanban extends PanacheEntityBase {
    @Id
    @SequenceGenerator(
            name = "BoardKanbanSequence",
            sequenceName = "BoardKanban_id_seq",
            allocationSize = 1,
            initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BoardKanbanSequence")
    @Basic(optional = false)
    private long boardId;
    @Column
    private String titel;
    @OneToMany
    @Column
    private List<ListeKanban> kanbanListen;

    public BoardKanban(){}

    public BoardKanban(String titel, List<ListeKanban> kanbanListen) {
        this.titel = titel;
        this.kanbanListen = kanbanListen;
    }
    public long getBoardId() {
        return boardId;
    }

    public void setBoardId(long boardId) {
        this.boardId = boardId;
    }


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
