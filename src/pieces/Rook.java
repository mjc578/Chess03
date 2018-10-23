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
	public boolean isValidMoveSpecific(Position np) {
		if(!isValidMove(np)) {
			return false;
		}
		if(np.getFile() == this.getPosition().getFile() || np.getRank() == this.getPosition().getRank()) {
			return true;
		}
		return false;
	}
}
