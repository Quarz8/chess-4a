public abstract class Piece
{
	final int MOVEMENT_RANGE = 0;
	final int ATTACK_RANGE = 0;
	final int[] MIN_ROLL_TO_CAPTURE = {7, 7, 7, 7, 7, 7};
		//in reading order for each row on the chart in the PDF. the minimum
		//die roll for this piece to capture a king, queen, knight, bishop,
		//rook, and pawn, respectively.
    boolean white;
    char charRep;
}