public class King extends Piece
{
    public King()
    {
        this.white = true;
        this.charRep = 'K';
    }
    
    public King(boolean white)
    {
        this.white = white;
        this.charRep = (white ? 'K' : 'k');
    }
}