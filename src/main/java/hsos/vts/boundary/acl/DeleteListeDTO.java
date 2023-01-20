package hsos.vts.boundary.acl;

public class DeleteListeDTO {
    public long listeId;
    public long boardId;

    public DeleteListeDTO(long listeId, long boardId) {
        this.listeId = listeId;
        this.boardId = boardId;
    }

    public DeleteListeDTO() {
    }
}
