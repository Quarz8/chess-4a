public class Knight extends Piece
{
	boolean hasMoved = false;
	
	public Knight(boolean white, int x, int y, Corp corp)
    {
    	super(white, x, y, corp);
    	this.movementRange = 5;
    	this.attackRange = 1;
    	this.minRollToCap = new int[]{6, 6, 4, 4, 5, 2};
    	this.charRep = (white ? 'N' : 'n');
    }
}