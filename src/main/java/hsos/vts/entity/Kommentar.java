package hsos.vts.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
@Cacheable
public class Kommentar extends PanacheEntityBase {

    @Id
    @SequenceGenerator(
            name = "KommentarSequence",
            sequenceName = "Kommentar_id_seq",
            allocationSize = 1,
            initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "KommentarSequence")
    @Basic(optional = false)
    private long kommentarId;
    private String ersteller;

    private String nachricht;

    public Kommentar() {}

    public String getErsteller() {
        return ersteller;
    }

    public void setErsteller(String ersteller) {
        this.ersteller = ersteller;
    }

    public String getNachricht() {
        return nachricht;
    }

    public void setNachricht(String nachricht) {
        this.nachricht = nachricht;
    }

    public long getKommentarId() {
        return kommentarId;
    }

    public void setKommentarId(long kommentarId) {
        this.kommentarId = kommentarId;
    }
}
