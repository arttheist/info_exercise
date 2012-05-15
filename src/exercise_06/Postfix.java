package exercise_06;

public class Postfix {

	// A Method that takes a String representing a postfix expression and determines the value.
	public int evaluate (String pfx){
		
		Stack<String> stack = new Stack();

	    //While pfx is not empty:
		while(pfx.length() == 0) {

			int expressionResult;
			int leftHand;
			int rightHand;
			
			//nextToken = next token of pfx.
			String nextToken = pfx;
			
			//If nextToken is an operand, push it;
			if (nextToken.matches("[0-9]")) {
				Stack.push(nextToken);
			}
			
	        //If nextToken is an operator:
			if (nextToken.matches("[+,*/]")) {
				
				// put the top into rightHand, pop it;
				rightHand = Integer.parseInt(Stack.pop());
	            
	            //put the top into leftHand, pop it;
				leftHand = Integer.parseInt(Stack.pop());
				
				char operator = nextToken.charAt(0);
				
				// calculate the result
				expressionResult = leftHand + operator + rightHand;
	            
	            //push the result
				Stack.push(Integer.toString(expressionResult));
			}
		}
		
		//The top of the stack is the result.
		return Stack.pop();
	}
}
