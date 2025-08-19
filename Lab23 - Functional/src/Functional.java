import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Functional {
	public static void main(String[] args) {
		List<Integer> nums = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
		List<String> list = new ArrayList<>(Arrays.asList("hello", "and", "goodbye"));

		//1
		//		nums.forEach(n -> System.out.println(n));
		//		nums.forEach(System.out::println);

		//2
		//		nums.removeIf(a -> a % 2 == 0);
		//		nums.forEach(System.out::println);

		//3
		//		System.out.println(nums.stream()
		//								.filter(n -> n % 2 == 1)
		//								.count());

		//4
		//		list.stream()
		//			.filter(w -> w.substring(0,1).equals("a"))
		//			.forEach(System.out::println);

		//5
		//		nums = nums.stream().map(n -> n *= 2).collect(Collectors.toList());
		//				nums.forEach(System.out::println);

		//6
		//		nums = nums.stream().map(n -> n = n * n + 10)
		//									.collect(Collectors.toList());
		//		nums.removeIf(n -> n % 10 == 5 || n % 10 == 6);
		//		nums.forEach(System.out::println);

		//7
		//		List<Double> doubles = new ArrayList<Double>(Arrays.asList(1.0, 2.0, 3.0, 4.0));
		//		doubles.stream()
		//			.map(n -> n *= 1.12)
		//			.forEach(n -> System.out.println(n));

		//8
		//		System.out.println(new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4)).stream().reduce((a,b) -> a + b).get());

		//9
		//		System.out.println(nums.stream().map(n -> n * 1.12).reduce((a,b) -> a + b).get());

		//10
//				System.out.println(nums.stream()
//										.max((n1,n2) -> n1.compareTo(n2))
//										.get());

		//11
//				JButton button = new JButton("Click here");
//				JFrame  frame  = new JFrame("Button test"); //window to contain the button
//				
//				button.addActionListener(e -> System.out.println("Button Clicked"));
//		
//				frame.setSize(200, 200); //set the size of the container window
//				frame.add(button);       //add button to the window
//				frame.setVisible(true);  //make frame visible

		//12
//				class Person {
//					String name;
//					int    age;
//		
//					Person(String name, int age) {
//						this.name = name;
//						this.age  = age;
//					}
//		
//					@Override
//					public String toString() {
//						return this.name + ", " + this.age;
//					}
//		
//					int getAge() { return this.age; }
//				}
//				
//				List<Person> users = new ArrayList<>();
//				users.add(new Person("Sarah",   40));
//				users.add(new Person("Peter",   50));
//				users.add(new Person("Lucy",    60));
//				users.add(new Person("Albert",  20));
//				users.add(new Person("Charlie", 30));
//				
//				System.out.println(users.stream()
//						.mapToInt(n -> n.getAge())
//						.boxed()
//						.max((n1,n2) -> n1.compareTo(n2))
//						.get());

	}
}
