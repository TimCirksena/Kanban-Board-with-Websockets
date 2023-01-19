package hsos.vts.boundary.acl;

public class PostListeDTO {


    public long boardId;
    public String titel;

    public PostListeDTO(long listeId, String titel) {
        this.titel = titel;
        this.boardId = listeId;
    }

    public PostListeDTO() {
    }
}
