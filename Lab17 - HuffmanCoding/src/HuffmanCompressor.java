import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class HuffmanCompressor {
	public static int[] countFrequencies(String file) {
		int[] asciiFrequency = new int[256];
		try (Scanner s = new Scanner(new File(file));) {
			while(s.hasNext()) {
				String line = s.nextLine() + "\n";
				for(int i = 0; i < line.length(); i++) {
					asciiFrequency[line.charAt(i)]++;
				}
			}
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return asciiFrequency;
	}
	
	public static void compress(String file) {
		String fileNoExtension = file.substring(0, file.length()-4);
		HuffmanTree h = new HuffmanTree(HuffmanCompressor.countFrequencies(file));
		File codeFile = new File(fileNoExtension + ".code");
		try {
			codeFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		h.write(fileNoExtension + ".code");
		File shortFile = new File(fileNoExtension + ".short");
		try {
			shortFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		h.encode(new BitOutputStream(fileNoExtension + ".short"), file);
	}
	
	public static void expand(String codeFile, String newFile) {
		String fileNoExtension = codeFile.substring(0, codeFile.length()-5);
		HuffmanTree hDecode = new HuffmanTree(codeFile);
		hDecode.decode(new BitInputStream(fileNoExtension + ".short"), newFile);
	}

	public static void main(String[] args) {
		compress("short.txt");
		expand("short.code", "short.new");
		compress("hamlet.txt");
		expand("hamlet.code", "hamlet.new");
	}
}
