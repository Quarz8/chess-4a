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
import java.util.HashMap;
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

    // GAME FRAME FOR OVERALL SET UP (UNIVERSAL BUTTONS)//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public GameFrame()
    {
        //INITIALIZATION OF CARD LAYOUT STYLE FOR THE MAIN PANEL AND FLOW LAYOUT STYLE FOR CONTROL PANEL -- THE MENU/GAME PANELS CONTAINED IN MAIN PANEL
        cardLayout = new CardLayout();
        flowLayout = new FlowLayout(SwingConstants.LEFT);
        
        menu = new MenuPanel();
        game = new GamePanel();
        
        mainPanel = new JPanel(cardLayout);
        mainPanel.add(menu, "menu");
        mainPanel.add(game, "game");

        //MOVE AND ATTACK BUTTONS (HIDDEN UNTIL GAME PANEL IS SHOWN) -- THESE ARE ADDED TO THE CONTROL PANEL
    	moveButton = new JButton("MOVE");
    	moveButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
    	moveButton.setPreferredSize(new Dimension(493,100));
    	moveButton.addActionListener(this);
    	
    	attackButton = new JButton("ATTACK");
    	attackButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
    	attackButton.setPreferredSize(new Dimension(493,100));;
    	attackButton.addActionListener(this);

    	//HOW TO PLAY AND PLAY BUTTONS 
        goGame = new JButton("PLAY");
        goGame.setFont(new Font("Tahoma", Font.PLAIN, 30));
        goGame.addActionListener(this);

        howTo = new JButton("HOW TO PLAY");
        howTo.setFont(new Font("Tahoma", Font.PLAIN, 30));
        howTo.addActionListener(this);

        //CONTROL PANEL (IS SHOWN WHEN GAME IS PLAYED, HIDDEN TO START)
    	controlPanel = new JPanel(flowLayout);
    	controlPanel.add(moveButton);
    	controlPanel.add(attackButton);

        //PUSH COMPONENTS TO GAMEFRAME (JFRAME)
        add(mainPanel);
        add(goGame, BorderLayout.EAST);
        add(howTo, BorderLayout.WEST);
        add(controlPanel, BorderLayout.SOUTH);
    	controlPanel.setVisible(false);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocation(0,0);
        setSize(1240,900);
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
        	//SHOWS CHANGE IN STATE TO MOVE GAMEPLAY
        	attackButton.setBackground(null);
        	attackButton.setForeground(Color.black);
        	moveButton.setBackground(new Color(136, 0, 27));
        	moveButton.setForeground(Color.white);
        	
        	//MOVEMENT STATE IMPLEMENTATION COULD POTENTIALLY GO HERE
        }
        else if (e.getSource() == attackButton)
        {
        	//SHOWS CHANGE IN STATE TO ATTACK GAMEPLAY
        	moveButton.setBackground(null);
        	moveButton.setForeground(Color.black);
        	attackButton.setBackground(new Color(136, 0, 27));
        	attackButton.setForeground(Color.white);
        	
        	//ATTACKING STATE IMPLEMENTATION COULD POTENTIALLY GO HERE
        }
    }

    // SWITCH TO ACTUAL GAME PANEL
    public void gameOn()
    {
        cardLayout.show(mainPanel, "game");
        controlPanel.setVisible(true);
        game.updateBoard(new GameBoard());
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

// MAIN MENU PANEL/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
class MenuPanel extends JPanel
{
    public MenuPanel()
    {
        setBackground(new Color(136, 189, 183));
        setLayout(new GridLayout(2, 0));

        // LOGO
        JLabel logoImage = new JLabel("LOGO HERE", SwingConstants.CENTER);
        logoImage.setIcon(null);
        logoImage.setFont(new Font("Tahoma", Font.PLAIN, 44));
        logoImage.setVerticalAlignment(SwingConstants.CENTER);
        logoImage.setHorizontalAlignment(SwingConstants.CENTER);
        add(logoImage);

        // TEAM NAME
        JLabel teamName = new JLabel("Team 4A");
        teamName.setFont(new Font("Tahoma", Font.PLAIN, 44));
        teamName.setVerticalAlignment(SwingConstants.CENTER);
        teamName.setHorizontalAlignment(SwingConstants.CENTER);
        add(teamName);
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(300, 300);
    }
}

// ACTUAL GAMEPLAY PANEL////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
class GamePanel extends JPanel
{
    GameBoard gBoard = new GameBoard();
    TilePanel[][] pnlChessCells = new TilePanel[gBoard.tiles.length][gBoard.tiles[0].length];
    TilePanel selectedTile;
    TilePanel selectedTile2;
    final Color DARK_COLOR = new Color(136, 0, 27);
    final Color LIGHT_COLOR = new Color(255, 206, 158);
    final Color SELECT_COLOR = new Color(24, 39, 64);
    final TilePanel NULL_TILE = new TilePanel(-1, -1, this, new BorderLayout());

    public GamePanel()
    {
        setLayout(new GridLayout(pnlChessCells.length, pnlChessCells[0].length));
        this.selectedTile = NULL_TILE;
        this.selectedTile2 = NULL_TILE;

        // CREATE BOARD IN GAME PANEL CONSTRUCTOR
        for (int y = 0; y < pnlChessCells.length; y++)
        {
            for (int x = 0; x < pnlChessCells[0].length; x++)
            {
                pnlChessCells[y][x] = new TilePanel(x, y, this, new BorderLayout());
                this.add(pnlChessCells[y][x]);

                if ((x + y) % 2 == 0)
                    pnlChessCells[y][x].setBackground(LIGHT_COLOR);
                else
                    pnlChessCells[y][x].setBackground(DARK_COLOR);
            }
        }
    }

    public void updateBoard(GameBoard gBoard)
    {
        // PLACE PIECES ON BOARD IN GAME PANEL CONSTRUCTOR
        for (int x = 0; x < pnlChessCells.length; x++)
        {
            for (int y = 0; y < pnlChessCells[0].length; y++)
            {
                pnlChessCells[x][y].removeAll();
                pnlChessCells[x][y].add(this.getPieceObject(gBoard.tiles[x][y].charRep), BorderLayout.CENTER);
                pnlChessCells[x][y].validate();
                pnlChessCells[x][y].repaint();
            }
        }
    }

    public void handleSelection(TilePanel newTile)
    {
        // new tile information
        int[] newLoc = newTile.getBoardLoc();
        int newXLoc = newLoc[0];
        int newYLoc = newLoc[1];

        // must not have selected a tile already
        if (selectedTile.x == -1)
        {
            // if empty tile/null piece, do nothing and break out
            if (gBoard.tiles[newYLoc][newXLoc].charRep == '-')
            {
                System.out.println("not a piece. must select piece first");
                return;
            }
            System.out.println(newTile.getComponentCount() + "handleSelection called, no previously selected"
                    + " tile found so this is selectedTile");

            // save selected tile
            selectedTile = newTile;
        }
        else if (selectedTile2.x == -1)
        {
            // check if destination is open
            if (gBoard.tiles[newYLoc][newXLoc].charRep != '-')
            {
                System.out.println("this isnt an attack, space is occupied");
                selectedTile = NULL_TILE; // reset all selected tiles
                return;
            }

            // info of previously selected tile
            int[] prevLoc = selectedTile.getBoardLoc();
            int prevXLoc = prevLoc[0];
            int prevYLoc = prevLoc[1];

            // rules for moving previously selected piece...
            // white pawns and bishops
            if (gBoard.tiles[prevYLoc][prevXLoc].charRep == 'P' || gBoard.tiles[prevYLoc][prevXLoc].charRep == 'B')
            {
                if ((newYLoc >= prevYLoc || newYLoc < prevYLoc - 1)
                        || (newXLoc > prevXLoc + 1 || newXLoc < prevXLoc - 1)) // only forward by 1
                {
                    System.out.println("invalid move noob");
                    selectedTile = NULL_TILE; // reset all selected tiles
                    return;
                }
            }

            // black pawns and bishops
            if (gBoard.tiles[prevYLoc][prevXLoc].charRep == 'p' || gBoard.tiles[prevYLoc][prevXLoc].charRep == 'b')
            {
                if ((newYLoc <= prevYLoc || newYLoc > prevYLoc + 1)
                        || (newXLoc > prevXLoc + 1 || newXLoc < prevXLoc - 1)) // only forward by 1
                {
                    System.out.println("invalid move noob");
                    selectedTile = NULL_TILE; // reset all selected tiles
                    return;
                }
            }

            // rooks
            if (gBoard.tiles[prevYLoc][prevXLoc].charRep == 'r' || gBoard.tiles[prevYLoc][prevXLoc].charRep == 'R')
            {
                if (newYLoc > prevYLoc + 1 || newYLoc < prevYLoc - 1 || newXLoc > prevXLoc + 1
                        || newXLoc < prevXLoc - 1) // only by 1
                {
                    System.out.println("invalid move noob");
                    selectedTile = NULL_TILE; // reset all selected tiles
                    return;
                }
            }

            // kings and queens
            // need an actual algorithm for these guys (3 spaces any direction), A* search specifically.
            
            // knights
            // same algorithm can be used, just with a larger range (5 spaces any direction)

            System.out.println("handleSelection called, selectedTile found"
                    + " so this is selectedTile2, performing move and resetting");
            selectedTile2 = newTile;
            int[] before =
            { selectedTile.x, selectedTile.y };
            int[] after =
            { selectedTile2.x, selectedTile2.y };
            gBoard.movePiece(before, after);
            this.updateBoard(gBoard);
            selectedTile = NULL_TILE;
            selectedTile2 = NULL_TILE;
        }
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
            lblTemp = new JLabel("black rook");
            // lblTemp.setIcon(new ImageIcon(GameFrame.class.getResource("Images/Rook
            // Piece.png")));
            lblTemp.setHorizontalAlignment(SwingConstants.CENTER);
            lblTemp.setVerticalAlignment(SwingConstants.CENTER);
            break;
        case 'b':
            lblTemp = new JLabel("black bishop");
            // lblTemp.setIcon(new ImageIcon(GameFrame.class.getResource("Images/Bishop
            // Piece.png")));
            lblTemp.setHorizontalAlignment(SwingConstants.CENTER);
            lblTemp.setVerticalAlignment(SwingConstants.CENTER);
            break;
        case 'n':
            lblTemp = new JLabel("black knight");
            // lblTemp.setIcon(new ImageIcon(GameFrame.class.getResource("Images/knight
            // piece.png")));
            lblTemp.setHorizontalAlignment(SwingConstants.CENTER);
            lblTemp.setVerticalAlignment(SwingConstants.CENTER);
            break;
        case 'q':
            lblTemp = new JLabel("black queen");
            // lblTemp.setIcon(new ImageIcon(GameFrame.class.getResource("Images/Queen
            // Piece.png")));
            lblTemp.setHorizontalAlignment(SwingConstants.CENTER);
            lblTemp.setVerticalAlignment(SwingConstants.CENTER);
            break;
        case 'k':
            lblTemp = new JLabel("black king");
            // lblTemp.setIcon(new ImageIcon(GameFrame.class.getResource("Images/King
            // Piece.png")));
            lblTemp.setHorizontalAlignment(SwingConstants.CENTER);
            lblTemp.setVerticalAlignment(SwingConstants.CENTER);
            break;
        case 'p':
            lblTemp = new JLabel("black pawn");
            // lblTemp.setIcon(new ImageIcon(GameFrame.class.getResource("Images/Pawn
            // Piece.png")));
            lblTemp.setHorizontalAlignment(SwingConstants.CENTER);
            lblTemp.setVerticalAlignment(SwingConstants.CENTER);
            break;
        case 'R':
            lblTemp = new JLabel("white rook");
            // lblTemp.setIcon(new ImageIcon(GameFrame.class.getResource("Images/Rook Piece
            // White.png")));
            lblTemp.setHorizontalAlignment(SwingConstants.CENTER);
            lblTemp.setVerticalAlignment(SwingConstants.CENTER);
            break;
        case 'B':
            lblTemp = new JLabel("white bishop");
            // lblTemp.setIcon(new ImageIcon(GameFrame.class.getResource("Images/Bishop
            // Piece white.png")));
            lblTemp.setHorizontalAlignment(SwingConstants.CENTER);
            lblTemp.setVerticalAlignment(SwingConstants.CENTER);
            break;
        case 'N':
            lblTemp = new JLabel("white knight");
            // lblTemp.setIcon(new ImageIcon(GameFrame.class.getResource("Images/knight
            // piece white.png")));
            lblTemp.setHorizontalAlignment(SwingConstants.CENTER);
            lblTemp.setVerticalAlignment(SwingConstants.CENTER);
            break;
        case 'Q':
            lblTemp = new JLabel("white queen");
            // lblTemp.setIcon(new ImageIcon(GameFrame.class.getResource("Images/Queen Piece
            // White.png")));
            lblTemp.setHorizontalAlignment(SwingConstants.CENTER);
            lblTemp.setVerticalAlignment(SwingConstants.CENTER);
            break;
        case 'K':
            lblTemp = new JLabel("white king");
            // lblTemp.setIcon(new ImageIcon(GameFrame.class.getResource("Images/King
            // Piece.png")));
            lblTemp.setHorizontalAlignment(SwingConstants.CENTER);
            lblTemp.setVerticalAlignment(SwingConstants.CENTER);
            break;
        case 'P':
            lblTemp = new JLabel("white pawn");
            // lblTemp.setIcon(new ImageIcon(GameFrame.class.getResource("Images/Pawn Piece
            // white.png")));
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
    int x;
    int y;

    public TilePanel(GamePanel parent, LayoutManager layout)
    {
        addMouseListener(this);
        this.setLayout(layout);
        this.parent = parent;
    }

    public TilePanel(int x, int y, GamePanel parent, LayoutManager layout)
    {
        addMouseListener(this);
        this.setLayout(layout);
        this.parent = parent;
        this.x = x;
        this.y = y;
    }

    public int[] getBoardLoc()
    {
        int[] loc =
        { x, y };
        return loc;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        System.out.println("mousePressed called by tile at (" + x + ", " + y + ")");
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
