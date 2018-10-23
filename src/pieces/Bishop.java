package pieces;

public class Bishop extends Pieces{
	
	public Bishop(String name, String color, Position position) {
		super(name, color, position);
	}

	@Override
	public boolean isValidMoveSpecific(Position np) {
		if(!super.isValidMove(np)) {
			return false;
		}
		if(Math.abs(np.getFile() - this.getPosition().getFile()) == Math.abs(np.getRank() - this.getPosition().getRank())) {
			return true;
		}
		return false;	
	}
}
