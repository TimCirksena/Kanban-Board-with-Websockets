package hsos.vts.entity;

import hsos.vts.boundary.acl.FullElementDTO;

import java.util.Optional;

public interface ElementKanbanCatalog {
    Optional<FullElementDTO> getElementById(long elementId);
}
