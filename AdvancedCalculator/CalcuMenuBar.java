package AdvancedCalculator;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JTextField;

public class CalcuMenuBar extends JMenuBar implements ActionListener {
	private JMenu settingMn, viewMn;  // Main menus.
	private JMenu modeMn, angleMn, trigoMn;  // Sub menus.
	private JCheckBoxMenuItem stdMode, scntfcMode, degAngle, radAngle, regularTrigo, inverseTrigo;
	
	// Button groups to group corresponding check box and ensure ONLY ONE box can be selected at a time.
	private final ButtonGroup modeGr, angleGr, trigoGr;
	
	CalcuFrame frame;
	ScientificPanel panel;
	
	// Constructor.
	CalcuMenuBar(CalcuFrame frame) {
		// Create a "link" to the calculator frame and panel.
		this.frame = frame;
		
		// Initialize instances.
		settingMn = new JMenu("Settings");
		viewMn = new JMenu("View");
		
		modeMn = new JMenu("Mode");
		angleMn = new JMenu("Angle");
		trigoMn = new JMenu("Trigonometry");
		
		stdMode = new JCheckBoxMenuItem("Standard");
		scntfcMode = new JCheckBoxMenuItem("Scientific");
		
		degAngle = new JCheckBoxMenuItem("Degree");
		radAngle = new JCheckBoxMenuItem("Radian");
		
		regularTrigo = new JCheckBoxMenuItem("Regular");
		inverseTrigo = new JCheckBoxMenuItem("Inverse");
		
		modeGr = new ButtonGroup();
		angleGr = new ButtonGroup();
		trigoGr = new ButtonGroup();
		
		// Register listeners.
		stdMode.addActionListener(this);
		scntfcMode.addActionListener(this);
		
		degAngle.addActionListener(this);
		radAngle.addActionListener(this);
		
		regularTrigo.addActionListener(this);
		inverseTrigo.addActionListener(this);
		
		// Add menu item into its menu.
		modeMn.add(stdMode);
		modeMn.add(scntfcMode);
		
		angleMn.add(degAngle);
		angleMn.add(radAngle);
		
		trigoMn.add(regularTrigo);
		trigoMn.add(inverseTrigo);
		
		settingMn.add(modeMn);
		settingMn.add(angleMn);
		viewMn.add(trigoMn);
		
		this.add(settingMn);
		this.add(viewMn);
		
		// Add check box menu items into its button group.
		modeGr.add(stdMode);
		modeGr.add(scntfcMode);
		
		angleGr.add(degAngle);
		angleGr.add(radAngle);
		
		trigoGr.add(regularTrigo);
		trigoGr.add(inverseTrigo);
		
		// Set the default mode, angle unit, trigonometric functions state when the calculator has been opened.
		radAngle.setSelected(true);
		regularTrigo.setSelected(true);
		
		// Default mode is standard.
		stdMode.doClick();
	}
	
	public void actionPerformed(ActionEvent e) {
		// Standard mode check box.
		if (e.getSource().equals(stdMode)) {
			trigoMn.setEnabled(false);
			angleMn.setEnabled(false);
			
			frame.setSize(466, 487);
			frame.scntfcPanel.setVisible(false);
			frame.stdPanel.setVisible(true);
			
			frame.modeTf.setText("Std");
			
			frame.angleUnitTf.setBackground(new Color(50, 191, 54));
			frame.angleUnitTf.setForeground(Color.black);
			frame.angleUnitTf.setText("- - - -");
		}
		
		// Scientific mode check box.
		else if (e.getSource().equals(scntfcMode)) {
			trigoMn.setEnabled(true);
			angleMn.setEnabled(true);
			
			frame.setSize(466, 725);
			frame.scntfcPanel.setVisible(true);
			frame.stdPanel.setVisible(false);
			
			frame.modeTf.setText("Sntf");
			
			frame.angleUnitTf.setBackground(Color.black);
			frame.angleUnitTf.setForeground(new Color(50, 191, 54));
			
			if (frame.scntfcPanel.degAngle)
				frame.angleUnitTf.setText("Degree");
			else
				frame.angleUnitTf.setText("Radian");
		}
		
		// Degree angle check box.
		else if (e.getSource().equals(degAngle)) {			
			frame.scntfcPanel.degAngle = true;
			
			frame.angleUnitTf.setText("Degree");
		}
		
		// Radian angle check box.
		else if (e.getSource().equals(radAngle)) {
			frame.scntfcPanel.degAngle = false;
			
			frame.angleUnitTf.setText("Radian");
		}
		
		// Regular trigonometric functions check box.
		else if (e.getSource().equals(regularTrigo)) {
			frame.scntfcPanel.inverseFuncs = false;
			
			frame.scntfcPanel.sinBt.setText("sin(x)");
			frame.scntfcPanel.cosBt.setText("cos(x)");
			frame.scntfcPanel.tanBt.setText("tan(x)");
			frame.scntfcPanel.cotBt.setText("cot(x)");
		}
		
		// Inverse trigonometric functions check box.
		else if (e.getSource().equals(inverseTrigo)) {
			frame.scntfcPanel.inverseFuncs = true;
			
			frame.scntfcPanel.sinBt.setText("asin(x)");
			frame.scntfcPanel.cosBt.setText("acos(x)");
			frame.scntfcPanel.tanBt.setText("atan(x)");
			frame.scntfcPanel.cotBt.setText("acot(x)");
		}
	}

}
