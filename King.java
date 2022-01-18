import java.util.ArrayList;
import java.util.List;

public class King extends Piece{
    static ArrayList<int[]> moves;
    static {
        moves = new ArrayList<>();
        moves.add(new int[]{1,1});
        moves.add(new int[]{1,-1});
        moves.add(new int[]{-1,1});
        moves.add(new int[]{-1,-1});
        moves.add(new int[]{0,1});
        moves.add(new int[]{0,-1});
        moves.add(new int[]{1,0});
        moves.add(new int[]{-1,0});
    }
    
    protected King(int r, int c) {
        super(r, c);
    }

    @Override
    public List<int[]> getPossibleMoves(Piece[][] chessBoard) {
        ArrayList<int[]> possibleMovesList = new ArrayList<>();

        for (int i=0;i<moves.size();i++){
            int rowDirection = moves.get(i)[0];
            int columnDirection = moves.get(i)[1]; 

            int currentRow = row+rowDirection;
            int currentColumn = column+columnDirection;

            if (checkOnBoard(currentRow, currentColumn) && checkCaptureablePiece(this, chessBoard[currentRow][currentColumn])){
                    possibleMovesList.add(new int[]{currentRow, currentColumn});
            }
        }
        return possibleMovesList;
    }
    
}
