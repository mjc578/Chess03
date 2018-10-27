package pieces;

import board.Board;

public class Knight extends Pieces{

	public Knight(String name, String color, Position currentPosition) {
		super(name, color, currentPosition);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean move(Position np, Board board) {
		
		if(!super.move(np, board)) {
			return false;
		}
		
		//Knight piece is special case so dont need to check if there are pieces obstructing its path as it is
		//permitted to jump over pieces, only need to check if destination has a teammate
		if(isTeammate(np, board)) {
			return false;
		}
		
		if(Math.abs(np.getFile() - this.getPosition().getFile()) == Math.abs(np.getRank() - this.getPosition().getRank())
				|| (np.getFile() == this.getPosition().getFile() || np.getRank() == this.getPosition().getRank())
				|| Math.abs(np.getFile() - this.getPosition().getFile()) > 2 
				|| Math.abs(np.getRank() - this.getPosition().getRank()) > 2) {
			board.updateBoard(this, np);
			return true;
		}
		
		return false;
	}

}
