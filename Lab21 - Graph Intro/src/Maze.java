import java.util.*;
import java.io.*;
public class Maze
{
    public static void main(String[] args) throws FileNotFoundException 
    {
        Scanner s = new Scanner(new File("maze.dat"));
        
        int r = s.nextInt();
        int c = s.nextInt();
        int t = s.nextInt();
        boolean[][] b = new boolean[r][c];   
        for(int x = 0; x < r; x++){
            for(int y = 0; y < c; y++){
                b[x][y] = s.nextInt() == 1;
            }
        }
        
        for(int i = 0; i < t; i++){
            Queue<Location> q = new LinkedList<Location>();
            q.add(new Location(s.nextInt(), s.nextInt(), 0));
            Location goal = new Location(s.nextInt(), s.nextInt(), -1);
            while(!q.isEmpty() && !q.peek().equals(goal)){
            	Location co = q.poll();
                if(!co.inRange(r, c) || !b[co.r][co.c]) continue;
                b[co.r][co.c] = false;
                q.add(co.up());
                q.add(co.down());
                q.add(co.left());
                q.add(co.right());
            }
            if(q.isEmpty()) System.out.println(-1);
            else System.out.println(q.poll().distance);
        }
    }
    private static class Location {
        int r, c, distance;
        public Location(int rr, int cc, int d){
            r = rr; 
            c = cc;
            distance = d;
        }
        
        public boolean equals(Object other){
          Location o = (Location) other;
          return r == o.r && c == o.c;  
        }
        
        public boolean inRange(int rows, int cols){
            return r >= 0 && c >= 0 && c < cols && r < rows;
        }
        
        public Location up(){
            return new Location(r-1, c, distance+1);
        }
        
        public Location down(){
            return new Location(r+1, c, distance+1);
        }
        
        public Location left(){
            return new Location(r, c-1, distance+1);
        }
        
        public Location right(){
            return new Location(r, c+1, distance+1);
        }
    }
}
