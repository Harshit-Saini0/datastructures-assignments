import java.util.ArrayList;
import java.util.Stack;

public class StackProbs {
	
	public static Stack<Integer> doubleUp(Stack<Integer> nums) {
		Stack<Integer> reverseNums = new Stack<Integer>();
		while(!nums.isEmpty()) {
			reverseNums.push(nums.pop());
		}
		
		Stack<Integer> doubleNums = new Stack<Integer>();
		while(!reverseNums.isEmpty()) {
			Integer poppedInt = reverseNums.pop();
			doubleNums.push(poppedInt); doubleNums.push(poppedInt);
		}
		return doubleNums;
	}
	
	public static Stack<Integer> posAndNeg(Stack<Integer>nums) {
		Stack<Integer> positiveNums = new Stack<Integer>();		
		Stack<Integer> negativeNums = new Stack<Integer>();		
		
		while(!nums.isEmpty()) {
			if(nums.peek() >= 0) {
				positiveNums.push(nums.pop());
			}
			else {
				negativeNums.push(nums.pop());
			}
		}	
		
		while(!positiveNums.isEmpty()) {
			negativeNums.push(positiveNums.pop());
		}
		return negativeNums;
	}
	
	public static Stack<Integer> shiftByN(Stack<Integer> nums, int n) {
		//reverse all nums
		Stack<Integer> reverseNums = new Stack<Integer>();
		while(!nums.isEmpty()) {
			reverseNums.push(nums.pop());
		}
		
		//pop off 'n' Integers
		Stack<Integer> shiftedNums = new Stack<Integer>();
		for(int i = 0; i < n; i++) {
			shiftedNums.push(reverseNums.pop());
		}
		
		//reverse the popped integers
		Stack<Integer> reverseShifted = new Stack<Integer>();
		while(!shiftedNums.isEmpty()) {
			reverseShifted.push(shiftedNums.pop());
		}
		
		//final list
		Stack<Integer> finalNums = new Stack<Integer>();
		while(!reverseNums.isEmpty()) {
			finalNums.push(reverseNums.pop());
		}
		while(!reverseShifted.isEmpty()) {
			finalNums.push(reverseShifted.pop());
		}
		
		return finalNums;
	}
	
	public static String reverseVowels(String str) {
		Stack<Character> stack = new Stack<Character>();
		String vowels = "aeiou";
		
		for(int i = 0; i < str.length(); i++) {
			if(vowels.indexOf(str.charAt(i)) >= 0) {
				stack.push(str.charAt(i));
			}
		}
		
		for(int i = 0; i < str.length(); i++) {
			if(vowels.indexOf(str.charAt(i)) >= 0) {
				str = str.substring(0,i) + stack.pop() + str.substring(i + 1);
			}
		}
		return str;
	}
	
	public static boolean bracketBalance(String s) {
		System.out.println("is \"" + s + "\" balanced?");
		Stack<Character> brackets = new Stack<Character>();
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == '(' || s.charAt(i) == '[') {
				brackets.push(s.charAt(i));
			}
			else if(s.charAt(i) == ')') {
				if(brackets.isEmpty()) {
					return false;
				}
				if(brackets.pop() != '(') {
					System.out.println(brackets);
					return false;
				}
			}
			
			else if(s.charAt(i) == ']') {
				if(brackets.isEmpty()) {
					return false;
				}
				if(brackets.pop() != '[') {
					return false;
				}
			}
		}
		
		if(!brackets.isEmpty()) {
			return false;
		}
		
		return true;
	}

}
