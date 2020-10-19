public class Bishop extends Piece
{
	public Bishop(boolean white, int x, int y, Corp corp)
    {
    	super(white, x, y, corp);
    	this.movementRange = 1;
    	this.attackRange = 1;
    	this.minRollToCap = new int[] {5, 5, 5, 4, 5, 3};
    	this.directions = (white ? new int[][]
		{
				{-1, -1}, //NW
				{-1, 0}, //N
				{-1, 1}, //NE
		}: new int[][]
		{
				{1, -1}, //SW
				{1, 0}, //S
				{1, 1}, //SE
		});
    	this.charRep = (white ? 'B' : 'b');
    }
}