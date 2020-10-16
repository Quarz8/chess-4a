public class Bishop extends Piece
{
    public Bishop()
    {
        this.white = true;
        this.charRep = 'B';
    }
    
    public Bishop(boolean white)
    {
        this.white = white;
        this.charRep = (white ? 'B' : 'b');
    }
}