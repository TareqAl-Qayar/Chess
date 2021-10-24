/**
 * 
 */

/**
 * @author otari
 *
 */
public class Queen extends Piece {

	/**
	 * @param type
	 * @param colour
	 * @param square
	 */
	public Queen(Colour colour, Square square) {
		super(PieceType.Queen, colour, square);
	}

	
	@Override
	public boolean legalMove(Square targetSquare) {
		if(targetSquare.isBlocked(this)) {
			return false;
		}

		if(targetSquare.getXcoordinate() == this.getSquare().getXcoordinate() || targetSquare.getYcoordinate() == this.getSquare().getYcoordinate()) {
			if(isWayFreeParallel(targetSquare)) {
				return true;
			}
		}

		if(Math.abs(targetSquare.getXcoordinate() - this.getSquare().getXcoordinate()) == Math.abs(targetSquare.getYcoordinate() - this.getSquare().getYcoordinate())) {
			if(isWayFreeDiagonal(targetSquare)) {
				return true;
			}
		}


		return false;
	}

	/**
	 * Checks if the way between current Square and targetSquare is free of pieces, This method is used when making a horizontal or vertical move.
	 * @param targetSquare
	 * @return true if there are no pieces on the squares between current and targetSquare, otherwise false.
	 */
	private boolean isWayFreeParallel(Square targetSquare) {
		if(targetSquare.getXcoordinate() - this.getSquare().getXcoordinate() !=0) {
			int start = Math.min(this.getSquare().getXcoordinate(), targetSquare.getXcoordinate());
			int end = Math.max(this.getSquare().getXcoordinate(), targetSquare.getXcoordinate());
			for(int i = start+1 ; i < end; i++) {
				if(Board.getSquare(i, targetSquare.getYcoordinate()).isOccupied()) {
					return false;
				}
			}
		}
		if(targetSquare.getYcoordinate()-this.getSquare().getYcoordinate() !=0) {
			int start = Math.min(this.getSquare().getYcoordinate(), targetSquare.getYcoordinate());
			int end = Math.max(this.getSquare().getYcoordinate(), targetSquare.getYcoordinate());
			for(int i = start+1 ; i < end ; i ++) {
				if(Board.getSquare(targetSquare.getXcoordinate(), i).isOccupied()) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * Checks if the way between current Square and targetSquare is free of pieces, This method is used when making a diagonal move.
	 * @param targetSquare
	 * @return true if there are no pieces on the squares between current and targetSquare, otherwise false.
	 */
	private boolean isWayFreeDiagonal(Square targetSquare) {
		
		// up and right.
		if(targetSquare.getXcoordinate() > this.getSquare().getXcoordinate() && targetSquare.getYcoordinate() > this.getSquare().getYcoordinate()) {
			int yCounter = this.getSquare().getYcoordinate();
			int xCounter = this.getSquare().getXcoordinate();
			while ( xCounter < targetSquare.getXcoordinate()) {
				xCounter ++;
				yCounter ++;
				if(Board.getSquare(xCounter, yCounter).isOccupied()) {
					return false;
				}
			}
		}
		
		// up and left
		if(targetSquare.getXcoordinate() < this.getSquare().getXcoordinate() && targetSquare.getYcoordinate() > this.getSquare().getYcoordinate()) {
			int yCounter = this.getSquare().getYcoordinate();
			int xCounter = this.getSquare().getXcoordinate();
			while ( xCounter > targetSquare.getXcoordinate()) {
				xCounter --;
				yCounter ++;
				if(Board.getSquare(xCounter, yCounter).isOccupied()) {
					return false;
				}
			}
		}
		
		// down and right
		if(targetSquare.getXcoordinate() > this.getSquare().getXcoordinate() && targetSquare.getYcoordinate() < this.getSquare().getYcoordinate()) {
			int yCounter = this.getSquare().getYcoordinate();
			int xCounter = this.getSquare().getXcoordinate();
			while ( xCounter < targetSquare.getXcoordinate()) {
				xCounter ++;
				yCounter --;
				if(Board.getSquare(xCounter, yCounter).isOccupied()) {
					return false;
				}
			}
		}

		// down and left.
		if(targetSquare.getXcoordinate() < this.getSquare().getXcoordinate() && targetSquare.getYcoordinate() < this.getSquare().getYcoordinate()) {
			int yCounter = this.getSquare().getYcoordinate();
			int xCounter = this.getSquare().getXcoordinate();
			while ( xCounter > targetSquare.getXcoordinate()) {
				xCounter --;
				yCounter --;
				if(Board.getSquare(xCounter, yCounter).isOccupied()) {
					return false;
				}
			}
		}
		return true;
	}

}
