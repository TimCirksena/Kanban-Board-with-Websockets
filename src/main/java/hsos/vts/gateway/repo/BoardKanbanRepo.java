package hsos.vts.gateway.repo;

import hsos.vts.boundary.acl.FullBoardDTO;
import hsos.vts.boundary.acl.StubBoardDTO;
import hsos.vts.entity.BoardKanban;
import hsos.vts.entity.BoardKanbanCatalog;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

import java.util.List;
import java.util.Optional;

public class BoardKanbanRepo implements BoardKanbanCatalog {
    @Override
    public List<StubBoardDTO> getAllKanbanBoards() {
        PanacheQuery<StubBoardDTO> query = BoardKanban.findAll().project(StubBoardDTO.class);
        return query.list();
    }

    @Override
    public FullBoardDTO getKanbanBoardById(long boardId) {
        //hier noch project hinkriegen, man kann ja safe optional selber machen
        Optional<BoardKanban> board = BoardKanban.findByIdOptional(boardId);
        if(board.isPresent()){
            return new FullBoardDTO();
        }
        return null;
    }
}
