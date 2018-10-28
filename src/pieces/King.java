package pieces;

import board.Board;

public class King extends Pieces{
	
	private boolean firstMove = false;
	private boolean inCheck = false; //dont know if this is actually useful

	public King(String name, String color, Position currentPosition) {
		super(name, color, currentPosition);
	}
	
	public boolean isValid(Position np, Board board) {
		
		if(!isOutOfBounds(np, board)) {
			return false;
		}
		
		if(isTeammate(np, board)) {
			return false;
		}
		
		//king may move one spot in any direction
		if(Math.abs(this.getPosition().getFile() - np.getFile()) > 1 || Math.abs(this.getPosition().getRank() - np.getRank()) > 1) {
			return false;
		}
		else if(Math.abs(this.getPosition().getFile() - np.getFile()) == 2) {
			if(castle(np, board)) {
				return true;
			}
			return false;
		}
		
		//check if new position is under attack
		if(Board.isUnderAttack(this, np, board)) {
			return false;
		}
		return true;
	}

	//king does not need to check if it is putting itself in check, as it is DA KING, and he already checked lmao
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
	
	//called if player tried to move the king over two files
	private boolean castle(Position np, Board board) {
		
		//if the king has already made its first move before, castle is prohibited
		if(this.firstMove == true) {
			return false;
		}
		
		//the king is in check castling not permitted
		if(Board.isUnderAttack(this, this.getPosition(), board)) {
			return false;
		}
		
		//check if king can move through these spaces
		if(!this.canMoveThrough(np, board)) {
			return false;
		}
		//check if result space is taken
		if(board.atPosition(np) == null) {
			
		}
		return false;
	}
}
