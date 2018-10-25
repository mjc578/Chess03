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
		
		
		
		if (this.getColor().equals("white")){
			if(isOccupied == true) {
				if (Math.abs(np.getFile() - this.getPosition().getFile()) == 1 && np.getRank()-this.getPosition().getRank() == 1) {
					return true; 
				}
				return false;
			}
			if (np.getFile() == this.getPosition().getFile()) { 
				if (this.firstMove == true  && np.getRank()-this.getPosition().getRank() <= 2) {
						return true;
				}
				else if  (np.getRank()-this.getPosition().getRank() == 1) {
						return true;
				}
			}
			return false;
		}
		if (this.getColor().equals("black")){
			if(isOccupied == true) {
				if (Math.abs(np.getFile() - this.getPosition().getFile()) == 1 && this.getPosition().getRank() - np.getRank() == 1) {
					return true; 
				}
				return false;
			}
			if (np.getFile() == this.getPosition().getFile()) { 
				if (this.firstMove == true  && this.getPosition().getRank() - np.getRank() <= 2) {
						return true;
				}
				else if  (this.getPosition().getRank() - np.getRank() == 1) {
						return true;
				}
			}
			return false;
		}
			
			
			
			
			
			
			
			
			
	
		
		
		//move must remain in confines of the board, does not depend on color
		if(!isValidMove(np)) {
			return false;
		}
		
		return false;
	}
	
	

}
