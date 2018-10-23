package pieces;

public class Rook extends Pieces{
	
	public Rook(String name, String color, Position position) {
		super(name, color, position);
	}

	@Override
	public boolean isValidMove(Position np) {
		// TODO check if move is out of range of the board? CHECK IF ACTUALLY CORRECT
		
		if(np.getFile() == this.getPosition().getFile() || np.getRank() == this.getPosition().getRank()) {
			if((np.getFile() < 8 && np.getFile() >= 0 && np.getRank() < 8 && np.getRank() >= 0)) {
				return true;
			}
			return false;
		}
		return false;
	}
}
