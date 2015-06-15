package com.deamon.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblId = new JLabel("ID");
		lblId.setBounds(10, 10, 54, 15);
		panel.add(lblId);

		textField = new JTextField();
		textField.setBounds(28, 6, 66, 21);
		panel.add(textField);
		textField.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 40, 312, 120);
		panel.add(scrollPane);

		JTextArea textArea = new JTextArea();
		textArea.setText("123");
		scrollPane.setViewportView(textArea);

		JLabel lblCurrentUser = new JLabel("Current User");
		lblCurrentUser.setBounds(332, 10, 82, 15);
		panel.add(lblCurrentUser);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(332, 40, 82, 120);
		panel.add(scrollPane_1);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 167, 312, 74);
		panel.add(scrollPane_2);

		JTextArea textArea_1 = new JTextArea();
		scrollPane_2.setViewportView(textArea_1);

		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(332, 170, 82, 71);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("use");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(102, 6, 93, 23);
		panel.add(btnNewButton_1);
	}
}
