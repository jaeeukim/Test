package board;

public class BoardService {
	
	BoardDao dao = null;
	
	//public int insertBoard(Board board) {
	public Board insertBoard(String boardTitle, String boardWriter, String boardContent) {
		dao = new BoardDao();
		Board board = null;
		
		board.setBoardTitle(boardTitle);
		board.setBoardWriter(boardWriter);
		board.setBoardContent(boardContent);
		
		boolean result = dao.insertBoard(board);
		
		if(result) {
			dao.commit();
			dao.close();
			return board;		
		} else {
			dao.rollback();
			dao.close();
			return null;
		}
	}
	
	public boolean updateBoard(Board data) {
		dao = new BoardDao();
		
		boolean result = dao.updateBoard(data);
		if(result) {
			dao.commit();
			dao.close();
			return true;
		}
		dao.rollback();
		dao.close();
		return false;
	}
	
	
	
}
