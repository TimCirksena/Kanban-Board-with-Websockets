package hsos.vts.control.elementKanban;

import hsos.vts.boundary.acl.FullElementDTO;

import java.util.List;
import java.util.Optional;

public interface ElementKanbanInterface {
    Optional<FullElementDTO> getElementById(long elementId);
    long deleteKanbanElementById(long elementId);
    FullElementDTO updateTitel(long elementId, String titel);
    FullElementDTO updateBeschreibung(long elementId, String beschreibung);

    FullElementDTO addElement(String ersteller, String titel, String beschreibung);

    public List<FullElementDTO> getAllElements();



    public FullElementDTO updateElement(FullElementDTO fullElementDTO);

}
