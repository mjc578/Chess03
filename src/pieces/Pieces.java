package pieces;

public abstract class Pieces {
	
	private String name;
	private String color;
	private Position currentPosition;
	
	public Pieces(String name, String color, Position currentPosition) {
		this.name = name;
		this.color = color;
		this.currentPosition = currentPosition;
	}
	
	public Position getPosition() {
		return currentPosition;
	}
	
	public abstract boolean isValidMove(Position np);
	
	public boolean movePiece(Position p1, Position p2) {
		
		if(!isValidMove(p2)) {
			return false;
		}
		
		//look through board and see if there is a piece there...
		
		return true;
	}
}
