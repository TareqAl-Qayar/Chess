
public class Knight extends Piece {

	public Knight(Colour colour, Square square) {
		super(PieceType.Knight, colour, square);
	}


	@Override
	public boolean legalMove(Square targetSquare) {
		if(targetSquare.isOccupied() == false || targetSquare.getPiece().getColour() != this.getColour()) { // TODO this might throw A NullPointerException if the square is empty, maybe put it in its own if.
			if((Math.abs(targetSquare.getXcoordinate()-this.getSquare().getXcoordinate())==1 && Math.abs(targetSquare.getYcoordinate()-this.getSquare().getYcoordinate())==2)
				||(Math.abs(targetSquare.getXcoordinate()-this.getSquare().getXcoordinate())==2 && Math.abs(targetSquare.getYcoordinate()-this.getSquare().getYcoordinate())==1)) {
				return true;
			}
		}
		return false;
	}

}
