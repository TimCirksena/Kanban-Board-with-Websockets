package hsos.vts.entity;

import hsos.vts.boundary.acl.FullElementDTO;
import hsos.vts.boundary.acl.StubElementDTO;

import java.util.List;

public interface ListeKanbanCatalog {
    List<StubElementDTO> getAllStubElementDTOInList(long listId);

    void addKanbanElement(long listId, FullElementDTO element);
}
