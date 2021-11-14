/**
 * 
 * @author Tareq Al-Qayar
 * @version 1.0
 * @since 28/10/2021
 *
 */
public class Rook extends Piece {

	/**
	 * @param colour
	 * @param square
	 */
	public Rook(Colour colour, Square square) {
		super(PieceType.Rook,colour,square);
	}



	@Override
	public boolean legalMove(Square targetSquare) {
		if(targetSquare.isBlocked(this)) {
			return false;
		}
		
		if(targetSquare.getXcoordinate() == getXCoordinate() || targetSquare.getYcoordinate() == getYCoordinate()) {
			if(isWayFree(targetSquare)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if the square between the current Square and the target Square are occupied by another piece.
	 * @param targetSquare
	 * @return false if there is a piece on a square between current square and targetSquare.
	 */
	public boolean isWayFree(Square targetSquare) {
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



	@Override
	public void findAttackedSquares() {
		getAttackedSquares().clear();
		
		//TODO can be optimised.
		for(int i = 1; i<=8;i++) {
			Square currentSquareX= Board.getSquare(i, getYCoordinate());
			Square currentSquareY=Board.getSquare(getXCoordinate(), i);
			if(isWayFree(currentSquareX)&&(!currentSquareX.isOccupied()||currentSquareX.OccupiedByOppositeColour(getColour()))) {
				getAttackedSquares().add(currentSquareX);
			}
			if(isWayFree(currentSquareY)&&(!currentSquareY.isOccupied()||currentSquareY.OccupiedByOppositeColour(getColour()))) {
				getAttackedSquares().add(currentSquareY);
			}
		}
		
	}



	@Override
	public String toUnicode() {
		if(getColour()==Colour.Black) {
			return "♜";
		}
		return "♖";
	}

}
