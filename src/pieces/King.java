package pieces;

public class King extends Pieces{
	
	private boolean firstMove = false;

	public King(String name, String color, Position currentPosition) {
		super(name, color, currentPosition);
	}

	@Override
	public boolean isValidMove(Position np) {
		//TODO: The king can move one space in any direction, can castle with the rook as long as they have both not been moved
		//And cannot move to or through spaces (when castling) that would out it in check
		return false;
	}
	
	
	
}
