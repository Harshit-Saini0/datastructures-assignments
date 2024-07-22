import java.awt.Color;
import java.awt.Font;

enum GemType {
    GREEN, BLUE, ORANGE; //define the different types of Gems, comma delimited
}

public class Gem 
{	
	GemType gemType;
	int pointVal;
	
	public Gem() {
		GemType[] gems = {GemType.GREEN, GemType.BLUE, GemType.ORANGE};
		int[] points = {0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50};
		pointVal = points[(int) (Math.random() * 11)];
		gemType = gems[(int) (Math.random() * 3)];
	}
	
	public Gem(GemType type, int i) {
		gemType = type;
		pointVal = i;
	}
	
	public String toString() {
		return gemType + " " + pointVal;
	}
	
	public GemType getType() {
		return gemType;
	}
	
	public int getPoints() {
		return pointVal;
	}
	
	public void draw(double x, double y) {
		String color = ""+gemType;
		String file = "gem_" + color.toLowerCase() + ".png";
		String text = pointVal + "";
		StdDraw.setFont(new Font("SansSerif", Font.BOLD, 14));
		StdDraw.setPenColor(Color.WHITE);
		StdDraw.picture(x,y,file);
		StdDraw.text(x,y,text);
	}

	/** Tester main method */
	public static void main(String [] args)
	{
		final int maxGems = 16;
		
		// Create a gem of each type
		Gem green  = new Gem(GemType.GREEN, 10);
		Gem blue   = new Gem(GemType.BLUE, 20);
		Gem orange = new Gem(GemType.ORANGE, 30);
		System.out.println(green  + ", " + green.getType()  + ", " + green.getPoints());		
		System.out.println(blue   + ", " + blue.getType()   + ", " + blue.getPoints());
		System.out.println(orange + ", " + orange.getType() + ", " + orange.getPoints());
		green.draw(0.3, 0.7);
		blue.draw(0.5, 0.7);
		orange.draw(0.7, 0.7);
		
		// A row of random gems
		for (int i = 0; i < maxGems; i++)
		{
			Gem g = new Gem();
			g.draw(1.0 / maxGems * (i + 0.5), 0.5);
		}
	}
}
