package board;

import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Pieces;
import pieces.Position;
import pieces.Queen;
import pieces.Rook;

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
	public boolean movePiece(Position p1, Position p2) {
		
		if(!atPosition(p1).isValidMove(p2)) {
			return false;
		}
		
		board[p2.getFile()][p2.getRank()] = atPosition(p1);
		board[p1.getFile()][p1.getRank()] = null;
		
		//look through board and see if there is a piece there...
		
		return true;
	}
	
	//returns the piece that is at the position on the board specified
	public Pieces atPosition(Position p) {
		int file = p.getFile();
		int rank = p.getRank();
		if(board[file][rank] == null) {
			return null;
		}
		else {
			return board[file][rank];
		}
	}
	//populates board with pieces at starting positions
	public void populate() {
		board[0][7] = new Rook("Rook", "black", new Position('a', 7));
		board[1][7] = new Knight("Night", "black", new Position('b', 7));
		board[2][7] = new Bishop("Bishop", "black", new Position('c', 7));
		board[3][7] = new Queen("Queen", "black", new Position('d', 7));
		board[4][7] = new King("King", "black", new Position('e', 7));
		board[5][7] = new Bishop("Bishop", "black", new Position('f', 7));
		board[6][7] = new Knight("Night", "black", new Position('g', 7));
		board[7][7] = new Rook("Rook", "black", new Position('h', 7));
		
		for(int i = 0; i < board.length; i++) {
			board[i][6] = new Pawn("pawn", "black", new Position(Character.toChars(i + 97)[0], 6));
		}
		
		//16 whites:
		
		board[0][0] = new Rook("Rook", "white", new Position('a', 7));
		board[1][0] = new Knight("Night", "white", new Position('b', 7));
		board[2][0] = new Bishop("Bishop", "white", new Position('c', 7));
		board[3][0] = new Queen("Queen", "white", new Position('d', 7));
		board[4][0] = new King("King", "white", new Position('e', 7));
		board[5][0] = new Bishop("Bishop", "white", new Position('f', 7));
		board[6][0] = new Knight("Night", "white", new Position('g', 7));
		board[7][0] = new Rook("Rook", "white", new Position('h', 7));
		
		for(int i = 0; i < board.length; i++) {
			board[i][1] = new Pawn("pawn", "white", new Position(Character.toChars(i + 97)[0], 6));
		}
	}
	
	//method prints out the chess board
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
