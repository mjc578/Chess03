package pieces;

import board.Board;

public class Bishop extends Pieces{
	
	public Bishop(String name, String color, Position position) {
		super(name, color, position);
	}

	@Override
	public boolean isValid(Position np, Board board) {
		
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
		
		if(Math.abs(np.getFile() - this.getPosition().getFile()) == Math.abs(np.getRank() - this.getPosition().getRank())) {
			return true;
		}
		return false;	
	}
	
	public boolean move(Position np, Board board) {
		
		if(!isValid(np, board)) {
			return false;
		}
		board.getBoard()[np.getFile()][np.getRank()] = this;
		board.getBoard()[this.getPosition().getFile()][this.getPosition().getRank()] = null;
		//update position field
		this.setPosition(Position.toChar(np.getFile()), np.getRank());
		return true;
	}
}
