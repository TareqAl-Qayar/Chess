
/**
 * 
 * @author Tareq Al-Qayar
 * @version 1.0
 * @since 28/10/2021
 *
 */
public class Knight extends Piece {

	public Knight(Colour colour, Square square) {
		super(PieceType.Knight, colour, square);
	}


	@Override
	public boolean legalMove(Square targetSquare) {
		if(targetSquare.isOccupied() == false || targetSquare.getPiece().getColour() != this.getColour()) { // TODO this might throw A NullPointerException if the square is empty, maybe put it in its own if.
			if((Math.abs(targetSquare.getXcoordinate()-getXCoordinate())==1 && Math.abs(targetSquare.getYcoordinate()-getYCoordinate())==2)
				||(Math.abs(targetSquare.getXcoordinate()-getXCoordinate())==2 && Math.abs(targetSquare.getYcoordinate()-getYCoordinate())==1)) {
				return true;
			}
		}
		return false;
	}


	@Override
	public void findAttackedSquares() {
		this.getAttackedSquares().clear();
		if(this.getYCoordinate()+2<9) {
			if(this.getXCoordinate()+1<9) {
				if(Board.getSquare(getXCoordinate()+1, getYCoordinate()+2).isBlocked(this)==false) {
					getAttackedSquares().add(Board.getSquare(getXCoordinate()+1, getYCoordinate()+2));
				}
			}
			if(this.getXCoordinate()-1>0) {
				if(Board.getSquare(getXCoordinate()-1, getYCoordinate()+2).isBlocked(this)==false) {
					getAttackedSquares().add(Board.getSquare(getXCoordinate()-1, getYCoordinate()+2));
				}
			}
		}
		if(this.getYCoordinate()+1<9) {
			if(this.getXCoordinate()+2<9) {
				if(Board.getSquare(getXCoordinate()+2, getYCoordinate()+1).isBlocked(this)==false) {
					getAttackedSquares().add(Board.getSquare(getXCoordinate()+2, getYCoordinate()+1));
				}
			}
			if(this.getXCoordinate()-2>0) {
				if(Board.getSquare(getXCoordinate()-2, getYCoordinate()+1).isBlocked(this)==false) {
					getAttackedSquares().add(Board.getSquare(getXCoordinate()-2, getYCoordinate()+1));
				}
			}
		}
		if(this.getYCoordinate()-2>0) {
			if(this.getXCoordinate()+1<9) {
				if(Board.getSquare(getXCoordinate()+1, getYCoordinate()-2).isBlocked(this)==false) {
					getAttackedSquares().add(Board.getSquare(getXCoordinate()+1, getYCoordinate()-2));
				}
			}
			if(this.getXCoordinate()-1>0) {
				if(Board.getSquare(getXCoordinate()-1, getYCoordinate()-2).isBlocked(this)==false) {
					getAttackedSquares().add(Board.getSquare(getXCoordinate()-1, getYCoordinate()-2));
				}
			}
		}
		if(this.getYCoordinate()-1>0) {
			if(this.getXCoordinate()+2<9) {
				if(Board.getSquare(getXCoordinate()+2, getYCoordinate()-1).isBlocked(this)==false) {
					getAttackedSquares().add(Board.getSquare(getXCoordinate()+2, getYCoordinate()-1));
				}
			}
			if(this.getXCoordinate()-2>0) {
				if(Board.getSquare(getXCoordinate()-2, getYCoordinate()-1).isBlocked(this)==false) {
					getAttackedSquares().add(Board.getSquare(getXCoordinate()-2, getYCoordinate()-1));
				}
			}
		}
		
	}


	@Override
	public String toUnicode() {
		if(getColour()==Colour.Black) {
			return "♞";
		}
		return "♘";
	}

}
