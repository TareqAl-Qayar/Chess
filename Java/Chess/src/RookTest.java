import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RookTest {
	Board b;
	
	Rook r;
	
	Pawn p;

	@BeforeEach
	void setUp() throws Exception {
		b = new Board();
		r = new Rook(Colour.White, b.getSquare(5, 4));
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		p = new Pawn(Colour.White,b.getSquare(6, 4));
		assertTrue(r.isWayFree(b.getSquare(8, 4)));
		System.out.println(b.toString());

	}
	
	@Test
	void test2() {
		assertTrue(r.isWayFree(b.getSquare(8, 4)));
		System.out.println(b.toString());

	}

}
