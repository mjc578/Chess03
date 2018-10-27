package pieces;

import board.Board;

public class Queen extends Pieces{

	public Queen(String name, String color, Position currentPosition) {
		super(name, color, currentPosition);
	}

	//queen movement does not depend on if there is a piece in space it is moving to, unlike pawn
	@Override
	public boolean move(Position np, Board board) {
		
		//piece cannot leave the confines of the board, uses the super method instead of override
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
			
		//queen can move like a bishop and a rook piece
		if((np.getFile() == this.getPosition().getFile() || np.getRank() == this.getPosition().getRank())
				|| (Math.abs(np.getFile() - this.getPosition().getFile()) == Math.abs(np.getRank() - this.getPosition().getRank()))){
			board.updateBoard(this, np);
			return true;
		}
		return false;
	}
}
