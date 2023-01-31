package hsos.vts.control.boardKanban;

import hsos.vts.boundary.acl.FullBoardDTO;
import hsos.vts.boundary.acl.ListeKanbanDTO;
import hsos.vts.boundary.acl.StubBoardDTO;
import hsos.vts.control.boardKanban.BoardKanbanInterface;
import hsos.vts.entity.BoardKanbanCatalog;
import hsos.vts.entity.ListeKanbanCatalog;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
@ApplicationScoped
public class BoardKanbanManagement implements BoardKanbanInterface {
    @Inject
    BoardKanbanCatalog boardKanbanCatalog;
    ListeKanbanCatalog listeKanbanCatalog;

    @Override
    public List<StubBoardDTO> getAllKanbanBoards() {
        return boardKanbanCatalog.getAllKanbanBoards();
    }

    @Override
    public FullBoardDTO getKanbanBoardById(long boardId) {
        return boardKanbanCatalog.getKanbanBoardById(boardId);
    }

    @Override
    public StubBoardDTO createBoard(String boardTitel) {
        return boardKanbanCatalog.createBoard(boardTitel);
    }

    @Override
    public long deleteKanbanBoardById(long boardId) {
        return boardKanbanCatalog.deleteKanbanBoardById(boardId);
    }

    @Override
    public ListeKanbanDTO addListToBoard(long boardId, String listTitel, String color) {
        return boardKanbanCatalog.addListToBoard(boardId,listTitel,color);
    }
}
