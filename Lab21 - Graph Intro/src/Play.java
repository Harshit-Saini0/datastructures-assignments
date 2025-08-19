import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Play {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("play.dat"));
		int numCases = Integer.parseInt(s.nextLine());
		for(int i = 0; i < numCases; i++) {
			String[] nml = s.nextLine().split(" ");
			int m = Integer.parseInt(nml[1]);
			int l = Integer.parseInt(nml[2]);
			PlayGraph graph = new PlayGraph();
			for(int j = 0; j < m; j++) {
				String[] nums = s.nextLine().split(" ");
				graph.addEdge(Integer.parseInt(nums[0]), Integer.parseInt(nums[1]));
			}
			int fallenDominos = 0;
			ArrayList<Integer> visited = new ArrayList<Integer>();
			for(int j = 0; j < l; j++) {
				fallenDominos += graph.howManyFall(Integer.parseInt(s.nextLine()), visited);
			}
			System.out.println(fallenDominos);
			if(s.hasNext()) 
				s.nextLine();
		}
		s.close();
	}
}
