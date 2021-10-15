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
	public void move() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean legalMove(Square targetSquare) {
		if(targetSquare.isBlocked(this)) {
			return false;
		}
		
		
		return false;
	}

}
