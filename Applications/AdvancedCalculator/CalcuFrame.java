package AdvancedCalculator;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class CalcuFrame extends JFrame {
	protected final JTextField ioTf = new JTextField();  // Input and output text field.
	protected final JTextField sttTf = new JTextField();  // Status text field.
	protected final JTextField angleUnitTf = new JTextField();  // Measuring unit of trigonometric angle display field.
	protected final JTextField modeTf = new JTextField();
	
	protected ScientificPanel scntfcPanel = new ScientificPanel(this);  // Scientific panel instance.
	protected StandardPanel stdPanel = new StandardPanel(this);  // Scientific panel instance.
	
	// Constructor.
	CalcuFrame() {
		// Configure the angle unit text field.
		angleUnitTf.setBounds(361, 7, 80, 30);
		angleUnitTf.setFont(new Font("ds-digital", Font.ITALIC, 25));
		angleUnitTf.setEditable(false);
		angleUnitTf.setHorizontalAlignment(JTextField.CENTER);
		
		// Configure the mode display text field.
		modeTf.setBounds(361, 40, 80, 50);
		modeTf.setFont(new Font("ds-digital", Font.ITALIC, 35));
		modeTf.setBackground(Color.black);
		modeTf.setForeground(new Color(50, 191, 54));
		modeTf.setEditable(false);
		modeTf.setHorizontalAlignment(JTextField.CENTER);
		
		// Configure the input text field.
		ioTf.setBounds(10, 40, 343, 50);
		ioTf.setFont(new Font("ds-digital", Font.BOLD, 35));
		ioTf.setBackground(Color.black);
		ioTf.setForeground(new Color(50, 191, 54));
		ioTf.setEditable(false);
		
		// Configure the status text field.
		sttTf.setBounds(10, 7, 343, 30);
		sttTf.setFont(new Font("ds-digital", Font.BOLD, 25));
		sttTf.setBackground(Color.black);
		sttTf.setForeground(new Color(50, 191, 54));
		sttTf.setEditable(false);
		
		// Add components into the frame.
		this.add(sttTf);
		this.add(ioTf);
		this.add(angleUnitTf);
		this.add(modeTf);
		this.add(stdPanel);
		this.add(scntfcPanel);
		
		// Set menu bar.
		this.setJMenuBar(new CalcuMenuBar(this));
		
		// Configure the frame,
		setSize(466, 487);  // The size of standard mode
		setTitle("Advanced Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		setVisible(true);
	}
}
