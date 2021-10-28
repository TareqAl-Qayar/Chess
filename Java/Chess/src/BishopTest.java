import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * 
 * @author Tareq Al-Qayar
 * @version 1.0
 * @since 28/10/2021
 *
 */
class BishopTest {
	Bishop bishop ;
	Board b;
	Pawn p1,p2,p3,p4;
	
	
	@BeforeEach
	void setUp() throws Exception {
		b = new Board();
		bishop= new Bishop(Colour.White, Board.getSquare(5, 4));
		p1 =  new Pawn(Colour.White,Board.getSquare(3,2));
		p2 =  new Pawn(Colour.White,Board.getSquare(7, 2));
		p3 =  new Pawn(Colour.White,Board.getSquare(3, 6));
		p4 =  new Pawn(Colour.White,Board.getSquare(7, 6));
		System.out.println(p4.getSquare().isOccupied());

		System.out.println(b.toString());
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testUpRight() {
		assertTrue(bishop.isWayFree(b.getSquare(8,7)));
	}
	
	@Test
	void testUpleft() {
		assertTrue(bishop.isWayFree(b.getSquare(2,7)));
	}
	
	@Test
	void testDownRight() {
		assertTrue(bishop.isWayFree(b.getSquare(8,1)));
	}
	
	@Test
	void testDownleft() {
		assertTrue(bishop.isWayFree(b.getSquare(2,1)));
	}

}
