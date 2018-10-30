package pieces;

import board.Board;

public class Knight extends Pieces{

	public Knight(String name, String color, Position currentPosition) {
		super(name, color, currentPosition);
	}
	
	@Override
	public boolean isValid(Position np, Board board) {
		
		if(!isOutOfBounds(np, board)) {
			return false;
		}
		
		
		
		//Knight piece is special case so don't need to check if there are pieces obstructing its path as it is
		//permitted to jump over pieces, only need to check if destination has a team mate
		if(isTeammate(np, board)) {
			return false;
		}
				
		if (Math.abs(np.getFile() - this.getPosition().getFile()) == 2 && Math.abs(np.getRank() - this.getPosition().getRank()) == 1 
			|| Math.abs(np.getFile() - this.getPosition().getFile()) == 1 && Math.abs(np.getRank() - this.getPosition().getRank()) == 2){
			boolean test = testPosition(this, np, board);
			if(!test) {
				return false;
			}
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean move(Position np, Board board) {
		
		if(!isValid(np, board)) {
			return false;
		}
		
		board.getBoard()[np.getFile()][np.getRank()] = this;
		board.getBoard()[this.getPosition().getFile()][this.getPosition().getRank()] = null;
		//update position field
		this.setPosition(Position.toChar(np.getFile()), np.getRank());
		return true;
	}

}
