public class GameBoard
{
    Piece tiles[][] = new Piece[8][8];
    int actionsTaken = 0;
    boolean whiteMoving;
    
    final int ACTIONS_PER_TURN = 3;
    final Piece INITIAL_SETUP[][] =
    		//i wrote this out by hand. trust me i tried figuring out how to
    		//automate this. help
    {
        {
            new Rook(false, 0, 0), new Knight(false, 0, 1), new Bishop(false, 0, 2),
            new Queen(false, 0, 3), new King(false, 0, 4), new Bishop(false, 0, 5),
            new Knight(false, 0, 6), new Rook(false, 0, 7)
        },
        {
            new Pawn(false, 1, 0), new Pawn(false, 1, 1), new Pawn(false, 1, 2), 
            new Pawn(false, 1, 3), new Pawn(false, 1, 4), new Pawn(false, 1, 5), 
            new Pawn(false, 1, 6), new Pawn(false, 1, 7)
        },
        {
            new NullPiece(2, 0), new NullPiece(2, 1), new NullPiece(2, 2), 
            new NullPiece(2, 3), new NullPiece(2, 4), new NullPiece(2, 5), 
            new NullPiece(2, 6), new NullPiece(2, 7)
        },
        {
        	new NullPiece(3, 0), new NullPiece(3, 1), new NullPiece(3, 2), 
            new NullPiece(3, 3), new NullPiece(3, 4), new NullPiece(3, 5), 
            new NullPiece(3, 6), new NullPiece(3, 7)
        },
        {
        	new NullPiece(4, 0), new NullPiece(4, 1), new NullPiece(4, 2), 
            new NullPiece(4, 3), new NullPiece(4, 4), new NullPiece(4, 5), 
            new NullPiece(4, 6), new NullPiece(4, 7)
        },
        {
        	new NullPiece(5, 0), new NullPiece(5, 1), new NullPiece(5, 2), 
            new NullPiece(5, 3), new NullPiece(5, 4), new NullPiece(5, 5), 
            new NullPiece(5, 6), new NullPiece(5, 7)
        },
        {
            new Pawn(true, 6, 0), new Pawn(true, 6, 1), new Pawn(true, 6, 2), 
            new Pawn(true, 6, 3), new Pawn(true, 6, 4), new Pawn(true, 6, 5), 
            new Pawn(true, 6, 6), new Pawn(true, 6, 7)
        },
        {
            new Rook(true, 7, 0), new Knight(true, 7, 1), new Bishop(true, 7, 2),
            new Queen(true, 7, 3), new King(true, 7, 4), new Bishop(true, 7, 5),
            new Knight(true, 7, 6), new Rook(true, 7, 7)
        }
    };
    
    public GameBoard()
    {
        this.whiteMoving = true;
        for (int row = 0; row < tiles.length; row++)
        {
        	for (int column = 0; column < tiles[row].length; column++)
        	{
        		tiles[row][column] = INITIAL_SETUP[row][column];
        	}
        }
    }
    
    public void movePiece(int[] before, int[] after)
            //Takes in a size-two array in the form of {row, column}
    {
        try
        {
            System.out.println("Moving tile (" + before[0] + ", " + before[1] +
                    ") to (" + after[0] + ", " + after[1] + ")");
            Piece movingPiece = tiles[before[0]][before[1]];
            movingPiece.row = after[0];
            movingPiece.column = after[1];
            tiles[after[0]][after[1]] = movingPiece;
            tiles[before[0]][before[1]] = new NullPiece(before[0], before[1]);
            System.out.println("Chessboard should now be:");
            this.toSysOut();
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            System.out.println("Exception caught in GameBoard.movePiece: " + 
                    "Expected array arguments of sizes 2 and 2, got " + 
                    before.length + " and " + after.length + ", respectively");
        }
        
    }
    
    public void toSysOut()
    {
        String output = "It's currently " + (whiteMoving ? "white's" : "black's")
                + " turn.\n" + (whiteMoving ? "White" : "Black") + " has used "
                + actionsTaken + " of " + ACTIONS_PER_TURN + " actions this turn.\n";
        
        for (int row = 0; row < tiles.length; row++)
        {
            for (int column = 0; column < tiles[row].length; column++)
            {
                Piece thisPiece = tiles[row][column];
                output = output.concat(Character.toString(thisPiece.charRep));
            }
            
            output = output.concat("\n");
        }
        
        System.out.println(output);
    }
}