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
public class King extends Piece {

	/**
	 * @param type
	 * @param colour
	 * @param square
	 */
	public King(Colour colour, Square square) {
		super(PieceType.King, colour, square);
	}


	@Override
	public boolean legalMove(Square targetSquare) {
		// TODO cant move into check 
		// TODO castling
		
		if(targetSquare.isBlocked(this)) {
			return false;
		}
		
		
		
		if(targetSquare.isReachable()) {
			return false;
		}
		
		if(Math.abs(targetSquare.getXcoordinate()-this.getSquare().getXcoordinate()) ==1 || Math.abs(targetSquare.getYcoordinate() - this.getSquare().getYcoordinate())==1) {
			return false;
		}
		
		return true;
	}

}
