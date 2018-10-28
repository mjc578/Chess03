package pieces;

import board.Board;

public class King extends Pieces{
	
	private boolean firstMove = false;

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
		
		//TODO: might have to change this below to true since king may be unable to castle
		if(Math.abs(this.getPosition().getFile() - np.getFile()) <= 1 && Math.abs(this.getPosition().getRank() - np.getRank()) <= 1) {
			//check if new position is under attack
			if(!Board.isUnderAttack(this, np, board)) {
				return true;
			
			}
		}
		//attempts castling
		else if(Math.abs(this.getPosition().getFile() - np.getFile()) == 2) {
			//must be in same rank for castlage
			if(this.getPosition().getRank() != np.getRank()) {
				return false;
			}
			if(!castle(np, board)) {
				return false;
			}
			return true;
		}
		return false;
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
		int rank = this.getPosition().getRank();
		
		//king is attempting castle right side
		if(this.getPosition().getFile() - np.getFile() == -2) {
			//check if spaces where king wants to go are under attack and are empty
			if(board.getBoard()[5][rank] != null || Board.isUnderAttack(this, new Position('f', rank + 1), board)) {
				return false;
			}
			if(board.getBoard()[6][rank] != null || Board.isUnderAttack(this, new Position('g', rank + 1), board)) {
				return false;
			}
			//check if rook has moved before and is even there
			if(board.getBoard()[7][rank] == null) {
				return false;
			}
			else {
				if(board.getBoard()[7][rank].getName().equals("Rook")) {
					Rook r = (Rook) board.getBoard()[7][rank];
					if(r.getFirstMove() == false) {
						//move the rook over the king
						board.getBoard()[5][rank] = board.getBoard()[7][rank];
						board.getBoard()[7][rank] = null;
						r.setFirstMove();
						r.setPosition('e', rank);
						return true;
					}
				}
			}
		}
		//king is attempting castle left side
		if(this.getPosition().getFile() - np.getFile() == 2) {
			//check if spaces where king wants to go are under attack and are empty
			if(board.getBoard()[3][rank] != null || Board.isUnderAttack(this, new Position('d', rank + 1), board)) {
				return false;
			}
			if(board.getBoard()[2][rank] != null || Board.isUnderAttack(this, new Position('c', rank + 1), board)) {
				return false;
			}
			//leftside requires check for the space between where rook is and king, can be in check just cant be occupado
			if(board.getBoard()[1][rank] != null) {
				return false;
			}
			if(board.getBoard()[0][rank] == null) {
				return false;
			}
			else {
				if(board.getBoard()[0][rank].getName().equals("Rook")) {
					Rook r = (Rook) board.getBoard()[rank][0];
					if(r.getFirstMove() == false) {
						//move the rook over the king
						board.getBoard()[3][rank] = board.getBoard()[0][rank];
						board.getBoard()[0][rank] = null;
						r.setPosition('c', rank);
						r.setFirstMove();
						return true;
					}
				}
			}
		}
		return false;
	}
}