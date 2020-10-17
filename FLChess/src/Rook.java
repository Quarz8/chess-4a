public class Rook extends Piece
{
	public Rook(boolean white, int x, int y)
    {
    	super(white, x, y);
    	this.movementRange = 1;
    	this.attackRange = 3;
    	this.minRollToCap = new int[] {4, 4, 5, 5, 6, 5};
    	this.charRep = (white ? 'R' : 'r');
    }
	
	public Rook(boolean white)
    {
    	super(white);
    	this.movementRange = 1;
    	this.attackRange = 3;
    	this.minRollToCap = new int[] {4, 4, 5, 5, 6, 5};
    	this.charRep = (white ? 'R' : 'r');
    }
}
