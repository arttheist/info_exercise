package exercise_07;

/**
 * The main part of the calculator doing the calculations.
 * 
 * @author Till Leinen & Rene Vos
 * @version 3.0
 */
public class CalcEngine {
	// The calculator's state is maintained in three fields:
	// buildingDisplayValue, haveLeftOperand, and lastOperator.

	// Are we already building a value in the display, or will the
	// next digit be the first of a new one?
	private boolean buildingDisplayValue = true;
	
	// next is a single operator not another one?
	private boolean operatorDisplay = true;
	
	// The current value (to be) shown in the display.
	private String displayValue;
	
	// add Booleans for the mathformat
    private String mathSystem = "DEC";
    
    private Postfix termEvaluator = new Postfix();
	
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
    	if (system.equals("HEX")){
    		displayValue=Integer.toHexString(Integer.parseInt(displayValue));
    	}
    	
    	if (system.equals("DEC")){
    		displayValue=Integer.toString(Integer.parseInt(displayValue,16));
    	}
    	mathSystem = system;
    }
    
    
	
	/**
	 * @return The value that should currently be displayed on the calculator
	 *         display.
	 */
	public String getDisplayValue() {
		return displayValue;
	}

	public void setDisplayValue(String s) {
		displayValue = s;
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
	
	public void bracketPressed(String bracket) {
		displayValue+=bracket;
	}
	/**
	 * A MathSystem button was pressed
	 * @param changingSystem The MathSystem that was pressed
	 * @param currentSystem The MathSystem before something was pressed
	 */
	
	
	
	/**
	 * The '=' button was pressed.
	 */
	public void equals() {
		// This should completes the building of a second operand,
		// so ensure that we really have a left operand, an operator
		// and a right operand.
		try {
			if (mathSystem.equals("HEX")) {
				displayValue = Integer.toHexString(Integer.parseInt("" + termEvaluator.evaluate(termEvaluator.infixToPostfix(displayValue))));
				} else {
				displayValue = "" + termEvaluator.evaluate(termEvaluator.infixToPostfix(displayValue));
				}
		} catch (InvalidInfixString e) {
			displayValue = "Not a valid Term!";
		}
	}

	/**
	 * The 'C' (clear) button was pressed. Reset everything to a starting state.
	 */
	public void clear() {
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


}
