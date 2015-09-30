package swing;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Test {
	public static void main(String[] args) {
		MyForm s = new MyForm();
		s.setVisible(true);
	}
}

class MyForm extends JFrame implements ActionListener {
	public MyForm() {
		JPanel panel = new JPanel();
		panel.setSize(600, 600);
		JButton bt = new JButton("!!");
		bt.addActionListener(this);
		panel.add(bt);
		this.add(panel);
		this.setTitle("test");
		this.setSize(1200, 900);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.dispose();
	}

}