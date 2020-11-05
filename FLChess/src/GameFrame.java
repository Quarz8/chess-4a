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
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.Scrollable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.lang.*;

public class GameFrame extends JFrame implements ActionListener
{
	CardLayout cardLayout;
    FlowLayout flowLayout;
    JPanel mainPanel, controlPanel, firstInstructPanel, secondInstructPanel;
    JScrollPane scroll1, scroll2;
    JTabbedPane tabs;
    MenuPanel menu;
    GamePanel game;
    JButton goGame, howTo;
    JButton skipButton;
    static JLabel dieDisplay;
    ImageIcon instruct, instruct2, dieIcon;

    // GAME FRAME FOR OVERALL SET UP (UNIVERSAL BUTTONS)//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public GameFrame()
    {
        // INITIALIZATION OF CARD LAYOUT STYLE FOR THE MAIN PANEL AND FLOW LAYOUT STYLE FOR CONTROL PANEL -- THE MENU/GAME PANELS CONTAINED IN MAIN PANEL
        cardLayout = new CardLayout();
        flowLayout = new FlowLayout(SwingConstants.LEFT);

        menu = new MenuPanel();
        game = new GamePanel();

        mainPanel = new JPanel(cardLayout);
        mainPanel.add(menu, "menu");
        mainPanel.add(game, "game");

        // SKIP TURN BUTTON (HIDDEN UNTIL GAME PANEL IS SHOWN) -- ADDED TO THE CONTROL PANEL
    	skipButton = new JButton("SKIP TURN");
    	skipButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
    	skipButton.setPreferredSize(new Dimension(830,100));
    	skipButton.addActionListener(this);

        // HOW TO PLAY AND PLAY BUTTONS
        goGame = new JButton();
        goGame.setIcon(new ImageIcon(GameFrame.class.getResource("Images/Play Button.png")));
        goGame.addActionListener(this);

        howTo = new JButton();
        howTo.setIcon(new ImageIcon(GameFrame.class.getResource("Images/How To Play.png")));
        howTo.addActionListener(this);

        // CONTROL PANEL (IS SHOWN WHEN GAME IS PLAYED, HIDDEN TO START) INCLUDES DIE ROLL DISPLAY
        controlPanel = new JPanel(flowLayout);
    	controlPanel.add(skipButton);
    	
		dieDisplay = new JLabel();
		dieDisplay.setPreferredSize(new Dimension(100,100));
		dieDisplay.setIcon(new ImageIcon(GameFrame.class.getResource("Images/die1.png")));
		controlPanel.add(dieDisplay);

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
        	// DEFINING JTABBEDPANE, JPANELS, JSCROLLS, AND JLABELS
        	tabs = new JTabbedPane();
        	firstInstructPanel = new JPanel();
        	secondInstructPanel = new JPanel();
			JLabel first = new JLabel();
			JLabel second = new JLabel();        	
        	scroll1 = new JScrollPane(firstInstructPanel);
        	scroll2 = new JScrollPane(secondInstructPanel);
        	
        	// ADDS INSTRUCTION IMAGES AS ICONS ON JLABELS AND MAKES JOPTIONPANE SCROLLABLE
        	instruct = new ImageIcon(GameFrame.class.getResource("Images/FL-Chess__DistAI_V5c2-page-001.jpg"));
        	instruct2 = new ImageIcon(GameFrame.class.getResource("Images/FL-Chess__DistAI_V5c2-page-002.jpg"));
        	first.setIcon(instruct);
        	second.setIcon(instruct2);
            scroll1.setPreferredSize(new Dimension(1210, 700));
            scroll2.setPreferredSize(new Dimension(1210, 700));

            // ADDS COMPONENTS TO PANELS AND THEN TABS FOR JOPTIONPANE
        	firstInstructPanel.add(first);
        	secondInstructPanel.add(second);
        	tabs.add(scroll1, "Page 1");
        	tabs.add(scroll2, "Page 2");
        	
        	// DISPLAY JOPTIONPANE
            JOptionPane.showMessageDialog(null, tabs, "How To Play", JOptionPane.PLAIN_MESSAGE);
        }
        else if (e.getSource() == skipButton)
        {
        	System.out.println("END OF TURN");
        	game.gBoard.whiteMoving = game.gBoard.whiteMoving ? false : true; // switches player turn after move is
                                                                    // made
        	game.gBoard.actionsTaken = 0; // reset actionsTaken
            // reset all corp's hasActed to false
        	game.gBoard.corpBB1.setHasActed(false);
        	game.gBoard.corpBB2.setHasActed(false);
        	game.gBoard.corpKB.setHasActed(false);
        	game.gBoard.corpBW1.setHasActed(false);
        	game.gBoard.corpBW2.setHasActed(false);
        	game.gBoard.corpKW.setHasActed(false);
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

// MAIN MENU PANEL/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
class MenuPanel extends JPanel
{
    public MenuPanel()
    {
        setBackground(new Color(255, 255, 255));
        setLayout(new GridLayout(2, 0));

        // LOGO
        JLabel logoImage = new JLabel();
        logoImage.setIcon(new ImageIcon(GameFrame.class.getResource("Images/Title.png")));
        logoImage.setVerticalAlignment(SwingConstants.CENTER);
        logoImage.setHorizontalAlignment(SwingConstants.CENTER);
        add(logoImage);

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
        {
            pnlChessCells[selectedTile.pieceAt.row][selectedTile.pieceAt.column].setBackground(SELECT_COLOR);
        }
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
    }

    public void handleSelection(TilePanel newTile)
    {
        // new tile information
        int[] newLoc = newTile.getBoardLoc();
        gBoard.tiles[newLoc[0]][newLoc[1]].toSysOut();

        if (selectedTile.pieceAt.charRep == new NullPiece().charRep)
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
                System.out.println(newTile.pieceAt.getCorp());
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
        else if (selectedTile2.pieceAt.charRep == new NullPiece().charRep)
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
                    if (gBoard.actionsTaken >= (gBoard.whiteMoving? gBoard.maxActionsWhite : gBoard.maxActionsBlack)) // if max action limit is reach...
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
                    	
                    	if(selectedTile2.pieceAt.charRep == 'b') {
                        	
                        	System.out.println("HERE");
                    		selectedTile2.pieceAt.reassignAll(gBoard.corpKB);
                    		
                    		gBoard.maxActionsBlack--;
                    	}
                    	else if(selectedTile2.pieceAt.charRep == 'B') {
                        
                    		System.out.println("HERE");
                    		selectedTile2.pieceAt.reassignAll(gBoard.corpKW);
                    		gBoard.maxActionsWhite--;
                    	}
                        //piece.killPiece(selectedTile2.pieceAt);
                        gBoard.movePiece(prevLoc, newLoc);
                        
                        piece.setDieDisplay();
                        System.out.println("You rolled a.... " + piece.getDieNum()); // temp text
                        // TODO display roll in GUI, piece.getDieNum();
                        
                        
                    }
                    else // if attack failed
                    {
                    	piece.setDieDisplay();
                        System.out.println("You rolled a.... " + piece.getDieNum()); // temp text
                        // TODO display roll in GUI, piece.getDieNum();
                    }
                    
                    if (gBoard.actionsTaken >= (gBoard.whiteMoving? gBoard.maxActionsWhite : gBoard.maxActionsBlack))
                    {
                        System.out.println("END OF TURN");
                        gBoard.whiteMoving = gBoard.whiteMoving ? false : true; // switches player turn after move is
                                                                                // made
                        gBoard.actionsTaken = 0; // reset actionsTaken
                        // reset all corp's hasActed to false
                        
                        //if(gBoard.corpBB1.units.get(0) != null)
                        	gBoard.corpBB1.setHasActed(false);
                        
                        //if(gBoard.corpBB2.units.get(0) != null)
                        	gBoard.corpBB2.setHasActed(false);
                        
                        gBoard.corpKB.setHasActed(false);
                        
                        //if(gBoard.corpBW1.units.get(0) != null)
                        	gBoard.corpBW1.setHasActed(false);
                        
                        //if(gBoard.corpBW1.units.get(0) != null)
                        	gBoard.corpBW2.setHasActed(false);
                        
                        gBoard.corpKW.setHasActed(false);
                       
                        	//gBoard.maxActionsBlack
                        	//if(gBoard.whiteMoving)
                        	//Bishop.reassignAll(gBoard.corpKW);
                        }
                    
                   /* if(Attack.enemyBishop() == false) {
                    	if(gBoard.whiteMoving) {
                    		gBoard.maxActionsWhite = 2;
                    		Piece.reassignAll(corpKW);
                    		
                    	}
                    	else
                    		gBoard.maxActionsBlack = 2;
                    	
                    	*/
                        // TODO if selectedtile.pieceAt = enemyBishop, enemyMaxActions-- and
                        // bishop.reassignAll(kingCorp)
                    
                    break;
                }
            }

            // reset selections, highlights
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
