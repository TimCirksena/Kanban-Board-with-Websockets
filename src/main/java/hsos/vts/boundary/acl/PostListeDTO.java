package hsos.vts.boundary.acl;

import hsos.vts.entity.ListeKanban;

public class PostListeDTO {


    public long boardId;
    public String titel;
    public String color;

    public PostListeDTO(long boardId, String titel, String color) {
        this.titel = titel;
        this.boardId = boardId;
        this.color = color;
    }


    public PostListeDTO() {
    }
}
