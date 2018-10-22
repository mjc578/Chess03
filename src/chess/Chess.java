package chess;

import java.util.Scanner;

public class Chess {

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		
		if (input.equals("resign")) {
			//TODO: print black/white wins
		}
		else if (input.equals("draw?")) {
			//TODO: 
		}
		else if (input.equals("draw")) {
			//TODO: 
		}
		else {
			String oldPosition = input.substring(0,2);
			String newPosition = input.substring(3,5);
			System.out.println(oldPosition + " " + newPosition);
		}
		sc.close();
		
	}

}
