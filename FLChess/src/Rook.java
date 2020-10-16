public class Rook extends Piece
{
    public Rook()
    {
        this.white = true;
        this.charRep = 'R';
    }
    
    public Rook(boolean white)
    {
        this.white = white;
        this.charRep = (white ? 'R' : 'r');
    }
}
