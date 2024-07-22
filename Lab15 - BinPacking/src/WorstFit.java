import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class WorstFit {
	Queue<Disk> diskQueue;
	LinkedList<Integer> files;
	
	public WorstFit(String filename) {
		files = new LinkedList<>();
		diskQueue = new PriorityQueue<Disk>();
	
		try (Scanner s = new Scanner(new File(filename))) {
			while(s.hasNext())
				files.offer(s.nextInt());
		} 
		catch (FileNotFoundException e) {e.printStackTrace();}
	}
	
	public void reset() {
		diskQueue = new PriorityQueue<Disk>();
	}
	
	public void worstFit() {
		Queue<Integer> filesCopy = new LinkedList<Integer>(files);
		while(!filesCopy.isEmpty()) {
			int file = filesCopy.poll();
			if(diskQueue.isEmpty() || diskQueue.peek().getStorageUsed() + file > 1000000) {
				diskQueue.offer(new Disk(file));
			}
			else {
				Disk disk = diskQueue.poll();
				disk.add(file);
				diskQueue.add(disk);
			}
		}
		print();
	}
	
	public void worstFitDecreasing() {
		LinkedList<Integer> filesCopy = new LinkedList<Integer>(files);
		//sort
		Collections.sort(filesCopy); Collections.reverse(filesCopy);
		while(!filesCopy.isEmpty()) {
			int file = filesCopy.poll();
			if(diskQueue.isEmpty() || diskQueue.peek().getStorageUsed() + file > 1000000) {
				diskQueue.offer(new Disk(file));
			}
			else {
				Disk disk = diskQueue.poll();
				disk.add(file);
				diskQueue.add(disk);
			}
		}
		print();
	}
	
	public void print() {
		int numDisks = diskQueue.size();	
		double totalSizeKb = 0;
		Queue<Disk> tempQueue = new LinkedList<>();
		
		while(!diskQueue.isEmpty()) {
			Disk d = diskQueue.poll();
			totalSizeKb += d.getStorageUsed();
			tempQueue.offer(d);
		}
		
		//print size and numdisks
		System.out.println("Total size = " + totalSizeKb/1000000 + " GB");
		System.out.println("Disks req'd = " + numDisks);
		
		//print disks
		if(numDisks < 100) {
			for(int i = 0; i < numDisks; i++) {
				Disk disk = tempQueue.poll();
				System.out.print(1000000 - disk.getStorageUsed() + ": ");
				for(int j = 0; j < disk.getFiles().size(); j++) {
					System.out.print(disk.getFiles().get(j) + " ");
				}
				System.out.println();
				tempQueue.offer(disk);
			}
		}
		
		while(!tempQueue.isEmpty()) 
			diskQueue.offer(tempQueue.poll());
	}	
		
	public static void main(String[] args) {
		WorstFit w = new WorstFit("input20.txt");
		w.worstFit();
		System.out.println();
		w.reset();
		w.worstFitDecreasing();
		System.out.println();
		
		WorstFit w2 = new WorstFit("input1000.txt");
		w2.worstFit();
		System.out.println();
		w2.reset();
		w2.worstFitDecreasing();
		System.out.println();
		
		WorstFit w3 = new WorstFit("input100000.txt");
		w3.worstFit();
		System.out.println();
		w3.reset();
		w3.worstFitDecreasing();
		System.out.println();

	}
}
