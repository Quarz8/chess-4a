public class Pawn extends Piece
{
    boolean hasMoved = false;
    
    public Pawn()
    {
        this.white = true;
        this.charRep = 'P';
    }
    
    public Pawn(boolean white)
    {
        this.white = white;
        this.charRep = (white ? 'P' : 'p');
    }
}