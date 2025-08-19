import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
class SierpinskiPanel extends JPanel
{
	public SierpinskiPanel()
	{
		super.setPreferredSize(new Dimension(400, 400));
		super.setBackground(Color.WHITE);
	}

	public void paintComponent(Graphics g)
	{
		int width  = getWidth();

		super.paintComponent(g);

		/*
		 * DRAWING CODE BELOW
		 */
		drawSierpinski(0,0,width,g);
	}
	
	public void drawSierpinski(int xOrigin, int yOrigin, int sideLength, Graphics g) {
		if(sideLength <= 2) {
			return;
		}		
		
		g.setColor(Color.BLUE);
		//draw triangle
		g.drawLine(xOrigin, yOrigin, xOrigin, yOrigin+sideLength);
		g.drawLine(xOrigin, yOrigin, xOrigin+sideLength, yOrigin);
		g.drawLine(xOrigin+sideLength, yOrigin, xOrigin, yOrigin+sideLength);
		//recursive triangles
		drawSierpinski(xOrigin+sideLength/2,yOrigin,sideLength/2,g);
		drawSierpinski(xOrigin,yOrigin+sideLength/2,sideLength/2,g);
		drawSierpinski(xOrigin,yOrigin,sideLength/2,g);
	
	}
}

public class Sierpinski
{
	public static void main ( String[] args )
	{
		/*
		 * A frame is a container for a panel
		 * The panel is where the drawing will take place
		 */
		JFrame frame = new JFrame("Sierpinski");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new SierpinskiPanel());
		frame.pack();
		frame.setVisible(true);
	}
}