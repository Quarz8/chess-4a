public class Queen extends Piece
{
    public Queen()
    {
        this.white = true;
        this.charRep = 'Q';
    }
    
    public Queen(boolean white)
    {
        this.white = white;
        this.charRep = (white ? 'Q' : 'q');
    }
}