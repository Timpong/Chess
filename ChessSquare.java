import javax.swing.Icon;
import java.awt.Color;
import javax.swing.JButton;

public class ChessSquare extends JButton{
    private int row;
    private int column;
    private Color color;
    private Icon pieceIcon;
    private int pieceKey;


    public ChessSquare(int row, int column) {

        // implement initial setup of pieces etc given coordinates
    }

    public int[][] getCoordinates() {
        //return coordinates
    }

    public boolean changeIcon(int pVal, Icon pPic) {
        // for change of icon
    }
    public boolean changeColor(Color c){
        // for change of color. TBD if full color change or just the borders.
    }
}
