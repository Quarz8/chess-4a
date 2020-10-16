public class Knight extends Piece
{
	public Knight(boolean white, int x, int y)
    {
    	super(white, x, y);
    	this.charRep = (white ? 'N' : 'n');
    }
	
	public Knight(boolean white)
    {
    	super(white);
    }
}