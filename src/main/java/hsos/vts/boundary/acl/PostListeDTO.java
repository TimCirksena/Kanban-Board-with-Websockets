package hsos.vts.boundary.acl;

import hsos.vts.entity.ListeKanban;

public class PostListeDTO {


    public long boardId;
    public String titel;
    public String color;

    public PostListeDTO(long listeId, String titel, String color) {
        this.titel = titel;
        this.boardId = listeId;
        this.color = color;
    }
    public PostListeDTO(ListeKanban listeKanban){
        this.titel = listeKanban.getTitel();
        this.boardId = listeKanban.getListeId();
        this.color = listeKanban.getFarbe();
    }

    public PostListeDTO() {
    }
}
