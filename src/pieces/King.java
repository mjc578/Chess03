package pieces;

import board.Board;

public class King extends Pieces{
	
	private boolean firstMove = false;
	private boolean inCheck; //dont know if this is actually useful

	public King(String name, String color, Position currentPosition) {
		super(name, color, currentPosition);
	}

	//maybe pass boolean if this new spot is in check
	public boolean move(Position np, Board board) {
		//TODO: The king can move one space in any direction, can castle with the rook as long as they have both not been moved
		//And cannot move to or through spaces (when castling) that would out it in check
		
		if(!super.move(np, board)) {
			return false;
		}
		return false;
	}
	
	
	
}
