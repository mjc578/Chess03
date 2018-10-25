package pieces;

public class Knight extends Pieces{

	public Knight(String name, String color, Position currentPosition) {
		super(name, color, currentPosition);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean isValidMove(Position np) {
		if(!super.isValidMove(np)) {
			return false;
		}
		if(Math.abs(np.getFile() - this.getPosition().getFile()) == Math.abs(np.getRank() - this.getPosition().getRank())
				|| (np.getFile() == this.getPosition().getFile() || np.getRank() == this.getPosition().getRank())
				|| Math.abs(np.getFile() - this.getPosition().getFile()) >= 2 
				|| Math.abs(np.getRank() - this.getPosition().getRank()) >= 2) {
			return false;
		}
		
		return true;
	}

}
