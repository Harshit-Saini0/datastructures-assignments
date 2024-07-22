public class FireModel
{
    public static int SIZE = 47;
    private FireCell[][] myGrid;
    private FireView myView;

    public FireModel(FireView view)
    {
        myGrid = new FireCell[SIZE][SIZE];
        int setNum = 0;
        for (int r=0; r<SIZE; r++)
        {
            for (int c=0; c<SIZE; c++)
            {
            	myGrid[r][c] = new FireCell();
            }
        }
        myView = view;
        myView.updateView(myGrid);
    }

    public static void recursiveFire(FireCell[][] myGrid, int size, int r, int c) {
    	FireCell cell = myGrid[r][c];
    	if(cell.getStatus() == 0 /*dirt*/ || cell.getStatus() == 2 /*fire*/) {
    		return;
    	}

    	cell.setStatus(2/*fire*/);
    	if(r + 1 < size) 
    		recursiveFire(myGrid, size, r + 1, c);
    	if(r - 1 < size)
    		recursiveFire(myGrid, size, r - 1, c);
    	if(c + 1 < size) 
    		recursiveFire(myGrid, size, r, c + 1);
    	if(c - 1 < size)
    		recursiveFire(myGrid, size, r, c - 1);
    }
    

    public void solve()
    {
        recursiveFire(myGrid, SIZE, SIZE - 1, SIZE - 1);
        myView.updateView(myGrid);
    }

}
