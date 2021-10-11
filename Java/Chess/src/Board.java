
/**
 * @author otari
 *
 */
public class Board {

	private Square[][] board = new Square[8][8];

	private char xAxis [] = {'a','b','c','d','e','f','g','h'};


	/**
	 * 
	 */
	public Board() {

		/*
		 * False for black. 
		 * True for white.
		 */
		boolean colour = false;

		for(int y = 0 ; y<8 ; y++) {
			board[y] = new Square[8];
			for(int x = 0; x<8; x++) {
				board[y][x] = new Square(Colour.booleanToColour(colour),false,x+1,y+1);
				colour = !colour;
			}
			colour = !colour;
		}
	}

	/**
	 *
	 */
	public String toString() {
		String board = "";
		for(int y = 7 ; y>=0 ; y--) {
			for(int x = 0; x<8; x++) {
				board = board + this.board[y][x].toString() + " 	";
			}
			board = board + "\n";
		}

		return board;
	}
}
