
/**
 * 
 * @author Tareq Al-Qayar
 * @version 1.0
 * @since 28/10/2021
 *
 */
public class Knight extends Piece {

	public Knight(Colour colour, Square square) {
		super(PieceType.Knight, colour, square);
	}


	@Override
	public boolean legalMove(Square targetSquare) {
		if(targetSquare.isOccupied() == false || targetSquare.getPiece().getColour() != this.getColour()) { // TODO this might throw A NullPointerException if the square is empty, maybe put it in its own if.
			if((Math.abs(targetSquare.getXcoordinate()-getXCoordinate())==1 && Math.abs(targetSquare.getYcoordinate()-getYCoordinate())==2)
				||(Math.abs(targetSquare.getXcoordinate()-getXCoordinate())==2 && Math.abs(targetSquare.getYcoordinate()-getYCoordinate())==1)) {
				return true;
			}
		}
		return false;
	}

}
