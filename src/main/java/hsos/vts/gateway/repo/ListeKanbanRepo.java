package hsos.vts.gateway.repo;

import hsos.vts.boundary.acl.FullElementDTO;
import hsos.vts.boundary.acl.StubElementDTO;
import hsos.vts.entity.ListeKanbanCatalog;

import java.util.List;

public class ListeKanbanRepo implements ListeKanbanCatalog {
    @Override
    public List<StubElementDTO> getAllStubElementDTOInList(long listId) {
        return null;
    }

    @Override
    public void addKanbanElement(long listId, FullElementDTO element) {

    }
}
