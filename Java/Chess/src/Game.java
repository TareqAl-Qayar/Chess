
public class Game {
	Pawn pawnsWhite[] = new Pawn[8];
	Pawn pawnsBlack[] = new Pawn[8];
	Rook rookWhiteA;
	Rook rookWhiteH;
	Rook rookBlackA;
	Rook rookBlackH;
	Knight knightWhiteB;
	Knight knightWhiteF;
	Knight knightBlackB;
	Knight knightBlackF;
	Bishop bishopWhiteDark;
	Bishop bishopWhiteLight;
	Bishop bishopBlackDark;
	Bishop bishopBlackLight;
	King kingWhite;
	King kingBlack;
	Queen queenWhite;
	Queen queenBlack;
	public Game() {
		// TODO Auto-generated constructor stub
	}


	public void setBoard() {

		for(int i = 1 ; i < 9 ; i++ ) {
			pawnsWhite[i-1] = new Pawn(Colour.White, Board.getSquare(i, 2));
			pawnsBlack[i-1] = new Pawn(Colour.Black, Board.getSquare(i, 7));
		}

		rookWhiteA = new Rook(Colour.White, Board.getSquare(1, 1));
		rookWhiteH = new Rook(Colour.White, Board.getSquare(8, 1));
		rookBlackA = new Rook(Colour.Black, Board.getSquare(1, 8));
		rookBlackH = new Rook(Colour.Black, Board.getSquare(8, 8));
		
		knightWhiteB = new Knight(Colour.White,Board.getSquare(2, 1));
		knightWhiteF = new Knight(Colour.White,Board.getSquare(7, 1));
		knightBlackB = new Knight(Colour.Black,Board.getSquare(2, 8));
		knightBlackF = new Knight(Colour.Black,Board.getSquare(7, 8));

		bishopWhiteDark = new Bishop(Colour.White, Board.getSquare(3, 1));
		bishopWhiteLight = new Bishop(Colour.White, Board.getSquare(6, 1));
		bishopBlackDark = new Bishop(Colour.Black, Board.getSquare(3, 8));
		bishopBlackLight = new Bishop(Colour.Black, Board.getSquare(6, 8));

		kingWhite = new King(Colour.White, Board.getSquare(5, 1));
		kingBlack = new King(Colour.Black, Board.getSquare(5, 8));

		queenWhite = new Queen(Colour.White,Board.getSquare(4,1));
		queenBlack = new Queen(Colour.Black,Board.getSquare(4, 8));
	}

}
