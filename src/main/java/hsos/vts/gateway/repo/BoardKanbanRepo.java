package hsos.vts.gateway.repo;

import hsos.vts.boundary.acl.FullBoardDTO;
import hsos.vts.boundary.acl.ListeKanbanDTO;
import hsos.vts.boundary.acl.StubBoardDTO;
import hsos.vts.entity.BoardKanban;
import hsos.vts.entity.BoardKanbanCatalog;
import hsos.vts.entity.ListeKanban;

import javax.enterprise.context.ApplicationScoped;
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

    @Override
    public long deleteKanbanBoardById(long boardId) {
        BoardKanban.deleteById(boardId);
        // Überprüfung ob das item noch existiert
        Optional<BoardKanban> board = BoardKanban.findByIdOptional(boardId);
        if(board.isEmpty()){
            return boardId;
        }
        return -1;
    }


    @Override
    public ListeKanbanDTO addListToBoard(long boardId, String listTitel) {
        //Zuerst persisten dann adden, sonst geht die Id verloren
        ListeKanban listeKanban = new ListeKanban(listTitel);
        listeKanban.persist();
        //Board wird gefunden
        Optional<BoardKanban> board = BoardKanban.findByIdOptional(boardId);
        //Mittels get() bekommen wir das richtige board, weil vorher nur optional
        board.get().getKanbanListen().add(listeKanban);
        ListeKanbanDTO listeKanbanDTO = new ListeKanbanDTO(listeKanban);
        //Debugging
        //System.out.println("DTO" + listeKanbanDTO.toString());
        //System.out.println("ListeKanban:" + listeKanban.toString());
        return listeKanbanDTO;
    }

}
