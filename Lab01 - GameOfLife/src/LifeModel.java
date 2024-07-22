import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.Timer;

public class LifeModel implements ActionListener
{

	/*
	 *  This is the Model component.
	 */

	private static int SIZE = 60;
	private LifeCell[][] grid;
	
	LifeView myView;
	Timer timer;

	/** Construct a new model using a particular file */
	public LifeModel(LifeView view, String fileName) throws IOException
	{       
		int r, c;
		grid = new LifeCell[SIZE][SIZE];
		for ( r = 0; r < SIZE; r++ )
			for ( c = 0; c < SIZE; c++ )
				grid[r][c] = new LifeCell();

		if ( fileName == null ) //use random population
		{                                           
			for ( r = 0; r < SIZE; r++ )
			{
				for ( c = 0; c < SIZE; c++ )
				{
					if ( Math.random() > 0.85) //15% chance of a cell starting alive
						grid[r][c].setAliveNow(true);
				}
			}
		}
		else
		{                 
			Scanner input = new Scanner(new File(fileName));
			int numInitialCells = input.nextInt();
			for (int count=0; count<numInitialCells; count++)
			{
				r = input.nextInt();
				c = input.nextInt();
				grid[r][c].setAliveNow(true);
			}
			input.close();
		}

		myView = view;
		myView.updateView(grid);

	}

	/** Constructor a randomized model */
	public LifeModel(LifeView view) throws IOException
	{
		this(view, null);
	}

	/** pause the simulation (the pause button in the GUI */
	public void pause()
	{
		timer.stop();
	}
	
	/** resume the simulation (the pause button in the GUI */
	public void resume()
	{
		timer.restart();
	}
	
	/** run the simulation (the pause button in the GUI */
	public void run()
	{
		timer = new Timer(50, this);
		timer.setCoalesce(true);
		timer.start();
	}

	/** called each time timer fires */
	public void actionPerformed(ActionEvent e)
	{
		oneGeneration();
		int r,c;
		for (r = 0; r < SIZE; r++) {
			for ( c = 0; c < SIZE; c++ ) {
				if(grid[r][c].isAliveNext()) {
					grid[r][c].setAliveNow(true);
				}
				else {
					grid[r][c].setAliveNext(false);
					grid[r][c].setAliveNow(false);
				}
			}
		}
		myView.updateView(grid);
	}
	
	private int howManyAlive(int r, int c) {
		int numAlive = 0;
		//check how many around are alive
		for(int x = -1; x <= 1; x++) {
			for(int y = -1; y <= 1; y++) {
				if((x == 0 && y == 0)) {
					//do nothing
				}
				else if((r+x) >= 0 && (r+x) < SIZE && (c+y) >= 0 && (c+y) < SIZE
					 && grid[r + x][c + y].isAliveNow()) {
					numAlive++;
				}
			}
		}
		return numAlive;
	}

	/** main logic method for updating the state of the grid / simulation */
	private void oneGeneration()
	{
		int r,c;
		for ( r = 0; r < SIZE; r++ ) {
			for ( c = 0; c < SIZE; c++ ) {
				int numAlive = howManyAlive(r,c);
				//if its alive will it live or die
				if(grid[r][c].isAliveNow()) {
					//live
					if(numAlive == 2 || numAlive == 3) {
						grid[r][c].setAliveNext(true);
					}
					
					//die
					else {
						grid[r][c].setAliveNext(false);
					}					
				}
				
				//will it be born
				else {					
					//birth
					if(numAlive == 3) {
						grid[r][c].setAliveNext(true);
					}
					
					//stay dead
					else {
						grid[r][c].setAliveNext(false);
					}
				}
			}
		}
	}
}

