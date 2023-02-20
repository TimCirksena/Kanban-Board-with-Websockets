package hsos.vts.gateway.repo;

import hsos.vts.entity.BoardKanban;
import hsos.vts.entity.ElementKanban;
import hsos.vts.entity.Kunde;
import hsos.vts.entity.ListeKanban;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class StartupRepoInitializer {

    //idee: das hier aufrufen können um die datenbank mit sachen zu befüllen von resource aus
    @Inject
    KundeRepo kundeRepo;

    @Transactional
    public void startupInit(@Observes StartupEvent evt){
        //deleteEverything();
        //kanbanBoardsErstellen();
        loadUsers();
    }


    public void loadUsers() {
        // reset and load all test users
        Kunde.deleteAll();
        kundeRepo.addKunde("admin", "admin", "admin");
        kundeRepo.addKunde("user", "user", "kunde");
    }

    private void deleteEverything(){
        BoardKanban.deleteAll();
    }

    private void kanbanBoardsErstellen(){
        String[] strings = new String[5];
        strings[0] = "VTS Board";
        strings[1] = "Working Student Board";
        strings[2] = "SWA Board";
        strings[3] = "Mathe Board";
        strings[4] = "Freizeit Board";

        for(String s : strings){
            List<ListeKanban> kanbanListen = new ArrayList<>();
            BoardKanban board = new BoardKanban(s,kanbanListen);

            kanbanListenErstellen(board);
            board.persist();

        }
    }

    private void kanbanListenErstellen(BoardKanban boardKanban){
        String[] strings = new String[3];
        strings[0] = "Backlog";
        strings[1] = "Next Sprint";
        strings[2] = "Done";

        //BoardKanban boardKanban = BoardKanban.findById(1L);

        for(String titel : strings){
            ListeKanban listeKanban = new ListeKanban(titel);
            kanbanElementeErstellen(listeKanban);
            boardKanban.getKanbanListen().add(listeKanban);
        }

        //hier nicht persisten, da es eigentlich von boardkanban so übernommen wird
    }

    private void kanbanElementeErstellen(ListeKanban listeKanban){
        String[] titelArray = new String[4];
        titelArray[0] = "kanban board erstellen";
        titelArray[1] = "einträge hinzufügen";
        titelArray[2] = "css hinzufügen";
        titelArray[3] = "drag and drop funktionalität erforschen";

        String[] erstellerArray = new String[4];
        erstellerArray[0] = "max";
        erstellerArray[1] = "tim";
        erstellerArray[2] = "peter";
        erstellerArray[3] = "pan";


        //BoardKanban boardKanban =  BoardKanban.findById(1L);

        for(int i = 0; i<titelArray.length; i++){
            listeKanban.getKanbanElementList().add(new ElementKanban(erstellerArray[i],titelArray[i],"beschreibung blabla"));
        }
    }
}
