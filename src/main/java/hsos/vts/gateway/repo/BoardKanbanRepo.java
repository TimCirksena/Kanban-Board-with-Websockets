package hsos.vts.gateway.repo;

import hsos.vts.boundary.acl.FullBoardDTO;
import hsos.vts.boundary.acl.StubBoardDTO;
import hsos.vts.entity.BoardKanban;
import hsos.vts.entity.BoardKanbanCatalog;
import hsos.vts.entity.ListeKanban;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@ApplicationScoped
public class BoardKanbanRepo implements BoardKanbanCatalog {


    @Override
    public List<StubBoardDTO> getAllKanbanBoards() {
        //PanacheQuery<StubBoardDTO> query = BoardKanban.findAll().project(StubBoardDTO.class);
        //return query.list();
        List<BoardKanban> boards = BoardKanban.findAll().list();
        List<StubBoardDTO> dtos = new ArrayList<>();
        for (BoardKanban b : boards){
            dtos.add(new StubBoardDTO(b.getBoardId(),b.getTitel()));
        }
        return dtos;
    }

    @Override
    public FullBoardDTO getKanbanBoardById(long boardId) {
        //hier noch project hinkriegen, man kann ja safe optional selber machen
        Optional<BoardKanban> board = BoardKanban.findByIdOptional(boardId);
        if(board.isPresent()){
            FullBoardDTO fullBoardDTO = new FullBoardDTO(board.get());
            return fullBoardDTO;
        }
        else {
            return null;
            //return Optional.empty();
        }
    }


    @Override
    public StubBoardDTO createBoard(String boardTitel) {
        List<ListeKanban> kanbanListen = new ArrayList<>();
        BoardKanban board = new BoardKanban(boardTitel,kanbanListen);
        board.persist();
        return new StubBoardDTO(board.getBoardId(), board.getTitel());
    }
}
