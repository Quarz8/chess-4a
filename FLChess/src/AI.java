import java.util.Random;
import java.util.ArrayList;
import java.util.Collection;

public class AI
{
    int goodBoyPoints; // used to track how well AI is playing
    boolean isWhite;
    ArrayList<Piece> pieces = new ArrayList<Piece>();

    public AI()
    {
        goodBoyPoints = 0;
    }
    
  

    public AI(boolean white)
    {
        goodBoyPoints = 0;
        isWhite = white;
    }

    // Checks every board tile and checks if it is null (-) AND on the AI's team AND
    /* if it's Corp has not yet acted. If piece is a match, checks if it can act
    void scanBoard(Piece[][] board)
    {
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[i].length; j++)
            {
                if (board[i][j].charRep != '-' && isWhite ? Character.isUpperCase(board[i][j].charRep)
                        : Character.isLowerCase(board[i][j].charRep) && !board[i][j].getCorp().getHasActed())
                {
                    System.out.println(board[i][j].charRep);
                    // check for valid attacks
                    Collection<int[]> actions = board[i][j].searchValidActions(board, board[i][j].directions, false);
                    // if attack found
                    if(!actions.isEmpty()) { 
                        //return this piece?
                    }
                }
            }
        }
    }
    */

}
