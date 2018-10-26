package chess;

import java.util.Scanner;
import board.Board;
import pieces.*;

public class Chess {

	public static void main(String[] args) {
		
		//make the board and print it out, TODO: populate first?
		Board board = new Board();
		
		//populate the board
		board.populate();
		
		board.printBoard();
		
		//just an idea, set states based on user input and conditions of game
		boolean blackTurn = false;
		boolean whiteTurn = true;
		boolean drawRequested = false;
		boolean check = false;
		boolean checkmate = false;
		boolean resign = false;
	
		Scanner sc = new Scanner(System.in);
		String input;
		
		/*
		while(sc.hasNextLine()) {
			
			input = sc.nextLine();
			String inputs[] = input.split(" ");
			
			
			
			//if player entered resign, break, then check after loop who's turn it was
			if(input.equals("resign")) {
				resign = true;
				break;
			}
			
			
		}*/
		
		System.out.println("");
		Position p1 = new Position('h', 1);
		Position p2 = new Position('a', 8);
		board.canMoveThrough(p1, p2);
		System.out.println("");
		//System.out.println("Position is: (" + p.getFile() + "," + p.getRank() + ")" );

		/*
		Position p1 = new Position('g', 1);		
		System.out.println(board.atPosition(p1));
		Position p2 = new Position('e', 2);
		boolean move = board.movePiece(p1, p2);
		
		if(move) {
			board.printBoard();
		}
		else {
			System.out.println("Illegal Move");
		}
		*/
		sc.close();
		
	}

}
