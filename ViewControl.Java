import java.util.*;
import java.awt.*;
import javax.swing.*;

public class ViewControl extends JFrame{

    private JButton[][] chessboardGridButtons = new JButton[8][8];

    public ViewControl() {
        setLayout(new GridLayout(2,1));
        //chessboardGridPanel will hold the chessboard
        JPanel chessboardPanel = new JPanel();   
        chessboardPanel.setLayout(new GridLayout(8,8));  //will be (8,8)
        
        for (int r=0; r<8; r++){
            for (int c=0; c<8; c++){
                chessboardGridButtons[r][c] = new JButton();
                // logic for blackwhite grid
                if ((r+c)%2==0){
                    chessboardGridButtons[r][c].setBackground(Color.black);
                    //chessboardGridButtons[r][c].setForeground(Color.black);
                    chessboardGridButtons[r][c].setOpaque(true);
                    //chessboardGridButtons[r][c].setText("r:"+Integer.toString(r)+", c:"+Integer.toString(c));
                } else {
                    chessboardGridButtons[r][c].setBackground(Color.white);
                    //chessboardGridButtons[r][c].setForeground(Color.white);
                    chessboardGridButtons[r][c].setOpaque(true);
                    //chessboardGridButtons[r][c].setText("r:"+Integer.toString(r)+", c:"+Integer.toString(c));
                }

                if(c==1){
                    chessboardGridButtons[r][c].setIcon(new ImageIcon("./ChessPieceImages/WhitePawnCopy.png"));
                }
                chessboardPanel.add(chessboardGridButtons[r][c]);
            }
        }
        //will be 
        //JLabel chessboardGridPanel = new JLabel("Chessboard", JLabel.CENTER);
        //chessboardPanel.add(chessboardGridPanel);
        add(chessboardPanel);   //adds on the main JFram grid


        JPanel infoPanel = new JPanel();
        // WhiteMsg     -    White/Black to move    -   BlackMsg
        infoPanel.setLayout(new GridLayout(1,3));
        JLabel whiteMsgLabel = new JLabel("White Msg", JLabel.CENTER);
        JLabel toMoveLabel = new JLabel("W/B to move", JLabel.CENTER);
        JLabel blackMsgLabel = new JLabel("Black Msg", JLabel.CENTER);

        infoPanel.add(whiteMsgLabel);
        infoPanel.add(toMoveLabel);
        infoPanel.add(blackMsgLabel);

        add(infoPanel);

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new ViewControl();
        //Small testcomment
    }
 }