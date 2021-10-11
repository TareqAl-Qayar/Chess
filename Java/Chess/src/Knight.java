
public class Knight extends Piece {

	public Knight(Colour colour, Square square) {
		super(PieceType.Knight, colour, square);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean legalMove(Square targetSquare) {
		if(targetSquare.isOccupied() == false || targetSquare.getPiece().getColour() != this.getColour()) { // TODO this might throw A NullPointerException if the square is empty, maybe put it in its own if.
			if((Math.abs(targetSquare.getXcooridiante()-this.getSquare().getXcooridiante())==1 && Math.abs(targetSquare.getYcoordinate()-this.getSquare().getYcoordinate())==2)
				||(Math.abs(targetSquare.getXcooridiante()-this.getSquare().getXcooridiante())==2 && Math.abs(targetSquare.getYcoordinate()-this.getSquare().getYcoordinate())==1)) {
				return true;
			}
		}
		return false;
	}

}
