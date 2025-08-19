public class PhotoMagicDeluxe {
	
	private static String alphaToBinary(String s) {
		String base64 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
		String binary = "";
		for(int i = 0; i < s.length(); i++) {
			String bit = Integer.toBinaryString(base64.indexOf(s.charAt(i)));
			while(bit.length() < 6) {
				bit = 0 + bit;
			}
			binary += bit;
		}
		return binary;
	}
	
	public static void main(String[] args) {
		Picture pipe = new Picture("mystery.png");
		pipe.show();
				
		LFSR lfsr = new LFSR(alphaToBinary("OPENSESAME"), 58);
		Picture newPipe = PhotoMagic.transform(pipe, lfsr);
		pipe.show();
		
		LFSR lfsrCopy = new LFSR(alphaToBinary("OPENSESAME"), 58);
		Picture originalPipe = PhotoMagic.transform(newPipe, lfsrCopy);
		originalPipe.show();
	}
}
