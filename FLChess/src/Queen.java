public class Queen extends Piece
{
	public Queen(boolean white, int x, int y)
    {
    	super(white, x, y);
    	this.charRep = (white ? 'Q' : 'q');
    }
	
	public Queen(boolean white)
    {
    	super(white);
    }
}