import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public abstract class Piece
{
	int movementRange;
	int attackRange;
	int[] minRollToCap;
		//in reading order for each row on the chart in the PDF. the minimum
		//die roll for this piece to capture a king, queen, knight, bishop,
		//rook, and pawn, respectively. should be initialized by each
		//individual piece, so this exact array shouldn't manifest in working code.
	int row;
	int column;
    boolean white;
    char charRep;
    int[][] directions =
	{
			{0, 1}, //S
			{1, 1}, //SE
			{1, 0}, //E
			{1, -1}, //NE
			{0, -1}, //N
			{-1, -1}, //NW
			{-1, 0}, //W
			{-1, 1} //SW
	};
    
    public Piece(boolean white, int row, int column)
    {
    	this.white = white;
    	this.row = row;
    	this.column = column;
    }
    
    public Piece()
    {
    	
    }
    
    public Collection<int[]> searchValidMoves(Piece[][] tiles, int[][] directions)
    //takes a set of allowed directions (presumably DIRECTIONS).
    //searches for any tiles bordering its current position, then repeats
    //for movementRange > 1.
    {
    	Collection<int[]> validMoves = new ArrayList<>();
    	Queue<int[]> unexploredPositions = new LinkedList<>();
//		System.out.println("searchValidMoves: movementRange is " + this.movementRange);
		
    	int[] seed = {this.row, this.column};
    	
//    	System.out.println("searchValidMoves: (" + seed[0] + ", " + seed[1] + ") pushed to unexploredPositions");
    	unexploredPositions.add(seed);
		
    	for (int moves = movementRange; moves > 0; moves--)
    	{
    		while (!unexploredPositions.isEmpty())
    		{
				int[] startingPos = unexploredPositions.poll();
//				System.out.println("searchValidMoves: exploring (" + startingPos[0] + ", " + startingPos[1] + ")");
				
    			for (int direction = 0; direction < directions.length; direction++)
    			{
        			int[] exploring = {startingPos[0], startingPos[1]};
        			exploring[0] = exploring[0] + directions[direction][0];
        			exploring[1] = exploring[1] + directions[direction][1];
        			
        			try
        			{
            			if (tiles[exploring[0]][exploring[1]].charRep == '-')
            			{
            				validMoves.add(exploring);
//            				System.out.println("searchValidMoves: adding (" + exploring[0] + ", " + exploring[1] + ")");
            			}
        			} catch (ArrayIndexOutOfBoundsException e)
        			{
//        				System.out.println("searchValidMoves: outside tile range, continuing");
        			}
    			}
    		}
    		
    		unexploredPositions.addAll(validMoves);
    	}
    	
		return validMoves;
    }
    
    void toSysOut()
    {
    	System.out.println(this.toString() + " with coordinates (" + this.row + ", " + this.column + ")");
    }
}