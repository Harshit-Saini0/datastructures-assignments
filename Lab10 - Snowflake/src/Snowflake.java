import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
class SnowFlakePanel extends JPanel
{
	public SnowFlakePanel()
	{
		super.setPreferredSize(new Dimension(400, 400));
		super.setBackground(Color.WHITE);
	}

	public void paintComponent(Graphics g)
	{
		int width  = getWidth();
		int height = getHeight();

		super.paintComponent(g);

		/*
		 * DRAWING CODE BELOW
		 */
		Color[] colorList = {Color.BLUE, Color.PINK, Color.ORANGE, Color.CYAN, Color.GREEN};
		for(int i = 0; i < 20; i++) {
			double sizeMultiplier = (Math.random()/2);
			Color randomColor = colorList[(int)(Math.random() * colorList.length)];
			drawSnowflake((int)(Math.random() * width), (int)(Math.random() * height),
					(int)(sizeMultiplier * width), (int)(sizeMultiplier * height), g, randomColor, 0);
		}
	}
	
	public void drawSnowflake(int x, int y, int width, int height, Graphics g, Color color, int numIterations) {
		if(numIterations >= 3) {
			return;
		}		
		g.setColor(color);
		
		for(int i = 1; i <= 6; i++) {
			int xCoord = (int) ((width/4) * Math.cos(i * Math.PI / 3.0) + x);
			int yCoord = (int) ((height/4) * Math.sin(i * Math.PI / 3.0) + y);
			g.drawLine(x, y, xCoord, yCoord);
			drawSnowflake(xCoord, yCoord, width/4,height/4, g, color, numIterations + 1);
		}
	}
}

public class Snowflake
{
	public static void main ( String[] args )
	{
		/*
		 * A frame is a container for a panel
		 * The panel is where the drawing will take place
		 */
		JFrame frame = new JFrame("Snowflake");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new SnowFlakePanel());
		frame.pack();
		frame.setVisible(true);
	}
}
