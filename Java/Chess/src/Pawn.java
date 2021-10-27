
/**
 * @author otari
 *
 */
public class Pawn extends Piece {

	/**
	 * @param colour
	 * @param square
	 */
	public Pawn(Colour colour, Square square) {
		super(PieceType.Pawn, colour, square);
	}



	/**
	 *
	 */
	@Override
	public boolean legalMove(Square targetSquare) {
		if(targetSquare.isOccupied() == false && (targetSquare.getYcoordinate() ==8||targetSquare.getYcoordinate() ==1) ) {
			promote(promoteTo());
			return true;
		}
		if(this.getColour() == Colour.White) {
			return legalMoveWhite(targetSquare);
		}
		return legalMoveBlack(targetSquare);

		// TODO en passant

	}


	/**
	 * @param targetSquare
	 * @return
	 */
	private boolean legalMoveWhite(Square targetSquare) {

		//Forward move
		if(targetSquare.isOccupied() == false) {
			//One square forward
			if((targetSquare.getYcoordinate() == this.getSquare().getYcoordinate()+1)&&(targetSquare.getXcoordinate()==this.getSquare().getXcoordinate())) {
				return true;
			}
			//First move 2 Squares forward
			if((targetSquare.getYcoordinate() == this.getSquare().getYcoordinate()+2 && this.getSquare().getYcoordinate() ==2&&(targetSquare.getXcoordinate()==this.getSquare().getXcoordinate()))) {
				return true;
			}
		}
		
		// TODO fix this, it is in the abstract class now.
		//Capturing 

		if((targetSquare.isOccupied()&&targetSquare.getPiece().getColour()!=this.getColour() && targetSquare.getYcoordinate() == this.getSquare().getYcoordinate()+1 )&& (Math.abs(targetSquare.getXcoordinate()-this.getSquare().getXcoordinate())==1)){
			return true;
		}
		return false;
	}

	/**
	 * @param targetSquare
	 * @return
	 */
	private boolean legalMoveBlack(Square targetSquare) {
		//Forward move
		if(targetSquare.isOccupied() == false) {
			//One square forward
			if(targetSquare.getYcoordinate() == this.getSquare().getYcoordinate()-1&&(targetSquare.getXcoordinate()==this.getSquare().getXcoordinate())) {
				return true;
			}
			//First move 2 Squares forward
			if((targetSquare.getYcoordinate() == this.getSquare().getYcoordinate()-2 && this.getSquare().getYcoordinate() ==7)&&(targetSquare.getXcoordinate()==this.getSquare().getXcoordinate())) {
				return true;
			}
		}

		//Capturing 

		if((targetSquare.isOccupied()&&targetSquare.getPiece().getColour()!=this.getColour()  && targetSquare.getYcoordinate() == this.getSquare().getYcoordinate()-1) && (Math.abs(targetSquare.getXcoordinate()-this.getSquare().getXcoordinate())==1)){
			return true;
		}
		return false;
	}

	public void promote(PieceType type) {
		this.setType(type);
	}


	//TODO
	// is invoked automatically when a pawn reaches the end of the board, just need to implement the method to ask what PieceType to promote to.
	public PieceType promoteTo() {
		return null;
	}

}
