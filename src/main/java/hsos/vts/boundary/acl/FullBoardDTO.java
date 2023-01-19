package hsos.vts.boundary.acl;

import hsos.vts.entity.BoardKanban;
import hsos.vts.entity.ListeKanban;

import java.util.ArrayList;
import java.util.List;

public class FullBoardDTO {
    public long boardId;
    public String titel;
    public List<ListeKanbanDTO> kanbanLists;

    public FullBoardDTO() {
    }
    public FullBoardDTO(BoardKanban boardKanban) {
        this.boardId = boardKanban.getBoardId();
        this.titel = boardKanban.getTitel();
        this.kanbanLists = new ArrayList<>();
        for (ListeKanban listeKanban : boardKanban.getKanbanListen()) {
            this.kanbanLists.add(new ListeKanbanDTO(listeKanban));
        }
    }
}
