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
		f.setBounds(0,0,250,250);
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		
		
		JPanel p = new JPanel();
		p.setBackground(Color.gray);
		//p.setLayout(new GridLayout());
		
		f.add(p);
		
		Board b = new Board();
		Game g = new Game();
		g.setBoard();
		p.add(b.boardGraphic());

		f.pack();
		f.setVisible(true);
	}

}
