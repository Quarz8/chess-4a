public class Knight extends Piece
{
	boolean hasMoved = false;
	
	public Knight(boolean white, int x, int y)
    {
    	super(white, x, y);
    	this.movementRange = 5;
    	this.attackRange = 1;
    	this.minRollToCap = new int[]{6, 6, 4, 5, 2};
    	this.charRep = (white ? 'N' : 'n');
    }
	
	public Knight(boolean white)
    {
    	super(white);
    	this.movementRange = 5;
    	this.attackRange = 1;
    	this.minRollToCap = new int[]{6, 6, 4, 5, 2};
    	this.charRep = (white ? 'N' : 'n');
    }
}