import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
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
	boolean captured;
	private Square square;
	private int moves;
	private Set<Square> attackedSquares;

	private JPanel pieceGraphic;



	protected Piece(PieceType type, Colour colour, Square square) {
		this.type = type;
		this.colour = colour;
		this.square = square;
		captured = false;
		this.moves = 0;
		square.setOccupied(true);
		square.setPiece(this);
		attackedSquares = new HashSet<Square>();
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
	public Set<Square> getAttackedSquares() {
		return attackedSquares;
	}

	/**
	 * @param attackedSquares the attackedSquares to set
	 */
	public void setAttackedSquares(Set<Square> attackedSquares) {
		this.attackedSquares = attackedSquares;
	}
	
	
}
