public class King extends Piece
{
	public King(boolean white, int x, int y)
    {
    	super(white, x, y);
    	this.movementRange = 3;
    	this.attackRange = 1;
    	this.minRollToCap = new int[] {4, 4, 4, 4, 5, 1};
    	this.charRep = (white ? 'K' : 'k');
    }
	
	public King(boolean white)
    {
    	super(white);
    	this.charRep = (white ? 'K' : 'k');
    }
}