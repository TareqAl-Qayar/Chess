/**
 * 
 */

/**
 * 
 * @author Tareq Al-Qayar
 * @version 1.0
 * @since 28/10/2021
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
	}


	@Override
	public boolean legalMove(Square targetSquare) {
		//check if way is free.
		if(isWayFree(targetSquare) == false) {
			return false;
		}
		// to check if it is on the same diagonal.
		if(this.differenceInX(targetSquare) != this.differenceInY(targetSquare)) {
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
		if(targetSquare.getXcoordinate() > getXCoordinate() && targetSquare.getYcoordinate() > getYCoordinate()) {
			int yCounter = getYCoordinate();
			int xCounter = getXCoordinate();
			while ( xCounter < targetSquare.getXcoordinate()-1) {
				xCounter ++;
				yCounter ++;
				if(Board.getSquare(xCounter, yCounter).isOccupied()) {
					return false;
				}
			}
		}

		// up and left
		if(targetSquare.getXcoordinate() < getXCoordinate() && targetSquare.getYcoordinate() > getYCoordinate()) {
			int yCounter = getYCoordinate();
			int xCounter = getXCoordinate();
			while ( xCounter > targetSquare.getXcoordinate()+1) {
				xCounter --;
				yCounter ++;
				if(Board.getSquare(xCounter, yCounter).isOccupied()) {
					return false;
				}
			}
		}


		// down and right
		if(targetSquare.getXcoordinate() > getXCoordinate() && targetSquare.getYcoordinate() < getYCoordinate()) {
			int yCounter = getYCoordinate();
			int xCounter = getXCoordinate();
			while ( xCounter < targetSquare.getXcoordinate()-1) {
				xCounter ++;
				yCounter --;
				if(Board.getSquare(xCounter, yCounter).isOccupied()) {
					return false;
				}
			}
		}

		// down and left.
		if(targetSquare.getXcoordinate() < getXCoordinate() && targetSquare.getYcoordinate() < getYCoordinate()) {
			int yCounter = getYCoordinate();
			int xCounter = getXCoordinate();
			while ( xCounter > targetSquare.getXcoordinate()+1) {
				xCounter --;
				yCounter --;
				if(Board.getSquare(xCounter, yCounter).isOccupied()) {
					return false;
				}
			}
		}
		return true;
	}

	
	

	@Override
	public void findAttackedSquares() {
		this.getAttackedSquares().clear();
		attackedSquaresLoopDiagonal(8,8,1,1);
		attackedSquaresLoopDiagonal(8,1,1,-1);
		attackedSquaresLoopDiagonal(1,8,-1,1);
		attackedSquaresLoopDiagonal(1,1,-1,-1);
		//System.out.println(this.toString()+ " is attacking " + this.getAttackedSquares().toString());
	}
	



	@Override
	public String toUnicode() {
		if(getColour()==Colour.Black) {
			return "♝";
		}
		return "♗";
	}

}
