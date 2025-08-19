import java.util.Stack;

public class RecursionProbs {
	public static double sumReciprocals(int n) {
		if(n == 1) {
			return 1;
		}
		return (1.0 / (double) n) + sumReciprocals(n-1);
	}
	
	public static int productOfEvens(int n) {
		if(n == 0) {
			return 1;
		}
		return 2 * n * productOfEvens(n-1);
	}
	
	public static String conversion(int num, int base) {
		if(num == 0) {
			return "";
		}
		return conversion(num/base, base) + (num % base);
	}
	
	public static int matchingDigits(int a, int b) {
		int numMatched = 0;

		if(a == -1 || b == -1) {
			return numMatched;
		}
		
		if(a % 10 == b % 10) {
			numMatched+=1;
		}
		
		if(a / 10 == 0) {
			a = -10;
		}
		
		if(b / 10 == 0) {
			b = -10;
		}
		
		return numMatched + matchingDigits((a / 10), (b / 10));
	}
	
	public static void doubleUp(Stack<Integer> nums) {
		if(nums.isEmpty()) {
			return;
		}
		int top = nums.pop();
		doubleUp(nums);
		nums.push(top); nums.push(top);
	}
	
	
	public static void printThis(int n) {
		if(n != 1 && n != 2) {
			System.out.print("<");
			printThis(n - 2);
			System.out.print(">");
		}
		else if(n == 1) {
			System.out.print("*");
		}
		else if(n == 2) {
			System.out.print("**");
		}
	}
	
	public static void printNums2(int n) {
		if(n != 1 && n != 2) {
			System.out.print((int) (n/2.0 + 0.5));
			printNums2(n-2);
			System.out.print((int) (n/2.0 + 0.5));
		}
		else if(n == 1) {
			System.out.print(1);
		}
		else if(n == 2) {
			System.out.print(11);
		}
	}
	
	public static void main(String[] args) {
		System.out.println("sumReciprocals tests");
		System.out.println("sumReciprocals(1) = " + sumReciprocals(1));
		System.out.println("sumReciprocals(2) = " + sumReciprocals(2));
		System.out.println("sumReciprocals(3) = " + sumReciprocals(3));
		System.out.println("sumReciprocals(10) = " + sumReciprocals(10));
		
		System.out.println("\nproductOfEvens tests");
		System.out.println("productOfEvens(1) = " + productOfEvens(1));
		System.out.println("productOfEvens(2) = " + productOfEvens(2));
		System.out.println("productOfEvens(3) = " + productOfEvens(3));
		System.out.println("productOfEvens(4) = " + productOfEvens(4));
		
		System.out.println("\nconversion tests");
		System.out.println("conversion(29,2) = " + conversion(29,2));
		System.out.println("conversion(10,2) = " + conversion(10,2));
		System.out.println("conversion(1453,8) = " + conversion(1453,8));
		System.out.println("conversion(33,2) = " + conversion(33,2));
		
		System.out.println("\nmatchingDigits tests");
		System.out.println("matchingDigits(1000,0) = " + matchingDigits(1000,0));
		System.out.println("matchingDigits(298892,7892) = " + matchingDigits(298892,7892));
		System.out.println("matchingDigits(1458,8) = " + matchingDigits(1458,8));
		System.out.println("matchingDigits(203,233) = " + matchingDigits(203,233));
		
		System.out.println("\ndoubleUp tests");
		Stack<Integer> stack1 = new Stack<Integer>();stack1.push(1);stack1.push(2);stack1.push(3);
		System.out.println("Orignal stack: " + stack1);
		doubleUp(stack1);
		System.out.println("doubleUp([1,2,3]) = " + stack1);
		Stack<Integer> stack2 = new Stack<Integer>();stack2.push(3);stack2.push(7);stack2.push(12);stack2.push(9);
		System.out.println("Orignal stack: " + stack2);
		doubleUp(stack2);
		System.out.println("doubleUp([3, 7, 12, 9]) = " + stack2);
		
		System.out.println("\nprintThis tests");
		System.out.print("printThis(1) = "); printThis(1); System.out.println();
		System.out.print("printThis(2) = "); printThis(2); System.out.println();
		System.out.print("printThis(3) = "); printThis(3); System.out.println();
		System.out.print("printThis(4) = "); printThis(4); System.out.println();
		System.out.print("printThis(7) = "); printThis(7); System.out.println();
		System.out.print("printThis(10) = "); printThis(10); System.out.println();
		
		System.out.println("\nprintNums2 tests");
		System.out.print("printNums2(1) = "); printNums2(1); System.out.println();
		System.out.print("printNums2(2) = "); printNums2(2); System.out.println();
		System.out.print("printNums2(3) = "); printNums2(3); System.out.println();
		System.out.print("printNums2(4) = "); printNums2(4); System.out.println();
		System.out.print("printNums2(7) = "); printNums2(7); System.out.println();
		System.out.print("printNums2(10) = "); printNums2(10); System.out.println();
	}
}
