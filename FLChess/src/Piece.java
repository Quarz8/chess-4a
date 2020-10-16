import java.util.ArrayList;

public abstract class Piece
{
	final int MOVEMENT_RANGE = 0;
	final int ATTACK_RANGE = 0;
	final int[] MIN_ROLL_TO_CAPTURE = {7, 7, 7, 7, 7, 7};
		//in reading order for each row on the chart in the PDF. the minimum
		//die roll for this piece to capture a king, queen, knight, bishop,
		//rook, and pawn, respectively.
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
    
//    abstract ArrayList<Move> searchValidMoves(int x, int y);
    
    void toSysOut()
    {
    	System.out.println(this.toString() + " with coordinates (" + x + ", " + y + ")");
    }
}