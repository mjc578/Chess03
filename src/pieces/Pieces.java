package pieces;

import board.Board;

public abstract class Pieces {
	
	private String name;
	private String color;
	private Position currentPosition;
	
	public Pieces(String name, String color, Position currentPosition) {
		this.name = name;
		this.color = color;
		this.currentPosition = currentPosition;
	}
	
	public String getName() {
		return name;
	}
	
	public Position getPosition() {
		return currentPosition;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setPosition(char c, int r) {
		currentPosition.setFile(c);
		currentPosition.setRank(r);
	}
	
	//checks if piece is leaving confines of the board
	public boolean move(Position np, Board board) {
		if(np.getFile() < 8 && np.getFile() >= 0 && np.getRank() < 8 && np.getRank() >= 0) {
			return true;
		}
		return false;
	}	
	
	//returns true if positions between the current piece's position and its desired position are empty, false otherwise
	//returns true for knight piece as it can jump over anything
	public boolean canMoveThrough(Position np, Board board) {
		int pf1 = currentPosition.getFile(); int pr1 = currentPosition.getRank();
		int pf2 = np.getFile(); int pr2 = np.getRank();
		
		//if a knight, don't need to calculate at all
		if(getName().equals("Night")) {
			return true;
		}
		//the piece is moving up the board or down the board in the same file
		if(pf1 == pf2) {
			//piece only moved up or down one
			if(Math.abs(pr1 - pr2) == 1) {
					return true;
			}
			//the piece is moving up
			if(pr1 < pr2) {
				for(int i = pr1 + 1; i < pr2; i++) {
					//position starts at position above current position and ends right before end position
					//if there is a piece in any of the spots the current piece wants to pass through, return FALSE SHOOT EM DOWN BOY
					if(board.atPosition(new Position(Position.toChar(pf1 + 1) , i + 1)) != null) {
						return false;
					}
				}
			}
			//piece is moving on down
			else {
				for(int i = pr1 - 1; i > pr2; i--) {
					if(board.atPosition(new Position(Position.toChar(pf1 + 1) , i + 1)) != null) {
						return false;
					}
				}
			}
		}
		//piece is moving left or right through the same rank
		else if(pr1 == pr2) {
			//piece only moved left or right one
			if(Math.abs(pf1 - pf2) == 1) {
				return true;
			}
			//the piece is moving right
			if(pf1 < pf2) {
				for(int i = pf1 + 1; i < pf2; i++) {
					if(board.atPosition(new Position(Position.toChar(i + 1) , pr1 + 1)) != null) {
						return false;
					}
				}
			}
			//piece is moving on left
			else {
				for(int i = pf1 - 1; i > pf2; i--) {
					if(board.atPosition(new Position(Position.toChar(i + 1) , pr1 + 1)) != null) {
						return false;
					}
				}
			}
		}
		else if(pf1 != pf2 && pr1 != pr2) {
			//piece only moved one space
			if(Math.abs(pf1 - pf2) == 1 && Math.abs(pr1 - pr2) == 1) {
				return true;
			}
			int i, j;
			//piece moving northeast 
			if(pf1 < pf2 && pr1 < pr2) {
				i = pf1 + 1;
				j = pr1 + 1;
				while(i != pf2 && j != pr2) {
					if(board.atPosition(new Position(Position.toChar(i + 1), j + 1)) != null) {
						return false;
					}
					i++; j++;
				}
			}
			//piece moving southeast 
			else if(pf1 < pf2 && pr1 > pr2) {
				i = pf1 + 1;
				j = pr1 - 1;
				while(i != pf2 && j != pr2) {
					if(board.atPosition(new Position(Position.toChar(i + 1), j + 1)) != null) {
						return false;
					}
					i++; j--;
				}
			}//piece moving southwest 
			else if(pf1 > pf2 && pr1 > pr2) {
				i = pf1 - 1;
				j = pr1 - 1;
				while(i != pf2 && j != pr2) {
					if(board.atPosition(new Position(Position.toChar(i + 1), j + 1)) != null) {
						return false;
					}
					i--; j--;
				}
			}//piece moving northwest 
			else {
				i = pf1 - 1;
				j = pr1 + 1;
				while(i != pf2 && j != pr2) {
					if(board.atPosition(new Position(Position.toChar(i + 1), j + 1)) != null) {
						return false;
					}
					i--; j++;
				}
			}		
		}
		//Nothing was in the way of DOMINATION YEAH!
		return true;
	}

	//returns false if the destination spot has a teammate, as a piece cannot take a teammate
	public boolean isTeammate(Position np, Board board) {
		if(board.atPosition(np) == null) {
			return false;
		}
		else if(board.atPosition(np).getColor().equals(color)) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "" + color.charAt(0) + name.charAt(0);
	}

}