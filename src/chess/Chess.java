package chess;

import java.util.Scanner;
import board.Board;
import pieces.*;

public class Chess {

	public static void main(String[] args) {
		
		//make the board and print it out, TODO: populate first?
		Board board = new Board();
		
		//populate the board
		//16 blacks:
		
		board.getBoard()[0][7] = new Rook("Rook", "black", new Position('a', 7));
		board.getBoard()[1][7] = new Knight("Night", "black", new Position('b', 7));
		board.getBoard()[2][7] = new Bishop("Bishop", "black", new Position('c', 7));
		board.getBoard()[3][7] = new Queen("Queen", "black", new Position('d', 7));
		board.getBoard()[4][7] = new King("King", "black", new Position('e', 7));
		board.getBoard()[5][7] = new Bishop("Bishop", "black", new Position('f', 7));
		board.getBoard()[6][7] = new Knight("Night", "black", new Position('g', 7));
		board.getBoard()[7][7] = new Rook("Rook", "black", new Position('h', 7));
		
		for(int i = 0; i < board.getBoard().length; i++) {
			board.getBoard()[i][6] = new Pawn("pawn", "black", new Position(Character.toChars(i + 97)[0], 6));
		}
		
		//16 whites:
		
		board.getBoard()[0][0] = new Rook("Rook", "white", new Position('a', 7));
		board.getBoard()[1][0] = new Knight("Night", "white", new Position('b', 7));
		board.getBoard()[2][0] = new Bishop("Bishop", "white", new Position('c', 7));
		board.getBoard()[3][0] = new Queen("Queen", "white", new Position('d', 7));
		board.getBoard()[4][0] = new King("King", "white", new Position('e', 7));
		board.getBoard()[5][0] = new Bishop("Bishop", "white", new Position('f', 7));
		board.getBoard()[6][0] = new Knight("Night", "white", new Position('g', 7));
		board.getBoard()[7][0] = new Rook("Rook", "white", new Position('h', 7));
		
		for(int i = 0; i < board.getBoard().length; i++) {
			board.getBoard()[i][1] = new Pawn("pawn", "white", new Position(Character.toChars(i + 97)[0], 6));
		}
		
		board.printBoard();
		
		//just an idea, set states based on user input and conditions of game
		boolean blackTurn = true;
		boolean whiteTurn = false;
		boolean drawRequested = false;
		boolean check = false;
		boolean checkmate = false;
		boolean resign = false;
	
		Scanner sc = new Scanner(System.in);
		String input;
		
		
		while(sc.hasNextLine()) {
			
			input = sc.nextLine();
			
			//if player entered reisgn, break, then check after loop who's turn it was
			if(input.equals("resign")) {
				resign = true;
				break;
			}
			
			
		}
				
		sc.close();
		
	}

}
