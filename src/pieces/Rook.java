package pieces;

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
	public boolean isValidMove(Position np) {
		
		//piece may not leave the confines of the board
		if(!super.isValidMove(np)) {
			return false;
		}
		//rook can only move through a file or through a rank
		if(np.getFile() == this.getPosition().getFile() || np.getRank() == this.getPosition().getRank()) {
			return true;
		}
		return false;
	}
}
