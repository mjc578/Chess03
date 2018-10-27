package pieces;

public class Position {
	
	private int file;
	private int rank;

	public Position(char file, int rank) {
		this.file = fromChar(file) - 1;
		this.rank = rank - 1;
	}
	
	public int getFile() {
		return file;
	}
	
	public int getRank() {
		return rank;
	}
	
	public void setFile(char file) {
		this.file = fromChar(file);
	}
	
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public static int fromChar(char c) {
		switch(c) {
		
		case 'a': return 1;
		case 'b': return 2;
		case 'c': return 3;
		case 'd': return 4;
		case 'e': return 5;
		case 'f': return 6;
		case 'g': return 7;
		case 'h': return 8;
		default: return 0;
		
		}
	}
	
	public static char toChar(int c) {
		switch(c) {
		
		case 1: return 'a';
		case 2: return 'b';
		case 3: return 'c';
		case 4: return 'd';
		case 5: return 'e';
		case 6: return 'f';
		case 7: return 'g';
		case 8: return 'h';
		default: return 0;
		
		}
	}
	
	public boolean equals(Object o) {
		if(o == null || !(o instanceof Position)){
			return false;
		}
		Position p = (Position) o;
		return file == p.file && rank ==  p.rank;
	}
	
}
