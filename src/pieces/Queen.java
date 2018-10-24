package pieces;

public class Queen extends Pieces{

	public Queen(String name, String color, Position currentPosition) {
		super(name, color, currentPosition);
	}

	@Override
	//queen movement does not depend on if there is a piece in space it is moving to, unlike pawn
	public boolean isValidMove(Position np) {
		
		//piece cannot leave the confines of the board, uses the super method instead of override
		if(!super.isValidMove(np)) {
			return false;
		}
		//queen can move like a bishop and a rook piece
		if((np.getFile() == this.getPosition().getFile() || np.getRank() == this.getPosition().getRank())
				|| (Math.abs(np.getFile() - this.getPosition().getFile()) == Math.abs(np.getRank() - this.getPosition().getRank()))){
			return true;
		}
		return false;
	}
	
	
	
	

}
