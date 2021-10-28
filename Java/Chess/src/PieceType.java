
/**
 * 
 * @author Tareq Al-Qayar
 * @version 1.0
 * @since 28/10/2021
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
