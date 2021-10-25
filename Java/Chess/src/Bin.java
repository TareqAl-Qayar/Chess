import java.awt.Dimension;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;

public class Bin extends Square {

	private Set<Piece> pieces;
	private JPanel binGraphic;


	Colour colour;


	public Bin(Colour colour) {
		super();
		this.colour = colour;
		binGraphic();
		pieces = new HashSet<Piece>();
	}


	public void binGraphic() {
		binGraphic = new JPanel();
		binGraphic.setPreferredSize(new Dimension(200,400));
		binGraphic.setBackground(colour.getSquareColor());

	}

	public void addPiece(Piece piece) {
		pieces.add(piece);
		//TODO add to the graphic
		binGraphic.add(piece.getPieceGraphic());
	}


	/**
	 * @return the pieces
	 */
	public Set<Piece> getPieces() {
		return pieces;
	}


	/**
	 * @param pieces the pieces to set
	 */
	public void setPieces(Set<Piece> pieces) {
		this.pieces = pieces;
	}
	
	
	/**
	 * @return the binGraphic
	 */
	public JPanel getBinGraphic() {
		return binGraphic;
	}


	/**
	 * @param binGraphic the binGraphic to set
	 */
	public void setBinGraphic(JPanel binGraphic) {
		this.binGraphic = binGraphic;
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

}
