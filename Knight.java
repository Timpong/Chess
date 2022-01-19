import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece{
    private static ArrayList<int[]> moves;
    static {
        moves = new ArrayList<>();
        moves.add(new int[]{1,2});
        moves.add(new int[]{1,-2});
        moves.add(new int[]{2,1});
        moves.add(new int[]{2,-1});
        moves.add(new int[]{-1,2});
        moves.add(new int[]{-1,-2});
        moves.add(new int[]{-2,1});
        moves.add(new int[]{-2,-1});
    }
    protected Knight(int r, int c) {
        super(r, c);
    }

    @Override
    public List<int[]> getPossibleMoves(Piece[][] chessBoard) {
        ArrayList<int[]> possibleMovesList = new ArrayList<>();
        for (int i=0; i<moves.size();i++){
            int r = row+moves.get(i)[0];
            int c = column+moves.get(i)[1];
            if (checkOnBoard(r, c) && checkCaptureablePiece(this, chessBoard[r][c])){
                possibleMovesList.add(new int[]{r,c});
            } 
        }
        return possibleMovesList;
    }
    
}
