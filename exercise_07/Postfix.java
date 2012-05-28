package exercise_07;

import java.io.*;

public class Postfix {

	public static void main(String[] args) {
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("please Input a Term: ");
		String line = null;
		try {
			while ((line = console.readLine()) != null) {
				try {
					System.out.println("The Result of the Term is: " + evaluate(infixToPostfix(line)));
				} catch (InvalidInfixString e) {
					System.out.println("Not a Valid String!");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Ihre Eingabe war: " + line);
	}
	
	// A Method that takes a String representing a postfix expression and determines the value.
	public static int evaluate (String pfx){
		
		Stack<String> stack = new Stack<String>();
		int result = 0;

	    //While pfx is not empty:
		while(pfx.length() != 0) {

			int expressionResult = 0;
			int leftHand = 0;
			int rightHand = 0;
			
			//nextToken = next token of pfx.
			String nextToken = pfx.substring(0,1);
			char nextOperatorToken = pfx.substring(0,1).charAt(0);
			
			
			//If nextToken is an operand, push it;
			if (nextToken.matches("[0-9]")) {
				stack.push(nextToken);
			}
			
	        //If nextToken is an operator:
			if (nextToken.matches("[+*//-]")) {
				
				try {
					// put the top into rightHand, pop it;
					rightHand = Integer.parseInt(stack.pop());
			        
			        //put the top into leftHand, pop it;
					leftHand = Integer.parseInt(stack.pop());
				} catch ( StackUnderflow e ) {
					System.out.println("The String is not a valid Postfix-Notation");
				}

				// calculate the result
				switch (nextOperatorToken) {
					case '+' :
						expressionResult = leftHand + rightHand;
						break;
					case '-' :
						expressionResult = leftHand - rightHand;
						break;
					case '/' :
						expressionResult = leftHand / rightHand;
						break;
					case '*' :
						expressionResult = leftHand * rightHand;
						break;
				}
	            
	            //push the result
				stack.push(Integer.toString(expressionResult));
			}
			pfx = pfx.substring(1);
		}
		
		//The top of the stack is the result.
		try {
			result = Integer.parseInt(stack.pop());
		} catch (StackUnderflow e) {
			System.out.println("No items on the stack. Evaluation is done.");
		}
		return result;
	}
	
	private static boolean isValidInfix(String ifx) {
		char[] chars = ifx.toCharArray();
		int p = 0;
		for(int i = 0; i < chars.length; i++) {
			if(!ifx.matches("[()0-9+*//-]")) {
				switch (chars[i]) {
					case '(' :
						p -= 1;
						break;
					case ')' :
						p += 1;
						break;
					default :
						boolean isNumber = false;
						if(String.valueOf(chars[i]).matches("[0-9]"))
							isNumber = true;
						try {
							if(((isNumber && String.valueOf(chars[i+1]).matches("[0-9]")) || (!isNumber && !String.valueOf(chars[i+1]).matches("[0-9]"))) && chars[i+1] != '(')
								return false;
						} catch (Exception e) {}
						break;
				}
			} else {
				return false;
			}
		}
		if(p == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public static String infixToPostfix (String ifx) throws InvalidInfixString {
		String result = "";
		Stack<String> stack = new Stack<String>();
		
		//Check if Valid
		if(!isValidInfix(ifx)) {
			throw new InvalidInfixString();
		}

		while (ifx.length() != 0) {
			String nextToken = ifx.substring(0, 1);	
			
			if (nextToken.matches("[0-9]")) {
				result += nextToken;
			}
			
			if (nextToken.matches("[(]")) {
				stack.push(nextToken);
			}
			
			if(nextToken.matches("[)]")){
				try {
					while(!stack.top().matches("[(]")) {
						result += stack.pop();
					}
				} catch (StackUnderflow e) {
					System.out.println("No items on the Stack.");
				}
				try {
					stack.pop();
				} catch (StackUnderflow e) {
					System.out.println("Nothing to pop.");
				}
			}

			if (nextToken.matches("[+*//-]")) {
				try {
					while (!stack.top().matches("[(]") && !((stack.top().matches("[+-]") && nextToken.matches("[*/]")) || (stack.top().matches("[//-]") && ((stack.top().matches("[*/]") && nextToken.matches("[*/]")) || (stack.top().matches("[+-]") && nextToken.matches("[+-]")))))) {
						result += stack.pop();
					}
				} catch (StackUnderflow e) {
				}
				stack.push(nextToken);
			}
			ifx = ifx.substring(1);
			}
		result += stack.toString();
		return result;
	}
}