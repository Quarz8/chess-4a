public class Queen extends Piece
{
	public Queen(boolean white, int x, int y)
    {
    	super(white, x, y);
    	this.movementRange = 3;
    	this.attackRange = 1;
    	this.minRollToCap = new int[] {4, 4, 4, 4, 5, 2};
    	this.charRep = (white ? 'Q' : 'q');
    }
}