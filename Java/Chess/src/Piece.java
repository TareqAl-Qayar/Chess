import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author Tareq Al-Qayar
 * @version 1.0
 * @since 28/10/2021
 *
 */
public abstract class Piece {


	private PieceType type;
	private Colour colour;
	private boolean captured;
	private Square square;
	private int moves;
	private LinkedList<Square> attackedSquares;

	private JPanel pieceGraphic;



	protected Piece(PieceType type, Colour colour, Square square) {
		this.type = type;
		this.colour = colour;
		this.square = square;
		captured = false;
		this.moves = 0;
		square.setOccupied(true);
		square.setPiece(this);
		attackedSquares = new LinkedList<Square>();
	}
	
	/**
	 * Finds the squares attacked by this piece and adds them to the attackedSquares set after every turn. Clears the set every time it is called.
	 */
	public abstract void findAttackedSquares();

	



	/**
	 * Checks if the move that is made is a legal move in terms of geometry and not being by own or other pieces.
	 * @param targetSquare The square that the piece is being moved to.
	 * @return true if move is possible and legal, otherwise false.
	 */
	public abstract boolean legalMove(Square targetSquare);

	


	// TODO maybe use JButton.
	// TODO use transparent background for pictures of pieces.
	public JPanel pieceGraphic() {
//		Icon imageIcon = new ImageIcon(("Assets/Pawn b.png"));
//		Image image = ((ImageIcon) imageIcon).getImage();
//		Image newimg = image.getScaledInstance(50,50,java.awt.Image.SCALE_SMOOTH);
//		imageIcon = new ImageIcon(newimg);
//		JLabel imageLabel = new JLabel((imageIcon));
//		imageLabel.setOpaque(true);
//		imageLabel.setLayout(new BorderLayout());
		pieceGraphic = new JPanel();
		pieceGraphic.setOpaque(false);
		pieceGraphic.repaint();
		
		JLabel label = new JLabel(this.toUnicode());
		label.setFont(new Font("Serif", Font.PLAIN, 50));
		label.setForeground(Color.black);
		label.setOpaque(false);
		label.repaint();
		
		pieceGraphic.add(label);
		
		return pieceGraphic;
	}


	protected void attackedSquaresLoopDiagonal(int xCap, int yCap, int xSign , int ySign) {
		int yIndex = this.getYCoordinate();
		for(int i = this.getXCoordinate()+xSign; i!= xCap+xSign; i = i+xSign) {
			yIndex   = yIndex + ySign;
			if(yIndex== yCap + ySign) {
				break;
			}
			if(Board.getSquare(i, yIndex).isBlocked(this)) {
				break;
			}
			if(Board.getSquare(i, yIndex).OccupiedByOppositeColour(getColour())) {
				getAttackedSquares().add(Board.getSquare(i, yIndex));
				break;
			}
			getAttackedSquares().add(Board.getSquare(i, yIndex));
		}
	}
	
	/**
	 * 
	 * @param cap
	 * @param sign
	 * @param direction true for checking in x, false for checking i y.
	 */
	protected void attackedSquaresLoopParallel(int cap , int sign, boolean direction) {
		int xIndex  =this.getXCoordinate();
		int yIndex = this .getYCoordinate();
		int xIncrement,yIncrement;
		int i;
		if(direction) {
			xIncrement = sign;
			yIncrement = 0;
			i = this.getXCoordinate();
		}
		else {
			xIncrement = 0;
			yIncrement = sign; 
			i = this.getYCoordinate();
		}
		while(i != cap) {
			xIndex = xIndex + xIncrement;
			yIndex = yIndex + yIncrement;
			Square square = Board.getSquare(xIndex, yIndex);
			if(square.isBlocked(this)) {
				break;
			}
			if(square.OccupiedByOppositeColour(getColour())) {
				getAttackedSquares().add(square);
				break;
			}
			getAttackedSquares().add(square);
			
			i=i+sign;
		}
	}



	/**
	 * @return the square
	 */
	public Square getSquare() {
		return square;
	}



	/**
	 * @param square the square to set
	 */
	public void setSquare(Square square) {
		this.square = square;
	}


	/**
	 * @return the type
	 */
	public PieceType getType() {
		return type;
	}


	/**
	 * @param type the type to set
	 */
	public void setType(PieceType type) {
		this.type = type;
	}


	/**
	 * @return the colour
	 */
	public Colour getColour() {
		return colour;
	}


	/**
	 * @param colour the colour to set
	 */
	public void setColour(Colour colour) {
		this.colour = colour;
	}


	/**
	 * @return the captured
	 */
	public boolean isCaptured() {
		return captured;
	}


	/**
	 * @param captured the captured to set
	 */
	public void setCaptured(boolean captured) {
		this.captured = captured;
	}

	public String toString() {
		return this.type.toString() + " " + this.colour.toString();
	}
	
	public abstract String toUnicode();


	/**
	 * @return the moves
	 */
	public int getMoves() {
		return moves;
	}


	/**
	 * @param moves the moves to set
	 */
	public void setMoves(int moves) {
		this.moves = moves;
	}

	/**
	 * increments moves variable by 1.
	 */
	public void incrementMoves() {
		this.moves = this.moves + 1;
	}
	
	public void decreaseMovesByOne() {
		this.moves = this.moves-1;
	}

	/**
	 * @return the pieceGraphic
	 */
	public JPanel getPieceGraphic() {
		return pieceGraphic;
	}

	/**
	 * @param pieceGraphic the pieceGraphic to set
	 */
	public void setPieceGraphic(JPanel pieceGraphic) {
		this.pieceGraphic = pieceGraphic;
	}
	
	/**
	 * Returns the absolute value of difference between this piece's x coordinate and the target square's.
	 * @param targetSquare
	 * @return
	 */
	public int differenceInX(Square targetSquare) {
		int differenceInX;
		differenceInX = Math.abs(targetSquare.getXcoordinate()-this.getSquare().getXcoordinate());
	return differenceInX;
	}
	
	
	/**
	 * Returns the absolute value of difference between this piece's y coordinate and the target square's.
	 * @param targetSquare
	 * @return
	 */
	public int differenceInY(Square targetSquare) {
		int differenceInY;
		differenceInY = Math.abs(targetSquare.getYcoordinate()-this.getSquare().getYcoordinate());
		return differenceInY;
	}
	
	/**
	 * 
	 * @return the x coordinate of this piece object on the board, returns 0 if piece is captured (in bin).
	 */
	public int getXCoordinate() {
		return this.getSquare().getXcoordinate();
	}
	
	/**
	 * 
	 * @return the y coordinate of this piece object on the board, returns 0 if piece is captured (in bin).
	 */
	public int getYCoordinate() {
		return this.getSquare().getYcoordinate();
	}

	/**
	 * @return the attackedSquares
	 */
	public LinkedList<Square> getAttackedSquares() {
		return attackedSquares;
	}

	/**
	 * @param attackedSquares the attackedSquares to set
	 */
	public void setAttackedSquares(LinkedList<Square> attackedSquares) {
		this.attackedSquares = attackedSquares;
	}
	
	public String getAttackedSquaresString() {
		String s="";
		for(int i = 0 ; i<attackedSquares.size();i++) {
			s = s + attackedSquares.get(i).toString();
		}
		return s;
	}
	public King getKing() {
		if(this.colour == Colour.Black) {
			return Game.getKingBlack();
		}
		else return Game.getKingWhite();
	}
	
	
}
