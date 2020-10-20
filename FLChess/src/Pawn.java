public class Pawn extends Piece
{
	public Pawn(boolean white, int x, int y, Corp corp)
    {
    	super(white, x, y, corp);
    	this.movementRange = 1;
    	this.attackRange = 1;
    	this.minRollToCap = new int[]{6, 6, 6, 5, 6, 4};
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
    	this.charRep = (white ? 'P' : 'p');
    }
}