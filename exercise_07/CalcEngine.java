package exercise_07;

/**
 * The main part of the calculator doing the calculations.
 * 
 * @author Till Leinen & Rene Vos
 * @version 2.0
 */
public class CalcEngine {
	// The calculator's state is maintained in three fields:
	// buildingDisplayValue, haveLeftOperand, and lastOperator.

	// Are we already building a value in the display, or will the
	// next digit be the first of a new one?
	private boolean buildingDisplayValue = true;
	// next operator is not behind another operator
	private boolean operatorDisplay = true;
	// Has a left operand already been entered (or calculated)?
	private boolean haveLeftOperand;
	// The most recent operator that was entered.
	private char lastOperator;

	// The current value (to be) shown in the display.
	private String displayValue;
	// The value of an existing left operand.
	private String leftOperand;
	
	// add Booleans for the mathformat
    private String mathSystem = "DEC";
	
	/**
	 * Create a CalcEngine.
	 */
	public CalcEngine() {
		clear();
	}

	/**
     * Getter & Setter of the String mathSystem 
     * 
     * 
     */
    
    public void setMathSystem(String system) {
    	mathSystem = system;
    }
    
    public String getMathSystem(){
    	return mathSystem;
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
	
	// modified so that you can press hexadecimal numbers
	public void numberPressed(String number) {
		if (buildingDisplayValue) {
			// Incorporate this digit.
			displayValue += number;
			buildingDisplayValue = false;
			operatorDisplay = true;
		} else {
			// Start building a new number.
			displayValue = displayValue.substring(0,displayValue.length()-1);
			displayValue += number;
			operatorDisplay = true;
		}
	}
	
	public void operatorPressed(String operator) {
		if (operatorDisplay) {
			displayValue += operator;
			operatorDisplay = false;
			buildingDisplayValue = true;
		} else {
			displayValue = displayValue.substring(0,displayValue.length()-1);
			displayValue += operator;
			buildingDisplayValue = true;
		}
	}


	/**
	 * A MathSystem button was pressed
	 * @param changingSystem The MathSystem that was pressed
	 * @param currentSystem The MathSystem before something was pressed
	 */
	
	public void changeSystem(String changingSystem, String currentSystem){
		if(currentSystem.equals("DEC") && changingSystem.equals("HEX")) {
			displayValue = Float.toHexString(Float.parseFloat(displayValue)).toUpperCase();
		}
		if(currentSystem.equals("DEC") && changingSystem.equals("BIN")) {
			displayValue = Integer.toBinaryString(Integer.parseInt(displayValue));
		}
		if(currentSystem.equals("DEC") && changingSystem.equals("OCT")) {
			displayValue = Integer.toOctalString(Integer.parseInt(displayValue));
		}
		if(currentSystem.equals("HEX") && changingSystem.equals("DEC")) {
			displayValue = Integer.toString(Integer.parseInt(displayValue,16));
		}
		if(currentSystem.equals("HEX") && changingSystem.equals("BIN")) {
			displayValue = Integer.toBinaryString(Integer.parseInt(displayValue,16));
		}
		if(currentSystem.equals("HEX") && changingSystem.equals("OCT")) {
			displayValue = Integer.toOctalString(Integer.parseInt(displayValue,16));
		}
		if(currentSystem.equals("BIN") && changingSystem.equals("DEC")) {
			displayValue = Integer.toString(Integer.parseInt(displayValue,2));
		}
		if(currentSystem.equals("BIN") && changingSystem.equals("HEX")) {
			displayValue = Integer.toHexString(Integer.parseInt(displayValue,2)).toUpperCase();
		}
		if(currentSystem.equals("BIN") && changingSystem.equals("OCT")) {
			displayValue = Integer.toOctalString(Integer.parseInt(displayValue,2));
		}
		if(currentSystem.equals("OCT") && changingSystem.equals("DEC")) {
			displayValue = Integer.toString(Integer.parseInt(displayValue,8));
		}
		if(currentSystem.equals("OCT") && changingSystem.equals("HEX")) {
			displayValue = Integer.toHexString(Integer.parseInt(displayValue,8)).toUpperCase();
		}
		if(currentSystem.equals("OCT") && changingSystem.equals("BIN")) {
			displayValue = Integer.toBinaryString(Integer.parseInt(displayValue,8));
		}
		
		mathSystem = changingSystem;
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
		return "Version 2.0";
	}

	/**
	 * Combine leftOperand, lastOperator, and the current display value. The
	 * result becomes both the leftOperand and the new display value.
	 */
	private void calculateResult() {
		if (mathSystem.equals("DEC")) {
			switch (lastOperator) {
			case '+':
				displayValue = Integer.toString(Integer.parseInt(leftOperand) + (Integer.parseInt(displayValue)));
				haveLeftOperand = true;
				leftOperand = displayValue;
				break;
			case '-':
				displayValue = Integer.toString(Integer.parseInt(leftOperand) - (Integer.parseInt(displayValue)));
				haveLeftOperand = true;
				leftOperand = displayValue;
				break;
	// new cases for multiply and divide		
			case '*':
				displayValue = Integer.toString(Integer.parseInt(leftOperand) * (Integer.parseInt(displayValue)));
				haveLeftOperand = true;
				leftOperand = displayValue;
				break;
			case '/':
				displayValue = Integer.toString(Integer.parseInt(leftOperand) / (Integer.parseInt(displayValue)));
				haveLeftOperand = true;
				leftOperand = displayValue;
				break;
			default:
				keySequenceError();
				break;
			}
		}
		if (mathSystem.equals("HEX")) {
			switch (lastOperator) {
			case '+':
				displayValue = Integer.toHexString(Integer.parseInt(leftOperand,16) + (Integer.parseInt(displayValue,16))).toUpperCase();
				haveLeftOperand = true;
				leftOperand = displayValue;
				break;
			case '-':
				displayValue = Integer.toHexString(Integer.parseInt(leftOperand,16) - (Integer.parseInt(displayValue,16))).toUpperCase();
				haveLeftOperand = true;
				leftOperand = displayValue;
				break;
	// new cases for multiply and divide		
			case '*':
				displayValue = Integer.toHexString(Integer.parseInt(leftOperand,16) * (Integer.parseInt(displayValue,16))).toUpperCase();
				haveLeftOperand = true;
				leftOperand = displayValue;
				break;
			case '/':
				displayValue = Integer.toHexString(Integer.parseInt(leftOperand,16) / (Integer.parseInt(displayValue,16))).toUpperCase();
				haveLeftOperand = true;
				leftOperand = displayValue;
				break;
			default:
				keySequenceError();
				break;
			}
		}
		if (mathSystem.equals("BIN")) {
			switch (lastOperator) {
			case '+':
				displayValue = Integer.toBinaryString(Integer.parseInt(leftOperand,2) + (Integer.parseInt(displayValue)));
				haveLeftOperand = true;
				leftOperand = displayValue;
				break;
			case '-':
				displayValue = Integer.toBinaryString(Integer.parseInt(leftOperand,2) - (Integer.parseInt(displayValue)));
				haveLeftOperand = true;
				leftOperand = displayValue;
				break;
	// new cases for multiply and divide		
			case '*':
				displayValue = Integer.toBinaryString(Integer.parseInt(leftOperand,2) * (Integer.parseInt(displayValue,2)));
				haveLeftOperand = true;
				leftOperand = displayValue;
				break;
			case '/':
				displayValue = Integer.toBinaryString(Integer.parseInt(leftOperand,2) / (Integer.parseInt(displayValue,2)));
				haveLeftOperand = true;
				leftOperand = displayValue;
				break;
			default:
				keySequenceError();
				break;
			}
		}
		if (mathSystem.equals("OCT")) {
			switch (lastOperator) {
			case '+':
				displayValue = Integer.toOctalString(Integer.parseInt(leftOperand,8) + (Integer.parseInt(displayValue,8)));
				haveLeftOperand = true;
				leftOperand = displayValue;
				break;
			case '-':
				displayValue = Integer.toOctalString(Integer.parseInt(leftOperand,8) - (Integer.parseInt(displayValue,8)));
				haveLeftOperand = true;
				leftOperand = displayValue;
				break;
	// new cases for multiply and divide		
			case '*':
				displayValue = Integer.toOctalString(Integer.parseInt(leftOperand,8) * (Integer.parseInt(displayValue,8)));
				haveLeftOperand = true;
				leftOperand = displayValue;
				break;
			case '/':
				displayValue = Integer.toOctalString(Integer.parseInt(leftOperand,8) / (Integer.parseInt(displayValue,8)));
				haveLeftOperand = true;
				leftOperand = displayValue;
				break;
			default:
				keySequenceError();
				break;
			}
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
			leftOperand = displayValue;
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
