import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HuffmanTree {
	Node root;
	String[] huffCodes;

	public HuffmanTree(String codeFile) {
		try (Scanner s = new Scanner(new File(codeFile))) {
			root = new Node(null, 0);
			huffCodes = new String[257];

			while(s.hasNext()) {
				int asciiChar = Integer.parseInt(s.nextLine());
				String code = s.nextLine();
				huffCodes[asciiChar] = code;
				constructorHelper(root, asciiChar, code);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void constructorHelper(Node node, int asciiChar, String code) {
		if(code.length() == 1) {
			if(code.substring(0, 1).equals("1")) {
				node.right = new Node((char) asciiChar, 0);
			}
			else {
				node.left = new Node((char) asciiChar, 0);
			}
		}
		else {
			if(code.substring(0, 1).equals("1")) {
				if(node.right == null) {
					node.right = new Node(null, 0);
				}
				constructorHelper(node.right, asciiChar, code.substring(1));
			}
			else {
				if(node.left == null) {
					node.left = new Node(null, 0);
				}
				constructorHelper(node.left, asciiChar, code.substring(1));
			}
		}
	}

	public HuffmanTree(int[] counts) {
		PriorityQueue<Node> q = new PriorityQueue<>();
		for(int i = 0; i < counts.length; i++) {
			if(counts[i] > 0) {
				q.offer(new Node((char) i, counts[i]));
			}
		}
		//eof character 
		q.add(new Node((char) 256, 1));
		while(q.size() > 1) {
			Node oldNode1 = q.poll();
			Node oldNode2 = q.poll();
			Node newNode = new Node(null, oldNode1.freq + oldNode2.freq);
			newNode.left = oldNode2;
			newNode.right = oldNode1;
			q.add(newNode);
		}
		root = q.peek();
		huffCodes = new String[257];
	}

	public void write(String fileName) {		
		try {
			PrintWriter p = new PrintWriter(new File(fileName));
			writeHelper(root, "", p);
			p.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	private void writeHelper(Node node, String currentEncoding, PrintWriter p) {
		if(node == null) {
			return;
		}
		if(node.c != null) {
			int index = (int) node.c;
			p.println(index);
			p.println(currentEncoding);
			huffCodes[index] = currentEncoding;
		}
		else {
			writeHelper(node.left, currentEncoding + "0", p);
			writeHelper(node.right, currentEncoding + "1", p);
		}
	}

	public void encode(BitOutputStream out, String file) {
		try (Scanner s = new Scanner(new File(file));) {
			while(s.hasNext()) {
				String line = s.nextLine() + "\n";
				char[] charArr = line.toCharArray();
				for(char c: charArr) {
					String bits = huffCodes[(int) c];
					while(bits.length() > 0) {
						out.writeBit(Integer.parseInt(bits.substring(0,1)));
						bits = bits.substring(1);
					}
				}
			}
			String eof = huffCodes[256];
			while(eof.length() > 0) {
				out.writeBit(Integer.parseInt(eof.substring(0,1)));
				eof = eof.substring(1);
			}
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void decode(BitInputStream in, String outFile) {
		
		try (PrintWriter p = new PrintWriter(new File(outFile))) {
			Node node = root;
			while(true) {
				int bit = in.readBit();
				if(bit == 1) {
					node = node.right;
				}
				else {
					node = node.left;
				}
				if(node.c != null) {
					if((int) node.c == (char) 256) {
						break;
					}
					p.print(node.c);
					node = root;
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
