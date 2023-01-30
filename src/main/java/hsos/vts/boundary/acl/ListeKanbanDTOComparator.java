package hsos.vts.boundary.acl;

import java.util.Comparator;

public class ListeKanbanDTOComparator implements Comparator<ListeKanbanDTO> {
    @Override
    public int compare(ListeKanbanDTO a, ListeKanbanDTO b) {
        return Long.compare(a.listeId, b.listeId);
    }
}
