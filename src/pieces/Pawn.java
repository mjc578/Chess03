package pieces;

public class Pawn extends Pieces{
	
	private boolean firstMove = false;

	public Pawn(String name, String color, Position currentPosition) {
		super(name, color, currentPosition);
	}

	//pawn specific
	public boolean isValidMovePawn(Position np, boolean isOccupied) {
		//Pawns can only move forward, on first move, can move to spaces ahead, otherwise only one space, TAKES PIECES DIAGONALLY ONLY
		//TODO: when board calls this method, it could pass a boolean telling if there is a piece in the spot it wants to go to??? seems like good idea
		//movement will be dependent on the color (black moves down white moves up)
		
		//move must remain in confines of the board, does not depend on color
		if(!isValidMove(np)) {
			return false;
		}
		
		return false;
	}
	
	

}
