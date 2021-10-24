import java.awt.Dimension;

import javax.swing.JPanel;

import java.awt.*;
/**
 * @author otari
 *
 */
public class Board {

	private static Square[][] board = new Square[8][8];

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
				board = board + Board.board[y][x].toString() + " 	";
			}
			board = board + "\n";
		}

		return board;
	}
	
	
	public static Square getSquare(int x , int y) {
		//Here the outside array is the Ycoordinate, thats why it has to come first.
		return board[y-1][x-1];
	}
	
	public JPanel boardGraphic(){
		JPanel board = new JPanel();
		board.setPreferredSize(new Dimension(800,800));
		board.setLayout(new GridLayout(8,8));
		
		for(int i = 7 ; i>=0;i--) {
			for(int j = 0 ; j <8 ; j++) {
				board.add(Board.board[i][j].squareGraphic());
			}
		}
		
		return board;
	}
}
