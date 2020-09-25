import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
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

public class GameFrame extends JFrame implements ActionListener{

    CardLayout cardLayout;
    JPanel mainPanel;
    MenuPanel menu;
    GamePanel game;
    JButton goGame;
    JButton howTo;
	ImageIcon instruct = new ImageIcon("Images/medChess-1.jpg");

	String longMessage;

	//GAME FRAME FOR OVERALL SET UP (UNIVERSAL BUTTONS)////////////////////////////////////////////////////////////////////////////////////////
    public GameFrame() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        menu = new MenuPanel();
        game = new GamePanel();
        mainPanel.add(menu, "menu");
        mainPanel.add(game, "game");


        goGame = new JButton("PLAY");
        goGame.setFont(new Font("Tahoma", Font.PLAIN, 30));
        goGame.addActionListener(this);
        
        howTo = new JButton("HOW TO PLAY");
        howTo.setFont(new Font("Tahoma", Font.PLAIN, 30));
        howTo.addActionListener(this);

        add(mainPanel);
        add(goGame, BorderLayout.EAST);
        add(howTo, BorderLayout.WEST);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationByPlatform(true);
        setVisible(true);
    }

    //BUTTON INTERACTIONS
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == goGame)
        {
        	gameOn();
        	goGame.setVisible(false);
        }
        else if (e.getSource() == howTo)
        {
        	longMessage = "CAPTURING PIECES "
        			+ "\nFuzzy-Logic Chess (F-L Chess) introduces uncertainty to the act of capturing a piece so\r\n" + 
        			"that players must use reasoning with uncertainty (fuzzy logic: probability) in planning their strategies. The\r\n" + 
        			"attacking player rolls a die to\r" + 
        			" determine if a capture is successful.\r"+ 
        			" The die roll needed to capture a piece\r\n" + 
        			"depends on the combination of the\r" + 
        			" attacking piece and the defending\r" + 
        			" piece, as shown in the Capture Table.\r\n" + 
        			"    • When an attack is successful, the attacking piece moves into the square of the captured piece.\r\n" + 
        			"    • When an attack is unsuccessful, the attacking piece remains in the square it attacked from.\r\n" + 
        			"    • All pieces may attempt to capture an opposing piece in any direction (left, right, up, down, diagonal).\n\n" +
        			
        			"MOUNTED KNIGHTS AND ROYALTY\r\n" + 
        			"In F-L Medieval Chess, the King," + 
        			" Queen, and Knights may each move in any direction, \nand do not have to move in a straight line. Not counting" + 
        			" the starting square, but counting the final square, \nthe King and Queen may move three squares, and the" + 
        			" Knights five squares. They may not jump over \nor pass through an occupied square. The King and Queen" + 
        			" represent the elite royalty, more heavily \narmored than knights, but slower.\r\n" + 
        			"Knights (only) may combine movement with a capture in the same action, attacking any adjacent enemy\r\n" + 
        			"piece after their movement, but subtract one from the die roll.\r\n\n" + 
        			"THE INFANTRY (Pawns and Bishop)\r\n" + 
        			"Pawns represent simple infantry with minimal training and arms, while Bishops represent pikemen with long\r\n" + 
        			"pikes or halberds with formal training and experience.\r\n" + 
        			"• Pawns and Bishops may move and attack in a forward direction only, either directly ahead or to either\r\n" + 
        			"  forward diagonal toward the opposing player.\r\n" + 
        			"• Pawns are never promoted after reaching the eighth rank (the opponent’s home row).\r\n" + 
        			"• Pawns may not move two squares forward when they have not yet moved in the game\r\n\n" + 
        			"ARCHERS (Rook/Castle)\r\n" + 
        			"The Rook represents a company of archers, who may move a single square in any direction. Archers may\r\n" + 
        			"attack any piece by shooting over up to two squares (not counting the square with the Rook or the square\r\n" + 
        			"with the enemy piece). ";

        	JOptionPane.showMessageDialog(null, longMessage, "How To Play", JOptionPane.INFORMATION_MESSAGE, instruct);
        }
    }
    
    //SWITCH TO ACTUAL GAME PANEL
    public void gameOn() {
        cardLayout.show(mainPanel, "game");
    }

    //FINAL SET UP (MAIN METHOD)
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                GameFrame gameFrame = new GameFrame();
            }
        });
    }
}

//MAIN MENU PANEL//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
class MenuPanel extends JPanel {

    public MenuPanel() {
        setBackground(new Color(136, 189, 183));
        setLayout(new GridLayout(2,0));
        
        //LOGO
        JLabel logoImage = new JLabel("LOGO HERE", SwingConstants.CENTER);
		logoImage.setIcon(null);
		logoImage.setFont(new Font("Tahoma", Font.PLAIN, 44));
		logoImage.setVerticalAlignment(SwingConstants.CENTER);
		logoImage.setHorizontalAlignment(SwingConstants.CENTER);
		add(logoImage);
		
		//TEAM NAME
		JLabel teamName = new JLabel ("Team 4A");
		teamName.setFont(new Font("Tahoma", Font.PLAIN, 44));
		teamName.setVerticalAlignment(SwingConstants.CENTER);
		teamName.setHorizontalAlignment(SwingConstants.CENTER);
		add(teamName);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(300, 300);
    }
}

//ACTUAL GAMEPLAY PANEL///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
class GamePanel extends JPanel implements MouseListener{

    public GamePanel() {
        setBackground(Color.DARK_GRAY);
        setLayout(new GridLayout(8,8));
    	JPanel[][] pnlChessCells = new JPanel[8][8];        
    	String[][] strChessBoard = new String[][] { {"RB", "NB", "BB", "QB", "KB", "BB", "NB", "RB" }, {"PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB"}, {"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "}, {"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "}, {"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "}, {"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "}, {"PW", "PW", "PW", "PW", "PW", "PW", "PW", "PW"}, {"RW", "NW", "BW", "QW", "KW", "BW", "NW", "RW"} };

        
         //CREATE BOARD IN GAME PANEL CONSTRUCTOR
         for (int y = 0; y < 8; y++)
         {

            for (int x = 0; x < 8; x++)

            {

                  pnlChessCells[y][x] = new JPanel(new BorderLayout());

                  pnlChessCells[y][x].addMouseListener(this);

                  this.add(pnlChessCells[y][x]);

                  if (y % 2 == 0)

                        if (x % 2 != 0)

                              pnlChessCells[y][x].setBackground(Color.DARK_GRAY);

                        else

                              pnlChessCells[y][x].setBackground(Color.WHITE);

                  else

                        if (x % 2 == 0)

                              pnlChessCells[y][x].setBackground(Color.DARK_GRAY);

                        else

                              pnlChessCells[y][x].setBackground(Color.WHITE);

            }
          }
         
         //PLACE PIECES ON BOARD IN GAME PANEL CONSTRUCTOR
         for(int y = 0; y < 8; y++)       

             for(int x = 0; x < 8; x++) 

             {                

                   pnlChessCells[y][x].add(this.getPieceObject(strChessBoard[y][x]), BorderLayout.CENTER);

                   pnlChessCells[y][x].validate();

             }      
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(300, 300);
    }
   
    //SETS IMAGE ICON TO EACH PIECE
    public JLabel getPieceObject(String strPieceName)

    {

          JLabel lblTemp;

          if(strPieceName.equals("RB")) {

        	  lblTemp = new JLabel();
        	  ImageIcon rookBlack = new ImageIcon("Images/rookBlack.jpg");
        	  lblTemp.setIcon(rookBlack);
        	  lblTemp.setHorizontalAlignment(SwingConstants.CENTER);
        	  lblTemp.setVerticalAlignment(SwingConstants.CENTER);
          }


          else if(strPieceName.equals("BB")) {

        	  lblTemp = new JLabel();
        	  ImageIcon bishopBlack = new ImageIcon("Images/bishopBlack.jpg");
        	  lblTemp.setIcon(bishopBlack);
        	  lblTemp.setHorizontalAlignment(SwingConstants.CENTER);
        	  lblTemp.setVerticalAlignment(SwingConstants.CENTER);
          }



          else if(strPieceName.equals("NB")) {

        	  lblTemp = new JLabel();
        	  ImageIcon knightBlack = new ImageIcon("Images/knightBlack.jpg");
        	  lblTemp.setIcon(knightBlack);
        	  lblTemp.setHorizontalAlignment(SwingConstants.CENTER);
        	  lblTemp.setVerticalAlignment(SwingConstants.CENTER);
          }


          else if(strPieceName.equals("QB")) {

        	  lblTemp = new JLabel();
        	  ImageIcon queenBlack = new ImageIcon("Images/queenBlack.jpg");
        	  lblTemp.setIcon(queenBlack);
        	  lblTemp.setHorizontalAlignment(SwingConstants.CENTER);
        	  lblTemp.setVerticalAlignment(SwingConstants.CENTER);
          }



          else if(strPieceName.equals("KB")) {

        	  lblTemp = new JLabel();
        	  ImageIcon kingBlack = new ImageIcon("Images/kingBlack.jpg");
        	  lblTemp.setIcon(kingBlack);
        	  lblTemp.setHorizontalAlignment(SwingConstants.CENTER);
        	  lblTemp.setVerticalAlignment(SwingConstants.CENTER);
          }



          else if(strPieceName.equals("PB")) {

        	  lblTemp = new JLabel();
        	  ImageIcon pawnBlack = new ImageIcon("Images/pawnBlack.jpg");
        	  lblTemp.setIcon(pawnBlack);
        	  lblTemp.setHorizontalAlignment(SwingConstants.CENTER);
        	  lblTemp.setVerticalAlignment(SwingConstants.CENTER);
          }


          else if(strPieceName.equals("RW")) {
    	
        	  lblTemp = new JLabel();
        	  ImageIcon rookWhite = new ImageIcon("Images/rookWhite.jpg");
        	  lblTemp.setIcon(rookWhite);
        	  lblTemp.setHorizontalAlignment(SwingConstants.CENTER);
        	  lblTemp.setVerticalAlignment(SwingConstants.CENTER);
          }


          else if(strPieceName.equals("BW")) {

        	  lblTemp =  new JLabel();
        	  ImageIcon bishopWhite = new ImageIcon("Images/bishopWhite.jpg");
        	  lblTemp.setIcon(bishopWhite);
        	  lblTemp.setHorizontalAlignment(SwingConstants.CENTER);
        	  lblTemp.setVerticalAlignment(SwingConstants.CENTER);
          }


          else if(strPieceName.equals("NW")) {

        	  lblTemp = new JLabel();
        	  ImageIcon knightWhite = new ImageIcon("Images/knightWhite.jpg");
        	  lblTemp.setIcon(knightWhite);
        	  lblTemp.setHorizontalAlignment(SwingConstants.CENTER);
        	  lblTemp.setVerticalAlignment(SwingConstants.CENTER);
          }


          else if(strPieceName.equals("QW")) {

        	  lblTemp = new JLabel();
        	  ImageIcon queenWhite = new ImageIcon("Images/queenWhite.jpg");
        	  lblTemp.setIcon(queenWhite);
        	  lblTemp.setHorizontalAlignment(SwingConstants.CENTER);
        	  lblTemp.setVerticalAlignment(SwingConstants.CENTER);
          }


          else if(strPieceName.equals("KW")) {

        	  lblTemp = new JLabel();
        	  ImageIcon kingWhite = new ImageIcon("Images/kingWhite.jpg");
        	  lblTemp.setIcon(kingWhite);
        	  lblTemp.setHorizontalAlignment(SwingConstants.CENTER);
        	  lblTemp.setVerticalAlignment(SwingConstants.CENTER);
          }


          else if(strPieceName.equals("PW")) {

        	  lblTemp = new JLabel();
        	  ImageIcon pawnWhite = new ImageIcon("Images/pawnWhite.jpg");
        	  lblTemp.setIcon(pawnWhite);
        	  lblTemp.setHorizontalAlignment(SwingConstants.CENTER);
        	  lblTemp.setVerticalAlignment(SwingConstants.CENTER);
          }


          else {

        	  lblTemp = new JLabel();  
          }


          	return lblTemp;

    }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
    
}

