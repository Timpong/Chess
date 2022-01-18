import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece{

    protected Pawn(int r, int c) {
        super(r, c);
    }

    @Override
    public List<int[]> getPossibleMoves(Piece[][] chessBoard) {
        ArrayList<int[]> possibleMovesList = new ArrayList<>();
        int direction = 1 -(2*color); 
        
        if (column == 0 || column ==7){
            return possibleMovesList;
        } 
        
        if (chessBoard[row][column+direction] ==null ){
            possibleMovesList.add(new int[]{row, column+direction});
            if (column==1+5*color && chessBoard[row][column+direction*2]==null){
                possibleMovesList.add(new int[]{row, column+direction*2});
            }
        } 
        for (int i=1; i>=-1; i=i-2){
            if (row != (int) 3.5-3.5*i && chessBoard[row+i][column+direction]!= null && chessBoard[row+i][column+direction].getColor()!= color){
                possibleMovesList.add(new int[]{row+i, column+direction});
        }
        }
        return possibleMovesList;
    }    
}
