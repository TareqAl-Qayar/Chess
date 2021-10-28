import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * 
 * @author Tareq Al-Qayar
 * @version 1.0
 * @since 28/10/2021
 *
 */
public class GameWindow {
	
	private JFrame window;
	private static Bin binWhite;
	private static Bin binBlack;
	private Board board;
	private JTextField inputField;
	private JLabel outputField;

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
		
		
		binWhite = new Bin(Colour.White);
		rightPanel.add(binWhite.getBinGraphic(),BorderLayout.PAGE_END);
		
		binBlack = new Bin(Colour.Black);
		leftPanel.add(binBlack.getBinGraphic(),BorderLayout.PAGE_START);
		
		
		//Text input and output.
		JPanel bottomBar = new JPanel();
		bottomBar.setPreferredSize(new Dimension(1200,25));
		bottomBar.setLayout(new GridLayout());
		background.add(bottomBar,BorderLayout.PAGE_END);
		
		inputField = new JTextField();
		inputField.setPreferredSize(new Dimension(600,25));
		bottomBar.add(inputField);
		
		outputField =  new JLabel();
		outputField.setPreferredSize(new Dimension(600,25));
		outputField.setText("GLHF");
		outputField.setBackground(Color.gray);
		outputField.setForeground(Color.black);
		bottomBar.add(outputField);
		
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

	/**
	 * @return the window
	 */
	public JFrame getWindow() {
		return window;
	}

	/**
	 * @param window the window to set
	 */
	public void setWindow(JFrame window) {
		this.window = window;
	}

	/**
	 * @return the board
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * @param board the board to set
	 */
	public void setBoard(Board board) {
		this.board = board;
	}

	/**
	 * @return the inputField
	 */
	public JTextField getInputField() {
		return inputField;
	}

	/**
	 * @param inputField the inputField to set
	 */
	public void setInputField(JTextField inputField) {
		this.inputField = inputField;
	}

	/**
	 * @return the outputField
	 */
	public JLabel getOutputField() {
		return outputField;
	}

	/**
	 * @param outputField the outputField to set
	 */
	public void setOutputField(JLabel outputField) {
		this.outputField = outputField;
	}
	
	public void setOutputFieldText(String text) {
		outputField.setText(text);
	}

}
