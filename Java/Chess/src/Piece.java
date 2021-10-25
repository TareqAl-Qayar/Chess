import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author otari
 *
 */
public abstract class Piece {


	private PieceType type;
	private Colour colour;
	boolean captured;
	private Square square;
	private int moves;

	private JPanel pieceGraphic;

	//Array of 2 int values first for x direction and second for y direction.
	//int move[]= new int[2];
	// maybe class MakeMove






	protected Piece(PieceType type, Colour colour, Square square) {
		this.type = type;
		this.colour = colour;
		this.square = square;
		captured = false;
		this.moves = 0;
		square.setOccupied(true);
		square.setPiece(this);
	}

	// TODO check if square coordinates are not valid (off the board).
	// TODO have to protect after being checked
	// TODO can't move if pinned to the king.
	// TODO change occupied states.
	// TODO increment moves
	public void move(Square targetSquare) {
		if(this.legalMove(targetSquare)) {
			if(targetSquare.isOccupied()){
				capturePiece(targetSquare);
			}
			
			//TODO update Square and square graphic to no longer hold the piece and the pieceGraphic.
			getSquare().setOccupied(false);
			getSquare().setPiece(null);
			getSquare().getSquareGraphic().remove(pieceGraphic);;
			
			
			setSquare(targetSquare);
			targetSquare.setPiece(this);
			targetSquare.setOccupied(true);
			targetSquare.getSquareGraphic().add(pieceGraphic);
			incrementMoves();
		}
		else {
			System.out.println("Move not legal");
		}
	}



	/**
	 * Checks if the move that is made is a legal move in terms of geometry and not being by own or other pieces.
	 * @param targetSquare The square that the piece is being moved to.
	 * @return true if move is possible and legal, otherwise false.
	 */
	public abstract boolean legalMove(Square targetSquare);

	
	/**
	 * 
	 * @param targetSquare
	 */
	public void capturePiece(Square targetSquare) {
		Piece targetPiece = targetSquare.getPiece();
		targetPiece.setCaptured(true);
		targetSquare.getSquareGraphic().remove(targetPiece.getPieceGraphic());
		if (targetPiece.getColour()==Colour.White) {
			targetPiece.setSquare(GameWindow.getBinBlack());
			GameWindow.getBinBlack().addPiece(targetPiece);
		} 
		else {
			targetPiece.setSquare(GameWindow.getBinWhite());
			GameWindow.getBinWhite().addPiece(targetPiece);

		}

	}


	// TODO maybe use JButton.
	// TODO use transparent background for pictures of pieces.
	public JPanel pieceGraphic() {
		pieceGraphic = new JPanel();
		// TODO change to an actual picture.
		pieceGraphic.add(new JLabel(this.toString()));
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
}
