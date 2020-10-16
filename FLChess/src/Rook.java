public class Rook extends Piece
{
	final int MOVEMENT_RANGE = 1;
	final int ATTACK_RANGE = 3;
	final int[] MIN_ROLL_TO_CAP = {4, 4, 5, 5, 6, 5};
	
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
