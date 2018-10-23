package chess;

import java.util.Scanner;
import board.Board;

public class Chess {

	public static void main(String[] args) {
		
		//make the board and print it out, TODO: populate first?
		Board board = new Board();
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
