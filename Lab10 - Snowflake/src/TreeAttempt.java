import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
class TreePanel extends JPanel
{
	public TreePanel()
	{
		super.setPreferredSize(new Dimension(400, 400));
		super.setBackground(Color.WHITE);
	}

	public void paintComponent(Graphics g)
	{
		int height = getHeight();
		int width = getWidth();
		int strokeWidth = 20;
		double angle = -Math.PI / 8.0;

		super.paintComponent(g);
		/*
		 * DRAWING CODE BELOW
		 */
		
		//draw trunk
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(1));
		g2.setColor(Color.BLACK);
		g2.drawLine(width/2, height, width/2, height/2);
		drawTree(width/2,height/2,width/8,g2,1,angle);
	}
	
	public void drawTree(int xOrigin, int yOrigin, int sideLength, Graphics2D g, int strokeWidth, double angle) {
		if(strokeWidth != 1) {
			g.setStroke(new BasicStroke(strokeWidth/2));
			strokeWidth/=2;
		}
		if(sideLength <= 2) {
			return;
		}		
		
		//draw branches
		double angle1 = -1 * Math.PI / 4.0;
		int xCoord1 = (int) ((sideLength) * Math.cos(angle + angle1) + xOrigin);
		int yCoord1 = (int) ((sideLength) * Math.sin(angle + angle1) + yOrigin);
		g.drawLine(xOrigin, yOrigin, xCoord1, yCoord1); 
		
		double angle2 = -2 * Math.PI / 4.0;	
		int xCoord2 = (int) ((sideLength/2) * Math.cos(angle + angle2) + xOrigin);
		int yCoord2 = (int) ((sideLength/2) * Math.sin(angle + angle2) + yOrigin);
		g.drawLine(xOrigin, yOrigin, xCoord2, yCoord2);       
		
		double angle3 = -3 * Math.PI / 4.0;	
		int xCoord3 = (int) ((sideLength) * Math.cos(angle + angle3) + xOrigin);
		int yCoord3 = (int) ((sideLength) * Math.sin(angle + angle3) + yOrigin);
		g.drawLine(xOrigin, yOrigin, xCoord3, yCoord3);   
		
		

		drawTree(xCoord1, yCoord1, (int) (sideLength/2.0), g, strokeWidth, angle1 + Math.PI/2.0);
		drawTree(xCoord2, yCoord2, (int) (sideLength/2.0), g, strokeWidth, angle2 + Math.PI/2.0);
		drawTree(xCoord3, yCoord3, (int) (sideLength/2.0), g, strokeWidth, angle3 + Math.PI/2.0);
	
	}
}

public class TreeAttempt
{
	public static void main (String[] args )
	{
		/*
		 * A frame is a container for a panel
		 * The panel is where the drawing will take place
		 */
		JFrame frame = new JFrame("Tree");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new TreePanel());
		frame.pack();
		frame.setVisible(true);
	}
}