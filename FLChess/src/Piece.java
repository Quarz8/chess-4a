import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public abstract class Piece
{
	final int MOVEMENT_RANGE = 0;
	final int ATTACK_RANGE = 0;
	final int[] MIN_ROLL_TO_CAPTURE = {7, 7, 7, 7, 7, 7};
		//in reading order for each row on the chart in the PDF. the minimum
		//die roll for this piece to capture a king, queen, knight, bishop,
		//rook, and pawn, respectively. should be initialized by each
		//individual piece, so this exact array shouldn't manifest in working code.
	final int[][] DIRECTIONS =
		{
				{0, 1}, //N
				{1, 1}, //NE
				{1, 0}, //E
				{1, -1}, //SE
				{0, -1}, //S
				{-1, -1}, //SW
				{-1, 0}, //W
				{-1, 1} //NW
		};
	int x;
	int y;
    boolean white;
    char charRep;
    
    public Piece(boolean white, int x, int y)
    {
    	this.white = white;
    	this.x = x;
    	this.y = y;
    }
    
    public Piece(boolean white)
    {
    	this.white = white;
    }
    
    public Collection<int[]> searchValidMoves(int[][] directions)
    //takes a set of allowed directions (presumably DIRECTIONS).
    //searches for any tiles bordering its current position, then repeats
    //for MOVEMENT_RANGE > 1.
    //ARRAYINDEXOUTOFBOUNDSEXCEPTION IS NOT ENFORCED INSIDE THIS METHOD.
    //wherever you handle the resulting Collection<int[]>, you'll likely want
    //to put something inside of a try/catch.
    //TODO check if tiles in validMoves are occupied, avoid if so
    {
    	Collection<int[]> validMoves = new ArrayList<>();
    	Queue<int[]> unexploredPositions = new LinkedList<>();
    	
    	int[] seed = {this.x, this.y};
    	unexploredPositions.add(seed);
    	
    	for (int index = MOVEMENT_RANGE; index > 0; index--)
    	{
    		while (!unexploredPositions.isEmpty())
    		{
				int[] startingPos = unexploredPositions.poll();
				
    			for (int direction = 0; direction < directions.length; direction++)
    			{
        			int[] exploring = startingPos;
        			exploring[0] = exploring[0] + directions[direction][0];
        			exploring[1] = exploring[1] + directions[direction][1];
        			if (!validMoves.contains(exploring))
        				//to remove redundancies
        			{
        				validMoves.add(exploring);
        			}
    			}
    		}
    		
    		unexploredPositions.addAll(validMoves);
    	}
    	
		return validMoves;
    }
    
    void toSysOut()
    {
    	System.out.println(this.toString() + " with coordinates (" + x + ", " + y + ")");
    }
}