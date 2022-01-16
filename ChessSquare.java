import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;

import javax.swing.JButton;

public class ChessSquare extends JButton{
    private int row;
    private int column;
    private Color baseColor;
    private Color currentColor;
    private int piece = -1;

    private static HashMap<Integer, String> pieceIconMap;   
    static {
        pieceIconMap = new HashMap<Integer, String>();
        pieceIconMap.put(0, "./ChessPieceImages/WhitePawn.png");
        pieceIconMap.put(1, "./ChessPieceImages/WhiteKnight.png");
        pieceIconMap.put(2, "./ChessPieceImages/WhiteBishop.png");
        pieceIconMap.put(3, "./ChessPieceImages/WhiteTower.png");
        pieceIconMap.put(4, "./ChessPieceImages/WhiteQueen.png");
        pieceIconMap.put(5, "./ChessPieceImages/WhiteKing.png");
        pieceIconMap.put(6, "./ChessPieceImages/BlackPawn.png");
        pieceIconMap.put(7, "./ChessPieceImages/BlackKnight.png");
        pieceIconMap.put(8, "./ChessPieceImages/BlackBishop.png");
        pieceIconMap.put(9, "./ChessPieceImages/BlackTower.png");
        pieceIconMap.put(11, "./ChessPieceImages/BlackQueen.png");
        pieceIconMap.put(10, "./ChessPieceImages/BlackKing.png");
    }


    public ChessSquare(int r, int c) {
        this.row = r;
        this.column = c;
        setBaseColor();
        setStartPiece();
        setStartIcon();
        setBorder(null);
    }
    private void setBaseColor(){
        
        if ((this.row+this.column)%2==0){
            this.baseColor = Color.gray;
        } else {
            this.baseColor = Color.white; 
        } 
        setBackground(this.baseColor);
        setOpaque(true);
    }
    private void setStartPiece(){
        if (this.column==0 || this.column==1){
            this.piece = 0;
        } 
        if(this.column==6 || this.column==7){
            this.piece = 6;
        }
        if (this.column==1 || this.column==6){
            this.piece += 0;
        } else if (this.column==0 || this.column==7){
            if(this.row==0 || this.row==7){
                this.piece += 3;
            }else if(this.row==1 || this.row==6){
                this.piece += 1;
            }else if(this.row==2 || this.row==5){
                this.piece += 2;
            }else if(this.row==3){
                this.piece += 4;
            }else if(this.row==4){
                this.piece += 5;
            }
        }
    }
    private void setStartIcon() {
        if (this.piece!=-1){
            setIcon(new ImageIcon (pieceIconMap.get(this.piece)));
        }
    }
    public int getRow(){
        return this.row;
    }
    public int getColumn(){
        return this.column;
    }

    // public int[][] getCoordinates() {
    //     //return coordinates
    // }

    // public boolean changeIcon(int pVal) {
    //     // for change of icon
    // }
    // public boolean changeColor(Color c){
    //     // for change of color. TBD if full color change or just the borders.
    // }
}
