package hsos.vts.boundary.acl;


import hsos.vts.entity.ElementKanban;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FullElementDTO {
    public long elementId;
    public String titel;
    public String beschreibung;
    public String ersteller;

    public FullElementDTO(ElementKanban elementKanban) {
        this.elementId = elementKanban.getElementId();
        this.ersteller = elementKanban.getErsteller();
        this.titel = elementKanban.getTitel();
        this.beschreibung = elementKanban.getBeschreibung();
        /**
         * Alternativ Lösung:
         this.kommentare = new ArrayList<>();
         for (Kommentar kommentar : elementKanban.getKommentare()) {
         this.kommentare.add(new KommentarDTO(kommentar));
         }
         */
    }

    public FullElementDTO(long elementId, String titel, String beschreibung, String ersteller) {
        this.elementId = elementId;
        this.titel = titel;
        this.beschreibung = beschreibung;
        this.ersteller = ersteller;
    }

    public FullElementDTO() {
    }
}
