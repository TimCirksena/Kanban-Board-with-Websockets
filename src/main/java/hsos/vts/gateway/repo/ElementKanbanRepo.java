package hsos.vts.gateway.repo;

import hsos.vts.boundary.acl.FullElementDTO;
import hsos.vts.entity.ElementKanbanCatalog;

import java.util.Optional;

public class ElementKanbanRepo implements ElementKanbanCatalog {
    @Override
    public Optional<FullElementDTO> getElementById(long elementId) {
        return Optional.empty();
    }
}
