public class King extends Piece
{
	public King(boolean white, int x, int y)
    {
    	super(white, x, y);
    	this.charRep = (white ? 'K' : 'k');
    }
	
	public King(boolean white)
    {
    	super(white);
    }
}