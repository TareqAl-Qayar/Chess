
/**
 * @author otari
 *
 */
public class Square implements Reachable {

	private Colour colour;
	private boolean occupied;

	private int xcoordinate;
	private int ycoordinate;

	private Piece piece;





	/**
	 * @param colour
	 * @param occupied
	 * @param x
	 * @param y
	 */
	public Square(Colour colour, boolean occupied,int x, int y) {
		this.colour = colour;
		this.occupied = occupied;
		this.xcoordinate = x;
		this.ycoordinate = y;
	}

	
	
	/**
	 * Checks if the square is block by another piece of the same colour.
	 * @param piece:piece to check if blocked by another piece of the same colour.
	 * @return true if square has a piece of the same colour otherwise returns false.
	 */
	public boolean isBlocked(Piece piece) {
		if(this.isOccupied() && this.getPiece().getColour() == piece.getColour()) {
			return true;
		}
		return false;
	}

	/**
	 * @return
	 */
	public Colour getColour() {
		return colour;
	}

	/**
	 * @param colour
	 */
	public void setColour(Colour colour) {
		this.colour = colour;
	}

	/**
	 * @return
	 */
	public boolean isOccupied() {
		return occupied;
	}

	/**
	 * @param occupied
	 */
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	/**
	 * @return
	 */
	public int getXcooridiante() {
		return xcoordinate;
	}



	/**
	 * @param xcooridiante
	 */
	public void setXcooridiante(char xcooridiante) {
		this.xcoordinate = xcooridiante;
	}

	/**
	 * @return
	 */
	public int getYcoordinate() {
		return ycoordinate;
	}

	/**
	 * @param ycoordinate
	 */
	public void setYcoordinate(int ycoordinate) {
		this.ycoordinate = ycoordinate;
	}

	/**
	 *
	 */
	public String toString() {
		return xcoordinate+ " " + ycoordinate+ " " + colour.toString();//TODO + piece.toString() with nullPointerException.

	}

	/**
	 * @return the piece
	 */
	public Piece getPiece() {
		// TODO if isOccupied is true
		return piece;
	}



	/**
	 * @param piece the piece to set
	 */
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
}
