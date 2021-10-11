
/**
 * @author otari
 *
 */
public enum Colour {
	Black, White;
	
	/**
	 * True is white, false is black
	 * @param b
	 * @return
	 */
	public static Colour booleanToColour(boolean b) {
		if(b) {
			return White;
		}
		return Black;
	}
}
