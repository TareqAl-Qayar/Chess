
/**
 * 
 * @author Tareq Al-Qayar
 * @version 1.0
 * @since 28/10/2021
 *
 */
public class Game {



	private Pawn pawnsWhite[] = new Pawn[8];
	private Pawn pawnsBlack[] = new Pawn[8];
	private Rook rookWhiteA,rookWhiteH,rookBlackA,rookBlackH;
	private Knight knightWhiteB,knightWhiteF,knightBlackB,knightBlackF;
	private Bishop bishopWhiteDark,bishopWhiteLight,bishopBlackDark,bishopBlackLight;
	private King kingWhite,kingBlack;
	private Queen queenWhite,queenBlack;

	private static Square startingSquare;
	private static Square targetSquare;
	private static GameWindow window;
	private static boolean turnWhite;

	public Game() {
		Board board = new Board();
		setBoard();
		board.CreateBoardGraphic();
		turnWhite=true;

		window = new GameWindow(board);

		//startingSquare = board.getSquare(4, 2);
		//targetSquare = board.getSquare(4, 4);
		//move();
	}


	/**
	 * Sets the board by creating the piece objects and placing them on the their initial squares.
	 */
	private void setBoard() {

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

	/**
	 * Takes a String in the form of "{Square letter}{Square number}_{Square letter}{Square number}", the first 2 corresponding to the square the piece is on and the second to 
	 * the square that the piece is moving to.
	 * @param TextMove
	 */
	private void convertTextToMove(String TextMove) {
		// TODO
	}

	private void logMove() {
		//TODO
	}

	// TODO check if square coordinates are not valid (off the board).
	// TODO have to protect after being checked
	// TODO can't move if pinned to the king.
	public static void move() {
		// TODO add try catch null pointer exception?

		if(turnWhite == startingSquare.getPiece().getColour().ColourToBoolean()) {
			if (startingSquare.isOccupied()==true) {
				Piece piece = startingSquare.getPiece();
				if (piece.legalMove(targetSquare)) {
					if (targetSquare.isOccupied()) {
						capturePiece();
					}

					startingSquare.setOccupied(false);
					startingSquare.setPiece(null);
					startingSquare.getSquareGraphic().remove(piece.getPieceGraphic());

					targetSquare.setOccupied(true);
					piece.setSquare(targetSquare);
					targetSquare.setPiece(piece);
					targetSquare.setPiece(piece);
					targetSquare.getSquareGraphic().add(piece.getPieceGraphic());
					piece.incrementMoves();


				}
				startingSquare.repaintSquare();
				startingSquare.getSquareGraphic().repaint();
				targetSquare.getSquareGraphic().repaint();
				startingSquare = null;
				targetSquare = null;
				turnWhite = !turnWhite;
			}
		}
		else {
			outputMessage("Move not possible.");
			startingSquare.repaintSquare();
			startingSquare=null;
			targetSquare=null;
		}
	}
	/**
	 * is called when the targetSquare is Occupied by a piece of the opposite colour, frees
	 * the targetSquare and changes the square for the previous piece to binWhite or binBlack
	 * depending on its colour.
	 * @param targetSquare on which the piece to be captured is.
	 */
	public static void capturePiece() {
		Piece targetPiece = targetSquare.getPiece();
		targetPiece.setCaptured(true);
		targetSquare.getSquareGraphic().remove(targetPiece.getPieceGraphic());
		if(targetPiece.getColour()==Colour.White) {
			targetPiece.setSquare(GameWindow.getBinBlack());
			GameWindow.getBinBlack().addPiece(targetPiece);
			GameWindow.getBinBlack().getBinGraphic().repaint();
		}
		else {
			targetPiece.setSquare(GameWindow.getBinWhite());
			GameWindow.getBinWhite().addPiece(targetPiece);
			GameWindow.getBinWhite().getBinGraphic().repaint();
		}
	}

	public static void outputMessage(String text) {
		window.getOutputField().setText(text);
		System.out.println(text);
	}

	/**
	 * @return the pawnsWhite
	 */
	public Pawn[] getPawnsWhite() {
		return pawnsWhite;
	}


	/**
	 * @param pawnsWhite the pawnsWhite to set
	 */
	public void setPawnsWhite(Pawn[] pawnsWhite) {
		this.pawnsWhite = pawnsWhite;
	}


	/**
	 * @return the pawnsBlack
	 */
	public Pawn[] getPawnsBlack() {
		return pawnsBlack;
	}


	/**
	 * @param pawnsBlack the pawnsBlack to set
	 */
	public void setPawnsBlack(Pawn[] pawnsBlack) {
		this.pawnsBlack = pawnsBlack;
	}


	/**
	 * @return the rookWhiteA
	 */
	public Rook getRookWhiteA() {
		return rookWhiteA;
	}


	/**
	 * @param rookWhiteA the rookWhiteA to set
	 */
	public void setRookWhiteA(Rook rookWhiteA) {
		this.rookWhiteA = rookWhiteA;
	}


	/**
	 * @return the rookWhiteH
	 */
	public Rook getRookWhiteH() {
		return rookWhiteH;
	}


	/**
	 * @param rookWhiteH the rookWhiteH to set
	 */
	public void setRookWhiteH(Rook rookWhiteH) {
		this.rookWhiteH = rookWhiteH;
	}


	/**
	 * @return the rookBlackA
	 */
	public Rook getRookBlackA() {
		return rookBlackA;
	}


	/**
	 * @param rookBlackA the rookBlackA to set
	 */
	public void setRookBlackA(Rook rookBlackA) {
		this.rookBlackA = rookBlackA;
	}


	/**
	 * @return the rookBlackH
	 */
	public Rook getRookBlackH() {
		return rookBlackH;
	}


	/**
	 * @param rookBlackH the rookBlackH to set
	 */
	public void setRookBlackH(Rook rookBlackH) {
		this.rookBlackH = rookBlackH;
	}


	/**
	 * @return the knightWhiteB
	 */
	public Knight getKnightWhiteB() {
		return knightWhiteB;
	}


	/**
	 * @param knightWhiteB the knightWhiteB to set
	 */
	public void setKnightWhiteB(Knight knightWhiteB) {
		this.knightWhiteB = knightWhiteB;
	}


	/**
	 * @return the knightWhiteF
	 */
	public Knight getKnightWhiteF() {
		return knightWhiteF;
	}


	/**
	 * @param knightWhiteF the knightWhiteF to set
	 */
	public void setKnightWhiteF(Knight knightWhiteF) {
		this.knightWhiteF = knightWhiteF;
	}


	/**
	 * @return the knightBlackB
	 */
	public Knight getKnightBlackB() {
		return knightBlackB;
	}


	/**
	 * @param knightBlackB the knightBlackB to set
	 */
	public void setKnightBlackB(Knight knightBlackB) {
		this.knightBlackB = knightBlackB;
	}


	/**
	 * @return the knightBlackF
	 */
	public Knight getKnightBlackF() {
		return knightBlackF;
	}


	/**
	 * @param knightBlackF the knightBlackF to set
	 */
	public void setKnightBlackF(Knight knightBlackF) {
		this.knightBlackF = knightBlackF;
	}


	/**
	 * @return the bishopWhiteDark
	 */
	public Bishop getBishopWhiteDark() {
		return bishopWhiteDark;
	}


	/**
	 * @param bishopWhiteDark the bishopWhiteDark to set
	 */
	public void setBishopWhiteDark(Bishop bishopWhiteDark) {
		this.bishopWhiteDark = bishopWhiteDark;
	}


	/**
	 * @return the staringSquare
	 */
	public static Square getStaringSquare() {
		return startingSquare;
	}


	/**
	 * @return the targetSquare
	 */
	public static Square getTargetSquare() {
		return targetSquare;
	}


	/**
	 * @param targetSquare the targetSquare to set
	 */
	public static void setTargetSquare(Square square) {
		targetSquare = square;
	}


	/**
	 * @param staringSquare the staringSquare to set
	 */
	public static void setStaringSquare(Square staringSquare) {
		startingSquare = staringSquare;
	}


	/**
	 * @return the bishopWhiteLight
	 */
	public Bishop getBishopWhiteLight() {
		return bishopWhiteLight;
	}


	/**
	 * @param bishopWhiteLight the bishopWhiteLight to set
	 */
	public void setBishopWhiteLight(Bishop bishopWhiteLight) {
		this.bishopWhiteLight = bishopWhiteLight;
	}


	/**
	 * @return the bishopBlackDark
	 */
	public Bishop getBishopBlackDark() {
		return bishopBlackDark;
	}


	/**
	 * @param bishopBlackDark the bishopBlackDark to set
	 */
	public void setBishopBlackDark(Bishop bishopBlackDark) {
		this.bishopBlackDark = bishopBlackDark;
	}


	/**
	 * @return the bishopBlackLight
	 */
	public Bishop getBishopBlackLight() {
		return bishopBlackLight;
	}


	/**
	 * @param bishopBlackLight the bishopBlackLight to set
	 */
	public void setBishopBlackLight(Bishop bishopBlackLight) {
		this.bishopBlackLight = bishopBlackLight;
	}


	/**
	 * @return the kingWhite
	 */
	public King getKingWhite() {
		return kingWhite;
	}


	/**
	 * @param kingWhite the kingWhite to set
	 */
	public void setKingWhite(King kingWhite) {
		this.kingWhite = kingWhite;
	}


	/**
	 * @return the kingBlack
	 */
	public King getKingBlack() {
		return kingBlack;
	}


	/**
	 * @param kingBlack the kingBlack to set
	 */
	public void setKingBlack(King kingBlack) {
		this.kingBlack = kingBlack;
	}


	/**
	 * @return the queenWhite
	 */
	public Queen getQueenWhite() {
		return queenWhite;
	}


	/**
	 * @param queenWhite the queenWhite to set
	 */
	public void setQueenWhite(Queen queenWhite) {
		this.queenWhite = queenWhite;
	}


	/**
	 * @return the queenBlack
	 */
	public Queen getQueenBlack() {
		return queenBlack;
	}


	/**
	 * @param queenBlack the queenBlack to set
	 */
	public void setQueenBlack(Queen queenBlack) {
		this.queenBlack = queenBlack;
	}

}
