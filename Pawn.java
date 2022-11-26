import java.util.ArrayList;
import java.util.ArrayList;

public class Pawn extends Piece {

    protected Pawn(int r, int c) {
        super(r, c);
    }

    @Override
    public ArrayList<int[]> getPossibleMoves(Piece[][] chessBoard) {
        ArrayList<int[]> possibleMovesList = new ArrayList<>();
        int direction = 1 - (2 * color);

        if (column == 0 || column == 7) { // Useless as they will always promote?
            return possibleMovesList;
        }

        if (checkEmpty(chessBoard[row][column + direction])) {
            possibleMovesList.add(new int[] { row, column + direction });

            int startColumn = 1 + 5 * color;
            if (column == startColumn && checkEmpty(chessBoard[row][column + direction * 2])) {
                possibleMovesList.add(new int[] { row, column + direction * 2 });
            }
        }

        for (int i = 1; i >= -1; i = i - 2) {
            if (checkOnBoard(row + i, column + direction) && !checkEmpty(chessBoard[row + i][column + direction])
                    && chessBoard[row + i][column + direction].getColor() != color) {
                possibleMovesList.add(new int[] { row + i, column + direction });
            }
        }
        return possibleMovesList;
    }
}
