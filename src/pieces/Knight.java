package pieces;

public class Knight extends Pieces{

	public Knight(String name, String color, Position currentPosition) {
		super(name, color, currentPosition);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean isValidMove(Position np) {
		return false;
	}

}
