import java.awt.BorderLayout;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

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
    static JButton skipButton;
    static JLabel dieDisplay, turnDisplay;
    ImageIcon instruct, instruct2;

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

        // CONTROL PANEL (IS SHOWN WHEN GAME IS PLAYED, HIDDEN TO START) INCLUDES DIE ROLL DISPLAY AND TURN DISPLAY
        controlPanel = new JPanel(flowLayout);

    	
		dieDisplay = new JLabel();
		//turnDisplay.setPreferredSize(new Dimension(100,100));
		//turnDisplay.setIcon(new ImageIcon(GameFrame.class.getResource("Images/knight piece.png")));
		dieDisplay.setPreferredSize(new Dimension(100,100));
		dieDisplay.setIcon(new ImageIcon(GameFrame.class.getResource("Images/die1.png")));

		turnDisplay = new JLabel();
		turnDisplay.setPreferredSize(new Dimension(190,100));
    turnDisplay.setFont(new Font("Tahoma", Font.PLAIN, 20));
		turnDisplay.setText("YOUR TURN");
		
		controlPanel.add(turnDisplay);
    controlPanel.add(skipButton);
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
        	game.gBoard.whiteMoving = game.gBoard.whiteMoving ? false : true; // switches turns after skipped turn
        	
        	game.gBoard.actionsTaken = 0; // reset actionsTaken
            // reset all corp's hasActed to false
        	game.gBoard.corpBB1.setHasActed(false);
        	game.gBoard.corpBB2.setHasActed(false);
        	game.gBoard.corpBW1.setHasActed(false);
        	game.gBoard.corpBW2.setHasActed(false);

        	
        	// reset selections, highlights
            game.selectedTile = game.NULL_TILE;
            game.selectedTile2 = game.NULL_TILE;
            game.highlightedMoveTiles.clear();
            game.highlightedAttackTiles.clear();
            game.updateBoard(game.gBoard);
            
            // start (black) AI's turn
            if(!game.gBoard.whiteMoving)
            for(int i=0; i<game.gBoard.maxActionsBlack; i++)
                game.scanBoard(false, game.gBoard.tiles);
        	
        	 // DISPLAY WHOSE TURN IT IS AFTER SKIP
            game.changeTurnDisplay();
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
    Piece selectedPiece;
    Piece selectedPiece2;
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
        this.selectedPiece = NULL_TILE.pieceAt;
        this.selectedPiece2 = NULL_TILE.pieceAt;

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
        

        // DISPLAY WHOSE TURN IT IS
       this.changeTurnDisplay();
        
    }

    // HANDLES HUMAN MOUSE CLICK SELECTION
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
                System.out.println("Not a piece. Must select piece first.");
                return;
            }

            System.out
                    .println("handleSelection called. No previously selected" + " tile found so this is selectedTile.");

            /*
             * if the selected tile color does not match the color of the player's turn,
             * return an error message
             */
            if (newTile.pieceAt.white != gBoard.whiteMoving)
            {
                System.out.println("Not your turn.");
                return;
            }

            if (!newTile.pieceAt.getCorp().getHasActed())// if piece's corp has not already acted...
            {
                // save selected piece and find highlighted tiles
                selectedTile = newTile;
                highlightedMoveTiles = newTile.pieceAt.searchValidActions(gBoard.tiles, newTile.pieceAt.directions,
                        true);
                highlightedAttackTiles = newTile.pieceAt.searchValidActions(gBoard.tiles, newTile.pieceAt.directions,
                        false);
            }
            else
            {
                System.out.println("This corp has already acted.");
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
                	if(selectedTile.pieceAt.getCorp() != gBoard.corpKB && selectedTile.pieceAt.getCorp() != gBoard.corpKW) {
                	
                		selectedTile.pieceAt.getCorp().setHasActed(true); // mark that that corp has now acted
                	}
                    gBoard.actionsTaken++; // increment actionsTaken for this turn
                    gBoard.movePiece(prevLoc, newLoc); // move the piece to its new location
                    if (gBoard.actionsTaken >= (gBoard.whiteMoving? gBoard.maxActionsWhite : gBoard.maxActionsBlack)) // if max action limit is reached...
                    {
                        System.out.println("END OF TURN");

                        gBoard.whiteMoving = gBoard.whiteMoving ? false : true; // switches turns after move is made
                        
                        gBoard.actionsTaken = 0; // reset actionsTaken
                        // reset all corp's hasActed to false
                        gBoard.corpBB1.setHasActed(false);
                        gBoard.corpBB2.setHasActed(false);
                        gBoard.corpBW1.setHasActed(false);
                        gBoard.corpBW2.setHasActed(false);
                        
                    }

                    break;
                }
            }
            for (Iterator<int[]> iterator = highlightedAttackTiles.iterator(); iterator.hasNext();)
            {
                if (Arrays.equals(newLoc, iterator.next())) // selected a highlighted attack tile
                {
                    selectedTile2 = newTile; // save selected tile

                    if(selectedTile.pieceAt.getCorp() != gBoard.corpKB && selectedTile.pieceAt.getCorp() != gBoard.corpKW) {
                    	selectedTile.pieceAt.getCorp().setHasActed(true); // mark that that corp has now acted
                    }
                    gBoard.actionsTaken++; // increment actionsTaken for this turn

                    Attack piece = new Attack();

                    if (piece.tryAttack(selectedTile.pieceAt, selectedTile2.pieceAt, selectedTile.pieceAt.hasMoved)) // if attack succeeds...
                    {
                    	
                    	if(selectedTile2.pieceAt.charRep == 'b') {
                        	
                        	Corp tempCorp1 = selectedTile2.pieceAt.corp;

                    		for (int i = 0; i < tempCorp1.units.size(); i++) // for every piece in this bishop's corp...
                            {

                    			tempCorp1.units.get(i).corp = gBoard.corpKB;
                            }
                            
                    		gBoard.maxActionsBlack--;
                    	}
                    	else if(selectedTile2.pieceAt.charRep == 'B') {
                        
                        	Corp tempCorp2 = selectedTile2.pieceAt.corp;

                    		for (int i = 0; i < tempCorp2.units.size(); i++) // for every piece in this bishop's corp...
                            {
                    			tempCorp2.units.get(i).corp = gBoard.corpKW; // set piece's corp to kingCorp
                            }
                            
                    		gBoard.maxActionsWhite--;
                    	}
                        //piece.killPiece(selectedTile2.pieceAt);
                        gBoard.movePiece(prevLoc, newLoc);
                        
                        piece.setDieDisplay();
                    }
                    else // if attack failed
                    {
                    	piece.setDieDisplay();
                    }
                    
                    if (gBoard.actionsTaken >= (gBoard.whiteMoving? gBoard.maxActionsWhite : gBoard.maxActionsBlack))
                    {
                        System.out.println("END OF TURN");
                        gBoard.whiteMoving = gBoard.whiteMoving ? false : true; // switches turns after move is made
                        gBoard.actionsTaken = 0; // reset actionsTaken
                        
                        gBoard.corpBB1.setHasActed(false);                       
                        gBoard.corpBB2.setHasActed(false);                                               
                        gBoard.corpBW1.setHasActed(false);                       
                        gBoard.corpBW2.setHasActed(false);                        

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

        this.updateBoard(gBoard);
        
        // start (black) AI's turn
        if(!gBoard.whiteMoving)
            for(int i=0; i < gBoard.maxActionsBlack; i++) {
                scanBoard(false, gBoard.tiles);
            }
    }
    
 // HANDLES AI Piece SELECTION
    public void handleAiSelection(Piece newPiece)
    {
        // new tile information
        int[] newLoc = {newPiece.row, newPiece.column};
        
        gBoard.tiles[newLoc[0]][newLoc[1]].toSysOut();

        if (selectedPiece.charRep == new NullPiece().charRep)
        // HANDLING FIRST SELECTION
        {
            // if empty tile/null piece, do nothing and break out
            if (gBoard.tiles[newLoc[0]][newLoc[1]].charRep == '-')
            {
                System.out.println("Not a piece. Must select piece first.");
                return;
            }

            System.out
                    .println("handleSelection called. No previously selected" + " tile found so this is selectedPiece.");

            /*
             * if the selected tile color does not match the color of the player's turn,
             * return an error message
             */
            if (newPiece.white != gBoard.whiteMoving)
            {
                System.out.println("Not your turn.");
                return;
            }

            if (!newPiece.getCorp().getHasActed())// if piece's corp has not already acted...
            {
                // save selected piece and find highlighted tiles
                selectedPiece = newPiece;
                highlightedMoveTiles = newPiece.searchValidActions(gBoard.tiles, newPiece.directions,
                        true);
                highlightedAttackTiles = newPiece.searchValidActions(gBoard.tiles, newPiece.directions,
                        false);
            }
            else
            {
                System.out.println("This corp has already acted.");
            }

        }
        else if (selectedPiece2.charRep == new NullPiece().charRep)
        // HANDLING SECOND SELECTION
        {
            // info of previously selected tile
            int[] prevLoc = {selectedPiece.row, selectedPiece.column};

            for (Iterator<int[]> iterator = highlightedMoveTiles.iterator(); iterator.hasNext();)
            {
                if (Arrays.equals(newLoc, iterator.next())) // selected a highlighted move tile
                {
                    if(selectedPiece.getCorp() != gBoard.corpKB && selectedPiece.getCorp() != gBoard.corpKW) {
                    
                        selectedPiece.getCorp().setHasActed(true); // mark that that corp has now acted
                    }
                    gBoard.actionsTaken++; // increment actionsTaken for this turn
                    gBoard.movePiece(prevLoc, newLoc); // move the piece to its new location
                    if (gBoard.actionsTaken >= (gBoard.whiteMoving? gBoard.maxActionsWhite : gBoard.maxActionsBlack)) // if max action limit is reach...
                    {
                        System.out.println("END OF TURN");
                        gBoard.whiteMoving = gBoard.whiteMoving ? false : true; // switches turns after move is made

                        gBoard.actionsTaken = 0; // reset actionsTaken
                        // reset all corp's hasActed to false
                        gBoard.corpBB1.setHasActed(false);
                        gBoard.corpBB2.setHasActed(false);
                        gBoard.corpBW1.setHasActed(false);
                        gBoard.corpBW2.setHasActed(false);

                    }

                    break;
                }
            }
            for (Iterator<int[]> iterator = highlightedAttackTiles.iterator(); iterator.hasNext();)
            {
                if (Arrays.equals(newLoc, iterator.next())) // selected a highlighted attack tile
                {
                    selectedPiece2 = newPiece; // save selected tile

                    if(selectedPiece.getCorp() != gBoard.corpKB && selectedPiece.getCorp() != gBoard.corpKW) {
                        selectedPiece.getCorp().setHasActed(true); // mark that that corp has now acted
                    }
                    gBoard.actionsTaken++; // increment actionsTaken for this turnAttack piece = new Attack();

                    Attack piece = new Attack();

                    if (piece.tryAttack(selectedPiece, selectedPiece2, selectedPiece.hasMoved)) // if attack succeeds...
                    {
                        
                        if(selectedPiece2.charRep == 'b') {
                            
                            Corp tempCorp1 = selectedPiece2.corp;

                            for (int i = 0; i < tempCorp1.units.size(); i++) // for every piece in this bishop's corp...
                            {

                                tempCorp1.units.get(i).corp = gBoard.corpKB;
                            }
                            
                            gBoard.maxActionsBlack--;
                        }
                        else if(selectedPiece2.charRep == 'B') {
                        
                            Corp tempCorp2 = selectedPiece2.corp;

                            for (int i = 0; i < tempCorp2.units.size(); i++) // for every piece in this bishop's corp...
                            {
                                tempCorp2.units.get(i).corp = gBoard.corpKW; // set piece's corp to kingCorp
                            }
                            
                            gBoard.maxActionsWhite--;
                        }
                        //piece.killPiece(selectedTile2.pieceAt);
                        gBoard.movePiece(prevLoc, newLoc);
                        piece.setDieDisplay();
                        
                    }
                    else // if attack failed
                    {
                        piece.setDieDisplay();
                    }
                    
                    if (gBoard.actionsTaken >= (gBoard.whiteMoving? gBoard.maxActionsWhite : gBoard.maxActionsBlack))
                    {
                        System.out.println("END OF TURN");
                        gBoard.whiteMoving = gBoard.whiteMoving ? false : true; // switches turns after move is made

                        gBoard.actionsTaken = 0; // reset actionsTaken
                        
                        gBoard.corpBB1.setHasActed(false);                       
                        gBoard.corpBB2.setHasActed(false);                                              
                        gBoard.corpBW1.setHasActed(false);                       
                        gBoard.corpBW2.setHasActed(false);                        
                        
                        }                   
                    
                    break;
                }
            }

            // reset selections, highlights
            selectedPiece = NULL_TILE.pieceAt;
            selectedPiece2 = NULL_TILE.pieceAt;
            highlightedMoveTiles.clear();
            highlightedAttackTiles.clear();
        }

        this.updateBoard(gBoard);
    }
    
    // Checks every board tile and checks if it is null (-) AND on the AI's team AND
    // if it's Corp has not yet acted. If piece is a match, checks if it can attack
    // and does so if possible. Else piece is stored as a potential move for later.
    void scanBoard(boolean isWhite, Piece[][] board)
    {
        ArrayList<Piece> movementCandidates = new ArrayList<>();
        ArrayList<int[]> attacks = new ArrayList<>();
        ArrayList<int[]> moves = new ArrayList<>();
        

        // for every tile of the board...
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[i].length; j++)
            {
                // if piece belongs to respective team and its Corp has not acted...
                if (board[i][j].charRep != '-' && isWhite ? Character.isUpperCase(board[i][j].charRep)
                        : Character.isLowerCase(board[i][j].charRep) && !board[i][j].getCorp().getHasActed())
                {                    
                    // check for valid attacks
                    attacks = board[i][j].searchValidActions(board, board[i][j].directions, false);
                    // if attack found...
                    if(!attacks.isEmpty()) { 
                        // select that piece and select a random valid attack and return
                        Random rng = new Random();
                        int whichAttack = rng.nextInt(attacks.size());
                        handleAiSelection(board[i][j]);
                        handleAiSelection(board[attacks.get(whichAttack)[0]][attacks.get(whichAttack)[1]]);
                        
                        return;
                    }
                    
                    // if at least 1 move is found...
                    if(!board[i][j].searchValidActions(board, board[i][j].directions, true).isEmpty()) {
                        // add to list of movementCandidates
                        movementCandidates.add(board[i][j]);
                    }
                }
            }
        }
        // if somehow no move can be made, skip turn
        if(movementCandidates.isEmpty())
            GameFrame.skipButton.doClick();
        else {
            // else, pick a random movementCandidate and a random valid move
            Random rng = new Random();
            Piece whichCandidate = movementCandidates.get(rng.nextInt(movementCandidates.size()));
            moves = whichCandidate.searchValidActions(board, whichCandidate.directions, true);
            int whichMove = rng.nextInt(moves.size());
            
            // select that piece and select a random valid move and return
            handleAiSelection(whichCandidate);
            handleAiSelection(board[moves.get(whichMove)[0]][moves.get(whichMove)[1]]);
            
            return;
        }
    }
    
    //UPDATES GAMEFRAME TURN DISPLAY LABEL 
    public void changeTurnDisplay()
    {
    	if (gBoard.whiteMoving == false)
        {
        	GameFrame.turnDisplay.setText("AI's TURN");
        }
        else
        {
        	GameFrame.turnDisplay.setText("YOUR TURN");
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
