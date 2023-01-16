package hsos.vts.boundary.acl;

import hsos.vts.entity.BoardKanban;
import hsos.vts.entity.ListeKanban;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FullBoardDTO {
    public long boardId;
    public String titel;
    public List<ListKanbanDTO> kanbanLists;

    public FullBoardDTO() {
    }
    public FullBoardDTO(BoardKanban boardKanban) {
        this.boardId = boardKanban.getBoardId();
        this.titel = boardKanban.getTitel();
        this.kanbanLists = new ArrayList<>();
        for (ListeKanban listeKanban : boardKanban.getKanbanListen()) {
            this.kanbanLists.add(new ListKanbanDTO(listeKanban));
        }
    }
}
