public class Move
{
	GameBoard movingOn;
	Piece movingPiece;
	int[] fromCoord; //[x, y]
	int[] toCoord; //[x, y]
	
	public Move(GameBoard movingOn, int[] fromCoord, int[] toCoord)
	{
		this.movingOn = movingOn;
		this.movingPiece = movingOn.tiles[fromCoord[0]][fromCoord[1]];
		this.fromCoord = fromCoord;
		this.toCoord = toCoord;
	}
}