package exercise_06;

import java.io.*;;

public class Postfix {

	public static void main(String[] args) {
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("please Input a Term: ");
		String line = null;
		try {
		while ((line = console.readLine()) != null) {
			System.out.println("The Result of the Term is: " + evaluate(infixToPostfix(console)));
		}
		} catch (IOException e) {
			// should never happen
			e.printStackTrace();
		}
		System.out.println("Ihre Eingabe war: " + line);

	}
	
	// A Method that takes a String representing a postfix expression and determines the value.
	public int evaluate (String pfx){
		
		Stack<String> stack = new Stack<String>();
		int result = 0;

	    //While pfx is not empty:
		while(pfx.length() != 0) {

			int expressionResult;
			int leftHand = 0;
			int rightHand = 0;
			
			//nextToken = next token of pfx.
			String nextToken = pfx.substring(0,1);
			
			//If nextToken is an operand, push it;
			if (nextToken.matches("[0-9]")) {
				stack.push(nextToken);
			}
			
	        //If nextToken is an operator:
			if (nextToken.matches("[+,*/]")) {
				
				try {
					// put the top into rightHand, pop it;
					rightHand = Integer.parseInt(stack.pop());
			        
			        //put the top into leftHand, pop it;
					leftHand = Integer.parseInt(stack.pop());
				} catch ( StackUnderflow e ) {
					System.out.println("The String is not a valid Postfix-Notation");
					e.printStackTrace();
				}
				
				char operator = nextToken.charAt(0);
				
				// calculate the result
				expressionResult = leftHand + operator + rightHand;
	            
	            //push the result
				stack.push(Integer.toString(expressionResult));
			}
		}
		
		//The top of the stack is the result.
		try {
			result = Integer.parseInt(stack.pop());
		} catch (StackUnderflow e) {
			e.printStackTrace();
		}
		return result;
	}
}
