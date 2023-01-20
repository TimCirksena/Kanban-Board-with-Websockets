package hsos.vts.gateway.repo;

import hsos.vts.boundary.acl.FullElementDTO;
import hsos.vts.boundary.acl.StubElementDTO;
import hsos.vts.entity.BoardKanban;
import hsos.vts.entity.ListeKanban;
import hsos.vts.entity.ListeKanbanCatalog;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ListeKanbanRepo implements ListeKanbanCatalog {
    @Override
    public List<StubElementDTO> getAllStubElementDTOInList(long listId) {
        return null;
    }

    @Override
    public void addKanbanElement(long listId, FullElementDTO element) {

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
