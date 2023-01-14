package hsos.vts.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Cacheable
public class ElementKanban extends PanacheEntity {
    private int position;
    private String ersteller;
    private String titel;
    private String beschreibung;
    @OneToMany
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
}
