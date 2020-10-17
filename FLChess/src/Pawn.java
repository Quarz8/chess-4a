public class Pawn extends Piece
{
	public Pawn(boolean white, int x, int y)
    {
    	super(white, x, y);
    	this.movementRange = 1;
    	this.attackRange = 1;
    	this.minRollToCap = new int[]{6, 6, 6, 5, 6, 4};
    	this.charRep = (white ? 'P' : 'p');
    }
    
    public Pawn(boolean white)
    {
    	super(white);
    	this.movementRange = 1;
    	this.attackRange = 1;
    	this.minRollToCap = new int[]{6, 6, 6, 5, 6, 4};
    	this.charRep = (white ? 'P' : 'p');
    }
}