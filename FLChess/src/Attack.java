import javax.swing.ImageIcon;

public class Attack
{

    private boolean attackSuccess = false;
    private int dieNum = DieRoll.roll();

    public boolean tryAttack(Piece attacker, Piece victim, boolean hasMoved)
    {

        char aChar = Character.toLowerCase(attacker.charRep);
        char vChar = Character.toLowerCase(victim.charRep);

        //int dieNum = DieRoll.roll();

        System.out.println("You roll: " + dieNum);
        if (hasMoved)
        {
            dieNum = dieNum - 1;
            System.out.println("You suffer a movement penalty (-1): " + dieNum);
        }

        // pawn attacking
        if (aChar == 'p')
        { // if pawn
            if (vChar == 'p')
            { // attacking pawn
                if (dieNum >= 4)
                    attackSuccess = true;
            }
            else if (vChar == 'b')
            { // attacking bishop
                if (dieNum >= 5) {
                    attackSuccess = true;
                }
            }
            else
            {
                if (dieNum == 6)
                    attackSuccess = true;
            }
        }

        // rook attacking
        else if (aChar == 'r')
        { // if rook
            if (vChar == 'k' || vChar == 'q')
            { // attacking king/queen
                if (dieNum >= 4)
                    attackSuccess = true;
            }
            else if (vChar == 'b' || vChar == 'n' || vChar == 'p')
            { // attacking bishop/knight/pawn
                if (dieNum >= 5)
                    attackSuccess = true;
            }
            else if(vChar == 'b') {
            	if(dieNum >= 5) {
            		attackSuccess = true;
            	}
            }
            else
            {
                if (dieNum == 6)
                    attackSuccess = true;
            }
        }

        // bishop attacking
        else if (aChar == 'b')
        { // if bishop
            if (vChar == 'p')
            { // attacking pawn
                if (dieNum >= 3)
                    attackSuccess = true;
            }
            else if (vChar == 'b')
            { // attacking bishop
                if (dieNum >= 4)
                    attackSuccess = true;
            }
            else
            {
                if (dieNum >= 5)
                    attackSuccess = true;
            }
        }

        // knight attacking
        else if (aChar == 'n')
        { // if knight
            if (vChar == 'p')
            { // attacking pawn
                if (dieNum >= 2)
                    attackSuccess = true;
            }
           /* else if (vChar == 'n' || vChar == 'b')
            { // attacking knight/bishop
                if (dieNum >= 4)
                    attackSuccess = true;
            }
            */
            else if (vChar == 'n')
            { // attacking knight/bishop
                if (dieNum >= 4)
                    attackSuccess = true;
            }
            else if (vChar == 'b')
            { // attacking knight/bishop
                if (dieNum >= 4) {
                    attackSuccess = true;
                }
                	
            }
            else if (vChar == 'r')
            { // attacking rook
                if (dieNum >= 5)
                    attackSuccess = true;
            }
            else
            {
                if (dieNum == 6)
                    attackSuccess = true;
            }
        }

        // queen attacking
        else if (aChar == 'q')
        { // if queen
            if (vChar == 'p')
            { // attacking pawn
                if (dieNum >= 2)
                    attackSuccess = true;
            }
            else if (vChar == 'k' || vChar == 'q' || vChar == 'n')
            { // attacking king/queen/knight/bishop
                if (dieNum >= 4)
                    attackSuccess = true;
            }
            else if(vChar == 'b') {
            	if(dieNum >= 4) {
            		attackSuccess = true;
            	}
            }
            else
            {
                if (dieNum >= 5)
                    attackSuccess = true;
            }
        }

        // king attacking
        else if (aChar == 'k')
        { // if king
            if (vChar == 'p') // attacking pawn
                attackSuccess = true;
            else if (vChar == 'k' || vChar == 'q' || vChar == 'n')
            { // attacking king/queen/knight/bishop
                if (dieNum >= 4)
                    attackSuccess = true;
            }
            else if (vChar == 'b') {
            	if(dieNum >= 4) {
            		attackSuccess = true;
            	}
            }
            else
            {
                if (dieNum >= 5)
                    attackSuccess = true;
            }
        }

        if (!attackSuccess)
        {
            System.out.println("Didn't roll high enough!");
        }
        return attackSuccess;
    }
    
    public int getDieNum() {
        return dieNum;
    }
    
    public void killPiece(Piece victim)
    {
        victim = null;
    }
    
    //CHANGES DIE DISPLAY IN GAMEFRAME
    public void setDieDisplay()
    {
    	if (dieNum == 1)
    	{
    		GameFrame.dieDisplay.setIcon(new ImageIcon(GameFrame.class.getResource("Images/die1.png")));
    	}
    	else if (dieNum == 2)
    	{
    		GameFrame.dieDisplay.setIcon(new ImageIcon(GameFrame.class.getResource("Images/die2.png")));
    	}
    	else if (dieNum == 3)
    	{
    		GameFrame.dieDisplay.setIcon(new ImageIcon(GameFrame.class.getResource("Images/die3.png")));
    	}
    	else if (dieNum == 4)
    	{
    		GameFrame.dieDisplay.setIcon(new ImageIcon(GameFrame.class.getResource("Images/die4.png")));
    	}
    	else if (dieNum == 5)
    	{
    		GameFrame.dieDisplay.setIcon(new ImageIcon(GameFrame.class.getResource("Images/die5.png")));
    	}
    	else if (dieNum == 6)
    	{
    		GameFrame.dieDisplay.setIcon(new ImageIcon(GameFrame.class.getResource("Images/die6.png")));
    	}
    }
}
