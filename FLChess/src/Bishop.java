public class Bishop extends Piece
{
	final int MOVEMENT_RANGE = 1;
	final int ATTACK_RANGE = 1;
	final int[] MIN_ROLL_TO_CAP = {5, 5, 5, 4, 5, 3};
	
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