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
		// TODO castling
		
		if(targetSquare.isBlocked(this)) {
			return false;
		}
		
		if(Game.getAttackedSquaresByOppositeColour(this).contains(targetSquare)) {
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
		this.getAttackedSquares().clear();
		for(int i = -1 ; i <2; i++) {
			if(this.getXCoordinate()+i>0&&this.getXCoordinate()<9) {
				if(Board.getSquare(this.getXCoordinate()+i, this.getYCoordinate()).isBlocked(this)==false) {
					this.getAttackedSquares().add(Board.getSquare(this.getXCoordinate()+i, this.getYCoordinate()));
				}
				if (this.getYCoordinate()+1<9) {
					if (Board.getSquare(this.getXCoordinate() + i, this.getYCoordinate()+1).isBlocked(this) == false) {
						this.getAttackedSquares()
								.add(Board.getSquare(this.getXCoordinate() + i, this.getYCoordinate()+1));
					} 
				}
				if (this.getYCoordinate()-1>0) {
					if (Board.getSquare(this.getXCoordinate() + i, this.getYCoordinate()-1).isBlocked(this) == false) {
						this.getAttackedSquares()
								.add(Board.getSquare(this.getXCoordinate() + i, this.getYCoordinate()-1));
					} 
				}
			}
		}
		//System.out.println(this.toString()+ " is attacking " + this.getAttackedSquares().toString());

	}


	@Override
	public String toUnicode() {
		if(getColour()==Colour.Black) {
			return "♚";
		}
		return "♔";
	}

}
