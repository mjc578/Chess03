package board;

import pieces.Pieces;

public class Board {

	Pieces[][] board; 
	
	public Board() {
		this.board = new Pieces[8][8];
	}
	
	public void printBoard() {
		for(int i = 0; i <= board.length; i++) {
			for(int j = 0; j <= board.length; j++) {
				if((i != 8 && j != 8) && board[i][j] == null) {
					if(i % 2 != 0) {
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
				else if(i == 8 || j == 8) {
					if(i == 8 && i != j) {
						System.out.print(" ");
						System.out.print(Character.toChars(j + 97));
						System.out.print(" ");
					}
					else if(j == 8 && j != i) {
						System.out.println(8 - i);
					}
					else if(i == 8 && j == 8) {
						System.out.println(" ");
					}
				}
			}
		}
	}
}
