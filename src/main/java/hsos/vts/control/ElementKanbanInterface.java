package hsos.vts.control;

import hsos.vts.boundary.acl.FullElementDTO;
import hsos.vts.boundary.acl.KommentarDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ElementKanbanInterface {
    Optional<FullElementDTO> getElementById(long elementId);
    KommentarDTO getKommentarById(long kommentarId);
    ArrayList<KommentarDTO> getKommentarFromKanbanElement(long elementId);
    long deleteKanbanElementById(long elementId);
    FullElementDTO updateTitel(long elementId, String titel);
    FullElementDTO updateBeschreibung(long elementId, String beschreibung);

    FullElementDTO addElement(String ersteller, String titel, String beschreibung);

    public List<FullElementDTO> getAllElements();



    public FullElementDTO updateElement(FullElementDTO fullElementDTO);

}
