import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public abstract class Piece
{
    int movementRange;
    int attackRange;
    int[] minRollToCap;
    // in reading order for each row on the chart in the PDF. the minimum
    // die roll for this piece to capture a king, queen, knight, bishop,
    // rook, and pawn, respectively. should be initialized by each
    // individual piece, so this exact array shouldn't manifest in working code.
    int row;
    int column;
    boolean white;
    char charRep;
    boolean hasMoved;
    int[][] directions =
    {
            { 0, 1 }, // S
            { 1, 1 }, // SE
            { 1, 0 }, // E
            { 1, -1 }, // NE
            { 0, -1 }, // N
            { -1, -1 }, // NW
            { -1, 0 }, // W
            { -1, 1 } // SW
    };

    private Corp corp;

    public Piece(boolean white, int row, int column, Corp newCorp)
    {
        this.white = white;
        this.row = row;
        this.column = column;
        this.setCorp(newCorp);
    }

    public Piece(boolean white, int row, int column)
    {
        this.white = white;
        this.row = row;
        this.column = column;
    }

    public Piece()
    {

    }

    public Collection<int[]> searchValidActions(Piece[][] tiles, int[][] directions, boolean move)
    /*
     * basically a breadth-first searching algorithm.
     * 
     * Piece[][] tiles: the game board, just without the additional game
     * information. int[][] directions: a set of all allowed directions the piece
     * can search. boolean move: whether the piece is looking for potential moves or
     * attacks. (this changes the range of the search and the criteria for adding
     * new actions to validActions.)
     * 
     * THE COORDINATES INCLUDED IN THE COLLECTION THIS METHOD RETURNS ARE
     * NON-UNIQUE. that is to say, some coordinates will appear multiple times. if
     * you're handling the return object and only want to search for the FIRST
     * occurrence of a coordinate, DO NOT use contains() or indexOf()-- this will
     * compare references, not values. iterate through it within a for loop, then
     * break out at the first occurrence. see GameFrame.handleSelection() for an
     * example.
     */
    {
        int range = (move ? this.movementRange : this.attackRange);
        Collection<int[]> validActions = new ArrayList<>();
        Collection<int[]> exploredPositions = new ArrayList<>(); // needed for non-adjacent attacks (thanks, rooks)
        Queue<int[]> unexploredPositions = new LinkedList<>();

        int[] seed =
        { this.row, this.column };

        unexploredPositions.add(seed);

        for (int actions = range; actions > 0; actions--)
        {
            while (!unexploredPositions.isEmpty())
            {
                int[] startingPos = unexploredPositions.poll();

                for (int direction = 0; direction < directions.length; direction++)
                {
                    int[] exploring =
                    { startingPos[0], startingPos[1] };
                    exploring[0] = exploring[0] + directions[direction][0];
                    exploring[1] = exploring[1] + directions[direction][1];

                    try
                    {
                        if (move && tiles[exploring[0]][exploring[1]].charRep == '-')
                        // potential move
                        {
                            validActions.add(exploring);
                        }
                        else if (!move && tiles[exploring[0]][exploring[1]].charRep != '-'
                                && this.white != tiles[exploring[0]][exploring[1]].white)
                        // potential attack
                        {
                            validActions.add(exploring);
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException e)
                    {
                    }

                    exploredPositions.add(exploring);
                }
            }

            unexploredPositions.addAll(move ? validActions : exploredPositions);
        }

        return validActions;
    }

    void toSysOut()
    {
        System.out.println(this.toString() + " with coordinates (" + this.row + ", " + this.column + ")");
    }

    // getter method for Corp
    public Corp getCorp()
    {
        return this.corp;
    }

    // setter method for the Corp and adds this unit to the newCorp
    public void setCorp(Corp newCorp)
    {
        this.corp = newCorp;
        newCorp.addUnit(this);
    }

    // reassigns piece's corp to kingCorp
    public void reassignAll(Corp kingCorp)
    {
        for (int i = 0; i < this.corp.units.size(); i++) // for every piece in this piece's corp...
        {
            this.corp.units.get(i).setCorp(kingCorp); // set piece's corp to kingCorp
            kingCorp.addUnit(this.corp.units.get(i)); // and add to kingCorp
        }
    }
}
