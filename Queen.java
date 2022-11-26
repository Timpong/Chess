import java.util.ArrayList;
import java.util.ArrayList;

public class Queen extends Piece {
    static ArrayList<int[]> moves;
    static {
        moves = new ArrayList<>();
        moves.add(new int[] { 1, 1 });
        moves.add(new int[] { 1, -1 });
        moves.add(new int[] { -1, 1 });
        moves.add(new int[] { -1, -1 });
        moves.add(new int[] { 0, 1 });
        moves.add(new int[] { 0, -1 });
        moves.add(new int[] { 1, 0 });
        moves.add(new int[] { -1, 0 });
    }

    protected Queen(int r, int c) {
        super(r, c);
    }

    @Override
    public ArrayList<int[]> getPossibleMoves(Piece[][] chessBoard) {
        ArrayList<int[]> possibleMovesList = new ArrayList<>();

        for (int i = 0; i < moves.size(); i++) {
            int rowDirection = moves.get(i)[0];
            int columnDirection = moves.get(i)[1];
            for (int j = 1; j < 8; j++) {
                int currentRow = row + rowDirection * j;
                int currentColumn = column + columnDirection * j;

                if (checkOnBoard(currentRow, currentColumn)
                        && checkCaptureablePiece(this, chessBoard[currentRow][currentColumn])) {
                    possibleMovesList.add(new int[] { currentRow, currentColumn });
                    if (!checkEmpty(chessBoard[currentRow][currentColumn])) {
                        break;
                    }
                } else {
                    break;
                }
            }
        }

        return possibleMovesList;
    }

}
