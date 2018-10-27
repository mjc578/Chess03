package pieces;

import board.Board;

public class Rook extends Pieces{
	
	private boolean firstMove = false;
	
	public Rook(String name, String color, Position position) {
		super(name, color, position);
	}
	
	public void setFirstMove() {
		firstMove = true;
	}
	
	public boolean getFirstMove() {
		return firstMove;
	}

	@Override
	public boolean move(Position np, Board board) {
		
		//piece may not leave the confines of the board
		if(!super.move(np, board)) {
			return false;
		}
		
		//piece is not obstructed by any pieces
		if(!canMoveThrough(np, board)) {
			return false;
		}
		
		//if the desired position has a piece of same color, return false
		if(isTeammate(np, board)) {
			return false;
		}
		
		//rook can only move through a file or through a rank
		if(np.getFile() == this.getPosition().getFile() || np.getRank() == this.getPosition().getRank()) {
			firstMove = true;
			board.updateBoard(this, np);
			return true;
		}
		return false;
	}
}
