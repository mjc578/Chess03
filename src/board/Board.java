package board;

import java.util.ArrayList;

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
		board[0][7] = new Rook("Rook", "black", new Position('a', 8));
		board[1][7] = new Knight("Night", "black", new Position('b', 8));
		board[2][7] = new Bishop("Bishop", "black", new Position('c', 8));
		board[3][7] = new Queen("Queen", "black", new Position('d', 8));
		board[4][7] = new King("King", "black", new Position('e', 8));
		board[5][7] = new Bishop("Bishop", "black", new Position('f', 8));
		board[6][7] = new Knight("Night", "black", new Position('g', 8));
		board[7][7] = new Rook("Rook", "black", new Position('h', 8));
		
		for(int i = 0; i < board.length; i++) {
			board[i][6] = new Pawn("pawn", "black", new Position(Character.toChars(i + 97)[0], 7));
		}
		
		//16 whites:
		
		board[0][0] = new Rook("Rook", "white", new Position('a', 1));
		board[1][0] = new Knight("Night", "white", new Position('b', 1));
		board[2][0] = new Bishop("Bishop", "white", new Position('c', 1));
		board[3][0] = new Queen("Queen", "white", new Position('d', 1));
		board[4][0] = new King("King", "white", new Position('e', 1));
		board[5][0] = new Bishop("Bishop", "white", new Position('f', 1));
		board[6][0] = new Knight("Night", "white", new Position('g', 1));
		board[7][0] = new Rook("Rook", "white", new Position('h', 1));
		
		for(int i = 0; i < board.length; i++) {
			board[i][1] = new Pawn("pawn", "white", new Position(Character.toChars(i + 97)[0], 2));
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

	public void updateBoard(Pieces p, Position np) {
		board[np.getFile()][np.getRank()] = atPosition(p.getPosition());
		board[p.getPosition().getFile()][p.getPosition().getRank()] = null;
		//update position field
		p.setPosition(Position.toChar(np.getFile()), np.getRank());
	}
	
	public void maintainPawn() {
		for (int i = 0; i<8; i++) {
			for (int j = 0; j<8; j++) {
				if(board[i][j] != null) {
					if(board[i][j].getName().equals("pawn")) {
						Pawn p = (Pawn) board[i][j];
						if(p.getCount() == 0 && p.getJustMovedTwo() == true) {
							p.setCount(1);
						}
						else {
							p.setJustMovedTwo(false);
						}
					}
				}
			}
		}
	}
	
	
}
