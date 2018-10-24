package pieces;

public class Position {
	
	private int file;
	private int rank;

	public Position(char file, int rank) {
		this.file = file - 97;
		this.rank = rank;
	}
	
	public int getFile() {
		return file;
	}
	
	public int getRank() {
		return rank;
	}
	
	public void setFile(char file) {
		this.file = file;
	}
	
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public boolean equals(Object o) {
		if(o == null || !(o instanceof Position)){
			return false;
		}
		Position p = (Position) o;
		return file == p.file && rank ==  p.rank;
	}
	
}
