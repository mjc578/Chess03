package pieces;

public abstract class Pieces {
	
	private String name;
	private String color;
	private Position currentPosition;
	
	public Pieces(String name, String color, Position currentPosition) {
		this.name = name;
		this.color = color;
		this.currentPosition = currentPosition;
	}
	
	public Position getPosition() {
		return currentPosition;
	}
	
	public boolean isValidMove(Position np) {
		if(np.getFile() < 8 && np.getFile() >= 0 && np.getRank() < 8 && np.getRank() >= 0) {
			return true;
		}
		return false;
	}	
	
	@Override
	public String toString() {
		return "" + color.charAt(0) + name.charAt(0);
	}
}
