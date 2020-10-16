import java.util.ArrayList;

public class Pawn extends Piece
{
	final int MOVEMENT_RANGE = 1;
	final int ATTACK_RANGE = 1;
	final int[] MIN_ROLL_TO_CAP = {6, 6, 6, 5, 6, 4};
    boolean hasMoved = false;
    
    public Pawn(boolean white, int x, int y)
    {
    	super(white, x, y);
    	this.charRep = (white ? 'P' : 'p');
    }
    
    public Pawn(boolean white)
    {
    	super(white);
    }
    
    public ArrayList<Move> searchValidMoves(int x, int y, int range)
    {
    	ArrayList<Move> moveSeq = new ArrayList<Move>();
		return moveSeq;
    }
}