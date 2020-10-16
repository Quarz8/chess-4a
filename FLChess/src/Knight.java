public class Knight extends Piece
{
    public Knight()
    {
        this.white = true;
        this.charRep = 'N';
    }
    
    public Knight(boolean white)
    {
        this.white = white;
        this.charRep = (white ? 'N' : 'n');
    }
}