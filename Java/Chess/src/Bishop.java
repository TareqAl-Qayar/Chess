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
	public Bishop(PieceType type, Colour colour, Square square) {
		super(type, colour, square);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean legalMove(Square targetSquare) {
		//if(targetSquare.)
		return false;
	}
	
	public boolean isWayFree() {
		return false;
	}

}
