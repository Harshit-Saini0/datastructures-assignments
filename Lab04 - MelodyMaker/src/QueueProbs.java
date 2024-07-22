import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class QueueProbs {
	
	public static Queue<Integer> evenFirst(Queue<Integer> nums) {
		Queue<Integer> oddNums = new LinkedList<Integer>();
		Queue<Integer> evenNums = new LinkedList<Integer>();
		
		while(!nums.isEmpty()) {
			if(nums.peek() % 2 == 0) {
				evenNums.offer(nums.poll());
			}
			else {
				oddNums.offer(nums.poll());
			}
		}
		
		while(!evenNums.isEmpty()) {
			nums.offer(evenNums.poll());
		}
		
		while(!oddNums.isEmpty()) {
			nums.offer(oddNums.poll());
		}
		
		return nums;
	}
	
	public static boolean numPalindrome(Queue<Integer> nums) {
		Queue<Integer> numsCopy = new LinkedList<Integer>();
		
		Stack<Integer> reversedNums = new Stack<Integer>();
		
		int size = nums.size();
		for(int i = 0; i < size; i++) {
			numsCopy.offer(nums.peek());
			
			reversedNums.push(nums.poll());
		}
		
		for(int i = 0; i < size; i++) {
			if(numsCopy.poll() != reversedNums.pop()) {
				return false;
			}
		}
		
		return true;
	}
	
	public static Queue<Integer> numPrime(int n) {
		Queue<Integer> queue = new LinkedList<Integer>();		
		
		for(int i = 2; i <= n; i++) {
			queue.offer(i);
		}
		
		Queue<Integer> primes = new LinkedList<Integer>();
		
		while(!queue.isEmpty()) {
			int theP = queue.peek();
			primes.offer(queue.poll());
			
			Queue<Integer> queueCopy = new LinkedList<Integer>();
			while(!queue.isEmpty()) {
				int j = queue.poll();
				if(j % theP == 0) {
					//j is removed
				}
				else {
					queueCopy.offer(j);
				}
			}
			
			queue = queueCopy;
		}

		
		return primes;
	}
}
