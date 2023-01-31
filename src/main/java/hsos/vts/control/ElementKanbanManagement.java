package hsos.vts.control;

import hsos.vts.boundary.acl.FullElementDTO;
import hsos.vts.boundary.acl.KommentarDTO;
import hsos.vts.entity.ElementKanbanCatalog;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@ApplicationScoped
public class ElementKanbanManagement implements ElementKanbanInterface{
    @Inject
    ElementKanbanCatalog elementKanbanCatalog;

    @Override
    public Optional<FullElementDTO> getElementById(long elementId) {
        return elementKanbanCatalog.getElementById(elementId);
    }

    @Override
    public KommentarDTO getKommentarById(long kommentarId) {
        return elementKanbanCatalog.getKommentarById(kommentarId);
    }

    @Override
    public ArrayList<KommentarDTO> getKommentarFromKanbanElement(long elementId) {
        return elementKanbanCatalog.getKommentarFromKanbanElement(elementId);
    }

    @Override
    public long deleteKanbanElementById(long elementId) {
        return elementKanbanCatalog.deleteKanbanElementById(elementId);
    }

    @Override
    public FullElementDTO updateTitel(long elementId, String titel) {
        return elementKanbanCatalog.updateTitel(elementId,titel);
    }

    @Override
    public FullElementDTO updateBeschreibung(long elementId, String beschreibung) {
        return elementKanbanCatalog.updateBeschreibung(elementId,beschreibung);
    }

    @Override
    public FullElementDTO addElement(String ersteller, String titel, String beschreibung) {
        return elementKanbanCatalog.addElement(ersteller,titel,beschreibung);
    }

    @Override
    public List<FullElementDTO> getAllElements() {
        return elementKanbanCatalog.getAllElements();
    }

    @Override
    public FullElementDTO updateElement(FullElementDTO fullElementDTO) {
        return elementKanbanCatalog.updateElement(fullElementDTO);
    }
}
