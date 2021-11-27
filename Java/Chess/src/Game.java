import java.awt.Color;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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
	private static King kingWhite, kingBlack;
	private Queen queenWhite,queenBlack;
	private static Set<Piece> whitePieces;
	private static Set<Piece> blackPieces;
	private static List<Piece> capturedPieces;

	private static Square startingSquare;
	private static Square targetSquare;
	private static GameWindow window;
	private static boolean turnWhite;
	private static LinkedList<Move> moves = new LinkedList<Move>();

	private static Set<Square>attackedSquaresWhite,attackedSquaresBlack;

	public Game() {
		Board board = new Board();
		setupBoard();
		board.CreateBoardGraphic();
		turnWhite=true;

		window = new GameWindow(board);
		attackedSquaresWhite = new HashSet<Square>();
		attackedSquaresBlack = new HashSet<Square>();
		capturedPieces = new LinkedList<Piece>();
		setupPieceHashSets();

		//System.out.println();


	}

	/**
	 * Initialises the sets whitePieces and blackPieces and adds all the piece objects to their corresponding set.
	 */
	private void setupPieceHashSets() {
		whitePieces = new HashSet<Piece>();
		blackPieces = new HashSet<Piece>();

		whitePieces.add(bishopWhiteDark);
		whitePieces.add(bishopWhiteLight);
		whitePieces.add(kingWhite);
		whitePieces.add(knightWhiteB);
		whitePieces.add(knightWhiteF);
		whitePieces.add(queenWhite);
		whitePieces.add(rookWhiteA);
		whitePieces.add(rookWhiteH);
		for(int i = 0 ; i<pawnsWhite.length;i++) {
			whitePieces.add(pawnsWhite[i]);
		}

		blackPieces.add(bishopBlackDark);
		blackPieces.add(bishopBlackLight);
		blackPieces.add(kingBlack);
		blackPieces.add(knightBlackB);
		blackPieces.add(knightBlackF);
		blackPieces.add(queenBlack);
		blackPieces.add(rookBlackA);
		blackPieces.add(rookBlackH);
		for(int i = 0 ; i<pawnsBlack.length;i++) {
			blackPieces.add(pawnsBlack[i]);
		}

	}


	/**
	 * Sets the board by creating the piece objects and placing them on the their initial squares.
	 */
	private void setupBoard() {

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

	private static void logMove(boolean isMoveCapture) {
		moves.add(new Move(startingSquare,targetSquare, isMoveCapture));
	}

	// TODO have to protect after being checked
	// TODO can't move if pinned to the king.
	public static void move() {

		if(turnWhite == startingSquare.getPiece().getColour().ColourToBoolean()) {
			if (startingSquare.isOccupied()==true) {
				Piece piece = startingSquare.getPiece();
				if (piece.legalMove(targetSquare)) {
					boolean isMoveCapture = false;
					if (!moves.isEmpty()) {
						changeLastSquarestoOriginalColour();
					}
					if (targetSquare.isOccupied()) {
						capturePiece(targetSquare);
						isMoveCapture = true;
					}
					repositionPiece(startingSquare, targetSquare);
					piece.incrementMoves(); 
					changeTurn();
					logMove(isMoveCapture);
					findAttackedSquares();
					if(isKingOnAttackedSquare(piece)) {
						undoMove();
					}
					else {
						changeLastSquaresToBlue();	
					}
				}
				else if(shortCastleCondition(startingSquare,targetSquare)) {
					castleShort(startingSquare,targetSquare);
				}
				else if(longCastleCondition(startingSquare,targetSquare)) {
					castleLong(startingSquare,targetSquare);
				}
				else {
					startingSquare.resetColour();	
				}		
				startingSquare = null;
				targetSquare = null;
			}
		}
		else {
			outputMessage("Move not possible.");
			startingSquare.resetColour();
			startingSquare=null;
			targetSquare=null;
		}
	}

	/**
	 * Implements the short castling move.
	 * @param startingSquare The square the king is on; e1 or e8.
	 * @param targetSquare The square the king is moved to; g1 or g8.
	 */
	private static void castleShort(Square startingSquare, Square targetSquare) {
		// TODO move the conditions to the conditons method.
		if(startingSquare.getPiece().getMoves()== 0 && isKingOnAttackedSquare(startingSquare.getPiece())==false) {
			Square rookSquare = Board.getSquare(targetSquare.getXcoordinate() + 1, targetSquare.getYcoordinate());
			if (rookSquare.isOccupied()) {
				if (rookSquare.getPiece().getMoves()==0) {
					Square f1Orf8 = Board.getSquare(startingSquare.getXcoordinate()+1, startingSquare.getYcoordinate());
					if((targetSquare.isOccupied()==false&&isSquareAttackedByOpposite(targetSquare,startingSquare.getPiece())==false)&&(f1Orf8.isOccupied()==false&&isSquareAttackedByOpposite(f1Orf8,startingSquare.getPiece())==false)) {
						changeLastSquarestoOriginalColour();
						rookSquare.getPiece().incrementMoves();
						startingSquare.getPiece().incrementMoves();
						repositionPiece(startingSquare,targetSquare);
						repositionPiece(rookSquare,f1Orf8);
						logMove(false);
						changeLastSquaresToBlue();
						changeTurn();
					}
				}
			}
		}
	}
	/**
	 * Checks that the king is not under attack and the squares are free and not being attacked when castling.
	 * @param startingSquare The square the king is on; e1 or e8.
	 * @param targetSquare The square the king is moved to; g1 or g8.
	 * @return
	 */
	private static boolean shortCastleCondition(Square startingSquare, Square targetSquare) {
		if((startingSquare.getXcoordinate()==5&&(startingSquare.getYcoordinate()==1||startingSquare.getYcoordinate()==8))&&(targetSquare.getXcoordinate()==7&&(targetSquare.getYcoordinate()==1||targetSquare.getYcoordinate()==8))) {
			return true;
		}
		return false;
	}

	/**
	 * implements the long castle move
	 * @param startingSquare The kings original square with the king on it; e1 or e8.
	 * @param targetSquare The square the king will be moved to; c1 or c8.
	 */
	private static void castleLong(Square startingSquare, Square targetSquare) {
		changeLastSquarestoOriginalColour();
		startingSquare.getPiece().incrementMoves();
		Board.getSquare(1, startingSquare.getYcoordinate()).getPiece().incrementMoves();
		repositionPiece(startingSquare,targetSquare);
		repositionPiece(Board.getSquare(1, startingSquare.getYcoordinate()),Board.getSquare(4, startingSquare.getYcoordinate()));
		logMove(false);
		changeLastSquaresToBlue();
		changeTurn();
	}
	
	/**
	 * Checks if the long castling move is possible 
	 * @param startingSquare The kings original square with the king on it; e1 or e8.
	 * @param targetSquare	 The square the king will be moved to; c1 or c8.
	 * @return	true if the castling long is possible, otherwise false.
	 */
	private static boolean longCastleCondition(Square startingSquare, Square targetSquare) {
		if(startingSquare.getXcoordinate()!=5&&(startingSquare.getYcoordinate()!=1||startingSquare.getYcoordinate()!=8)) {
			System.out.println("1");
			return false;
		}
		if(targetSquare.getXcoordinate()!=3 && (targetSquare.getYcoordinate()!=1||targetSquare.getYcoordinate()!=8)){
			System.out.println("2");
			return false;
		}
		if(startingSquare.getPiece().getMoves()!=0 || isSquareAttackedByOpposite(startingSquare,startingSquare.getPiece())) {
			System.out.println("3");
			return false;
		}
		if(Board.getSquare(1, startingSquare.getYcoordinate()).isOccupied()==false) {
			System.out.println("4");
			return false;
		}
		if(Board.getSquare(1, startingSquare.getYcoordinate()).getPiece().getMoves()!=0) {
			System.out.println("5");
			return false;
		}
		for(int i = 4 ; i>1 ;i--) {
			Square square = Board.getSquare(i, targetSquare.getYcoordinate());
			if(square.isOccupied()||isSquareAttackedByOpposite(square, startingSquare.getPiece())){
				System.out.println("6" + i);
				return false;
			}
		}
		return true;
	}


	/**
	 * 
	 * @param square The square that is being checked if being attacked.
	 * @param piece Provides the colour to check if the opposite side is attacking.
	 * @return true if the square is being attacked by a piece of the opposite colour, otherwise false.
	 */
	private static boolean isSquareAttackedByOpposite(Square square, Piece piece) {
		if(piece.getColour()==Colour.Black&&getAttackedSquaresWhite().contains(square)) {
			return true;
		}
		if(piece.getColour()==Colour.White&&getAttackedSquaresBlack().contains(square)) {
			return true;
		}
		return false;
	}

	/**
	 * Repositions a piece from a square to another, does not check if the move is legal or not, does not increment moves for the piece.
	 * @param startingSquare square on which the piece is.
	 * @param targetSquare square onto which the piece is repositioned.
	 */
	private static void repositionPiece(Square startingSquare, Square targetSquare) {
		Piece tempPiece = startingSquare.getPiece();
		startingSquare.removePiece();
		targetSquare.addPiece(tempPiece);		
	}

	/**
	 * Checks if the king of piece is on a square that is attacked by an enemy piece.
	 * @param piece
	 * @return
	 */
	private static boolean isKingOnAttackedSquare(Piece piece) {
		return getAttackedSquaresByOppositeColour(piece).contains(piece.getKing().getSquare());
	}

	/**
	 * is called when the targetSquare is Occupied by a piece of the opposite colour, frees
	 * the targetSquare and changes the square for the previous piece to binWhite or binBlack
	 * depending on its colour.
	 * @param targetSquare on which the piece to be captured is.
	 */
	public static void capturePiece(Square targetSquare) {
		Piece targetPiece = targetSquare.getPiece();
		targetPiece.setCaptured(true);
		capturedPieces.add(targetPiece);
		targetSquare.getSquareGraphic().remove(targetPiece.getPieceGraphic());
		targetSquare.getSquareGraphic().repaint();
		targetSquare.setOccupied(false);
		targetSquare.setPiece(null);
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

	private static void findAttackedSquares() {
		attackedSquaresWhite.clear();
		attackedSquaresBlack.clear();
		for(Piece piece: whitePieces) {
			if(piece.isCaptured()==false) {
				piece.findAttackedSquares();
				attackedSquaresWhite.addAll(piece.getAttackedSquares());
			}
		}
		for(Piece piece: blackPieces) {
			if(piece.isCaptured()==false) {
				piece.findAttackedSquares();
				attackedSquaresBlack.addAll(piece.getAttackedSquares());
			}
		}
	}

	public static Set<Square> getAttackedSquaresByOppositeColour(Piece piece){
		if(piece.getColour() == Colour.Black) {
			return attackedSquaresWhite;
		}
		return attackedSquaresBlack;
	}



	public static void undoMove() {
		Square startingSquare = moves.getLast().getTargetSquare();
		Square targetSquare = moves.getLast().getStartingSquare();
		startingSquare.getPiece().decreaseMovesByOne();
		repositionPiece(startingSquare, targetSquare);
		changeLastSquarestoOriginalColour();
		if(moves.get(moves.size()-1).isMoveCapture()) {
			unCaptureLastPiece(startingSquare);
		}
		moves.removeLast();
		changeTurn();
	}


	private static void unCaptureLastPiece(Square targetSquare) {
		Piece piece = capturedPieces.get(capturedPieces.size()-1);
		piece.setCaptured(false);
		targetSquare.addPiece(piece);
		if(piece.getColour()==Colour.Black) {
			GameWindow.getBinWhite().removePiece(piece);
		}
		else {
			GameWindow.getBinBlack().removePiece(piece);
		}
	}

	/**
	 * Changes the last target and starting squares' colours to blue to indicate the last move.
	 */
	private static void changeLastSquaresToBlue() {

		startingSquare.getSquareGraphic().setBackground(startingSquare.getColour().getSquareBlue());
		targetSquare.getSquareGraphic().setBackground(targetSquare.getColour().getSquareBlue());
	}


	/**
	 * Changes the colours of the squares of the previous move back to original, called before a new move is logged.
	 */
	private static void changeLastSquarestoOriginalColour() {
		Square lastStartingSquare = moves.getLast().getStartingSquare();
		Square lastTargetSquare = moves.getLast().getTargetSquare();
		lastStartingSquare.resetColour();
		lastTargetSquare.resetColour();
	}

	private static void changeTurn() {
		turnWhite = !turnWhite;
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
	public static King getKingWhite() {
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
	public static King getKingBlack() {
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


	/**
	 * @return the startingSquare
	 */
	public static Square getStartingSquare() {
		return startingSquare;
	}


	/**
	 * @param startingSquare the startingSquare to set
	 */
	public static void setStartingSquare(Square startingSquare) {
		Game.startingSquare = startingSquare;
	}


	/**
	 * @return the window
	 */
	public static GameWindow getWindow() {
		return window;
	}


	/**
	 * @param window the window to set
	 */
	public static void setWindow(GameWindow window) {
		Game.window = window;
	}


	/**
	 * @return the turnWhite
	 */
	public static boolean isTurnWhite() {
		return turnWhite;
	}


	/**
	 * @param turnWhite the turnWhite to set
	 */
	public static void setTurnWhite(boolean turnWhite) {
		Game.turnWhite = turnWhite;
	}


	/**
	 * @return the moves
	 */
	public static LinkedList<Move> getMoves() {
		return moves;
	}


	/**
	 * @param moves the moves to set
	 */
	public static void setMoves(LinkedList<Move> moves) {
		Game.moves = moves;
	}

	public static Move getLastMove() {
		return moves.getLast();
	}

	/**
	 * @return the whitePieces
	 */
	public Set<Piece> getWhitePieces() {
		return whitePieces;
	}

	/**
	 * @param whitePieces the whitePieces to set
	 */
	public void setWhitePieces(Set<Piece> whitePieces) {
		this.whitePieces = whitePieces;
	}

	/**
	 * @return the blackPieces
	 */
	public Set<Piece> getBlackPieces() {
		return blackPieces;
	}

	/**
	 * @param blackPieces the blackPieces to set
	 */
	public void setBlackPieces(Set<Piece> blackPieces) {
		this.blackPieces = blackPieces;
	}

	/**
	 * @return the attackedSquaresWhite
	 */
	public static Set<Square> getAttackedSquaresWhite() {
		return attackedSquaresWhite;
	}

	/**
	 * @param attackedSquaresWhite the attackedSquaresWhite to set
	 */
	public static void setAttackedSquaresWhite(Set<Square> attackedSquaresWhite) {
		Game.attackedSquaresWhite = attackedSquaresWhite;
	}

	/**
	 * @return the attackedSquaresBlack
	 */
	public static Set<Square> getAttackedSquaresBlack() {
		return attackedSquaresBlack;
	}

	/**
	 * @param attackedSquaresBlack the attackedSquaresBlack to set
	 */
	public static void setAttackedSquaresBlack(Set<Square> attackedSquaresBlack) {
		Game.attackedSquaresBlack = attackedSquaresBlack;
	}

}
