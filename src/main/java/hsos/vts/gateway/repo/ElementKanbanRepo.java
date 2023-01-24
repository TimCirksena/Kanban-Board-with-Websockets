package hsos.vts.gateway.repo;

import hsos.vts.boundary.acl.FullElementDTO;
import hsos.vts.boundary.acl.KommentarDTO;
import hsos.vts.entity.*;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ElementKanbanRepo implements ElementKanbanCatalog {

    @Override
    public Optional<FullElementDTO> getElementById(long elementId) {
        //Falls hier nichts gefunden wird
        ElementKanban elementKanban = ElementKanban.findById(elementId);

        if (elementKanban != null) {
            FullElementDTO fullElementDTO = new FullElementDTO(elementKanban);
            return Optional.of(fullElementDTO);
            //Wenn nicht gefunden wurde, return empty Opt
        } else {
            return Optional.empty();
        }
    }
    @Override
    public List<FullElementDTO> getAllElements() {
        List<FullElementDTO> elementList = new ArrayList<>();
        List<ElementKanban> elementKanbanList = ElementKanban.findAll().list();
        for (ElementKanban element : elementKanbanList) {
            elementList.add(new FullElementDTO(element));
        }
        return elementList;
    }

    @Override
    public KommentarDTO getKommentarById(long kommentarId) {
        Kommentar kommentar = Kommentar.findById(kommentarId);
        KommentarDTO kommentarDTO = new KommentarDTO(kommentar);
        return kommentarDTO;
    }

    @Override
    public ArrayList<KommentarDTO> getKommentarFromKanbanElement(long elementId) {
        ElementKanban elementKanban = ElementKanban.findById(elementId);
        ArrayList<KommentarDTO> kommentarDTOs = new ArrayList<>();
        if (elementKanban != null) {
            for (Kommentar kommentar : elementKanban.getKommentareList()) {
                // Create a new KommentarDTO instance
                KommentarDTO kommentarDTO = new KommentarDTO(kommentar);
                kommentarDTOs.add(kommentarDTO);
            }
            return kommentarDTOs;
        }
        return null;
    }

    @Override
    public long deleteKanbanElementById(long elementId) {
        ElementKanban elementKanban = ElementKanban.findById(elementId);
        Optional<ListeKanban> liste = ListeKanban.findByIdOptional(elementKanban.getListeId());
        liste.get().getKanbanElementList().removeIf(elementKanban1 -> elementKanban1.getElementId() == elementId);
        ElementKanban.deleteById(elementId);
        Optional<ElementKanban> elementKanban1 = ElementKanban.findByIdOptional(elementId);
        if(elementKanban1.isEmpty()){
            return elementId;
        }
        return -1;
    }

    @Override
    public FullElementDTO updateTitel(long elementId, String titel) {
        ElementKanban elementKanban = ElementKanban.findById(elementId);
        elementKanban.setTitel(titel);
        FullElementDTO fullElementDTO = new FullElementDTO(elementKanban);
        return fullElementDTO;
    }

    @Override
    public FullElementDTO updateBeschreibung(long elementId, String beschreibung) {
        ElementKanban elementKanban = ElementKanban.findById(elementId);
        elementKanban.setBeschreibung(beschreibung);
        FullElementDTO fullElementDTO = new FullElementDTO(elementKanban);
        return fullElementDTO;
    }

    @Override
    public FullElementDTO addElement(String ersteller, String titel, String beschreibung) {
        ElementKanban elementKanban = new ElementKanban(ersteller,titel,beschreibung);
        elementKanban.persist();
        FullElementDTO fullElementDTO = new FullElementDTO(elementKanban);
        return fullElementDTO;
    }

    @Override
    public FullElementDTO updateElement(FullElementDTO fullElementDTO) {
        System.out.println("!!!! " + fullElementDTO.elementId);
        ElementKanban elementKanban = ElementKanban.findById(fullElementDTO.elementId);
        elementKanban.setBeschreibung(fullElementDTO.beschreibung + "");
        elementKanban.setTitel(fullElementDTO.titel + "");
        elementKanban.setErsteller(fullElementDTO.ersteller + "");
        return fullElementDTO;
    }
}
