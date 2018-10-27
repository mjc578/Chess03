package pieces;

import board.Board;

public class Bishop extends Pieces{
	
	public Bishop(String name, String color, Position position) {
		super(name, color, position);
	}

	@Override
	public boolean move(Position np, Board board) {
		
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
		
		if(Math.abs(np.getFile() - this.getPosition().getFile()) == Math.abs(np.getRank() - this.getPosition().getRank())) {
			board.updateBoard(this, np);
			return true;
		}
		return false;	
	}
}
