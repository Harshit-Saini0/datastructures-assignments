import java.util.Stack;

public class Runner {
	
	
	public static Stack<Integer> makeStack(int[] nums) {
		Stack<Integer> stack = new Stack<>();
		for (int num : nums)
			stack.push(num);
		return stack;
	}
	
	//example call: makeStack(new int[] {1, 2, 3, 4})
	
	public static void main(String[] args) {
		
		//double test
		
		Stack<Integer> stack1 = Runner.makeStack(new int[] {1,3,5,0,-1});
		System.out.println(stack1);
		Stack<Integer> doubleStack = StackProbs.doubleUp(stack1);
		System.out.println(doubleStack + "\n");
		
		//pos neg test
		
		Stack<Integer> stack2 = Runner.makeStack(new int[] {2,9,-4,3,-1,0,-6});
		System.out.println(stack2);
		Stack<Integer> posAndNeg = StackProbs.posAndNeg(stack2);
		System.out.println(posAndNeg + "\n");
		
		//shiftN test
		
		Stack<Integer> stack3 = Runner.makeStack(new int[] {7, 23, -7, 0, 22, -8, 4, 5});
		System.out.println(stack3);
		Stack<Integer> shiftN = StackProbs.shiftByN(stack3, 3);
		System.out.println(shiftN + "\n");
		
		//reverseVowels test
	
		System.out.println("hello how are you");
		System.out.println(StackProbs.reverseVowels("hello how are you") + "\n");
		
		//bracketBalance test
		
		System.out.println(StackProbs.bracketBalance(("(([()])))")));
		System.out.println(StackProbs.bracketBalance(("([()[]()])()")));
		
	}
}
