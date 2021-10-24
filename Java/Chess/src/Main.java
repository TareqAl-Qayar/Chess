import javax.swing.*;
import java.awt.*;
public class Main {

	// TODO game input through chess notation.
	// TODO capturePiece method on Piece
	// TODO move() in Piece needs to be implemented. Change from abstract method to normal method and implement in the abstract class using sub-legalMove()-methods.
	// TODO protecting the king after check.
	// TODO not moving a piece pinned to the king.
	// TODO isReachable in Square class.
	// TODO finish legalMove() for King
	// TODO implement castling 
	// TODO implement en passant.
	// TODO implement pawn promotion.
	
	public static void main(String[] args) {
		System.out.println("Test");

		Board b = new Board();

		System.out.println(b.toString());

		GameWindow g = new GameWindow();
	}

}


