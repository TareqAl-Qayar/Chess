
// A turn is 2 moves, one for white and the other for black.
// A move is 4 characters in the form "d4e6".

/**
 * 
 * @author Tareq Al-Qayar
 * @version 1.0
 * @since 08/11/2021
 *
 */
public class Move {
	
	private String move;
	private static char[] rows = {'a','b','c','d','e','f','g','h'};
	private Square startingSquare, targetSquare;
	private boolean isMoveCapture;

	
	public Move(String move) throws NotAMoveException {
		move.toLowerCase();
		if(isAMove(move.toLowerCase())) {
			this.move = move.toLowerCase();
		}
		else {
			throw new NotAMoveException();
		}	
	}
	
	public Move(Square startingSquare,Square targetSquare, boolean isMovecapture) {
		this.move = startingSquare.toString() +targetSquare.toString();
		this.setStartingSquare(startingSquare);
		this.setTargetSquare(targetSquare);
		this.setMoveCapture(isMovecapture);
	}
	
	private boolean isAMove(String move) {
		if(!isARow(move.charAt(1))|| !isARow(move.charAt(3))) {
			return false;
		}
		if(move.length()!=4) {
			return false;
		}
		
		if(!isAFile(move.charAt(0))||!isAFile(move.charAt(2))) {
			return false;
		}
		
		return true;
	}
	
	
	
	private boolean isAFile(char letter) {
		for(int i = 0 ; i< rows.length ; i++) {
			if(rows[i]==letter) {
				return true;
			}
		}
		return false;
	}
	
	private boolean isARow(char num) {
		if(num >0 && num < 9) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		return move;
	}

	/**
	 * @return the startingSquare
	 */
	public Square getStartingSquare() {
		return startingSquare;
	}

	/**
	 * @param startingSquare the startingSquare to set
	 */
	public void setStartingSquare(Square startingSquare) {
		this.startingSquare = startingSquare;
	}

	/**
	 * @return the targetSquare
	 */
	public Square getTargetSquare() {
		return targetSquare;
	}

	/**
	 * @param targetSquare the targetSquare to set
	 */
	public void setTargetSquare(Square targetSquare) {
		this.targetSquare = targetSquare;
	}

	/**
	 * @return the isMoveCapture
	 */
	public boolean isMoveCapture() {
		return isMoveCapture;
	}

	/**
	 * @param isMoveCapture the isMoveCapture to set
	 */
	public void setMoveCapture(boolean isMoveCapture) {
		this.isMoveCapture = isMoveCapture;
	}

}
