public class GameBoard
{
    Piece tiles[][] = new Piece[8][8];
    int actionsTaken = 0;
    boolean whiteMoving;
    
    final int ACTIONS_PER_TURN = 3;
    final Piece INITIAL_SETUP[][] =
    {
        {
            new Rook(false), new Knight(false), new Bishop(false),
            new Queen(false), new King(false), new Bishop(false),
            new Knight(false), new Rook(false)
        },
        {
            new Pawn(false), new Pawn(false), new Pawn(false), 
            new Pawn(false), new Pawn(false), new Pawn(false), 
            new Pawn(false), new Pawn(false)
        },
        {
            new NullPiece(), new NullPiece(), new NullPiece(), 
            new NullPiece(), new NullPiece(), new NullPiece(), 
            new NullPiece(), new NullPiece()
        },
        {
            new NullPiece(), new NullPiece(), new NullPiece(), 
            new NullPiece(), new NullPiece(), new NullPiece(), 
            new NullPiece(), new NullPiece()
        },
        {
            new NullPiece(), new NullPiece(), new NullPiece(), 
            new NullPiece(), new NullPiece(), new NullPiece(), 
            new NullPiece(), new NullPiece()
        },
        {
            new NullPiece(), new NullPiece(), new NullPiece(), 
            new NullPiece(), new NullPiece(), new NullPiece(), 
            new NullPiece(), new NullPiece()
        },
        {
            new Pawn(true), new Pawn(true), new Pawn(true), 
            new Pawn(true), new Pawn(true), new Pawn(true), 
            new Pawn(true), new Pawn(true)
        },
        {
            new Rook(true), new Knight(true), new Bishop(true),
            new Queen(true), new King(true), new Bishop(true),
            new Knight(true), new Rook(true)
        }
    };
    
    public GameBoard()
    {
        this.whiteMoving = true;
        for (int i = 0; i < INITIAL_SETUP.length; i++) 
        {
            System.arraycopy(INITIAL_SETUP[i], 0, this.tiles[i], 0, INITIAL_SETUP[i].length);
        }
    }
    
    public void movePiece(int[] before, int[] after)
            //Takes in a size-two array in the form of {x, y}
    {
        try
        {
            System.out.println("Moving tile (" + before[0] + ", " + before[1] +
                    ") to (" + after[0] + ", " + after[1] + ")");
            Piece movingPiece = tiles[before[1]][before[0]];
            tiles[after[1]][after[0]] = movingPiece;
            tiles[before[1]][before[0]] = new NullPiece();
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
                + " turn.\n";
        
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