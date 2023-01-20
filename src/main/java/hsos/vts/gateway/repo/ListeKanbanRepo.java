package hsos.vts.gateway.repo;

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
        ElementKanban elementKanban = new ElementKanban(dto.ersteller, dto.titel, dto.beschreibung);
        elementKanban.persist();

        ListeKanban listeToAdd = ListeKanban.findById(dto.listeId);
        listeToAdd.getKanbanElementList().add(elementKanban);

        return new StubElementDTO(listeToAdd.getListeId(), elementKanban.getElementId(), elementKanban.getTitel());
    }

    /**
     *
     * @param listIdProvider
     * @param listIdConsumer
     * @param elementId
     *
     * wir gehen davon aus, dass es nur ein Element gibt
     */
    @Override
    public void moveFromListToList(long listIdProvider, long listIdConsumer, long elementId) {
        ListeKanban provider = ListeKanban.findById(listIdProvider);
        ListeKanban consumer = ListeKanban.findById(listIdConsumer);
        Optional<ElementKanban> toMove = Optional.empty();
        for (ElementKanban element : provider.getKanbanElementList()){
            if(element.getElementId() == elementId){
                toMove = Optional.of(element);

            }
        }
        if(toMove.isPresent()){
            provider.getKanbanElementList().add(toMove.get());
            consumer.getKanbanElementList().remove(toMove.get());
        } else {
            throw new RuntimeException("moveFromListToList, element wurde nicht in ProviderList gefunden");
        }

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
