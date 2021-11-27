
import java.awt.*;
import java.awt.event.*;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import java.awt.Font;

/**
 * 
 * @author Tareq Al-Qayar
 * @version 1.0
 * @since 28/10/2021
 *
 */
public class Square implements  ActionListener {

	private Colour colour;
	private boolean occupied;

	private int xcoordinate;
	private int ycoordinate;

	private Piece piece;
	private JPanel squareGraphic;




	/**
	 * @param colour
	 * @param occupied
	 * @param x
	 * @param y
	 */
	public Square(Colour colour, boolean occupied,int x, int y) {
		this.colour = colour;
		this.occupied = occupied;
		this.xcoordinate = x;
		this.ycoordinate = y;
	}

	public Square() {
	}



	/**
	 * Checks if the square is block by another piece of the same colour.
	 * @param piece:piece to check if blocked by another piece of the same colour.
	 * @return true if square has a piece of the same colour otherwise returns false.
	 */
	public boolean isBlocked(Piece piece) {
		if(this.isOccupied() && this.getPiece().getColour() == piece.getColour()) {
			return true;
		}
		return false;
	}

	/**
	 * @return
	 */
	public Colour getColour() {
		return colour;
	}

	/**
	 * @param colour
	 */
	public void setColour(Colour colour) {
		this.colour = colour;
	}

	/**
	 * @return
	 */
	public boolean isOccupied() {
		return occupied;
	}
	
	public boolean OccupiedByOppositeColour(Colour colour) {
		if(isOccupied()) {
			if(getPiece().getColour()!=colour) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param occupied
	 */
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	/**
	 * @return
	 */
	public int getXcoordinate() {
		return xcoordinate;
	}



	/**
	 * @param xcooridiante
	 */
	public void setXcoordinate(char xcooridiante) {
		this.xcoordinate = xcooridiante;
	}

	/**
	 * @return
	 */
	public int getYcoordinate() {
		return ycoordinate;
	}

	/**
	 * @param ycoordinate
	 */
	public void setYcoordinate(int ycoordinate) {
		this.ycoordinate = ycoordinate;
	}

	/**
	 *
	 */
	public String toString() {
		return (char)(xcoordinate + 96)+""+ycoordinate; //+ " " + colour.toString() + " " + isOccupied() + " ";//TODO + piece.toString() with nullPointerException.

	}

	/**
	 * @return the piece
	 */
	public Piece getPiece() {
		// TODO if isOccupied is true
		return piece;
	}



	/**
	 * @param piece the piece to set
	 */
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	
	
	public void addPiece(Piece piece) {
		setOccupied(true);
		this.piece = piece;
		piece.setSquare(this);
		squareGraphic.add(piece.getPieceGraphic());
		squareGraphic.repaint();
	}
	
	public void removePiece() {
		setOccupied(false);
		squareGraphic.remove(piece.getPieceGraphic());
		squareGraphic.repaint();
		piece = null;
	}
	

	public boolean isReachable() {
		// TODO Auto-generated method stub
		return false;
	}

	public JPanel squareGraphic() {
		Square square = this;

		// TODO bottom row has letters bottom right, left column has number top left  
		squareGraphic = new JPanel();
		squareGraphic.setPreferredSize(new Dimension(100,100));
		squareGraphic.setBackground(colour.getSquareColor());
		//squareGraphic.setLayout(new GroupLayout(squareGraphic));
		squareGraphic.setLayout(null);
		
		if(getXcoordinate()==1) {
			JLabel name = new JLabel("" + ycoordinate);
			name.setOpaque(false);
			name.setForeground(Color.black);
			name.setFont(new Font("test",1,16));

			squareGraphic.add(name);
			name.setBounds(5,0,20,20);
		}
		if(getYcoordinate()==1) {
			JLabel name = new JLabel((char)(xcoordinate + 96) + "");
			name.setOpaque(false);
			name.setForeground(Color.black);
			name.setFont(new Font("test",1,16));

			squareGraphic.add(name);
			name.setBounds(85,75,20,20);
		}
		
		squareGraphic.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				squareGraphic.repaint();
				if (Game.getStaringSquare()==null&& square.isOccupied()) {
					Game.setStaringSquare(square);
					//Game.outputMessage(square.getXcoordinate()+""+square.getYcoordinate());
					System.out.println(square.toString());
					square.getSquareGraphic().setBackground(new Color(222, 88, 40));
				}
				else {
					if (Game.getStaringSquare()!=null && Game.getTargetSquare()==null) {
						Game.setTargetSquare(square);
						Game.move();
						System.out.println(square.toString());
					}
				}

			}
		});

		if(isOccupied()) {
			squareGraphic.add(piece.pieceGraphic());
			addPieceGraphic(piece.getPieceGraphic());
			squareGraphic.repaint();
		}
		return squareGraphic;
	}

	public void addPieceGraphic(JPanel pieceGraphic) {
		getSquareGraphic().add(pieceGraphic);
		pieceGraphic.setBounds(0, 10, 100, 100);
		getSquareGraphic().repaint();
	}

	public void resetColour() {
		squareGraphic.setBackground(colour.getSquareColor());
	}

	/**
	 * @return the squareGraphic
	 */
	public JPanel getSquareGraphic() {
		return squareGraphic;
	}

	/**
	 * @param squareGraphic the squareGraphic to set
	 */
	public void setSquareGraphic(JPanel squareGraphic) {
		this.squareGraphic = squareGraphic;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(toString());

	}
}
