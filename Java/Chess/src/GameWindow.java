import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
public class GameWindow {
	
	JFrame window;
	private static Bin binWhite;
	private static Bin binBlack;
	Board board;
	

	public GameWindow(Board board) {
		this.board = board;
		createGameWindow();
	}
	
	private void createGameWindow() {
		window = new JFrame("Chess");
		window.setSize(1200,800);
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		JPanel background = new JPanel();
		background.setLayout(new BorderLayout());
		window.add(background);
		
		JPanel leftPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension(200,800));
		leftPanel.setBackground(Color.gray);
		leftPanel.setLayout(new BorderLayout());
		
		JPanel rightPanel = new JPanel();
		rightPanel.setPreferredSize(new Dimension(200,800));
		rightPanel.setBackground(Color.gray);
		rightPanel.setLayout(new BorderLayout());
		
		background.add(leftPanel,BorderLayout.LINE_START);
		background.add(board.getBoardGraphic());
		background.add(rightPanel,BorderLayout.LINE_END);
		
		
		Bin binWhite = new Bin(Colour.White);
		rightPanel.add(binWhite.getBinGraphic(),BorderLayout.PAGE_END);
		
		Bin binBlack = new Bin(Colour.Black);
		leftPanel.add(binBlack.getBinGraphic(),BorderLayout.PAGE_START);
		
		window.pack();
		window.setVisible(true);
	}
	

	/**
	 * @return the binWhite
	 */
	public static Bin getBinWhite() {
		return binWhite;
	}

	/**
	 * @param binWhite the binWhite to set
	 */
	public static void setBinWhite(Bin binWhite) {
		GameWindow.binWhite = binWhite;
	}

	/**
	 * @return the binBlack
	 */
	public static Bin getBinBlack() {
		return binBlack;
	}

	/**
	 * @param binBlack the binBlack to set
	 */
	public static void setBinBlack(Bin binBlack) {
		GameWindow.binBlack = binBlack;
	}

}
