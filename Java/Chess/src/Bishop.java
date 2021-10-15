/**
 * 
 */

/**
 * @author otari
 *
 */
public class Bishop extends Piece {

	/**
	 * @param type
	 * @param colour
	 * @param square
	 */
	public Bishop(Colour colour, Square square) {
		super(PieceType.Bishop, colour, square);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean legalMove(Square targetSquare) {
		//check if way is free.
		if(isWayFree(targetSquare) == false) {
			return false;
		}
		// to check if it is on the same diagonal.
		if(Math.abs(targetSquare.getXcoordinate() - this.getSquare().getXcoordinate()) != Math.abs(targetSquare.getYcoordinate() - this.getSquare().getYcoordinate())) {
			return false;
		}
		//check if its blocked by another piece of the same colour.
		if(targetSquare.isBlocked(this)) {
			return false;
		}

		return true;
	}

	/**
	 * Checks if the square between the current Square and the target Square are occupied by another piece.
	 * @param targetSquare
	 * @return false if there is a piece on a square between current square and targetSquare.
	 */
	public boolean isWayFree(Square targetSquare) {

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
