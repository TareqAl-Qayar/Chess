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

	@Override
	public boolean legalMove(Square targetSquare) {
		if(targetSquare.isBlocked(this)) {
			return false;
		}
		
		if(isWayFree(targetSquare) == false) {
			return false;
		}
		
		if(targetSquare.getXcoordinate() == this.getSquare().getXcoordinate() || targetSquare.getYcoordinate() == this.getSquare().getYcoordinate()) {
			if(targetSquare.isOccupied() == false) {
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
		if(targetSquare.getXcoordinate() - this.getSquare().getXcoordinate() !=0) {
			int start = Math.min(this.getSquare().getXcoordinate(), targetSquare.getXcoordinate());
			int end = Math.max(this.getSquare().getXcoordinate(), targetSquare.getXcoordinate());
			for(int i = start+1 ; i < end; i++) {
				if(Board.getSquare(i, targetSquare.getYcoordinate()).isOccupied()) {
					return false;
				}
			}
		}
		if(targetSquare.getYcoordinate()-this.getSquare().getYcoordinate() !=0) {
			int start = Math.min(this.getSquare().getYcoordinate(), targetSquare.getYcoordinate());
			int end = Math.max(this.getSquare().getYcoordinate(), targetSquare.getYcoordinate());
			for(int i = start+1 ; i < end ; i ++) {
				if(Board.getSquare(targetSquare.getXcoordinate(), i).isOccupied()) {
					return false;
				}
			}
		}
		
		return true;
	}

}
