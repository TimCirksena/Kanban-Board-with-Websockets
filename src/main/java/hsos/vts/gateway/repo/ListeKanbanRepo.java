package hsos.vts.gateway.repo;

import hsos.vts.boundary.acl.ElementChangePosDTO;
import hsos.vts.boundary.acl.FullElementDTO;
import hsos.vts.boundary.acl.PostElementDTO;
import hsos.vts.boundary.acl.StubElementDTO;
import hsos.vts.boundary.websockets.SingleBoardWebsocket;
import hsos.vts.entity.ElementKanban;
import hsos.vts.entity.ListeKanban;
import hsos.vts.entity.BoardKanban;
import hsos.vts.entity.ListeKanbanCatalog;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ListeKanbanRepo implements ListeKanbanCatalog {
    @Inject
    SingleBoardWebsocket singleBoardWebsocket;

    @Override
    public List<StubElementDTO> getAllStubElementDTOInList(long listId) {
        return null;
    }

    @Override
    public StubElementDTO addKanbanElement(PostElementDTO dto) {
        System.out.println("!!!! " + dto.listeId);
        ElementKanban elementKanban = new ElementKanban(dto.ersteller, dto.titel, dto.beschreibung, dto.listeId);
        elementKanban.persist();

        ListeKanban listeToAdd = ListeKanban.findById(dto.listeId);
        listeToAdd.getKanbanElementList().add(elementKanban);
        return new StubElementDTO(listeToAdd.getListeId(), elementKanban.getElementId(), elementKanban.getTitel());
    }

    /**
     * @param listIdConsumer
     * @param elementId      wir gehen davon aus, dass es nur ein Element gibt
     */
    @Override
    public ElementChangePosDTO moveFromListToList(long listIdConsumer, long elementId) {
        ListeKanban.flush();
        ElementKanban elementKanban = ElementKanban.findById(elementId);
        ElementKanban.flush();
        ListeKanban.flush();
        ListeKanban provider = ListeKanban.findById(elementKanban.getListeId());
        ListeKanban.flush();
        ListeKanban consumer = ListeKanban.findById(listIdConsumer);
        ListeKanban.flush();

        if (elementKanban != null) {
            //FullElementDTO fullElementDTO = new FullElementDTO(elementKanban);
            provider.getKanbanElementList().remove(elementKanban);
            ListeKanban.flush();
            //ElementKanban elementKanban1 = new ElementKanban(fullElementDTO.ersteller, fullElementDTO.titel, fullElementDTO.beschreibung,listIdConsumer);
            consumer.getKanbanElementList().add(elementKanban);
            ListeKanban.flush();
            elementKanban.setListeId(listIdConsumer);
            ListeKanban.flush();
            ElementKanban.flush();
            ElementChangePosDTO elementChangePosDTO = new ElementChangePosDTO(listIdConsumer,elementId);
            return elementChangePosDTO;

        } else {
            throw new RuntimeException("moveFromListToList, element wurde nicht in ProviderList gefunden");
        }
    }

    @Override
    public String getColorFromList(long listeId) {
        ListeKanban listeKanban = ListeKanban.findById(listeId);
        return listeKanban.getFarbe();
    }
    @Override
    public String setColorFromList(long listeId, String color) {
        ListeKanban listeKanban = ListeKanban.findById(listeId);
        listeKanban.setFarbe(color);
        return listeKanban.getFarbe();
    }

    @Override
    public long deleteListeKanbanById(long listeId, long boardId) {
        Optional<BoardKanban> board = BoardKanban.findByIdOptional(boardId);
        board.get().getKanbanListen().removeIf(listeKanban -> listeKanban.getListeId() == listeId);
        ListeKanban.deleteById(listeId);
        Optional<ListeKanban> listeKanban = ListeKanban.findByIdOptional(listeId);
        if (listeKanban.isEmpty()) {
            return listeId;
        }
        return -1;
    }

}
