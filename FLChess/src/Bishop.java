public class Bishop extends Piece
{
	public Bishop(boolean white, int x, int y)
    {
    	super(white, x, y);
    	this.movementRange = 1;
    	this.attackRange = 1;
    	this.minRollToCap = new int[] {5, 5, 5, 4, 5, 3};
    	this.charRep = (white ? 'B' : 'b');
    }
	
	public Bishop(boolean white)
    {
    	super(white);
    	this.movementRange = 1;
    	this.attackRange = 1;
    	this.minRollToCap = new int[] {5, 5, 5, 4, 5, 3};
    	this.charRep = (white ? 'B' : 'b');
    }
}