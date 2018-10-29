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
	public boolean isValid(Position np, Board board) {
		
		//piece may not leave the confines of the board
		if(!isOutOfBounds(np, board)) {
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
			return true;
		}
		return false;
	}

	@Override
	public boolean move(Position np, Board board) {
		//if rook tries to make an invalid move, prevent it from moving
		if(!isValid(np, board)){
			return false;
		}
		
		//if testPosition is false, that means this move puts king in check, which is illegal
		boolean test = testPosition(this, np, board);
		if(!test) {
			return false;
		}
		
		board.getBoard()[np.getFile()][np.getRank()] = this;
		board.getBoard()[this.getPosition().getFile()][this.getPosition().getRank()] = null;
		//update position field
		this.setPosition(Position.toChar(np.getFile()), np.getRank());
		firstMove = true;
		return true;
	}
}
