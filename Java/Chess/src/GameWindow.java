import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
public class GameWindow {

	public GameWindow() {
		// TODO Auto-generated constructor stub
		//Dimension d = new Dimension(500, 500);
		JFrame f = new JFrame("Chess");
		//f.setSize(d);
		f.setBounds(0,0,1920,1080);
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		
		
		JPanel background = new JPanel();
		background.setBackground(Color.gray);
		
		//TODO fix layout, next make place for captured pieces, implement capture, implement move with capture method. add listeners.
		//background.setLayout(new GridLayout(0,3));
		
		JPanel leftSide = new JPanel();
		leftSide.setPreferredSize(new Dimension(200,800));
		leftSide.setBackground(Color.orange);
		JPanel rightSide = new JPanel();
		rightSide.setPreferredSize(new Dimension(200,800));
		rightSide.setBackground(Color.black);
		f.add(background);
		
		Board b = new Board();
		Game g = new Game();
		g.setBoard();
		
		background.add(leftSide);
		background.add(b.boardGraphic());
		background.add(rightSide);
		f.pack();
		f.setVisible(true);
	}

}
