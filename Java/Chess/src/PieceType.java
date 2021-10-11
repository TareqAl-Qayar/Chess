
/**
 * @author otari
 *
 */
public enum PieceType {

	Rook(1),Knight(2),Bishop(3),King(4), Queen(5),Pawn(6);

	private final int value;

	/**
	 * @param value
	 */
	private PieceType(int value) {
		this.value = value;
	}

	/**
	 * @return
	 */
	public int getValue() {
		return value;
	}


}
