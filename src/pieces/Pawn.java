package pieces;

import board.Board;

public class Pawn extends Pieces{
	
	private boolean firstMove = false;
	private boolean justMovedTwo = false;
	private int count = 0;

	public Pawn(String name, String color, Position currentPosition) {
		super(name, color, currentPosition);
	}
	
	public boolean getFirstMove() {
		return firstMove;
	}
	
	public boolean getJustMovedTwo() {
		return justMovedTwo;
	}
	
	public void setJustMovedTwo(boolean justMovedTwo) {
		this.justMovedTwo = justMovedTwo;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public boolean isValid(Position np, Board board) {		
		//move must remain in confines of the board, does not depend on color
		if(!isOutOfBounds(np, board)) {
			return false;
		}
		
		//piece is not obstructed by any pieces (in this case, when the pawn moves two)
		if(!canMoveThrough(np, board)) {
			return false;
		}
				
		//if the desired position has a piece of same color, return false
		if(isTeammate(np, board)) {
			return false;
		}
		
		//white pawns can only move up through the ranks, so each move must have a positive rank2 - rank1
		if(this.getColor().equals("white")){
			if(canAttack(np, board)) {
				return true;
			}
			//moved up one or two...
			if(np.getFile() == this.getPosition().getFile()) {
				//piece moved up two
				if(np.getRank() - this.getPosition().getRank() == 2 && firstMove == false && board.atPosition(np) == null) {
					firstMove = true;
					justMovedTwo = true;
					return true;
				}
				//piece moved up one
				else if(np.getRank() - this.getPosition().getRank() == 1 && board.atPosition(np) == null) {
					justMovedTwo = false;
					firstMove = true;
					return true;
				}
			}
		}
		//pawn is black, may only move down
		else{
			if(canAttack(np, board)) {
				return true;
			}
			if(np.getFile() == this.getPosition().getFile()) {
				if(np.getRank() - this.getPosition().getRank() == -2 && firstMove == false && board.atPosition(np) == null) {
					firstMove = true;
					justMovedTwo = true;
					return true;
				}
				else if(np.getRank() - this.getPosition().getRank() == -1 && board.atPosition(np) == null) {
					justMovedTwo = false;
					firstMove = true;
					return true;
				}
			}
		}
		return false;
	}
	
	//this is a pawn specific method, since it attacks weird
	public boolean canAttack(Position np, Board board) {
		//we already checked if the destination spot has a teammate so this spot, if occupied, is guaranteed to have an enemy piece
		//therefore, this is an attempted capture
		//pawn is moving diagonally, therefore the pawn is either attempting en passant or capturing a diagonal enemy piece
		if(this.getColor().equals("white")) {
			//pawn is moving diagonally, therefore the pawn is either attempting en passant or capturing a diagonal enemy piece
			if(Math.abs(np.getFile() - this.getPosition().getFile()) == 1 && np.getRank() - this.getPosition().getRank() == 1) {
				//piece is attempting a capture, we already checked if where it wants to go is occupied by teammate, so it is guaranteed to be an enemy
				if(board.atPosition(np) != null) {
					//pawn is able to capture as its first move
					if(!firstMove) {
						firstMove = true;
					}
					justMovedTwo = false;
					return true; 
				}
				//piece is attempting en passant, so an enemy pawn must be either on its right file or left file and have JUST moved two squares on its first move
				else {
					//get the position right under where pawn wants to go to see if it is a pawn who just moved 2
					Position p = new Position(Position.toChar(np.getFile() + 1), this.getPosition().getRank() + 1);
					//is the position under occupied?
					if(board.atPosition(p) != null){
						//is position a pawn?
						if(board.atPosition(p).getName().equals("pawn")){
							Pawn wpawn = (Pawn) board.atPosition(p);
							//DID IT JUST MOVE TWO?
							if(wpawn.getJustMovedTwo()) {
								//DENIED, get en passanteddddd
								board.getBoard()[p.getFile()][p.getRank()] = null;
								return true;
							}
						}	
					}
				}
			}
		}
		else {
			if(Math.abs(np.getFile() - this.getPosition().getFile()) == 1 && np.getRank() - this.getPosition().getRank() == -1) {
				//PROBLEM HERE  vvvvvvvvvvvvvvv WHEN TESTING TO SEE IF IT CAN ATTACK SPACE
				if(board.atPosition(np) != null) {
					if(!firstMove) {
						firstMove = true;
					}
					justMovedTwo = false;
					return true; 
				}
				else {
					Position p = new Position(Position.toChar(np.getFile() + 1), this.getPosition().getRank() + 1);
					if(board.atPosition(p) != null){
						if(board.atPosition(p).getName().equals("pawn")){
							Pawn wpawn = (Pawn) board.atPosition(p);
							if(wpawn.getJustMovedTwo()) {
								board.getBoard()[p.getFile()][p.getRank()] = null;
								return true;
							}
						}	
					}
				}
			}
		}
		return false;
	}
	
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
