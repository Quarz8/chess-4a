import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
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

public class GameFrame extends JFrame implements ActionListener
{
    CardLayout cardLayout;
    JPanel mainPanel;
    MenuPanel menu;
    GamePanel game;
    JButton goGame;
    JButton howTo;
    ImageIcon instruct = new ImageIcon("Images/medChess-1.jpg");

    String longMessage;

    // GAME FRAME FOR OVERALL SET UP (UNIVERSAL
    // BUTTONS)////////////////////////////////////////////////////////////////////////////////////////
    public GameFrame()
    {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        menu = new MenuPanel();
        game = new GamePanel();
        mainPanel.add(menu, "menu");
        mainPanel.add(game, "game");

        goGame = new JButton();
        goGame.setIcon(new ImageIcon(GameFrame.class.getResource("Images/Play Button.png")));
        goGame.addActionListener(this);

        howTo = new JButton();
//        howTo.setIcon(new ImageIcon(GameFrame.class.getResource("Images/How To Play.png")));
        //howTo.setFont(new Font("Tahoma", Font.PLAIN, 30));
        howTo.addActionListener(this);

        add(mainPanel);
        add(goGame, BorderLayout.EAST);
        add(howTo, BorderLayout.WEST);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationByPlatform(true);
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
    }

    // SWITCH TO ACTUAL GAME PANEL
    public void gameOn()
    {
        cardLayout.show(mainPanel, "game");
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
// PANEL//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
class MenuPanel extends JPanel
{
    public MenuPanel()
    {
        setBackground(new Color(255, 255, 255));
        setLayout(new GridLayout(2, 0));

//        // LOGO
//        JLabel logoImage = new JLabel();
//        logoImage.setIcon (new ImageIcon(GameFrame.class.getResource("Images/Title.png")));
//        //logoImage.setFont(new Font("Tahoma", Font.PLAIN, 44));
//        logoImage.setVerticalAlignment(SwingConstants.CENTER);
//        logoImage.setHorizontalAlignment(SwingConstants.CENTER);
//        add(logoImage);

        // TEAM NAME
        //JLabel teamName = new JLabel("Team 4A");
        //teamName.setFont(new Font("Tahoma", Font.PLAIN, 44));
        //teamName.setVerticalAlignment(SwingConstants.CENTER);
        //teamName.setHorizontalAlignment(SwingConstants.CENTER);
        //add(teamName);
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(300, 300);
    }
}

// ACTUAL GAMEPLAY
// PANEL///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
class GamePanel extends JPanel
{
    GameBoard gBoard = new GameBoard();
    TilePanel[][] pnlChessCells = new TilePanel[gBoard.tiles.length][gBoard.tiles[0].length];
    TilePanel selectedTile;
    TilePanel selectedTile2;
    Collection<int[]> highlightedMoveTiles = new ArrayList<>();
    Collection<int[]> highlightedAttackTiles = new ArrayList<>();
    final Color DARK_COLOR = new Color(136, 0, 27);
    final Color LIGHT_COLOR = new Color(255, 206, 158);
    final Color SELECT_COLOR = new Color(78, 245, 98);
    final Color MOVE_COLOR = new Color(122, 77, 201);
    final Color ATTACK_COLOR = new Color(184, 35, 9);
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

                if ((column + row) % 2 == 0)
                    pnlChessCells[row][column].setBackground(LIGHT_COLOR);
                else
                    pnlChessCells[row][column].setBackground(DARK_COLOR);
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
                pnlChessCells[row][column].add(this.getPieceObject(gBoard.tiles[row][column].charRep), BorderLayout.CENTER);
                pnlChessCells[row][column].pieceAt = gBoard.tiles[row][column];
                
                if ((column + row) % 2 == 0)
                    pnlChessCells[row][column].setBackground(LIGHT_COLOR);
                else
                    pnlChessCells[row][column].setBackground(DARK_COLOR);
                
                pnlChessCells[row][column].validate();
                pnlChessCells[row][column].repaint();
            }
        }
        
        for (Iterator<int[]> iterator = highlightedMoveTiles.iterator(); iterator.hasNext();)
        {
        	int[] highlightPos = iterator.next();
        	pnlChessCells[highlightPos[0]][highlightPos[1]].setBackground(MOVE_COLOR);
        }
    }

    public void handleSelection(TilePanel newTile)
    {
        // new tile information
        int[] newLoc = newTile.getBoardLoc();
    	gBoard.tiles[newLoc[0]][newLoc[1]].toSysOut();

        if (selectedTile.pieceAt.charRep == new NullPiece().charRep)
        	//HANDLING FIRST SELECTION
        {
            // if empty tile/null piece, do nothing and break out
            if (gBoard.tiles[newLoc[0]][newLoc[1]].charRep == '-')
            {
                System.out.println("not a piece. must select piece first");
                return;
            }
            System.out.println("handleSelection called, no previously selected"
                    + " tile found so this is selectedTile");

            // save selected tile
            selectedTile = newTile;
            highlightedMoveTiles = newTile.pieceAt.searchValidMoves(gBoard.tiles, newTile.pieceAt.directions);
            
        }
        else if (selectedTile2.pieceAt.charRep == new NullPiece().charRep)
        	//HANDLING SECOND SELECTION
        {
            // info of previously selected tile
            int[] prevLoc = selectedTile.getBoardLoc();
            
            for (Iterator<int[]> iterator = highlightedMoveTiles.iterator(); iterator.hasNext();)
            {
            	if (Arrays.equals(newLoc, iterator.next())) //valid move
                {
                	gBoard.movePiece(prevLoc, newLoc);
                }
            }
            for (Iterator<int[]> iterator = highlightedAttackTiles.iterator(); iterator.hasNext();)
            {
            	if (Arrays.equals(newLoc, iterator.next())) //valid attack
                {
                	//TODO handle attack behavior here
                }
            }
            
            //reset selections, highlights
            selectedTile = NULL_TILE;
            selectedTile2 = NULL_TILE;
            highlightedMoveTiles.clear();
            highlightedAttackTiles.clear();
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
            lblTemp. setVerticalAlignment(SwingConstants.CENTER);
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
