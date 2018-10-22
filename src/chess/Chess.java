package chess;

import java.util.Scanner;

public class Chess {

	public static void main(String[] args) {
		
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
		
		System.out.println('a' - 96);
		
		sc.close();
		
	}

}
