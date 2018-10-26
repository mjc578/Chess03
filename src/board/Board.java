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
	
	//returns true if positions between the current piece's position and its desired position are empty, false otherwise
	//returns true for knight piece
	public boolean canMoveThrough(Position p1, Position p2) {
		int pf1 = p1.getFile(); int pr1 = p1.getRank();
		int pf2 = p2.getFile(); int pr2 = p2.getRank();
		
		//if a knight, don't need to calculate at all
		if(atPosition(p1).getName().equals("Night")) {
			return true;
		}
		//the piece is moving up the board or down the board in the same file
		if(pf1 == pf2) {
			//piece only moved up or down one
			if(Math.abs(pr1 - pr2) == 1) {
				return true;
			}
			//the piece is moving up
			if(pr1 < pr2) {
				for(int i = pr1 + 1; i < pr2; i++) {
					//position starts at position above current position and ends right before end position
					//if there is a piece in any of the spots the current piece wants to pass through, return FALSE SHOOT EM DOWN BOY
					if(atPosition(new Position(Position.toChar(pf1 + 1) , i + 1)) != null) {
						//return false;
					}
				}
			}
			//piece is moving on down
			else {
				for(int i = pr1 - 1; i > pr2; i--) {
					if(atPosition(new Position(Position.toChar(pf1 + 1) , i + 1)) != null) {
						//return false;
					}
				}
			}
		}
		//piece is moving left or right through the same rank
		else if(pr1 == pr2) {
			//piece only moved left or right one
			if(Math.abs(pf1 - pf2) == 1) {
				return true;
			}
			//the piece is moving right
			if(pf1 < pf2) {
				for(int i = pf1 + 1; i < pf2; i++) {
					if(atPosition(new Position(Position.toChar(i + 1) , pr1 + 1)) != null) {
						//return false;
					}
				}
			}
			//piece is moving on left
			else {
				for(int i = pf1 - 1; i > pf2; i--) {
					if(atPosition(new Position(Position.toChar(i + 1) , pr1 + 1)) != null) {
						//return false;
					}
				}
			}
		}
		else if(pf1 != pf2 && pr1 != pr2) {
			//piece only moved one space
			if(Math.abs(pf1 - pf2) == 1 && Math.abs(pr1 - pr2) == 1) {
				return true;
			}
			int i, j;
			//piece moving northeast 
			if(pf1 < pf2 && pr1 < pr2) {
				i = pf1 + 1;
				j = pr1 + 1;
				while(i != pf2 && j != pr2) {
					if(atPosition(new Position(Position.toChar(i + 1), j + 1)) != null) {
						return false;
					}
					i++; j++;
				}
			}
			//piece moving southeast 
			else if(pf1 < pf2 && pr1 > pr2) {
				i = pf1 + 1;
				j = pr1 - 1;
				while(i != pf2 && j != pr2) {
					if(atPosition(new Position(Position.toChar(i + 1), j + 1)) != null) {
						return false;
					}
					i++; j--;
				}
			}//piece moving southwest 
			else if(pf1 > pf2 && pr1 > pr2) {
				i = pf1 - 1;
				j = pr1 - 1;
				while(i != pf2 && j != pr2) {
					if(atPosition(new Position(Position.toChar(i + 1), j + 1)) != null) {
						return false;
					}
					i--; j--;
				}
			}//piece moving northwest 
			else {
				i = pf1 - 1;
				j = pr1 + 1;
				while(i != pf2 && j != pr2) {
					if(atPosition(new Position(Position.toChar(i + 1), j + 1)) != null) {
						return false;
					}
					i--; j++;
				}
			}		
		}
		//Nothing was in the way of DOMINATION YEAH!
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
}
