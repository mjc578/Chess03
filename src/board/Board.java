package board;

import pieces.Pieces;
import pieces.Position;

public class Board {

	private Pieces[][] board; 
	
	public Board() {
		this.board = new Pieces[8][8];
	}
	
	public Pieces[][] getBoard() {
		return board;
	}
	
	//TODO: this probably needs some re-thinking...
	//check if p2 is valid for the piece at p1 to go to
	//somehow check all the spots in between the two spots for bishop, rook, pawns, and queen
	//and determine if there are pieces there cause these pieces cannot jump over other pieces
	public boolean movePiece(Pieces piece, Position p1, Position p2) {
		
		if(!piece.isValidMove(p2)) {
			return false;
		}
		
		//look through board and see if there is a piece there...
		
		return true;
	}
	
	//TODO: still needs to print out pieces
	public void printBoard() {

		for(int j = board.length - 1; j >= -1; j--) {
			for(int i = 0; i <= 8; i++) {
				if((i != 8 && j != -1) && board[i][j] == null) {
					if(i % 2 == 0) {
						if(j % 2 == 0) {
							System.out.print("## ");
						}
						else {
							System.out.print("   ");
						}
					}
					else {
						if(j % 2 != 0) {
							System.out.print("## ");
						}
						else {
							System.out.print("   ");
						}
					}
				}
				else if((i != 8 && j != -1) && board[i][j] != null) {
					System.out.print(board[i][j].toString());
					System.out.print(" ");
				}
				else if(i == 8 || j == -1) {
					if(j == -1 && i + j != 7) {
						System.out.print(" ");
						System.out.print(Character.toChars(97 + i));
						System.out.print(" ");
					}
					else if(i == 8 && i + j != 7) {
						System.out.println(j + 1);
					}
					else if(i == 8 && j == -1) {
						System.out.println(" ");
					}
				}
			}
		}
	}
}
