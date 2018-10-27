package pieces;

import board.Board;

public class King extends Pieces{
	
	private boolean firstMove = false;
	private boolean inCheck = false; //dont know if this is actually useful

	public King(String name, String color, Position currentPosition) {
		super(name, color, currentPosition);
	}

	//maybe pass boolean if this new spot is in check
	public boolean move(Position np, Board board) {
		//TODO: The king can move one space in any direction, can castle with the rook as long as they have both not been moved
		//And cannot move to or through spaces (when castling) that would out it in check
		//after every move, must check if this put the oppenents king is put into check/checkmate
		
		//TODO : JUST CHECK AFTER A MOVE IS ATTEMPTED IF KING IS IN CHECK AND RETURN ILLEGAL MOVE IF IT STILL IS
		//FOR ALL PIECESSSS	
		
		if(!super.move(np, board)) {
			return false;
		}
		return false;
	}
	
	//called if player tried to move the king over two files
	private boolean castle(Position np, Board board) {
		
		//if the king has already made its first move before, castle is prohibited
		if(this.firstMove == true) {
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
	
	//checks if position is attackable 
	public boolean isInCheck(Position p, Board board) {
		
		if(this.getColor().equals("black")) {
			
		}
		else {
			
		}
		
		return false;
	}
	
	
	
}
