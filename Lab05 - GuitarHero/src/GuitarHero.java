import java.util.ArrayList;

public class GuitarHero {
	
	public static void main(String[] args) {
        
		final double A4 = 440.0;
		final double A2 = A4/4;
                
        ArrayList<GuitarString> strings = new ArrayList<GuitarString>();
        strings.add(new GuitarString(A2));
        for(double i = 1; i <= 36; i++) {
        	strings.add(new GuitarString(A2 * Math.pow(2, i/12.0)));
        }

        
        // the main input loop
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        StdDraw.picture(0.5,0.5,"keyboard.png",0.95,0.2);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.001);
        StdDraw.text(0.5, 0.75, "Click the letters associated with the keyboard to play notes");
        
        while (true) {

            // check if the user has typed a key, and, if so, process it
            if (StdDraw.hasNextKeyTyped()) {
 
                // the user types this character
                char key = StdDraw.nextKeyTyped();
                if(keyboard.indexOf(key) >= 0)
                strings.get(keyboard.indexOf(key)).pluck();
                
            }

            // compute the superposition of the samples
            double sample = 0;
            for(int i = 0; i < strings.size(); i++) {
            	double value = strings.get(i).sample();
            	sample += value;
            }

            // send the result to standard audio
            StdAudio.play(sample);
            
            // advance the simulation of each guitar string by one step
            for(int i = 0; i < strings.size(); i++) {
            	strings.get(i).tic();
            }
          
        }
    }
	
}
