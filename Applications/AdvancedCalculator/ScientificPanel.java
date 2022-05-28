package AdvancedCalculator;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ScientificPanel extends JPanel implements ActionListener {
	private JButton[] numberButtons = new JButton[10];
	private JButton[] funcButtons = new JButton[25];
	private final JButton addBt, subBt, mulBt, divBt;
	private final JButton decBt, eqlBt, delBt, clrBt, negBt;
	private final JButton pwrBt, facBt, absBt, piBt, eBt;
	private final JButton pwrOf10Bt, expBt, sqrtBt, cbrtBt, recBt;
	protected final JButton sinBt, cosBt, tanBt, cotBt, lnBt, logBt;

	protected Font buttonFont = new Font("arial", Font.BOLD, 20);

	int eqlPressed = 0; // The time that equal button has been pressed.
	int operPressed = 0; // The time that any of those operator buttons have been pressed.
	boolean degAngle = false;
	boolean inverseFuncs = false;

	double num1 = 0, num2 = 0, result = 0;
	char operator = 'n'; // Initial operator is null.

	// Constants.
	final double PI = 3.14159265359;
	final double E = 2.71828182846;
	final Color Gray = new Color(80, 80, 80);
	final Color SeaGreen = new Color(21, 179, 118);
	final Color NauticalBlue = new Color(21, 163, 179);
	final Color Orange = new Color(219, 149, 26);

	CalcuFrame frame;

	// Constructor.
	ScientificPanel(CalcuFrame frame) {
		// Create a "link" to the calculator frame.
		this.frame = frame;

		// Initialize number buttons.
		for (int i = 0; i < 10; i++) {
			numberButtons[i] = new JButton(String.valueOf(i));
			numberButtons[i].addActionListener(this);
			numberButtons[i].setFont(buttonFont);
			numberButtons[i].setFocusable(false);
		}

		// Initialize function buttons.
		addBt = new JButton("+");
		subBt = new JButton("-");
		mulBt = new JButton("*");
		divBt = new JButton("/");
		decBt = new JButton(".");
		eqlBt = new JButton("=");

		delBt = new JButton("Del");
		clrBt = new JButton("AC");
		negBt = new JButton("(-)");
		pwrBt = new JButton("x^y");
		facBt = new JButton("x!");
		absBt = new JButton("|x|");
		recBt = new JButton("1/x");
		expBt = new JButton("e^x");
		pwrOf10Bt = new JButton("10^x");
		piBt = new JButton("pi");
		eBt = new JButton("e");

		sqrtBt = new JButton("sqrt(x)");
		cbrtBt = new JButton("cbrt(x)");
		sinBt = new JButton("sin(x)");
		cosBt = new JButton("cos(x)");
		tanBt = new JButton("tan(x)");
		cotBt = new JButton("cot(x)");
		lnBt = new JButton("ln(x)");
		logBt = new JButton("log(x)");

		funcButtons[0] = addBt;
		funcButtons[1] = subBt;
		funcButtons[2] = mulBt;
		funcButtons[3] = divBt;
		funcButtons[4] = decBt;
		funcButtons[5] = eqlBt;
		funcButtons[6] = delBt;
		funcButtons[7] = clrBt;
		funcButtons[8] = negBt;
		funcButtons[9] = pwrBt;
		funcButtons[10] = facBt;
		funcButtons[11] = absBt;
		funcButtons[12] = recBt;
		funcButtons[13] = expBt;
		funcButtons[14] = pwrOf10Bt;
		funcButtons[15] = piBt;
		funcButtons[16] = eBt;
		funcButtons[17] = sqrtBt;
		funcButtons[18] = cbrtBt;
		funcButtons[19] = sinBt;
		funcButtons[20] = cosBt;
		funcButtons[21] = tanBt;
		funcButtons[22] = cotBt;
		funcButtons[23] = lnBt;
		funcButtons[24] = logBt;

		// Configure function buttons properties.
		for (int i = 0; i < 25; i++) {
			funcButtons[i].addActionListener(this);
			funcButtons[i].setFont(buttonFont);
			funcButtons[i].setFocusable(false);
		}

		// Set the color for some function buttons.
		negBt.setBackground(Orange);
		delBt.setBackground(Orange);
		clrBt.setBackground(Orange);

		eqlBt.setBackground(new Color(166, 33, 33));
		eqlBt.setForeground(Color.white);

		sinBt.setBackground(Gray);
		cosBt.setBackground(Gray);
		tanBt.setBackground(Gray);
		cotBt.setBackground(Gray);

		sinBt.setForeground(Color.white);
		cosBt.setForeground(Color.white);
		tanBt.setForeground(Color.white);
		cotBt.setForeground(Color.white);

		sqrtBt.setBackground(SeaGreen);
		cbrtBt.setBackground(SeaGreen);
		lnBt.setBackground(SeaGreen);
		logBt.setBackground(SeaGreen);
		facBt.setBackground(SeaGreen);
		absBt.setBackground(SeaGreen);
		recBt.setBackground(SeaGreen);
		expBt.setBackground(SeaGreen);
		pwrOf10Bt.setBackground(SeaGreen);

		piBt.setBackground(Color.white);
		eBt.setBackground(Color.white);

		pwrBt.setBackground(NauticalBlue);
		addBt.setBackground(NauticalBlue);
		subBt.setBackground(NauticalBlue);
		mulBt.setBackground(NauticalBlue);
		divBt.setBackground(NauticalBlue);

		// Set the custom font size for some function button.
		pwrOf10Bt.setFont(new Font("arial", Font.BOLD, 19));
		sqrtBt.setFont(new Font("arial", Font.BOLD, 13));
		cbrtBt.setFont(new Font("arial", Font.BOLD, 13));
		sinBt.setFont(new Font("arial", Font.BOLD, 12));
		cosBt.setFont(new Font("arial", Font.BOLD, 12));
		tanBt.setFont(new Font("arial", Font.BOLD, 12));
		cotBt.setFont(new Font("arial", Font.BOLD, 12));
		lnBt.setFont(new Font("arial", Font.BOLD, 13));
		logBt.setFont(new Font("arial", Font.BOLD, 13));

		// Add buttons into the panel:
		// 1st row.
		this.add(piBt);
		this.add(eBt);
		this.add(negBt);
		this.add(delBt);
		this.add(clrBt);

		// 2nd row.
		this.add(logBt);
		this.add(cbrtBt);
		this.add(expBt);
		this.add(pwrOf10Bt);
		this.add(recBt);

		// 3rd row.
		this.add(lnBt);
		this.add(sqrtBt);
		this.add(facBt);
		this.add(absBt);
		this.add(pwrBt);

		// 4th row.
		this.add(sinBt);
		this.add(numberButtons[7]);
		this.add(numberButtons[8]);
		this.add(numberButtons[9]);
		this.add(addBt);

		// 5th row.
		this.add(cosBt);
		this.add(numberButtons[4]);
		this.add(numberButtons[5]);
		this.add(numberButtons[6]);
		this.add(subBt);

		// 6th row.
		this.add(tanBt);
		this.add(numberButtons[1]);
		this.add(numberButtons[2]);
		this.add(numberButtons[3]);
		this.add(mulBt);

		// 7th row.
		this.add(cotBt);
		this.add(decBt);
		this.add(numberButtons[0]);
		this.add(eqlBt);
		this.add(divBt);

		// Configure the panel.
		this.setBounds(10, 100, 430, 555);
		this.setLayout(new GridLayout(7, 5, 10, 10));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			// Numeric buttons.
			for (int i = 0; i < 10; i++)
				if (e.getSource().equals(numberButtons[i])) {
					frame.ioTf.setText(frame.ioTf.getText().concat(String.valueOf(i)));

					if (operator != 'n')
						frame.sttTf.setText(String.valueOf(operator));
					else
						frame.sttTf.setText("");
				}

			// Decimal button.
			if (e.getSource().equals(decBt))
				frame.ioTf.setText(frame.ioTf.getText().concat("."));

			// Negative value button.
			else if (e.getSource().equals(negBt))
				frame.ioTf.setText(frame.ioTf.getText().concat("-"));

			// Pi constants button.
			else if (e.getSource().equals(piBt))
				frame.ioTf.setText(String.valueOf(PI));

			// Euler constants button.
			else if (e.getSource().equals(eBt))
				frame.ioTf.setText(String.valueOf(E));

			// Delete button.
			else if (e.getSource().equals(delBt)) {
				String temp = frame.ioTf.getText();
				frame.ioTf.setText("");
				if (operator != 'n')
					frame.sttTf.setText(String.valueOf(operator));
				else
					frame.sttTf.setText("");

				for (int i = 0; i < temp.length() - 1; i++)
					frame.ioTf.setText(frame.ioTf.getText() + temp.charAt(i));
			}

			// Clear button.
			else if (e.getSource().equals(clrBt)) {
				// Reset variables.
				num1 = 0;
				num2 = 0;
				result = 0;
				eqlPressed = 0;
				operPressed = 0;
				operator = 'n';
				frame.ioTf.setText("");
				frame.sttTf.setText("");

				// Set these buttons color to its default.
				addBt.setBackground(NauticalBlue);
				subBt.setBackground(NauticalBlue);
				mulBt.setBackground(NauticalBlue);
				divBt.setBackground(NauticalBlue);
				pwrBt.setBackground(NauticalBlue);
			}

			// Factorial button.
			else if (e.getSource().equals(facBt)) {
				if (frame.ioTf.getText().isBlank())
					throw new MissingRequiredValueException("Enter the value first");

				// If the operator hasn't been chose.
				else if (operator == 'n') {
					int numTemp = Integer.parseInt(frame.ioTf.getText());
					frame.sttTf.setText("fac(" + num1 + ") = ");

					int fact = 1;
					for (int i = 1; i <= numTemp; i++)
						fact *= i;

					num1 = (double) fact;
					frame.ioTf.setText(String.valueOf(num1));
				}

				// Otherwise,
				else {
					int numTemp = Integer.parseInt(frame.ioTf.getText());
					frame.sttTf.setText("fac(" + num2 + ") = ");

					int fact = 1;
					for (int i = 1; i <= numTemp; i++)
						fact *= i;

					num2 = (double) fact;
					frame.ioTf.setText(String.valueOf(num2));
				}
			}

			// Absolute button.
			else if (e.getSource().equals(absBt)) {
				if (frame.ioTf.getText().isBlank())
					throw new MissingRequiredValueException("Enter the value first");

				// If the operator hasn't been chose.
				else if (operator == 'n') {
					num1 = Double.parseDouble(frame.ioTf.getText());

					frame.sttTf.setText("abs(" + num1 + ") = ");
					num1 = Math.abs(num1);
					frame.ioTf.setText(String.valueOf(num1));
				}

				// Otherwise,
				else {
					num2 = Double.parseDouble(frame.ioTf.getText());

					frame.sttTf.setText("abs(" + num2 + ") = ");
					num2 = Math.abs(num2);
					frame.ioTf.setText(String.valueOf(num2));
				}
			}

			// Sine and Arcsine button.
			else if (e.getSource().equals(sinBt)) {
				if (frame.ioTf.getText().isBlank())
					throw new MissingRequiredValueException("Enter the value first");

				// If the operator hasn't been chose, then assign the result to num1.
				else if (operator == 'n') {
					num1 = Double.parseDouble(frame.ioTf.getText());

					// Set the status text field to the corresponding trigonometric functions state.
					if (inverseFuncs)
						frame.sttTf.setText("arcsin(" + num1 + ") = ");
					else
						frame.sttTf.setText("sin(" + num1 + ") = ");

					// If the angle is measured in degree.
					if (degAngle)
						// If they are inverse functions.
						if (inverseFuncs)
							num1 = Math.toDegrees(Math.asin(num1));
						// Else if they are regular functions.
						else
							num1 = Math.sin(Math.toRadians(num1));

					// Else if the angle is measured in radian.
					else if (inverseFuncs)
						num1 = Math.asin(num1);
					else
						num1 = Math.sin(num1);

					frame.ioTf.setText(String.valueOf(num1));
				}

				// Otherwise, assign the result to num2.
				else {
					num2 = Double.parseDouble(frame.ioTf.getText());

					if (inverseFuncs)
						frame.sttTf.setText("arcsin(" + num2 + ") = ");
					else
						frame.sttTf.setText("sin(" + num2 + ") = ");

					if (degAngle)
						if (inverseFuncs)
							num2 = Math.toDegrees(Math.asin(num2));
						else
							num2 = Math.sin(Math.toRadians(num2));

					else if (inverseFuncs)
						num2 = Math.asin(num2);
					else
						num2 = Math.sin(num2);

					frame.ioTf.setText(String.valueOf(num2));

				}
			}

			// Cosine and Arccosine button.
			else if (e.getSource().equals(cosBt)) {
				if (frame.ioTf.getText().isBlank())
					throw new MissingRequiredValueException("Enter the value first");

				// If the operator hasn't been chose, then assign the result to num1.
				else if (operator == 'n') {
					num1 = Double.parseDouble(frame.ioTf.getText());

					// Set the status text field to the corresponding trigonometric functions state.
					if (inverseFuncs)
						frame.sttTf.setText("arccos(" + num1 + ") = ");
					else
						frame.sttTf.setText("cos(" + num1 + ") = ");

					// If the angle is measured in degree.
					if (degAngle)
						// If they are inverse functions.
						if (inverseFuncs)
							num1 = Math.toDegrees(Math.acos(num1));
						// Else if they are regular functions.
						else
							num1 = Math.cos(Math.toRadians(num1));

					// Else if the angle is measured in radian.
					else if (inverseFuncs)
						num1 = Math.acos(num1);
					else
						num1 = Math.cos(num1);

					frame.ioTf.setText(String.valueOf(num1));
				}

				// Otherwise, assign the result to num2.
				else {
					num2 = Double.parseDouble(frame.ioTf.getText());

					if (inverseFuncs)
						frame.sttTf.setText("arccos(" + num2 + ") = ");
					else
						frame.sttTf.setText("cos(" + num2 + ") = ");

					if (degAngle)
						if (inverseFuncs)
							num2 = Math.toDegrees(Math.acos(num2));
						else
							num2 = Math.cos(Math.toRadians(num2));

					else if (inverseFuncs)
						num2 = Math.acos(num2);
					else
						num2 = Math.cos(num2);

					frame.ioTf.setText(String.valueOf(num2));

				}
			}

			// Tangent and Arctangent button.
			else if (e.getSource().equals(tanBt)) {
				if (frame.ioTf.getText().isBlank())
					throw new MissingRequiredValueException("Enter the value first");

				// If the operator hasn't been chose, then assign the result to num1.
				else if (operator == 'n') {
					num1 = Double.parseDouble(frame.ioTf.getText());

					// Set the status text field to the corresponding trigonometric functions state.
					if (inverseFuncs)
						frame.sttTf.setText("arctan(" + num1 + ") = ");
					else
						frame.sttTf.setText("tan(" + num1 + ") = ");

					// If the angle is measured in degree.
					if (degAngle)
						// If they are inverse functions.
						if (inverseFuncs)
							num1 = Math.toDegrees(Math.atan(num1));
						// Else if they are regular functions.
						else
							num1 = Math.tan(Math.toRadians(num1));

					// Else if the angle is measured in radian.
					else if (inverseFuncs)
						num1 = Math.atan(num1);
					else
						num1 = Math.tan(num1);

					frame.ioTf.setText(String.valueOf(num1));
				}

				// Otherwise, assign the result to num2.
				else {
					num2 = Double.parseDouble(frame.ioTf.getText());

					if (inverseFuncs)
						frame.sttTf.setText("arctan(" + num2 + ") = ");
					else
						frame.sttTf.setText("tan(" + num2 + ") = ");

					if (degAngle)
						if (inverseFuncs)
							num2 = Math.toDegrees(Math.atan(num2));
						else
							num2 = Math.tan(Math.toRadians(num2));

					else if (inverseFuncs)
						num2 = Math.atan(num2);
					else
						num2 = Math.tan(num2);

					frame.ioTf.setText(String.valueOf(num2));

				}
			}

			// Cotangent and Arccotangent button.
			else if (e.getSource().equals(cotBt)) {
				if (frame.ioTf.getText().isBlank())
					throw new MissingRequiredValueException("Enter the value first");

				// If the operator hasn't been chose, then assign the result to num1.
				else if (operator == 'n') {
					num1 = Double.parseDouble(frame.ioTf.getText());

					// Set the status text field to the corresponding trigonometric functions state.
					if (inverseFuncs)
						frame.sttTf.setText("arccot(" + num1 + ") = ");
					else
						frame.sttTf.setText("cot(" + num1 + ") = ");

					// If the angle is measured in degree.
					if (degAngle)
						// If they are inverse functions.
						if (inverseFuncs)
							// If num1 is 0, then arccot(0) = pi / 2.
							if (num1 == 0)
								num1 = PI / 2.0;

							// Else if num1 > 0, then arccot(num1) = arctan(1 / num1).
							else if (num1 > 0)
								num1 = Math.toDegrees(Math.atan(1.0 / num1));

							// Otherwise, then arccot(num1) = pi + arctan(1 / num1).
							else
								num1 = Math.toDegrees(PI + Math.atan(1.0 / num1));

						// Else if they are regular functions.
						else
							// If num1 is 0, then cot(0) = 1 / tan(0) = INFINITY.
							// Otherwise, calculate the cotangent normally.
							num1 = 1.0 / Math.tan(Math.toRadians(num1));

					// Else if the angle is measured in radian.
					else if (inverseFuncs)
						if (num1 == 0)
							num1 = PI / 2.0;

						else if (num1 > 0)
							num1 = Math.atan(1.0 / num1);

						else
							num1 = PI + Math.atan(1.0 / num1);

					// Otherwise, radian angle and regular functions.
					else
						num1 = 1.0 / Math.tan(num1);

					// Print out the result.
					frame.ioTf.setText(String.valueOf(num1));
				}

				// Otherwise, assign the result to num2.
				else {
					num2 = Double.parseDouble(frame.ioTf.getText());

					if (inverseFuncs)
						frame.sttTf.setText("arccot(" + num2 + ") = ");
					else
						frame.sttTf.setText("cot(" + num2 + ") = ");

					// If the angle is measured in degree.
					if (degAngle)
						// If they are inverse functions.
						if (inverseFuncs)
							// If num2 is 0, then arccot(0) = pi / 2.
							if (num2 == 0)
								num2 = PI / 2.0;

							// Else if num1 > 0, then arccot(num1) = arctan(1 / num1).
							else if (num2 > 0)
								num2 = Math.toDegrees(Math.atan(1.0 / num2));

							// Otherwise, then arccot(num2) = pi + arctan(1 / num2).
							else
								num2 = Math.toDegrees(PI + Math.atan(1.0 / num2));

						// Else if they are regular functions.
						else
							// If num2 is 0, then cot(0) = 1 / tan(0) = INFINITY.
							// Otherwise, calculate the cotangent normally.
							num2 = 1.0 / Math.tan(Math.toRadians(num2));

					// Else if the angle is measured in radian.
					else if (inverseFuncs)
						if (num2 == 0)
							num2 = PI / 2.0;

						else if (num2 > 0)
							num2 = Math.atan(1.0 / num2);

						else
							num2 = PI + Math.atan(1.0 / num2);

					// Otherwise, radian angle and regular functions.
					else
						num2 = 1.0 / Math.tan(num2);

					// Print out the result.
					frame.ioTf.setText(String.valueOf(num2));

				}
			}

			// Reciprocal value button.
			else if (e.getSource().equals(recBt)) {
				if (frame.ioTf.getText().isBlank())
					throw new MissingRequiredValueException("Enter the value first");

				// If the operator hasn't been chose, then assign the result to num1.
				else if (operator == 'n') {
					num1 = Double.parseDouble(frame.ioTf.getText());

					frame.sttTf.setText("1 / " + num1 + " = ");

					num1 = Math.pow(num1, -1);
					frame.ioTf.setText(String.valueOf(num1));
				}

				// Otherwise, assign the result to num2.
				else {
					num2 = Double.parseDouble(frame.ioTf.getText());

					frame.sttTf.setText("1 / " + num2 + " = ");

					num2 = Math.pow(num2, -1);
					frame.ioTf.setText(String.valueOf(num2));
				}
			}

			// Power of Euler value button.
			else if (e.getSource().equals(expBt)) {
				if (frame.ioTf.getText().isBlank())
					throw new MissingRequiredValueException("Enter the exponent first");

				// If the operator hasn't been chose, then assign the result to num1.
				else if (operator == 'n') {
					num1 = Double.parseDouble(frame.ioTf.getText());

					frame.sttTf.setText("e ^ " + num1 + " = ");

					// If there is a negative number or NaN, then the result is NaN.
					// If there is a positive infinity, then the result is positive infinity.
					// If there is a -INFINITY, then the result is 0.
					// If there is an 0, then the result is 1.
					num1 = Math.exp(num1);
					frame.ioTf.setText(String.valueOf(num1));
				}

				// Otherwise, assign the result to num2.
				else {
					num2 = Double.parseDouble(frame.ioTf.getText());

					frame.sttTf.setText("e ^ " + num2 + " = ");

					num2 = Math.exp(num2);
					frame.ioTf.setText(String.valueOf(num2));
				}
			}

			// Power of 10 button.
			else if (e.getSource().equals(pwrOf10Bt)) {
				if (frame.ioTf.getText().isBlank())
					throw new MissingRequiredValueException("Enter the exponent first");

				// If the operator hasn't been chose, then assign the result to num1.
				else if (operator == 'n') {
					num1 = Double.parseDouble(frame.ioTf.getText());

					frame.sttTf.setText("10 ^ " + num1 + " = ");

					num1 = Math.pow(10, num1);
					frame.ioTf.setText(String.valueOf(num1));
				}

				// Otherwise, assign the result to num2.
				else {
					num2 = Double.parseDouble(frame.ioTf.getText());

					frame.sttTf.setText("10 ^ " + num2 + " = ");

					num2 = Math.pow(10, num2);
					frame.ioTf.setText(String.valueOf(num2));
				}
			}

			// Logarithm (base e) button.
			else if (e.getSource().equals(lnBt)) {
				if (frame.ioTf.getText().isBlank())
					throw new MissingRequiredValueException("Enter the value first");

				// If the operator hasn't been chose, then assign the result to num1.
				else if (operator == 'n') {
					num1 = Double.parseDouble(frame.ioTf.getText());

					frame.sttTf.setText("ln(" + num1 + ") = ");

					// If there is a negative number or NaN, then the result is NaN.
					// If there is a positive infinity, then the result is positive infinity.
					// If there is a 0, then the result is -INFINITY.
					// If there is an 1, then the result is 0.
					num1 = Math.log(num1);
					frame.ioTf.setText(String.valueOf(num1));
				}

				// Otherwise, assign the result to num2.
				else {
					num2 = Double.parseDouble(frame.ioTf.getText());

					frame.sttTf.setText("ln(" + num2 + ") = ");

					num2 = Math.log(num2);
					frame.ioTf.setText(String.valueOf(num2));
				}
			}

			// Logarithm (base 10) button.
			else if (e.getSource().equals(logBt)) {
				if (frame.ioTf.getText().isBlank())
					throw new MissingRequiredValueException("Enter the value first");

				// If the operator hasn't been chose, then assign the result to num1.
				else if (operator == 'n') {
					num1 = Double.parseDouble(frame.ioTf.getText());

					frame.sttTf.setText("log(" + num1 + ") = ");

					// If there is a negative number or NaN, then the result is NaN.
					// If there is a positive infinity, then the result is positive infinity.
					// If there is a 0, then the result is -INFINITY.
					// If there is an 1, then the result is 0.
					num1 = Math.log10(num1);
					frame.ioTf.setText(String.valueOf(num1));
				}

				// Otherwise, assign the result to num2.
				else {
					num2 = Double.parseDouble(frame.ioTf.getText());

					frame.sttTf.setText("log(" + num2 + ") = ");

					num2 = Math.log10(num2);
					frame.ioTf.setText(String.valueOf(num2));
				}
			}

			// Square root button.
			else if (e.getSource().equals(sqrtBt)) {
				if (frame.ioTf.getText().isBlank())
					throw new MissingRequiredValueException("Enter the squared value first");

				// If the operator hasn't been chose, then assign the result to num1.
				else if (operator == 'n') {
					num1 = Double.parseDouble(frame.ioTf.getText());

					frame.sttTf.setText("sqrt(" + num1 + ") = ");

					// If there is a negative number or NaN, then the result is NaN.
					// If there is a positive infinity, then the result is positive infinity.
					num1 = Math.sqrt(num1);
					frame.ioTf.setText(String.valueOf(num1));
				}

				// Otherwise, assign the result to num2.
				else {
					num2 = Double.parseDouble(frame.ioTf.getText());

					frame.sttTf.setText("sqrt(" + num2 + ") = ");

					num2 = Math.sqrt(num2);
					frame.ioTf.setText(String.valueOf(num2));
				}
			}

			// Cube root button.
			else if (e.getSource().equals(cbrtBt)) {
				if (frame.ioTf.getText().isBlank())
					throw new MissingRequiredValueException("Enter the cubed value first");

				// If the operator hasn't been chose, then assign the result to num1.
				else if (operator == 'n') {
					num1 = Double.parseDouble(frame.ioTf.getText());

					frame.sttTf.setText("cbrt(" + num1 + ") = ");

					// If there is an NaN, then the result is NaN.
					// If there is an infinity, then the result is an infinity with the same sign.
					num1 = Math.cbrt(num1);
					frame.ioTf.setText(String.valueOf(num1));
				}

				// Otherwise, assign the result to num2.
				else {
					num2 = Double.parseDouble(frame.ioTf.getText());

					frame.sttTf.setText("cbrt(" + num2 + ") = ");

					num2 = Math.cbrt(num2);
					frame.ioTf.setText(String.valueOf(num2));
				}
			}

			// Add button.
			else if (e.getSource().equals(addBt)) {
				if (frame.ioTf.getText().isBlank() && operPressed == 0)
					throw new MissingRequiredValueException("Missing the 1st operand");

				else if (operPressed == 0) {
					num1 = Double.parseDouble(frame.ioTf.getText());
					frame.ioTf.setText("");
					++operPressed; // Increase the operator buttons pressed time by 1.
				}

				operator = '+';
				frame.sttTf.setText("+");

				// Reset the equal button pressed time.
				eqlPressed = 0;

				addBt.setBackground(new Color(212, 28, 202));
				subBt.setBackground(NauticalBlue);
				mulBt.setBackground(NauticalBlue);
				divBt.setBackground(NauticalBlue);
				pwrBt.setBackground(NauticalBlue);
			}

			// Subtract button.
			else if (e.getSource().equals(subBt)) {
				if (frame.ioTf.getText().isBlank() && operPressed == 0)
					throw new MissingRequiredValueException("Missing the 1st operand");

				else if (operPressed == 0) {
					num1 = Double.parseDouble(frame.ioTf.getText());
					frame.ioTf.setText("");
					++operPressed;
				}

				operator = '-';
				frame.sttTf.setText("-");

				eqlPressed = 0;

				subBt.setBackground(new Color(212, 28, 202));
				addBt.setBackground(NauticalBlue);
				mulBt.setBackground(NauticalBlue);
				divBt.setBackground(NauticalBlue);
				pwrBt.setBackground(NauticalBlue);
			}

			// Multiply button.
			else if (e.getSource().equals(mulBt)) {
				if (frame.ioTf.getText().isBlank() && operPressed == 0)
					throw new MissingRequiredValueException("Missing the 1st operand");

				else if (operPressed == 0) {
					num1 = Double.parseDouble(frame.ioTf.getText());
					frame.ioTf.setText("");
					++operPressed;
				}

				operator = '*';
				frame.sttTf.setText("*");

				eqlPressed = 0;

				mulBt.setBackground(new Color(212, 28, 202));
				addBt.setBackground(NauticalBlue);
				subBt.setBackground(NauticalBlue);
				divBt.setBackground(NauticalBlue);
				pwrBt.setBackground(NauticalBlue);
			}

			// Divide button.
			else if (e.getSource().equals(divBt)) {
				if (frame.ioTf.getText().isBlank() && operPressed == 0)
					throw new MissingRequiredValueException("Missing the 1st operand");

				else if (operPressed == 0) {
					num1 = Double.parseDouble(frame.ioTf.getText());
					frame.ioTf.setText("");
					++operPressed;
				}

				operator = '/';
				frame.sttTf.setText("/");

				eqlPressed = 0;

				divBt.setBackground(new Color(212, 28, 202));
				addBt.setBackground(NauticalBlue);
				subBt.setBackground(NauticalBlue);
				mulBt.setBackground(NauticalBlue);
				pwrBt.setBackground(NauticalBlue);
			}

			// Power button.
			else if (e.getSource().equals(pwrBt)) {
				if (frame.ioTf.getText().isBlank() && operPressed == 0)
					throw new MissingRequiredValueException("Missing the base number");

				else if (operPressed == 0) {
					num1 = Double.parseDouble(frame.ioTf.getText());
					frame.ioTf.setText("");
					++operPressed; // Increase the operator buttons pressed time by 1.
				}

				operator = '^';
				frame.sttTf.setText("^");

				// Reset the equal button pressed time.
				eqlPressed = 0;

				pwrBt.setBackground(new Color(212, 28, 202));
				addBt.setBackground(NauticalBlue);
				subBt.setBackground(NauticalBlue);
				mulBt.setBackground(NauticalBlue);
				divBt.setBackground(NauticalBlue);
			}

			// Equal button.
			else if (e.getSource().equals(eqlBt)) {
				/*
				 * If the user haven't pressed the equal button before, the field isn't empty
				 * and the operator has been chose.
				 */
				if (eqlPressed == 0 && !frame.ioTf.getText().isBlank() && operator != 'n') {
					num2 = Double.parseDouble(frame.ioTf.getText());

					// If both nums are negative, then put them between parentheses.
					if (num1 < 0 && num2 < 0)
						frame.sttTf.setText("(" + num1 + ") " + operator + " (" + num2 + ") = ");

					// If only num1 is negative, then put it between parentheses.
					else if (num1 < 0)
						frame.sttTf.setText("(" + num1 + ") " + operator + " " + num2 + " = ");

					// If only num1 is negative, then put it between parentheses.
					else if (num2 < 0)
						frame.sttTf.setText(num1 + " " + operator + " (" + num2 + ") = ");

					// Else,
					else
						frame.sttTf.setText(num1 + " " + operator + " " + num2 + " = ");

					++eqlPressed;
				}

				// If it've been pressed more than 1 time, then reuse the previous num2.
				else if (!frame.ioTf.getText().isBlank()) {
					if (num1 < 0 && num2 < 0)
						frame.sttTf.setText("(" + num1 + ") " + operator + " (" + num2 + ") = ");

					else if (num1 < 0)
						frame.sttTf.setText("(" + num1 + ") " + operator + " " + num2 + " = ");

					else if (num2 < 0)
						frame.sttTf.setText(result + " " + operator + " (" + num2 + ") = ");

					else
						frame.sttTf.setText(result + " " + operator + " " + num2 + " = ");
				}

				// If the user haven't enter the 2nd operand.
				else if (frame.ioTf.getText().isBlank() && operator != 'n') {
					if (operator == '^')
						throw new MissingRequiredValueException("Missing the exponent");
					else
						throw new MissingRequiredValueException("Missing the 2nd operand");
				}

				// Reset the operator buttons pressed time if no error occurred.
				operPressed = 0;

				// Reset the color of these buttons.
				addBt.setBackground(NauticalBlue);
				subBt.setBackground(NauticalBlue);
				mulBt.setBackground(NauticalBlue);
				divBt.setBackground(NauticalBlue);
				pwrBt.setBackground(NauticalBlue);

				switch (operator) {
				case '+':
					result = num1 + num2;
					break;
				case '-':
					result = num1 - num2;
					break;
				case '*':
					result = num1 * num2;
					break;
				case '/':
					result = num1 / num2;
					break;
				case '^':
					result = Math.pow(num1, num2);
					break;
				// If the user haven't chose any of above operators and the field isn't empty.
				default:
					if (!frame.ioTf.getText().isBlank()) {
						result = Double.parseDouble(frame.ioTf.getText());
						frame.sttTf.setText("");
					}
				}

				// If the input and output field this not empty, then print out the result and
				// vice versa.
				if (!frame.ioTf.getText().isBlank()) {
					if (result == Double.POSITIVE_INFINITY)
						frame.ioTf.setText(String.valueOf(Double.POSITIVE_INFINITY));

					else if (result == Double.NEGATIVE_INFINITY)
						frame.ioTf.setText(String.valueOf(Double.NEGATIVE_INFINITY));

					else if (num1 == 0 && num2 == 0 && operator == '/')
						frame.ioTf.setText(String.valueOf(Double.NaN));

					else {
						// Round to 4 decimal places.
						result = Math.round(result * 10000.0) / 10000.0;
						num2 = Math.round(num2 * 10000.0) / 10000.0;

						frame.ioTf.setText(String.valueOf(result));
					}
				}

				num1 = result;
			}
		} catch (MissingRequiredValueException x) {
			frame.sttTf.setText(x.getMessage());
		} catch (NumberFormatException x) {
			frame.sttTf.setText("Invalid number");
		}
	}
}
