package hsos.vts.entity;

import hsos.vts.boundary.acl.PostElementDTO;
import hsos.vts.boundary.acl.StubElementDTO;

import java.util.List;

public interface ListeKanbanCatalog {
    List<StubElementDTO> getAllStubElementDTOInList(long listId);
    StubElementDTO addKanbanElement(PostElementDTO postElementDTO);

    long deleteListeKanbanById(long listeId, long boardId);

    void moveFromListToList(long listeId, long elementId);

}
