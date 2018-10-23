package pieces;

public class Queen extends Pieces{

	public Queen(String name, String color, Position currentPosition) {
		super(name, color, currentPosition);
	}

	@Override
	public boolean isValidMoveSpecific(Position np) {
		if(!isValidMove(np)) {
			return false;
		}
		if((np.getFile() == this.getPosition().getFile() || np.getRank() == this.getPosition().getRank())
				|| (Math.abs(np.getFile() - this.getPosition().getFile()) == Math.abs(np.getRank() - this.getPosition().getRank()))){
			return true;
		}
		return false;
	}
	
	
	
	

}
