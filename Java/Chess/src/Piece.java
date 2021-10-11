
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
	//Array of 2 int values first for x direction and second for y direction.
	//int move[]= new int[2];
	// maybe class MakeMove
	
	


	protected Piece(PieceType type, Colour colour, Square square) {
		this.type = type;
		this.colour = colour;
		this.square = square;
		captured = false;
		this.moves = 0;
	}


	public abstract void move();//TODO check if square coordinates are not valid (off the board).
	// TODO have to protect after being checked
	
	public abstract boolean legalMove(Square targetSquare);
	
	public void capturePiece(Square targetSquare) {
		targetSquare.getPiece().setCaptured(true);
		
		
		
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
}
