import java.util.LinkedList;
import java.util.Queue;

public class Runner {
	
	
	public static Queue<Integer> makeQueue(int[] nums) {
		Queue<Integer> queue = new LinkedList<Integer>();
		for (int num : nums)
			queue.offer(num);
		return queue;
	}
		
	public static void main(String[] args) {
		
		//evenFirst test
		System.out.println("evenFirst test\n");
		
		Queue<Integer> queue = Runner.makeQueue(new int[] {3,5,4,17,6,83,1,84,16,37});
		System.out.println(queue);
		Queue<Integer> evenQueue = QueueProbs.evenFirst(queue);
		System.out.println(evenQueue + "\n");
		
		//numPalindrome test
		System.out.println("numPalindrome test\n");
		
		Queue<Integer> palindrome = Runner.makeQueue(new int[] {3,8,17,9,17,8,3});
		Queue<Integer> notPalindrome = Runner.makeQueue(new int[] {3,10,17,9,17,8,3});

		System.out.println(palindrome + " is palindrome: " + QueueProbs.numPalindrome(palindrome));
		System.out.println(notPalindrome + " is palindrome: " + QueueProbs.numPalindrome(notPalindrome));
		
		//numPalindrome test
		System.out.println("\nnumPrime test");
		
		System.out.println("Primes to 10 " + QueueProbs.numPrime(10));
		System.out.println("Primes to 100 " + QueueProbs.numPrime(100));

	}
}
