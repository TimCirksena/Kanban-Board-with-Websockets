package hsos.vts.entity;

import hsos.vts.boundary.acl.FullBoardDTO;
import hsos.vts.boundary.acl.ListeKanbanDTO;
import hsos.vts.boundary.acl.PostListeDTO;
import hsos.vts.boundary.acl.StubBoardDTO;

import java.util.List;

public interface BoardKanbanCatalog {
    List<StubBoardDTO>  getAllKanbanBoards();
    FullBoardDTO getKanbanBoardById(long boardId);
    StubBoardDTO createBoard(String boardTitel);

    long deleteKanbanBoardById(long boardId);

    ListeKanbanDTO addListToBoard(long boardId, String listTitel, String color);

}

