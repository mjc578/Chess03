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
						this.firstMove = true;
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


	//checks if king of same color as piece calling this method is in check
	public boolean isInCheck(Board board) {
		
		if(Board.isUnderAttack(this, this.getPosition(), board)) {
			return true;
		}
		return false;
		
	}
	
	//sees if player is in checkmate by testing all players oieces if they can make any valid moves which
	//place king out of check, returns true if none can
	public boolean isCheckmated(Board board) {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				Pieces p = board.getBoard()[i][j];
				if(p != null && p.getColor().equals(this.getColor())) {
					for(int k = 0; k < 8; k++) {
						for(int l = 0; l < 8; l++) {
							if(board.getBoard()[k][l] != null && board.getBoard()[k][l].getColor().equals(p.getColor())) {
								continue;
							}
							else if(board.getBoard()[k][l] == null || !board.getBoard()[k][l].getColor().equals(p.getColor())) {
								if(p.getName().equals("pawn")) {
									Pawn pp = (Pawn) p;
									if(pp.isValid(board.getBoardPosition(k, l), board)){
										return false;
									}
								}
								else {
									if(p.isValid(board.getBoardPosition(k, l), board)) {
										return false;
									}
								}
							}
						}
					}
				}
			}
		}
		return true;
	}
}