package hsos.vts.entity;

import hsos.vts.boundary.acl.ElementChangePosDTO;
import hsos.vts.boundary.acl.PostElementDTO;
import hsos.vts.boundary.acl.StubElementDTO;

import java.util.List;

public interface ListeKanbanCatalog {
    List<StubElementDTO> getAllStubElementDTOInList(long listId);
    StubElementDTO addKanbanElement(PostElementDTO postElementDTO);

    long deleteListeKanbanById(long listeId, long boardId);

    ElementChangePosDTO moveFromListToList(long listeId, long elementId);

    String getColorFromList(long listeId);

    String setColorFromList(long listeId, String color);
}
