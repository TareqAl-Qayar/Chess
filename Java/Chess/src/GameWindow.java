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
		
		p.add(b.boardGraphic());
//		JLabel l1 = new JLabel("label1");
//		p.add(new Button("B1"));
//		p.add(l1);
//		
//		JPanel p2 = new JPanel();
//		p2.setBackground(Color.orange);
//		p.add("SOUTH",p2);
//		
//		l1.setBackground(Color.white);
		f.pack();
		f.setVisible(true);
	}

}
