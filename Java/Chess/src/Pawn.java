import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import java.awt.*;

/**
 * 
 * @author Tareq Al-Qayar
 * @version 1.0
 * @since 28/10/2021
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
			if((targetSquare.getYcoordinate() == getYCoordinate()+1)&&(targetSquare.getXcoordinate()==getXCoordinate())) {
				return true;
			}
			//First move 2 Squares forward
			if((targetSquare.getYcoordinate() == getYCoordinate()+2 && getYCoordinate() ==2&&(targetSquare.getXcoordinate()==getXCoordinate()))) {
				if (Board.getSquare(getXCoordinate(), getYCoordinate()+1).isOccupied()==false) {
					return true;
				}
			}
		}

		//Capturing 

		if((targetSquare.isOccupied()&&targetSquare.getPiece().getColour()!=this.getColour() && targetSquare.getYcoordinate() == getYCoordinate()+1 )&& (Math.abs(targetSquare.getXcoordinate()-getXCoordinate())==1)){
			return true;
		}

		//en passant
		if((getYCoordinate()==5)&&(!targetSquare.isOccupied())&&(this.differenceInX(targetSquare)==1 && this.differenceInY(targetSquare)==1)) {
			if((Game.getLastMove().getStartingSquare().getYcoordinate()==targetSquare.getYcoordinate()+1)&&(Game.getLastMove().getTargetSquare().getYcoordinate()==targetSquare.getYcoordinate()-1)&&(targetSquare.getXcoordinate()==Game.getLastMove().getStartingSquare().getXcoordinate())) {
				Game.capturePiece(Board.getSquare(Game.getLastMove().getTargetSquare().getXcoordinate(), Game.getLastMove().getTargetSquare().getYcoordinate()));
				return true;
			}
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
			if(targetSquare.getYcoordinate() == getYCoordinate()-1&&(targetSquare.getXcoordinate()==getXCoordinate())) {
				return true;
			}
			//First move 2 Squares forward
			if((targetSquare.getYcoordinate() == getYCoordinate()-2 && getYCoordinate() ==7)&&(targetSquare.getXcoordinate()==getXCoordinate())) {
				if (Board.getSquare(getXCoordinate(), getYCoordinate()-1).isOccupied()==false) {
					return true;
				}
			}
		}

		//Capturing 

		if((targetSquare.isOccupied()&&targetSquare.getPiece().getColour()!=this.getColour()  && targetSquare.getYcoordinate() == getYCoordinate()-1) && (Math.abs(targetSquare.getXcoordinate()-getXCoordinate())==1)){
			return true;
		}


		//en passant
		if((getYCoordinate()==4)&&(!targetSquare.isOccupied())&&(this.differenceInX(targetSquare)==1 && this.differenceInY(targetSquare)==1)) {
			if((Game.getLastMove().getStartingSquare().getYcoordinate()==targetSquare.getYcoordinate()-1)&&(Game.getLastMove().getTargetSquare().getYcoordinate()==targetSquare.getYcoordinate()+1)&&(targetSquare.getXcoordinate()==Game.getLastMove().getStartingSquare().getXcoordinate())) {
				Game.capturePiece(Board.getSquare(Game.getLastMove().getTargetSquare().getXcoordinate(), Game.getLastMove().getTargetSquare().getYcoordinate()));
				return true;
			}
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



	@Override
	public void findAttackedSquares() {
		// TODO fix not checking for blocked sqaures.
		getAttackedSquares().clear();
		if(getYCoordinate()<8&& getYCoordinate()>1){
			if(getColour()==Colour.Black) {
				if(this.getXCoordinate()<8 && this.getXCoordinate()>1) {
					getAttackedSquares().add(Board.getSquare(getXCoordinate()+1, getYCoordinate()-1));
					getAttackedSquares().add(Board.getSquare(getXCoordinate()-1, getYCoordinate()-1));
				}
				if(getXCoordinate()==1) {
					getAttackedSquares().add(Board.getSquare(getXCoordinate()+1, getYCoordinate()-1));
				}
				else {
					getAttackedSquares().add(Board.getSquare(getXCoordinate()-1, getYCoordinate()-1));
				}
			}
			if(getColour()==Colour.White) {
				if(this.getXCoordinate()<8 && this.getXCoordinate()>1) {
					getAttackedSquares().add(Board.getSquare(getXCoordinate()+1, getYCoordinate()+1));
					getAttackedSquares().add(Board.getSquare(getXCoordinate()-1, getYCoordinate()+1));
				}
				if(getXCoordinate()==1) {
					getAttackedSquares().add(Board.getSquare(getXCoordinate()+1, getYCoordinate()+1));
				}
				else {
					getAttackedSquares().add(Board.getSquare(getXCoordinate()-1, getYCoordinate()+1));
				}
			}

		}

	}



	@Override
	public String toUnicode() {
		if(getColour()==Colour.Black) {
			return "???";
		}
		return "???";
	}

}
