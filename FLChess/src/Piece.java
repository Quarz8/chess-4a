import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
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
    
    public Collection<int[]> searchValidActions(Piece[][] tiles, int[][] directions, int range, boolean move)
    //Piece[][] tiles: the game board, just without the additional game information.
    //int[][] directions: a set of all allowed directions the piece can search.
    //int range: how many tiles a piece will search orthogonally.
    //boolean move: whether the piece is looking for potential moves or attacks.
    //	(this changes the criterion for adding new actions to validActions.)
    {
    	Collection<int[]> validActions = new ArrayList<>();
    	Collection<int[]> exploredPositions = new ArrayList<>();
    	Queue<int[]> unexploredPositions = new LinkedList<>();
		
    	int[] seed = {this.row, this.column};
    	
//    	System.out.println("searchValidActions: (" + seed[0] + ", " + seed[1] + ") pushed to unexploredPositions");
    	unexploredPositions.add(seed);
		
    	for (int actions = range; actions > 0; actions--)
    	{
    		while (!unexploredPositions.isEmpty())
    		{
				int[] startingPos = unexploredPositions.poll();
//				System.out.println("searchValidActions: exploring (" + startingPos[0] + ", " + startingPos[1] + ")");
				
    			for (int direction = 0; direction < directions.length; direction++)
    			{
        			int[] exploring = {startingPos[0], startingPos[1]};
        			exploring[0] = exploring[0] + directions[direction][0];
        			exploring[1] = exploring[1] + directions[direction][1];
        			
        			try
        			{
            			if (move && tiles[exploring[0]][exploring[1]].charRep == '-')
            			{
            				validActions.add(exploring);
//            				System.out.println("searchValidActions: adding (" + exploring[0] + ", " + exploring[1] + ")");
            			}
            			else if (this.white != tiles[exploring[0]][exploring[1]].white)
            			{
            				validActions.add(exploring);
//            				System.out.println("searchValidActions: adding (" + exploring[0] + ", " + exploring[1] + ")");
            			}
        			} catch (ArrayIndexOutOfBoundsException e)
        			{
//        				System.out.println("searchValidActions: outside tile range, continuing");
        			}
        			
        			exploredPositions.add(exploring);
    			}
    		}
    		
    		unexploredPositions.addAll(exploredPositions);
    		exploredPositions.clear();
    	}
    	
		return validActions;
    }
    
    void toSysOut()
    {
    	System.out.println(this.toString() + " with coordinates (" + this.row + ", " + this.column + ")");
    }
}