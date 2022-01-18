import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
    protected int row;
    protected int column;
    protected int color;

    protected Piece(int r, int c){
        row = r;
        column = c;
        
        if (column==0 || column==1){
            color = 0;
        } else if(column==6 || column==7){
            color = 1; 
        } 
    }
    public static Piece create(int startRow, int startColumn){
        if (startColumn==1 || startColumn==6){
            return new Pawn(startRow, startColumn);
        }
        if (startColumn==0 || startColumn==7){
            if(startRow==0 || startRow==7){
                return new Tower(startRow, startColumn);
            }else if(startRow==1 || startRow==6){
                return new Knight(startRow, startColumn);
            }else if(startRow==2 || startRow==5){
                return new Bishop(startRow, startColumn);
            }else if(startRow==3){
                return new Queen(startRow, startColumn);
            }else if(startRow==4){
                return new King(startRow, startColumn);
            }
        } else {
            return null;
        }
        ;
    }
    public int getColor(){
        return color;
    }
    public void updateCoordinates(int r, int c){
        this.row = r;
        this.column = c;
    }
    
    public abstract List<int[]> getPossibleMoves(Piece[][] chessBoard);
    // returns list of int[]{r,c} of possible moves
}
