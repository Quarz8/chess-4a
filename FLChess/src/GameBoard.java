import javax.swing.*;

public class GameBoard
{
    Piece tiles[][] = new Piece[8][8];
    int actionsTaken = 0; // actions taken on turn so far
    boolean whiteMoving;

    
    int maxActionsWhite = 3; // maximum number of actions white can perform
    int maxActionsBlack = 3; // maximum number of actions black can perform
    
    Corp corpBW1 = new Corp(); // white bishop corp 1
    Corp corpBW2 = new Corp(); // white bishop corp 2
    Corp corpKW = new Corp(); // white king corp
    
    Corp corpBB1 = new Corp(); // black bishop corp 1
    Corp corpBB2 = new Corp(); // black bishop corp 2
    Corp corpKB = new Corp(); // black king corp
    
    
    final Piece INITIAL_SETUP[][] =
    		//i wrote this out by hand. trust me i tried figuring out how to
    		//automate this. help
    {
        {
            new Rook(false, 0, 0, corpKB), new Knight(false, 0, 1, corpBB1), new Bishop(false, 0, 2, corpBB1),
            new Queen(false, 0, 3, corpKB), new King(false, 0, 4, corpKB), new Bishop(false, 0, 5, corpBB2),
            new Knight(false, 0, 6, corpBB2), new Rook(false, 0, 7, corpKB)
        },
        {
            new Pawn(false, 1, 0, corpBB1), new Pawn(false, 1, 1, corpBB1), new Pawn(false, 1, 2, corpBB1), 
            new Pawn(false, 1, 3, corpKB), new Pawn(false, 1, 4, corpKB), new Pawn(false, 1, 5, corpBB2), 
            new Pawn(false, 1, 6, corpBB2), new Pawn(false, 1, 7, corpBB2)
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
            new Pawn(true, 6, 0, corpBW1), new Pawn(true, 6, 1, corpBW1), new Pawn(true, 6, 2, corpBW1), 
            new Pawn(true, 6, 3, corpKW), new Pawn(true, 6, 4, corpKW), new Pawn(true, 6, 5, corpBW2), 
            new Pawn(true, 6, 6, corpBW2), new Pawn(true, 6, 7, corpBW2)
        },
        {
            new Rook(true, 7, 0, corpKW), new Knight(true, 7, 1, corpBW1), new Bishop(true, 7, 2, corpBW1),
            new Queen(true, 7, 3, corpKW), new King(true, 7, 4, corpKW), new Bishop(true, 7, 5, corpBW2),
            new Knight(true, 7, 6, corpBW2), new Rook(true, 7, 7, corpKW)
        }
    };
    
    public GameBoard() {
        this.whiteMoving = true;
        for (int row = 0; row < tiles.length; row++) {
            for (int column = 0; column < tiles[row].length; column++) {
                tiles[row][column] = INITIAL_SETUP[row][column];
            }
        }
    }
    
    public GameBoard(Piece[][] tempTiles, boolean tempWhiteMoving) {
        this.whiteMoving = tempWhiteMoving;
        for (int row = 0; row < tiles.length; row++) {
            for (int column = 0; column < tiles[row].length; column++) {
                tiles[row][column] = tempTiles[row][column];
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
                + actionsTaken + " of " + (whiteMoving ? maxActionsWhite : maxActionsBlack) + " actions this turn.\n";
        
        System.out.println("Chessboard should now be:");
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