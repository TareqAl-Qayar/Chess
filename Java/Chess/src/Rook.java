/**
 * 
 * @author otari
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
	
	
	/**
	 *
	 */
	@Override
	public void move() {
		

	}

	/**
	 *
	 */
	@Override
	public boolean legalMove(Square targetSquare) {
		
		//castling 
		// castling as method for the king piece
		
		if(targetSquare.getXcooridiante() == this.getSquare().getXcooridiante() || targetSquare.getYcoordinate() == this.getSquare().getYcoordinate()) {
			if(targetSquare.isOccupied() == false) {
				return true;
			}
			if(targetSquare.isOccupied() && targetSquare.getPiece().getColour() != this.getColour()) {
				this.capturePiece(targetSquare);
				return true;
			}
		}
		return false;
	}

}
