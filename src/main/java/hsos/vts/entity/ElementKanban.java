package hsos.vts.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Cacheable
public class ElementKanban extends PanacheEntityBase {

    @Id
    @SequenceGenerator(
            name = "ElementKanbanSequence",
            sequenceName = "ElementKanban_id_seq",
            allocationSize = 1,
            initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ElementKanbanSequence")
    @Basic(optional = false)
    private long elementId;
    @Column
    private int position;
    @Column
    private String ersteller;
    @Column
    private String titel;
    @Column
    private String beschreibung;
    @OneToMany
    @Column
    private List<Kommentar> KommentareList;

    public ElementKanban(){}

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getErsteller() {
        return ersteller;
    }

    public void setErsteller(String ersteller) {
        this.ersteller = ersteller;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public List<Kommentar> getKommentareList() {
        return KommentareList;
    }

    public void setKommentareList(List<Kommentar> kommentareList) {
        KommentareList = kommentareList;
    }

    public ElementKanban(String ersteller, String titel, String beschreibung) {
        this.ersteller = ersteller;
        this.titel = titel;
        this.beschreibung = beschreibung;
        KommentareList = new ArrayList<>();
    }

    public long getElementId() {
        return elementId;
    }

    public void setElementId(long elementId) {
        this.elementId = elementId;
    }
}
