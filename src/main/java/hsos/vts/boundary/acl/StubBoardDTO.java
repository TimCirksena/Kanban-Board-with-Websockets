package hsos.vts.boundary.acl;

public class StubBoardDTO {
    public long boardId;
    public String titel;

    public StubBoardDTO(){}

    public StubBoardDTO(long boardId, String titel){
        this.boardId = boardId;
        this.titel = titel;
    }
}
