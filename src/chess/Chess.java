/**
 * Class to host the strategic playings of the Chess
 * 
 * @author Michael Chapman
 * @author Krishna Mistry
 */

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
		//make the board and print it out
		Board board = new Board();
		//populate the board
		board.populate();
		board.printBoard();
		System.out.print("\nWhite's move: ");
				
		//scan for input
		Scanner sc = new Scanner(System.in);
		String input;
		
		while(sc.hasNextLine()) {
			//get the user's input
			input = sc.nextLine();
			String inputs[] = input.split(" ");
		
			//resign
			if(inputs.length == 1) {
				if(inputs[0].equals("resign")) {
					if(blackTurn) {
						System.out.println("\nWhite wins");
					}
					else {
						System.out.println("\nBlack wins");
					}
					break;
				}
				else {
					if(inputs[0].equals("draw")) {
						if(drawRequested) {
							break;
						}
						else {
							printIllegal(whiteTurn);
							continue;
						}
					}
				}
			}
			
			char pf1 = inputs[0].charAt(0);
			int pr1 = inputs[0].charAt(1) - '0';
			char pf2 = inputs[1].charAt(0);
			int pr2 = inputs[1].charAt(1) - '0';
			
			if(pf1 < 'a' || pf1 > 'h' || pf2 < 'a' || pf2 > 'h' || pr1 < 1 || pr1 > 8 || pr2 < 1 || pr2 > 8){
				printIllegal(whiteTurn);
				continue;
			}
			if(pf1 == pf2 && pr1 == pr2) {
				printIllegal(whiteTurn);
				continue;
			}
			
			Position p1 = new Position(pf1, pr1);
			Position p2 = new Position(pf2, pr2);
			
			if(board.atPosition(p2) != null && board.atPosition(p2).getName().equals("King")) {
				printIllegal(whiteTurn);
				continue;
			}
			
			if(board.atPosition(p1) == null) {
				printIllegal(whiteTurn);
				continue;
			}
			
			//player may only touch their own pieces
			if(blackTurn) {
				if(board.atPosition(p1).getColor().equals("white")) {
					printIllegal(whiteTurn);
					continue;
				}
			}
			else {
				if(board.atPosition(p1).getColor().equals("black")) {
					printIllegal(whiteTurn);
					continue;
				}
			}
			
			//just a simple move
			if(inputs.length == 2) {
				boolean can = board.atPosition(p1).move(p2, board);
				if(!can) {
					printIllegal(whiteTurn);
					continue;
				}
				else {
					System.out.println("");
					board.printBoard();
					drawRequested = false;
				}
			}
			
			if(inputs.length == 3) {
				//can be request for draw, can be confirm draw, can be promotion specifier
				String three = inputs[2];
				if(three.equals("draw?")) {
					boolean can = board.atPosition(p1).move(p2, board);
					if(!can) {
						printIllegal(whiteTurn);
						continue;
					}
					else {
						System.out.println("");
						board.printBoard();
					}
					drawRequested = true;
				}
				else {
					//piece must be a pawn for promotion
					if(!board.atPosition(p1).getName().equals("pawn")) {
						printIllegal(whiteTurn);
						continue;
					}
					//is pawn
					else {
						Pawn p = (Pawn) board.atPosition(p1);
						boolean can = p.promotionMove(inputs[2], p2, board);
						if(!can) {
							printIllegal(whiteTurn);
							continue;
						}
						else {
							System.out.println("");
							board.printBoard();
							drawRequested = false;
						}
					}
				}
			}
			
			if(whiteTurn) {
				System.out.println("");
				//check if white put black into check or checkmate, so check if black king is in check/mate
				King k = (King) board.atPosition(board.getPositionKing("black", board));
				if(k.isInCheck(board)) {
					//if king is in fact in hecking flabbergastingly checkidy check check checkied, check if its also checkmated and if it
					//is, end the game, but if no, print check and proceed as normal
					if(k.isCheckmated(board)) {
						System.out.println("Checkmate");
						System.out.println("\nWhite wins");
						break;
					}
					System.out.println("Check\n");
				}
				else {
					if(board.isStalemate("black")){
						System.out.println("Stalemate");
						break;
					}
				}
				whiteTurn = false;
				blackTurn = true;
			}
			else {
				System.out.println("");
				//check if black put white into check or checkmate
				King k = (King) board.atPosition(board.getPositionKing("white", board));
				if(k.isInCheck(board)) {
					if(k.isCheckmated(board)) {
						System.out.println("Checkmate");
						System.out.println("\nBlack wins");
						break;
					}
					System.out.println("Check\n");
				}
				else {
					if(board.isStalemate("white")){
						System.out.println("Stalemate");
						break;
					}
				}
				blackTurn = false;
				whiteTurn = true;
			}
			if(whiteTurn) {
				System.out.print("White's move: ");
			}
			else {
				System.out.print("Black's move: ");
			}			
			board.maintainPawn();
		}
		sc.close();
	}
	
	/**
	 * Method to print the repetitive "Illegal move, try again" prompt
	 * @param whiteTurn Prints out who's turn it is depending on boolean
	 */
	public static void printIllegal(boolean whiteTurn) {
		System.out.println("");
		System.out.println("Illegal move, try again");
		System.out.println("");
		if(whiteTurn) {
			System.out.print("White's move: ");
			return;
		}
		System.out.print("Black's move: ");
	}
}