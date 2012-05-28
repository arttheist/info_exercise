package exercise_07;
import java.awt.*;
import java.awt.event.*;
import java.util.Enumeration;

import javax.swing.*;
import javax.swing.border.*;

/**
 * A graphical user interface for the calculator. No calculation is being
 * done here. This class is responsible just for putting up the display on 
 * screen. It then refers to the "CalcEngine" to do all the real work.
 * 
 * @author Till Leinen & Rene Vos
 * @version 2.0
 */
public class UserInterface
    implements ActionListener
{
    private CalcEngine calc;
    private boolean showingAuthor;

    private JFrame frame;
    private JTextField display;
    private JLabel status;
    
 // add a Buttongroup for the Math Formats
    private ButtonGroup mathFormats = new ButtonGroup();
    private ButtonGroup hexNumbers = new ButtonGroup();
    private ButtonGroup decNumbers = new ButtonGroup();
    private ButtonGroup octNumbers = new ButtonGroup();



    /**
     * Create a user interface.
     * @param engine The calculator engine.
     */
    public UserInterface(CalcEngine engine)
    {
        calc = engine;
        showingAuthor = true;
        makeFrame();
        frame.setVisible(true);
    }

    /**
     * Set the visibility of the interface.
     * @param visible true if the interface is to be made visible, false otherwise.
     */
    public void setVisible(boolean visible)
    {
        frame.setVisible(visible);
    }

    /**
     * Make the frame for the user interface.
     */
    private void makeFrame()
    {
        frame = new JFrame(calc.getTitle());
        
        JPanel contentPane = (JPanel)frame.getContentPane();
        contentPane.setLayout(new BorderLayout(8, 8));
        contentPane.setBorder(new EmptyBorder( 10, 10, 10, 10));

        display = new JTextField();
        contentPane.add(display, BorderLayout.NORTH);
        
        JPanel buttonPanel = new JPanel(new GridLayout(7, 5));
	 
        
 // add Buttons for the different Math Formats
        	addRadioButton(buttonPanel, "DEC", true);
	        addRadioButton(buttonPanel, "HEX", false);
	        addRadioButton(buttonPanel, "BIN", false);
	        addRadioButton(buttonPanel, "OCT", false);
	        
	        // add Button for Postfix
	        JCheckBox checkButton = new JCheckBox("POSTFIX");
	        checkButton.addActionListener(this);
	        buttonPanel.add(checkButton);
	        checkButton.setSelected(false);

	        // add Buttons for Hex
	        addHexButton(buttonPanel, "D");
            addHexButton(buttonPanel, "E");
            addHexButton(buttonPanel, "F");
            addButton(buttonPanel, "?");
            addButton(buttonPanel, "+");

            addHexButton(buttonPanel, "A");
            addHexButton(buttonPanel, "B");
            addHexButton(buttonPanel, "C");
            buttonPanel.add(new JLabel(" "));
            addButton(buttonPanel, "-");
            
        	addOctButton(buttonPanel, "7");
            addDecButton(buttonPanel, "8");
            addDecButton(buttonPanel, "9");
            buttonPanel.add(new JLabel(" "));
            addButton(buttonPanel, "x");
            
            addOctButton(buttonPanel, "4");
            addOctButton(buttonPanel, "5");
            addOctButton(buttonPanel, "6");
            buttonPanel.add(new JLabel(" "));
            addButton(buttonPanel, "/");
            
            addButton(buttonPanel, "1");
            addOctButton(buttonPanel, "2");
            addOctButton(buttonPanel, "3");
            buttonPanel.add(new JLabel(" "));
            addButton(buttonPanel, "^");
            
            addButton(buttonPanel, "0");
            buttonPanel.add(new JLabel(" "));
            buttonPanel.add(new JLabel(" "));
            addButton(buttonPanel, "DEL");
            addButton(buttonPanel, "=");
            
        contentPane.add(buttonPanel, BorderLayout.CENTER);

        status = new JLabel(calc.getAuthor());
        contentPane.add(status, BorderLayout.SOUTH);

        frame.pack();
    }

    /**
     * Add a button to the button panel.
     * @param panel The panel to receive the button.
     * @param buttonText The text for the button.
     */
    private void addButton(Container panel, String buttonText)
    {
        JButton button = new JButton(buttonText);
        button.addActionListener(this);
        panel.add(button);
    }
    
    private void addHexButton(Container panel, String buttonText)
    {
        JButton button = new JButton(buttonText);
        button.addActionListener(this);
        panel.add(button);
        button.setEnabled(false);
        hexNumbers.add(button);

    }
   
    private void addDecButton(Container panel, String buttonText)
    {
        JButton button = new JButton(buttonText);
        button.addActionListener(this);
        panel.add(button);
        decNumbers.add(button);
    }
    
    private void addOctButton(Container panel, String buttonText)
    {
        JButton button = new JButton(buttonText);
        button.addActionListener(this);
        panel.add(button);
        octNumbers.add(button);
    }
    
    
    
    
    
    /**
     * Add a button to the button panel.
     * @param panel The panel to receive the button.
     * @param buttonText The text for the button.
     * @param radioValue The value of the Radiobutton
     */
    private void addRadioButton(Container panel, String buttonText, Boolean radioValue)
    {
        JRadioButton radioButton = new JRadioButton(buttonText, radioValue);
        radioButton.addActionListener(this);
        panel.add(radioButton);
        mathFormats.add(radioButton);
    }

    /**
     * An interface action has been performed.
     * Find out what it was and handle it.
     * @param event The event that has occured.
     */
    public void actionPerformed(ActionEvent event)
    {
        String command = event.getActionCommand();

       
        if(command.equals("0") ||
           command.equals("1") ||
           command.equals("2") ||
           command.equals("3") ||
           command.equals("4") ||
           command.equals("5") ||
           command.equals("6") ||
           command.equals("7") ||
           command.equals("8") ||
           command.equals("9") ||
           command.equals("A") || 
           command.equals("B") || 
           command.equals("C") || 
           command.equals("D") || 
           command.equals("E") ||
           command.equals("F")) {
            String number = command;
            calc.numberPressed(number);
        }
        else if(command.equals("+") ||
                command.equals("-") ||
                command.equals("x") ||
                command.equals("/") ||
                command.equals("^")) {
        	String operator = command;
        	calc.operatorPressed(operator);
        }
        else if(command.equals("POSTFIX")){
        	calc.postfix();
        }
        else if(command.equals("HEX")){
        	enableHex();
        	enableDec();
        	enableOct();
        	calc.changeSystem("HEX", calc.getMathSystem());
        }
        else if(command.equals("DEC")){
        	disableHex();
        	enableDec();
        	enableOct();
        	calc.changeSystem("DEC", calc.getMathSystem());
        }
        else if(command.equals("BIN")){
        	disableHex();
        	disableDec();
        	disableOct();
        	calc.changeSystem("BIN", calc.getMathSystem());
        }
        else if(command.equals("OCT")){
        	disableHex();
        	disableDec();
        	enableOct();
        	calc.changeSystem("OCT", calc.getMathSystem());
        }
        
        else if(command.equals("=")) {
            calc.equals();
        }
        else if(command.equals("DEL")) {
            calc.clear();
        }
        else if(command.equals("?")) {
            showInfo();
        }
        // else unknown command.

        redisplay();
    }

    
    // Methods for enable/disable the Buttongroups
    
    // hexadecimal numbers
    private void enableHex() {
    	Enumeration<?> i = hexNumbers.getElements();
    	while (i.hasMoreElements()) {
    		((AbstractButton) i.nextElement()).setEnabled(true);
    	}
    }
    private void disableHex() {
    	Enumeration<?> i = hexNumbers.getElements();
    	while (i.hasMoreElements()) {
    		((AbstractButton) i.nextElement()).setEnabled(false);
    	}
    }
    // decimal numbers
    private void enableDec() {
    	Enumeration<?> i = decNumbers.getElements();
    	while (i.hasMoreElements()) {
    		((AbstractButton) i.nextElement()).setEnabled(true);
    	}
    }
    private void disableDec() {
    	Enumeration<?> i = decNumbers.getElements();
    	while (i.hasMoreElements()) {
    		((AbstractButton) i.nextElement()).setEnabled(false);
    	}
    }
    // octal numbers
    private void enableOct() {
    	Enumeration<?> i = octNumbers.getElements();
    	while (i.hasMoreElements()) {
    		((AbstractButton) i.nextElement()).setEnabled(true);
    	}
    }
    private void disableOct() {
    	Enumeration<?> i = octNumbers.getElements();
    	while (i.hasMoreElements()) {
    		((AbstractButton) i.nextElement()).setEnabled(false);
    	}
    }
   

    
    
	/**
     * Update the interface display to show the current value of the 
     * calculator.
     */
    private void redisplay()
    {
        display.setText("" + calc.getDisplayValue());
    }

    /**
     * Toggle the info display in the calculator's status area between the
     * author and version information.
     */
    private void showInfo()
    {
        if(showingAuthor)
            status.setText(calc.getVersion());
        else
            status.setText(calc.getAuthor());

        showingAuthor = !showingAuthor;
    }
}
