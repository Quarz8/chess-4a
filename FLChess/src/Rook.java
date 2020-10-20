public class Rook extends Piece
{
	public Rook(boolean white, int x, int y, Corp corp)
    {
    	super(white, x, y, corp);
    	this.movementRange = 1;
    	this.attackRange = 3;
    	this.minRollToCap = new int[] {4, 4, 5, 5, 6, 5};
    	this.charRep = (white ? 'R' : 'r');
    }
}
