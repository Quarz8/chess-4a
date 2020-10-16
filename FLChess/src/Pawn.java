public class Pawn extends Piece
{
	final int MOVEMENT_RANGE = 1;
	final int ATTACK_RANGE = 1;
	final int[] MIN_ROLL_TO_CAP = {6, 6, 6, 5, 6, 4};
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