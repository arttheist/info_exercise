package exercise_07;

/**
 * The main class of a simple calculator. Create one of these and you'll get the
 * calculator on screen.
 * 
 * @author Till Leinen & Rene Vos
 * @version 2.0
 */
public class Calculator {
	private CalcEngine engine;

	private UserInterface gui;

	/**
	 * Create a new calculator and show it.
	 */
	public Calculator() {
		engine = new CalcEngine();
		gui = new UserInterface(engine);
	}

	/**
	 * In case the window was closed, show it again.
	 */
	public void show() {
		gui.setVisible(true);
	}

	
	public static void main(String[] arguments) {
		(new Calculator()).show();
	}
}
