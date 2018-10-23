package pieces;

public abstract class Pieces {
	
	String color;
	Position currentPosition;
	
	public abstract boolean isValidMove(Position np);
	
	public boolean movePiece(Position p1, Position p2) {
		
		if(!isValidMove(p2)) {
			return false;
		}
		
		//look through board and see if there is a piece there...
		
		return true;
	}
	
	
	
	
}
