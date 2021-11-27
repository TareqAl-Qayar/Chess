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

		if(targetSquare.getXcoordinate() == getXCoordinate() || targetSquare.getYcoordinate() == getYCoordinate()) {
			if(isWayFreeParallel(targetSquare)) {
				return true;
			}
		}

		if(Math.abs(targetSquare.getXcoordinate() - getXCoordinate()) == Math.abs(targetSquare.getYcoordinate() - getYCoordinate())) {
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
		if(targetSquare.getXcoordinate() - getXCoordinate() !=0) {
			int start = Math.min(getXCoordinate(), targetSquare.getXcoordinate());
			int end = Math.max(getXCoordinate(), targetSquare.getXcoordinate());
			for(int i = start+1 ; i < end; i++) {
				if(Board.getSquare(i, targetSquare.getYcoordinate()).isOccupied()) {
					return false;
				}
			}
		}
		if(targetSquare.getYcoordinate()-getYCoordinate() !=0) {
			int start = Math.min(getYCoordinate(), targetSquare.getYcoordinate());
			int end = Math.max(getYCoordinate(), targetSquare.getYcoordinate());
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
		attackedSquaresLoopParallel(8,1,true);
		attackedSquaresLoopParallel(1,-1,true);
		attackedSquaresLoopParallel(8,1,false);
		attackedSquaresLoopParallel(1,-1,false);
		attackedSquaresLoopDiagonal(8,8,1,1);
		attackedSquaresLoopDiagonal(8,1,1,-1);
		attackedSquaresLoopDiagonal(1,8,-1,1);
		attackedSquaresLoopDiagonal(1,1,-1,-1);
		//System.out.println(this.toString()+ " is attacking " + this.getAttackedSquares().toString());
	}




	@Override
	public String toUnicode() {
		if(getColour()==Colour.Black) {
			return "♛";
		}
		return "♕";
	}

}
