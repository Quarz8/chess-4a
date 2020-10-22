import java.awt.BorderLayout;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Scrollable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.lang.*;

public class GameFrame extends JFrame implements ActionListener
{
    CardLayout cardLayout;
    FlowLayout flowLayout;
    JPanel mainPanel;
    JPanel controlPanel;
    MenuPanel menu;
    GamePanel game;
    JButton goGame;
    JButton howTo;
    JButton moveButton;
    JButton attackButton;
    ImageIcon instruct = new ImageIcon("Images/medChess-1.jpg");

    String longMessage;

    // GAME FRAME FOR OVERALL SET UP (UNIVERSAL
    // BUTTONS)//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public GameFrame()
    {
        // INITIALIZATION OF CARD LAYOUT STYLE FOR THE MAIN PANEL AND FLOW LAYOUT STYLE
        // FOR CONTROL PANEL -- THE MENU/GAME PANELS CONTAINED IN MAIN PANEL
        cardLayout = new CardLayout();
        flowLayout = new FlowLayout(SwingConstants.LEFT);

        menu = new MenuPanel();
        game = new GamePanel();

        mainPanel = new JPanel(cardLayout);
        mainPanel.add(menu, "menu");
        mainPanel.add(game, "game");

        // MOVE AND ATTACK BUTTONS (HIDDEN UNTIL GAME PANEL IS SHOWN) -- THESE ARE ADDED
        // TO THE CONTROL PANEL
        moveButton = new JButton("MOVE");
        moveButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
        moveButton.setPreferredSize(new Dimension(493, 100));
        moveButton.addActionListener(this);

        attackButton = new JButton("ATTACK");
        attackButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
        attackButton.setPreferredSize(new Dimension(493, 100));
        ;
        attackButton.addActionListener(this);

        // HOW TO PLAY AND PLAY BUTTONS
        goGame = new JButton();
        goGame.setIcon(new ImageIcon(GameFrame.class.getResource("Images/Play Button.png")));
        goGame.addActionListener(this);

        howTo = new JButton();
        howTo.setIcon(new ImageIcon(GameFrame.class.getResource("Images/How To Play.png")));
        // howTo.setFont(new Font("Tahoma", Font.PLAIN, 30));
        howTo.addActionListener(this);

        // CONTROL PANEL (IS SHOWN WHEN GAME IS PLAYED, HIDDEN TO START)
        controlPanel = new JPanel(flowLayout);
        controlPanel.add(moveButton);
        controlPanel.add(attackButton);

        // PUSH COMPONENTS TO GAMEFRAME (JFRAME)
        add(mainPanel);
        add(goGame, BorderLayout.EAST);
        add(howTo, BorderLayout.WEST);
        add(controlPanel, BorderLayout.SOUTH);
        controlPanel.setVisible(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocation(0, 0);
        setSize(1240, 900);
        setResizable(false);
        setVisible(true);
    }

    // BUTTON INTERACTIONS
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == goGame)
        {
            gameOn();
            goGame.setVisible(false);
        }
        else if (e.getSource() == howTo)
        {
            longMessage = "CAPTURING PIECES "
                    + "\nFuzzy-Logic Chess (F-L Chess) introduces uncertainty to the act of capturing a piece so\r\n"
                    + "that players must use reasoning with uncertainty (fuzzy logic: probability) in planning their strategies. The\r\n"
                    + "attacking player rolls a die to\r" + " determine if a capture is successful.\r"
                    + " The die roll needed to capture a piece\r\n" + "depends on the combination of the\r"
                    + " attacking piece and the defending\r" + " piece, as shown in the Capture Table.\r\n"
                    + "    - When an attack is successful, the attacking piece moves into the square of the captured piece.\r\n"
                    + "    - When an attack is unsuccessful, the attacking piece remains in the square it attacked from.\r\n"
                    + "    - All pieces may attempt to capture an opposing piece in any direction (left, right, up, down, diagonal).\n\n"
                    +

                    "MOUNTED KNIGHTS AND ROYALTY\r\n" + "In F-L Medieval Chess, the King,"
                    + " Queen, and Knights may each move in any direction, \nand do not have to move in a straight line. Not counting"
                    + " the starting square, but counting the final square, \nthe King and Queen may move three squares, and the"
                    + " Knights five squares. They may not jump over \nor pass through an occupied square. The King and Queen"
                    + " represent the elite royalty, more heavily \narmored than knights, but slower.\r\n"
                    + "Knights (only) may combine movement with a capture in the same action, attacking any adjacent enemy\r\n"
                    + "piece after their movement, but subtract one from the die roll.\r\n\n"
                    + "THE INFANTRY (Pawns and Bishop)\r\n"
                    + "Pawns represent simple infantry with minimal training and arms, while Bishops represent pikemen with long\r\n"
                    + "pikes or halberds with formal training and experience.\r\n"
                    + "- Pawns and Bishops may move one square and attack in a forward direction only, moving or attacking either\r\n"
                    + "  directly ahead or to either forward diagonal toward the opposing player.\r\n"
                    + "- Pawns are never promoted after reaching the eighth rank (the opponent's home row).\r\n"
                    + "- Pawns may not move two squares forward when they have not yet moved in the game\r\n\n"
                    + "ARCHERS (Rook/Castle)\r\n"
                    + "The Rook represents a company of archers, who may move a single square in any direction. Archers may\r\n"
                    + "attack any piece by shooting over up to two squares (not counting the square with the Rook or the square\r\n"
                    + "with the enemy piece). ";

            JOptionPane.showMessageDialog(null, longMessage, "How To Play", JOptionPane.INFORMATION_MESSAGE, instruct);
        }
        else if (e.getSource() == moveButton)
        {
            // SHOWS CHANGE IN STATE TO MOVE GAMEPLAY
            attackButton.setBackground(null);
            attackButton.setForeground(Color.black);
            moveButton.setBackground(new Color(136, 0, 27));
            moveButton.setForeground(Color.white);

            // MOVEMENT STATE IMPLEMENTATION COULD POTENTIALLY GO HERE
        }
        else if (e.getSource() == attackButton)
        {
            // SHOWS CHANGE IN STATE TO ATTACK GAMEPLAY
            moveButton.setBackground(null);
            moveButton.setForeground(Color.black);
            attackButton.setBackground(new Color(136, 0, 27));
            attackButton.setForeground(Color.white);

            // ATTACKING STATE IMPLEMENTATION COULD POTENTIALLY GO HERE
        }
    }

    // SWITCH TO ACTUAL GAME PANEL
    public void gameOn()
    {
        cardLayout.show(mainPanel, "game");
        controlPanel.setVisible(true);
        game.updateBoard(new GameBoard());
        game.gBoard.toSysOut();
    }

    // FINAL SET UP (MAIN METHOD)
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                GameFrame gameFrame = new GameFrame();
            }
        });
    }
}

// MAIN MENU
// PANEL/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
class MenuPanel extends JPanel
{
    public MenuPanel()
    {
        setBackground(new Color(255, 255, 255));
        setLayout(new GridLayout(2, 0));

        // LOGO
        JLabel logoImage = new JLabel();
        logoImage.setIcon(new ImageIcon(GameFrame.class.getResource("Images/Title.png")));
        // logoImage.setFont(new Font("Tahoma", Font.PLAIN, 44));
        logoImage.setVerticalAlignment(SwingConstants.CENTER);
        logoImage.setHorizontalAlignment(SwingConstants.CENTER);
        add(logoImage);

        // TEAM NAME
        // JLabel teamName = new JLabel("Team 4A");
        // teamName.setFont(new Font("Tahoma", Font.PLAIN, 44));
        // teamName.setVerticalAlignment(SwingConstants.CENTER);
        // teamName.setHorizontalAlignment(SwingConstants.CENTER);
        // add(teamName);
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(300, 300);
    }
}

// ACTUAL GAMEPLAY
// PANEL////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
class GamePanel extends JPanel
{
    GameBoard gBoard = new GameBoard();
    TilePanel[][] pnlChessCells = new TilePanel[gBoard.tiles.length][gBoard.tiles[0].length];
    TilePanel selectedTile;
    TilePanel selectedTile2;
    Collection<int[]> highlightedMoveTiles = new ArrayList<>();
    Collection<int[]> highlightedAttackTiles = new ArrayList<>();
    Collection<int[]> highlightedCorpTiles = new ArrayList<>();
    final Color DARK_COLOR = new Color(136, 0, 27);
    final Color LIGHT_COLOR = new Color(255, 206, 158);
    final Color SELECT_COLOR = new Color(78, 245, 98);
    final Color MOVE_COLOR = new Color(122, 77, 201);
    final Color ATTACK_COLOR = new Color(184, 35, 9);
    final Color CORP_COLOR = new Color(186, 189, 175);
    final TilePanel NULL_TILE = new TilePanel(this, new BorderLayout());

    public GamePanel()
    {
        setLayout(new GridLayout(pnlChessCells.length, pnlChessCells[0].length));
        NULL_TILE.pieceAt = new NullPiece();
        this.selectedTile = NULL_TILE;
        this.selectedTile2 = NULL_TILE;

        // CREATE BOARD IN GAME PANEL CONSTRUCTOR
        for (int row = 0; row < pnlChessCells.length; row++)
        {
            for (int column = 0; column < pnlChessCells[0].length; column++)
            {
                pnlChessCells[row][column] = new TilePanel(this, new BorderLayout());
                this.add(pnlChessCells[row][column]);
            }
        }
    }

    public void updateBoard(GameBoard gBoard)
    {
        // PLACE PIECES ON BOARD IN GAME PANEL CONSTRUCTOR
        for (int row = 0; row < pnlChessCells.length; row++)
        {
            for (int column = 0; column < pnlChessCells[0].length; column++)
            {
                pnlChessCells[row][column].removeAll();
                pnlChessCells[row][column].add(this.getPieceObject(gBoard.tiles[row][column].charRep),
                        BorderLayout.CENTER);
                pnlChessCells[row][column].pieceAt = gBoard.tiles[row][column];

                if ((column + row) % 2 == 0)
                    pnlChessCells[row][column].setBackground(LIGHT_COLOR);
                else
                    pnlChessCells[row][column].setBackground(DARK_COLOR);

                pnlChessCells[row][column].validate();
                pnlChessCells[row][column].repaint();
            }
        }

        // highlighting tiles here
        if (selectedTile.pieceAt.charRep != new NullPiece().charRep)
            pnlChessCells[selectedTile.pieceAt.row][selectedTile.pieceAt.column].setBackground(SELECT_COLOR);
        
        for (Iterator<int[]> iterator = highlightedMoveTiles.iterator(); iterator.hasNext();)
        {
            int[] highlightPos = iterator.next();
            pnlChessCells[highlightPos[0]][highlightPos[1]].setBackground(MOVE_COLOR);
        }
        for (Iterator<int[]> iterator = highlightedAttackTiles.iterator(); iterator.hasNext();)
        {
            int[] highlightPos = iterator.next();
            pnlChessCells[highlightPos[0]][highlightPos[1]].setBackground(ATTACK_COLOR);
        }
        for (Iterator<int[]> iterator = highlightedCorpTiles.iterator(); iterator.hasNext();)
        {
            int[] highlightPos = iterator.next();
            pnlChessCells[highlightPos[0]][highlightPos[1]].setBackground(CORP_COLOR);
        }
    }

    public void handleSelection(TilePanel newTile)
    {
        // new tile information
        int[] newLoc = newTile.getBoardLoc();
//        gBoard.tiles[newLoc[0]][newLoc[1]].toSysOut();

        if (!gBoard.gameOver && selectedTile.pieceAt.charRep == new NullPiece().charRep)
        // HANDLING FIRST SELECTION
        {
            // if empty tile/null piece, do nothing and break out
            if (gBoard.tiles[newLoc[0]][newLoc[1]].charRep == '-')
            {
                System.out.println("not a piece. must select piece first");
                return;
            }

            System.out
                    .println("handleSelection called, no previously selected" + " tile found so this is selectedTile");

            /*
             * if the selected tile color does not match the color of the player's turn,
             * return an error message
             */
            if (newTile.pieceAt.white != gBoard.whiteMoving)
            {
                System.out.println("Not your turn");
                return;
            }

            if (!newTile.pieceAt.getCorp().getHasActed())// if piece's corp has not already acted...
            {
                // save selected tile
                selectedTile = newTile;
                highlightedMoveTiles = newTile.pieceAt.searchValidActions(gBoard.tiles, newTile.pieceAt.directions,
                        true);
                highlightedAttackTiles = newTile.pieceAt.searchValidActions(gBoard.tiles, newTile.pieceAt.directions,
                        false);
            }
            else
            {
                System.out.println("This corp has already acted");
            }

        }
        else if (!gBoard.gameOver && selectedTile2.pieceAt.charRep == new NullPiece().charRep)
        // HANDLING SECOND SELECTION
        {
            // info of previously selected tile
            int[] prevLoc = selectedTile.getBoardLoc();

            for (Iterator<int[]> iterator = highlightedMoveTiles.iterator(); iterator.hasNext();)
            {
                if (Arrays.equals(newLoc, iterator.next())) // selected a highlighted move tile
                {
                    selectedTile.pieceAt.getCorp().setHasActed(true); // mark that that corp has now acted
                    gBoard.actionsTaken++; // increment actionsTaken for this turn
                    gBoard.movePiece(prevLoc, newLoc); // move the piece to its new location
                    if (gBoard.actionsTaken >= gBoard.maxActionsWhite) // if max action limit is reach...
                    {
                        System.out.println("END OF TURN");
                        gBoard.whiteMoving = gBoard.whiteMoving ? false : true; // switches player turn after move is
                                                                                // made
                        gBoard.actionsTaken = 0; // reset actionsTaken
                        // reset all corp's hasActed to false
                        gBoard.corpBB1.setHasActed(false);
                        gBoard.corpBB2.setHasActed(false);
                        gBoard.corpKB.setHasActed(false);
                        gBoard.corpBW1.setHasActed(false);
                        gBoard.corpBW2.setHasActed(false);
                        gBoard.corpKW.setHasActed(false);
                        // TODO reset actions taken to 0 and switch whose turn it is
                    }

                    break;
                }
            }
            for (Iterator<int[]> iterator = highlightedAttackTiles.iterator(); iterator.hasNext();)
            {
                if (Arrays.equals(newLoc, iterator.next())) // selected a highlighted attack tile
                {
                    selectedTile2 = newTile; // save selected tile
                    selectedTile.pieceAt.getCorp().setHasActed(true); // mark that that corp has now acted
                    gBoard.actionsTaken++; // increment actionsTaken for this turnAttack piece = new Attack();

                    Attack piece = new Attack();

                    if (piece.tryAttack(selectedTile.pieceAt, selectedTile2.pieceAt, selectedTile.pieceAt.hasMoved)) // if attack succeeds...
                    {
                        piece.killPiece(selectedTile2.pieceAt);
                        gBoard.movePiece(prevLoc, newLoc);
                        
                        System.out.println("You rolled a.... " + piece.getDieNum()); // temp text
                        // TODO display roll in GUI, piece.getDieNum();
                    }
                    else // if attack failed
                    {
                        System.out.println("You rolled a.... " + piece.getDieNum()); // temp text
                        // TODO display roll in GUI, piece.getDieNum();
                    }

                    if (gBoard.actionsTaken >= gBoard.maxActionsWhite)
                    {
                        System.out.println("END OF TURN");
                        gBoard.whiteMoving = gBoard.whiteMoving ? false : true; // switches player turn after move is
                                                                                // made
                        gBoard.actionsTaken = 0; // reset actionsTaken
                        // reset all corp's hasActed to false
                        gBoard.corpBB1.setHasActed(false);
                        gBoard.corpBB2.setHasActed(false);
                        gBoard.corpKB.setHasActed(false);
                        gBoard.corpBW1.setHasActed(false);
                        gBoard.corpBW2.setHasActed(false);
                        gBoard.corpKW.setHasActed(false);
                        // TODO if selectedtile.pieceAt = enemyBishop, enemyMaxActions-- and
                        // bishop.reassignAll(kingCorp)
                    }
                    break;
                }
            }

            // reset selections, highlights
            selectedTile = NULL_TILE;
            selectedTile2 = NULL_TILE;
            highlightedMoveTiles.clear();
            highlightedAttackTiles.clear();
        }
        else
        	System.out.println("handleSelection: The game is over, no more actions");

        // testing if game is over, inelegant but whatevs
        boolean whiteKingDead = true;
        boolean blackKingDead = true;
        for (int row = 0; row < gBoard.tiles.length; row++)
        {
        	for (int column = 0; column < gBoard.tiles[row].length; column++)
        	{
        		if (gBoard.tiles[row][column].charRep == 'k')
        			whiteKingDead = false;
        		if (gBoard.tiles[row][column].charRep == 'K')
        			blackKingDead = false;
        	}
        }
        if (whiteKingDead || blackKingDead)
        {
        	System.out.println((whiteKingDead ? "White" : "Black") + " king is dead! Game is now over");
        	gBoard.gameOver = true;
        }
        
        this.updateBoard(gBoard);
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(300, 300);
    }

    // SETS IMAGE ICON TO EACH PIECE
    public JLabel getPieceObject(char pieceRep)
    {
        JLabel lblTemp;

        switch (pieceRep)
        {
        case 'r':
            lblTemp = new JLabel();
            lblTemp.setIcon(new ImageIcon(GameFrame.class.getResource("Images/Rook Piece.png")));
            lblTemp.setHorizontalAlignment(SwingConstants.CENTER);
            lblTemp.setVerticalAlignment(SwingConstants.CENTER);
            break;
        case 'b':
            lblTemp = new JLabel();
            lblTemp.setIcon(new ImageIcon(GameFrame.class.getResource("Images/Bishop Piece.png")));
            lblTemp.setHorizontalAlignment(SwingConstants.CENTER);
            lblTemp.setVerticalAlignment(SwingConstants.CENTER);
            break;
        case 'n':
            lblTemp = new JLabel();
            lblTemp.setIcon(new ImageIcon(GameFrame.class.getResource("Images/knight piece.png")));
            lblTemp.setHorizontalAlignment(SwingConstants.CENTER);
            lblTemp.setVerticalAlignment(SwingConstants.CENTER);
            break;
        case 'q':
            lblTemp = new JLabel();
            lblTemp.setIcon(new ImageIcon(GameFrame.class.getResource("Images/Queen Piece.png")));
            lblTemp.setHorizontalAlignment(SwingConstants.CENTER);
            lblTemp.setVerticalAlignment(SwingConstants.CENTER);
            break;
        case 'k':
            lblTemp = new JLabel();
            lblTemp.setIcon(new ImageIcon(GameFrame.class.getResource("Images/King Piece.png")));
            lblTemp.setHorizontalAlignment(SwingConstants.CENTER);
            lblTemp.setVerticalAlignment(SwingConstants.CENTER);
            break;
        case 'p':
            lblTemp = new JLabel();
            lblTemp.setIcon(new ImageIcon(GameFrame.class.getResource("Images/Pawn Piece.png")));
            lblTemp.setHorizontalAlignment(SwingConstants.CENTER);
            lblTemp.setVerticalAlignment(SwingConstants.CENTER);
            break;
        case 'R':
            lblTemp = new JLabel();
            lblTemp.setIcon(new ImageIcon(GameFrame.class.getResource("Images/Rook Piece White.png")));
            lblTemp.setHorizontalAlignment(SwingConstants.CENTER);
            lblTemp.setVerticalAlignment(SwingConstants.CENTER);
            break;
        case 'B':
            lblTemp = new JLabel();
            lblTemp.setIcon(new ImageIcon(GameFrame.class.getResource("Images/Bishop Piece white.png")));
            lblTemp.setHorizontalAlignment(SwingConstants.CENTER);
            lblTemp.setVerticalAlignment(SwingConstants.CENTER);
            break;
        case 'N':
            lblTemp = new JLabel();
            lblTemp.setIcon(new ImageIcon(GameFrame.class.getResource("Images/knight piece white.png")));
            lblTemp.setHorizontalAlignment(SwingConstants.CENTER);
            lblTemp.setVerticalAlignment(SwingConstants.CENTER);
            break;
        case 'Q':
            lblTemp = new JLabel();
            lblTemp.setIcon(new ImageIcon(GameFrame.class.getResource("Images/Queen Piece White.png")));
            lblTemp.setHorizontalAlignment(SwingConstants.CENTER);
            lblTemp.setVerticalAlignment(SwingConstants.CENTER);
            break;
        case 'K':
            lblTemp = new JLabel();
            lblTemp.setIcon(new ImageIcon(GameFrame.class.getResource("Images/King Piece white.png")));
            lblTemp.setHorizontalAlignment(SwingConstants.CENTER);
            lblTemp.setVerticalAlignment(SwingConstants.CENTER);
            break;
        case 'P':
            lblTemp = new JLabel("white pawn");
            lblTemp.setIcon(new ImageIcon(GameFrame.class.getResource("Images/Pawn Piece white.png")));
            lblTemp.setHorizontalAlignment(SwingConstants.CENTER);
            lblTemp.setVerticalAlignment(SwingConstants.CENTER);
            break;
        default:
            lblTemp = new JLabel();
        }

        return lblTemp;
    }
}

class TilePanel extends JPanel implements MouseListener
{
    GamePanel parent;
    Piece pieceAt;

    public TilePanel(GamePanel parent, LayoutManager layout)
    {
        this.parent = parent;
        addMouseListener(this);
        this.setLayout(layout);
    }

    public int[] getBoardLoc()
    {
        int[] loc =
        { pieceAt.row, pieceAt.column };
        return loc;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        parent.handleSelection(this);
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {

    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }
}
