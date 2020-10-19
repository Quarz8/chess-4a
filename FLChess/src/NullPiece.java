public class NullPiece extends Piece
{
	public NullPiece(int x, int y)
    {
    	super(true, x, y);
    	this.movementRange = 0;
    	this.attackRange = 0;
    	this.minRollToCap = new int[] {7, 7, 7, 7, 7, 7};
    	this.charRep = '-';
    }
	
	public NullPiece()
	{
    	this.movementRange = 0;
    	this.attackRange = 0;
    	this.minRollToCap = new int[] {7, 7, 7, 7, 7, 7};
    	this.charRep = '-';
	}
}