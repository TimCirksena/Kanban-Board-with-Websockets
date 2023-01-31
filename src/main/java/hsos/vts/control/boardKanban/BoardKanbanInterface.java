package hsos.vts.control.boardKanban;

import hsos.vts.boundary.acl.FullBoardDTO;
import hsos.vts.boundary.acl.ListeKanbanDTO;
import hsos.vts.boundary.acl.StubBoardDTO;

import java.util.List;

public interface BoardKanbanInterface {

    List<StubBoardDTO> getAllKanbanBoards();
    FullBoardDTO getKanbanBoardById(long boardId);
    StubBoardDTO createBoard(String boardTitel);

    long deleteKanbanBoardById(long boardId);

    ListeKanbanDTO addListToBoard(long boardId, String listTitel, String color);
}
