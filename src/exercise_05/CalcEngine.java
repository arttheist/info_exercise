package exercise_05;

/**
 * The main part of the calculator doing the calculations.
 * 
 * @author Till Leinen & Rene Vos
 * @version 1.0
 */
public class CalcEngine {
	// The calculator's state is maintained in three fields:
	// buildingDisplayValue, haveLeftOperand, and lastOperator.

	// Are we already building a value in the display, or will the
	// next digit be the first of a new one?
	private boolean buildingDisplayValue;
	// Has a left operand already been entered (or calculated)?
	private boolean haveLeftOperand;
	// The most recent operator that was entered.
	private char lastOperator;

	// The current value (to be) shown in the display.
	private String displayValue;
	// The value of an existing left operand.
	private int leftOperand;

	/**
	 * Create a CalcEngine.
	 */
	public CalcEngine() {
		clear();
	}

	/**
	 * @return The value that should currently be displayed on the calculator
	 *         display.
	 */
	public String getDisplayValue() {
		return displayValue;
	}

	/**
	 * A number button was pressed. Either start a new operand, or incorporate
	 * this number as the least significant digit of an existing one.
	 * 
	 * @param number
	 *            The number pressed on the calculator.
	 */
	public void numberPressed(int number) {
		if (buildingDisplayValue) {
			// Incorporate this digit.
			displayValue = Integer.toString(Integer.parseInt(displayValue) * 10 + number);
		} else {
			// Start building a new number.
			displayValue = Integer.toString(number);
			buildingDisplayValue = true;
		}
	}
	
	/**
	 * A Hex Number was pressed
	 * 
	 * @param hexNumber
	 *            The Hex number pressed on the calculator.
	 */
	public void hexPressed(){
		System.out.println("ich habe hex gedr�ckt");
	}
	
	public void hexNumberPressed(String hexNumber) {
			displayValue = hexNumber;
	}

	/**
	 * The 'plus' button was pressed.
	 */
	public void plus() {
		applyOperator('+');
	}

	/**
	 * The 'minus' button was pressed.
	 */
	public void minus() {
		applyOperator('-');
	}

// new Methods for pressing multiply and divide
	/**
	 * The 'multiply' button was pressed.
	 */
	public void multiply() {
		applyOperator('*');
	}
	
	/**
	 * The 'divide' button was pressed.
	 */
	public void divide() {
		applyOperator('/');
	}
	
	/**
	 * The '=' button was pressed.
	 */
	public void equals() {
		// This should completes the building of a second operand,
		// so ensure that we really have a left operand, an operator
		// and a right operand.
		if (haveLeftOperand && lastOperator != '?' && buildingDisplayValue) {
			calculateResult();
			lastOperator = '?';
			buildingDisplayValue = false;
		} else {
			keySequenceError();
		}
	}

	/**
	 * The 'C' (clear) button was pressed. Reset everything to a starting state.
	 */
	public void clear() {
		lastOperator = '?';
		haveLeftOperand = false;
		buildingDisplayValue = false;
		displayValue = "0";
	}

	/**
	 * @return The title of this calculation engine.
	 */
	public String getTitle() {
		return "Java Calculator";
	}

	/**
	 * @return The author of this engine.
	 */

// changed the author
	public String getAuthor() {
		return "Till Leinen & Rene Vos";
	}

	/**
	 * @return The version number of this engine.
	 */
	public String getVersion() {
		return "Version 1.0";
	}

	/**
	 * Combine leftOperand, lastOperator, and the current display value. The
	 * result becomes both the leftOperand and the new display value.
	 */
	private void calculateResult() {
		switch (lastOperator) {
		case '+':
			displayValue = Integer.toString(leftOperand + (Integer.parseInt(displayValue)));
			haveLeftOperand = true;
			leftOperand = Integer.parseInt(displayValue);
			break;
		case '-':
			displayValue = Integer.toString(leftOperand - (Integer.parseInt(displayValue)));
			haveLeftOperand = true;
			leftOperand = Integer.parseInt(displayValue);
			break;
// new cases for multiply and divide		
		case '*':
			displayValue = Integer.toString(leftOperand * (Integer.parseInt(displayValue)));
			haveLeftOperand = true;
			leftOperand = Integer.parseInt(displayValue);
			break;
		case '/':
			displayValue = Integer.toString(leftOperand / (Integer.parseInt(displayValue)));
			haveLeftOperand = true;
			leftOperand = Integer.parseInt(displayValue);
			break;
		default:
			keySequenceError();
			break;
		}
	}

	/**
	 * Apply an operator.
	 * 
	 * @param operator
	 *            The operator to apply.
	 */
	private void applyOperator(char operator) {
		// If we are not in the process of building a new operand
		// then it is an error, unless we have just calculated a
		// result using '='.
		if (!buildingDisplayValue && !(haveLeftOperand && lastOperator == '?')) {
			keySequenceError();
			return;
		}

		if (lastOperator != '?') {
			// First apply the previous operator.
			calculateResult();
		} else {
			// The displayValue now becomes the left operand of this
			// new operator.
			haveLeftOperand = true;
			leftOperand = Integer.parseInt(displayValue);
		}
		lastOperator = operator;
		buildingDisplayValue = false;
	}

	/**
	 * Report an error in the sequence of keys that was pressed.
	 */
	private void keySequenceError() {
		System.out.println("A key sequence error has occurred.");
		// Reset everything.
		clear();
	}

}