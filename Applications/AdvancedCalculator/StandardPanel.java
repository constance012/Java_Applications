package AdvancedCalculator;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class StandardPanel extends JPanel implements ActionListener {
	private JButton[] numberButtons = new JButton[10];
	private JButton[] funcButtons = new JButton[10];
	private JButton addBt, subBt, mulBt, divBt;
	private JButton decBt, eqlBt, delBt, clrBt, negBt, recBt;

	protected Font buttonFont = new Font("arial", Font.BOLD, 20);

	int eqlPressed = 0; // The time that equal button has been pressed.
	int operPressed = 0; // The time that any of those operator buttons have been pressed.

	double num1 = 0, num2 = 0, result = 0;
	char operator = 'n'; // Initial operator is null.

	final Color SeaGreen = new Color(21, 179, 118);
	final Color NauticalBlue = new Color(21, 163, 179);
	final Color Orange = new Color(219, 149, 26);

	CalcuFrame frame;

	// Constructor.
	StandardPanel(CalcuFrame frame) {
		// Create a "link" to the calculator frame,
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
		recBt = new JButton("1/x");

		funcButtons[0] = addBt;
		funcButtons[1] = subBt;
		funcButtons[2] = mulBt;
		funcButtons[3] = divBt;
		funcButtons[4] = decBt;
		funcButtons[5] = eqlBt;
		funcButtons[6] = delBt;
		funcButtons[7] = clrBt;
		funcButtons[8] = negBt;
		funcButtons[9] = recBt;

		// Configure function buttons properties.
		for (int i = 0; i < 10; i++) {
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

		recBt.setBackground(SeaGreen);

		addBt.setBackground(NauticalBlue);
		subBt.setBackground(NauticalBlue);
		mulBt.setBackground(NauticalBlue);
		divBt.setBackground(NauticalBlue);

		// Add button into the panel.
		// 1st row.
		this.add(recBt);
		this.add(numberButtons[7]);
		this.add(numberButtons[8]);
		this.add(numberButtons[9]);
		this.add(addBt);

		// 2nd row.
		this.add(negBt);
		this.add(numberButtons[4]);
		this.add(numberButtons[5]);
		this.add(numberButtons[6]);
		this.add(subBt);

		// 3rd row.
		this.add(delBt);
		this.add(numberButtons[1]);
		this.add(numberButtons[2]);
		this.add(numberButtons[3]);
		this.add(mulBt);

		// 4th row.
		this.add(clrBt);
		this.add(decBt);
		this.add(numberButtons[0]);
		this.add(eqlBt);
		this.add(divBt);

		// Configure the panel.
		this.setBounds(10, 100, 430, 315);
		this.setLayout(new GridLayout(4, 5, 10, 10));
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
