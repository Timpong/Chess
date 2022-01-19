import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.*;

public class ViewControl extends JFrame implements ActionListener{

    private JButton[][] chessboardGridButtons = new JButton[8][8];
    private JPanel infoPanel;

    private ChessSquare clickedFirst;
    private ChessSquare clickedSecond;

    private int moveNr = 0;         // only incremented as the model gives a correctMove response 

    private static HashMap<Integer, String> piecesMap;   
    static {
        piecesMap = new HashMap<>();
        piecesMap.put(0, "whitePawn");
        piecesMap.put(1, "whiteKnight");
        piecesMap.put(2, "whiteBishop");
        piecesMap.put(3, "whiteTower");
        piecesMap.put(4, "whiteQueen");
        piecesMap.put(5, "whiteKing");
        piecesMap.put(6, "blackPawn");
        piecesMap.put(7, "blackKnight");
        piecesMap.put(8, "blackBishop");
        piecesMap.put(9, "blackTower");
        piecesMap.put(11, "blackQueen");
        piecesMap.put(10, "blackKing");
    }
    private static HashMap<Integer, String> toMoveMap;   
    static {
        toMoveMap = new HashMap<>();
        toMoveMap.put(0, "White");
        toMoveMap.put(1, "Black");
    }
    private static HashMap<String, Integer> infoPanelMap;   
    static {
        infoPanelMap = new HashMap<>();
        infoPanelMap.put("White", 0);
        infoPanelMap.put("toMove", 1);
        infoPanelMap.put("Black", 2);
    }

    public ViewControl() {
        super("Chess");
        setLayout(new GridLayout(2,1));     // TBD: change in order for different sizes of every grid
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        add(createChessboardGrid());
        add(createInfoPanel());

        pack();
        setVisible(true);
    }
    private JPanel createChessboardGrid(){  // make a seperate class ? 
        JPanel chessboardPanel = new JPanel();   
        chessboardPanel.setLayout(new GridLayout(8,8)); 
        
        for (int r=0; r<8; r++){
            for (int c=0; c<8; c++){
                chessboardGridButtons[r][c] = new ChessSquare(r, c);
                chessboardGridButtons[r][c].addActionListener(this);
                chessboardPanel.add(chessboardGridButtons[r][c]);   // would have to connect actionlistener as parameter this-->InstanceOfClass (ViewControl) where actionPerformed is implemented
            }
        }
        return(chessboardPanel);
    }
    private JPanel createInfoPanel() {      // make a seperate class ? 
        infoPanel = new JPanel();

        infoPanel.setLayout(new GridLayout(1,3));  
        JLabel whiteMsgLabel = new JLabel("White Info", SwingConstants.CENTER);
        JLabel toMoveLabel = new JLabel("To Move: White", SwingConstants.CENTER);
        JLabel blackMsgLabel = new JLabel("Black Info", SwingConstants.CENTER);

        infoPanel.add(whiteMsgLabel);
        infoPanel.add(toMoveLabel);
        infoPanel.add(blackMsgLabel);

        return infoPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ChessSquare clickedSquare = (ChessSquare) e.getSource();
        registerClick(clickedSquare);    
    }

    private void registerClick(ChessSquare clickedSquare) {
        if (clickedFirst == null){
            if (clickedSquare.getPiece()>=0 && (6*(moveNr % 2)<=clickedSquare.getPiece()) && (6*(moveNr % 2)+6>clickedSquare.getPiece())){
                clickedSquare.setClickedColor();

                // TBD: Check all possible moves with Model
                this.clickedFirst = clickedSquare;
                // TBD: remove eventual infoText
            } else {
                JLabel playerInfoLabel = (JLabel) infoPanel.getComponent(infoPanelMap.get(toMoveMap.get(moveNr % 2)));
                playerInfoLabel.setText("Error");   //TBC
            }
        } else {
            if (clickedSquare == clickedFirst) {
                clickedSquare.setBaseColor();
                this.clickedFirst = null;
            } else {
                //TBD
                // check if ok from model !!!
                // if ok from model --> move (as code below describes)
                this.clickedSecond = clickedSquare;
                this.clickedSecond.changePiece(this.clickedFirst.getPiece());
                
                this.clickedSecond.setBaseColor();
                this.clickedSecond = null;

                this.clickedFirst.setBaseColor();
                this.clickedFirst.removePiece();
                this.clickedFirst = null;

                this.moveNr++;
                // TBD:
                // remove eventual infoText 
                // add look for check
                // add if promotion
                
                // TBD:
                // if not approved:
                    // error message
            }
        }
    }
    public void updateToMove(){
        moveNr++;
        JLabel toMoveLabel = (JLabel) infoPanel.getComponent(infoPanelMap.get("toMove"));
        toMoveLabel.setText("To Move: "+toMoveMap.get(moveNr%2));
    }
    public static void main(String[] args) {
        new ViewControl();
    }
 }