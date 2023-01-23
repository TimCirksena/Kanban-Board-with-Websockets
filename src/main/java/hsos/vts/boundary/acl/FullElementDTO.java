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
    public List<KommentarDTO> kommentare;

    public FullElementDTO(ElementKanban elementKanban) {
        this.elementId = elementKanban.getElementId();
        this.ersteller = elementKanban.getErsteller();
        this.titel = elementKanban.getTitel();
        this.beschreibung = elementKanban.getBeschreibung();
        //TODO:Hier könnte falsch sein: Spätere Überprüfung erforderlich!
        this.kommentare = elementKanban.getKommentareList().stream().map(KommentarDTO::new).collect(Collectors.toList());
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
        this.kommentare = new ArrayList<>();
    }

    public FullElementDTO() {
    }
}
