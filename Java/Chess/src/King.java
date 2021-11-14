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
		
		if((this.differenceInX(targetSquare)==1&&this.differenceInY(targetSquare)==1)||(this.differenceInX(targetSquare)+this.differenceInY(targetSquare)== 1)) {
			return true;
		}
		
		return false;
	}


	@Override
	public void findAttackedSquares() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String toUnicode() {
		if(getColour()==Colour.Black) {
			return "♚";
		}
		return "♔";
	}

}
