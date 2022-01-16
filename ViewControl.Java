import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.*;

public class ViewControl extends JFrame implements ActionListener{

    private JButton[][] chessboardGridButtons = new JButton[8][8];
    // private JButton clickedButtonFirst;
    // private JButton clickedButtonSecond;
    private ChessSquare clickedButtonFirst;
    private ChessSquare clickedButtonSecond;

    private int moveNr = 0;         // only incremented as the model gives a correctMove response 
    private int colorToMove = 0;    // {0: white, 1: black}     alt moveNr and keep track of colorToMove := moveNr % 2  

    // static directroies:         - allowing for the chessSquares to only 
    //      {0: white pawn, 1: white knight, 2:white bishop, 3: white rook, 4: white queen, 5: white king}
    //      {6: black pawn, 7: black knight, 8:black bishop, 9: black rook, 10: black queen, 11: black king}
    //
    //      {0: WHITE, 1: BLACK} 

    public ViewControl() {
        super("Chess");
        setLayout(new GridLayout(2,1));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        add(createChessboardGrid());
        add(createInfoPanel());

        pack();
        setVisible(true);
    }
    private JPanel createChessboardGrid(){
        JPanel chessboardPanel = new JPanel();   
        chessboardPanel.setLayout(new GridLayout(8,8));  //will be (8,8)
        
        for (int r=0; r<8; r++){
            for (int c=0; c<8; c++){
                chessboardGridButtons[r][c] = new ChessSquare(r,c);
                chessboardGridButtons[r][c].addActionListener(this);
                chessboardPanel.add(chessboardGridButtons[r][c]);
            }
        }
        return(chessboardPanel);
    }
    private JPanel createInfoPanel() {
        JPanel infoPanel = new JPanel();

        infoPanel.setLayout(new GridLayout(1,3));   // WhiteMsg     -    White/Black to move    -   BlackMsg
        JLabel whiteMsgLabel = new JLabel("White Msg", JLabel.CENTER);
        JLabel toMoveLabel = new JLabel("W/B to move", JLabel.CENTER);
        JLabel blackMsgLabel = new JLabel("Black Msg", JLabel.CENTER);

        infoPanel.add(whiteMsgLabel);
        infoPanel.add(toMoveLabel);
        infoPanel.add(blackMsgLabel);

        return infoPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("click registererd");

        if (this.clickedButtonFirst==null){
            this.clickedButtonFirst = (ChessSquare) e.getSource();
            // FOR TESTING OF CHESSSQUARE FUNCTIONALITY
            // this.clickedButtonFirst.changeColor();
            // if (this.clickedButtonFirst.getPiece()==-1){
            //     this.clickedButtonFirst.changePiece(0);
            // }
            System.out.println("First click: r:"+ Integer.toString(this.clickedButtonFirst.getRow())+", c:"+Integer.toString(this.clickedButtonFirst.getColumn()));
        } else {
            this.clickedButtonSecond = (ChessSquare) e.getSource();
            System.out.println("Second click: r:"+ Integer.toString(this.clickedButtonSecond.getRow())+", c:"+Integer.toString(this.clickedButtonSecond.getColumn()));
        }
    }
    public static void main(String[] args) {
        new ViewControl();
        //Small testcomment
    }
 }