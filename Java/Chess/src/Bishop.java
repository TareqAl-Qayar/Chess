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
		//check if way is free.
		if(isWayFree(targetSquare) == false) {
			return false;
		}
		//check if its blocked by another piece of the same colour.
		if(targetSquare.isBlocked(this)) {
			return false;
		}
		// to check if it is on the same diagonal.
		if(Math.abs(targetSquare.getXcoordinate() - this.getSquare().getXcoordinate()) != Math.abs(targetSquare.getYcoordinate() - this.getSquare().getYcoordinate())) {
			return false;
		}
		return true;
	}
	
	/**
	 * Checks if the square between the current Square and the target Square are occupied by another piece.
	 * @param targetSquare
	 * @return false if there is a piece on a square between current square and targetSquare.
	 */
	public boolean isWayFree(Square targetSquare) {
		// TODO daigonaly check if the squares in the way are free 
		int startX = Math.min(targetSquare.getXcoordinate(), this.getSquare().getXcoordinate());
		int endX = Math.max(targetSquare.getXcoordinate(), this.getSquare().getXcoordinate());
		
		if(targetSquare.getYcoordinate() > this.getSquare().getYcoordinate()) {
			for(int i = startX +1 ; i < endX ; i++) {
				//if(Board.getSquare(x, y))
			}
		}
		
		
		
//		if(targetSquare.getXcoordinate() > this.getSquare().getXcoordinate() && targetSquare.getYcoordinate() > this.getSquare().getYcoordinate()) {
//			for(int i = this.getSquare().getXcoordinate()+1 ; i <= targetSquare.getXcoordinate(); i++) {
//				if(Board.getSquare(i,i).isOccupied()) {
//					return false;
//				}
//			}
//		}
//		if(targetSquare.getXcoordinate() < this.getSquare().getXcoordinate() && targetSquare.getYcoordinate() < this.getSquare().getYcoordinate()) {
//			for(int i = this.getSquare().getXcoordinate()+1 ; i >= targetSquare.getXcoordinate(); i--) {
//				if(Board.getSquare(i,i).isOccupied()) {
//					return false;
//				}
//			}
//		}
		return true;
	}

}
