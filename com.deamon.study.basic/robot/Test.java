package robot;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Test {
	public static void main(String[] args) {
		Panel p = new Panel();
	}
}

class Panel extends JFrame implements ActionListener {

	private JPanel panel;
	Canvas canv = new Mycanvas();
	JButton bt = new JButton("»æÖÆ");

	// init main panel
	public Panel() {
		this.setSize(400, 400);
		this.setTitle("act");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		init();
		this.add(panel);
		// this.add(new Mycanvas());
		this.setVisible(true);
	}

	private void init() {
		panel = new JPanel();
		bt.addActionListener(this);
		bt.setActionCommand("repaint");
		panel.add(canv);
		panel.add(bt);
		panel.setBackground(Color.YELLOW);
	}

	public void actionPerformed(ActionEvent e) {
		String actcom = e.getActionCommand();
		if (actcom.equals("repaint")) {
			if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "paint!")) {
				System.out.println("1");
				panel.remove(canv);
				canv.setSize(400, 400);
				panel.add(canv);
			}
		}
	}

	class Mycanvas extends Canvas {
		public Mycanvas() {
			this.setSize(200, 200);
		}

		public void paint(Graphics g) {

			int width = Panel.this.getWidth();
			int height = Panel.this.getHeight();
			g.clearRect(0, 0, width, height);
			g.setColor(Color.BLACK);
			Rectangle rect = Panel.this.getBounds();
			g.drawLine(rect.x, rect.y, rect.width, rect.height);
		}
	}
}