package hsos.vts.boundary.acl;


import hsos.vts.entity.ElementKanban;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FullElementDTO {
    public long id;
    public String titel;
    public String beschreibung;
    public String ersteller;
    public List<KommentarDTO> kommentare;

    public FullElementDTO(ElementKanban elementKanban) {
        this.id = elementKanban.getElementId();
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

    public FullElementDTO(long id, String titel, String beschreibung) {
        this.id = id;
        this.titel = titel;
        this.beschreibung = beschreibung;
        this.kommentare = new ArrayList<>();
    }

    public FullElementDTO() {
    }
}
