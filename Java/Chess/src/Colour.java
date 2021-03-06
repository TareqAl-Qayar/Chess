import java.awt.Color;
/**
 * 
 * @author Tareq Al-Qayar
 * @version 1.0
 * @since 28/10/2021
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
	
	public boolean ColourToBoolean() {
		if(this == Black) {
			return false;
		}
		return true;
	}
	
	public Color getSquareColor() {
		if( this == Black) {
			return new Color(46, 54, 33);
		}
		return new Color(232, 255, 189);
	}
	
	public Color getPieceColor() {
		if(this == Black) {
			return new Color(0,0,0);
		}
		return new Color(255,255,255);
	}
	
	public Color getBinColor() {
		if(this == Black) {
			return new Color(58, 61, 45);
		}
		return new Color(211, 219, 173);
	}
	
	public Color getSquareBlue() {
		if(this == Black) {
			return new Color(41, 78, 138);
		}
		return new Color(69, 127, 222);
	}
	
}
