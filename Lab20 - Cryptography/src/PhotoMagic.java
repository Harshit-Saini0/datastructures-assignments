import java.awt.Color;

public class PhotoMagic {
	public static Picture transform(Picture pic, LFSR lfsr) {
		for(int i = 0; i < pic.width(); i++) {
			for(int j = 0; j < pic.height(); j++) {
				Color c = pic.get(i, j);
				
				int red = c.getRed();
				int green = c.getGreen();
				int blue = c.getBlue();
				
				int newRed = red ^ lfsr.generate(8);
				int newGreen = green ^ lfsr.generate(8);
				int newBlue = blue ^ lfsr.generate(8);
				
				Color newC = new Color(newRed, newGreen, newBlue);
				pic.set(i, j, newC);
			}
		}
		return pic;
	}
	
	public static void main(String[] args) {
		Picture pipe = new Picture("pipe.png");
		pipe.show();
		
		LFSR lfsr = new LFSR("01101000010100010000", 16);
		Picture newPipe = PhotoMagic.transform(pipe, lfsr);
		newPipe.show();
		
		LFSR lfsrCopy = new LFSR("01101000010100010000", 16);
		Picture originalPipe = PhotoMagic.transform(newPipe, lfsrCopy);
		originalPipe.show();
	}
}