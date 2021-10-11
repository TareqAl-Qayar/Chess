/**
 * 
 */

/**
 * @author otari
 *
 */
public class King extends Piece {

	/**
	 * @param type
	 * @param colour
	 * @param square
	 */
	public King(PieceType type, Colour colour, Square square) {
		super(type, colour, square);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean legalMove(Square targetSquare) {
		// TODO cant move into check 
		// TODO castling
		return false;
	}

}
