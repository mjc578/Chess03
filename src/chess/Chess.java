package chess;

import java.util.Scanner;
import board.Board;
import pieces.*;

public class Chess {

	public static void main(String[] args) {
		
		//just an idea, set states based on user input and conditions of game
		boolean blackTurn = false;
		boolean whiteTurn = true;
		boolean drawRequested = false;
		boolean check = false;
		boolean checkmate = false;
		boolean resign = false;
		
		//make the board and print it out
		Board board = new Board();
		//populate the board
		board.populate();
		board.printBoard();
		System.out.println("\nWhite move: \n");
				
		//scan for input
		Scanner sc = new Scanner(System.in);
		String input;
		
		while(sc.hasNextLine()) {
			
			//get the user's input
			input = sc.nextLine();
			String inputs[] = input.split(" ");
		
			//resign
			if(inputs.length == 1) {
				
			}
			
			//only moves past this point
			
			char pf1 = inputs[0].charAt(0);
			int pr1 = inputs[0].charAt(1) - '0';
			char pf2 = inputs[1].charAt(0);
			int pr2 = inputs[1].charAt(1) - '0';
			
			if(pf1 < 'a' || pf1 > 'h' || pf2 < 'a' || pf2 > 'h' || pr1 < 1 || pr1 > 8 || pr2 < 1 || pr2 > 8){
				System.out.println("Invalid move entry, try again");
				continue;
			}
			
			Position p1 = new Position(pf1, pr1);
			Position p2 = new Position(pf2, pr2);
			
			if(board.atPosition(p1) == null) {
				System.out.println("Invalid move, try again");
				continue;
			}
			
			
			//TODO: check if entered position to move away from is a piece that belongs to the player
			
			//just a simple move
			if(inputs.length == 2) {
				boolean can = board.atPosition(p1).move(p2, board);
				if(!can) {
					System.out.println("Illegal move, try again");
					continue;
				}
				else {
					board.printBoard();
				}
			}
			
			
			if(whiteTurn) {
				System.out.println("\nBlack move: \n");
				whiteTurn = false;
				blackTurn = true;
			}
			else {
				System.out.println("\nWhite move: \n");
				blackTurn = false;
				whiteTurn = true;
			}
			
			board.maintainPawn();
		}
		sc.close();
	}

}
