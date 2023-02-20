package hsos.vts.control.listeKanban;

import hsos.vts.boundary.acl.ElementChangePosDTO;
import hsos.vts.boundary.acl.PostElementDTO;
import hsos.vts.boundary.acl.StubElementDTO;
import hsos.vts.entity.BoardKanbanCatalog;
import hsos.vts.entity.ListeKanbanCatalog;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
@ApplicationScoped
public class ListeKanbanManagement implements ListeKanbanInterface{

    @Inject
    ListeKanbanCatalog listeKanbanCatalog;

    @Override
    public List<StubElementDTO> getAllStubElementDTOInList(long listId) {
        return listeKanbanCatalog.getAllStubElementDTOInList(listId);
    }

    @Override
    public StubElementDTO addKanbanElement(PostElementDTO postElementDTO) {
        return listeKanbanCatalog.addKanbanElement(postElementDTO);
    }

    @Override
    public long deleteListeKanbanById(long listeId, long boardId) {
        return listeKanbanCatalog.deleteListeKanbanById(listeId,boardId);
    }

    @Override
    public ElementChangePosDTO moveFromListToList(long listeId, long elementId) {
        return listeKanbanCatalog.moveFromListToList(listeId,elementId);
    }

    @Override
    public String getColorFromList(long listeId) {
        return listeKanbanCatalog.getColorFromList(listeId);
    }

    @Override
    public String setColorFromList(long listeId, String color) {
        return listeKanbanCatalog.setColorFromList(listeId,color);
    }
}
