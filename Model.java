import java.lang.ProcessBuilder.Redirect.Type;
import java.util.Arrays;
import java.util.ArrayList;

public class Model {
    private Piece[][] chessBoard = new Piece[8][8];
    private Piece[] kings = new Piece[2];

    public Model() {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                chessBoard[r][c] = Piece.create(r, c);
            }
        }
        kings[0] = chessBoard[4][0];
        kings[1] = chessBoard[4][7];
    }

    public Boolean checkFirstClick(int row, int column) {
        return true;
    }

    public ArrayList<int[]> getPossibleMoves(int row, int column) {
        return chessBoard[row][column].getPossibleMoves(chessBoard);
    }

    public boolean checkAllowedMove(int rowFrom, int columnFrom, int rowTarget, int columnTarget) {
        ArrayList<int[]> allowedMoves = getPossibleMoves(rowFrom, columnFrom);
        for (int i = 0; i < allowedMoves.size(); i++) {
            if (rowTarget == allowedMoves.get(i)[0] && columnTarget == allowedMoves.get(i)[1]) {
                updateBoard(rowFrom, columnFrom, rowTarget, columnTarget);
                return true;
            }
        }
        return false;
    }

    private void updateBoard(int rowFrom, int columnFrom, int rowTarget, int columnTarget) {
        chessBoard[rowFrom][columnFrom].updateCoordinates(rowTarget, columnTarget);
        chessBoard[rowTarget][columnTarget] = chessBoard[rowFrom][columnFrom];
        chessBoard[rowFrom][columnFrom] = null;
        // TODO: see if the game is won
    }

    public boolean checkInCheck(int colorInCheck) {
        System.out.println("In Check!");

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if ((chessBoard[r][c] != null) && chessBoard[r][c].getColor() != colorInCheck) {
                    ArrayList<int[]> allowedMoves = getPossibleMoves(r, c);
                    for (int i = 0; i < allowedMoves.size(); i++) {
                        if (kings[colorInCheck].getRow() == allowedMoves.get(i)[0]
                                && kings[colorInCheck].getColumn() == allowedMoves.get(i)[1]) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean checkPromotion(int row, int column) {
        if ((column == 0 || column == 7) && chessBoard[row][column] instanceof Pawn) {
            chessBoard[row][column] = chessBoard[row][column].promoteToQueen();
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Integer> getKingsCoordinates(int color) {
        ArrayList<Integer> coordinates = new ArrayList<>();

        coordinates.add(kings[color].getRow());
        coordinates.add(kings[color].getColumn());

        return coordinates;
    }

    public static void main(String[] args) {
        // FOR TESTING
        Model test = new Model();
        // System.out.println(test.chessBoard[0][1].getClass());
        // System.out.println(test.chessBoard[0][1].getPossibleMoves(test.chessBoard));
        for (int i = 0; i < 8; i++) {
            ArrayList<int[]> moves = test.chessBoard[i][0].getPossibleMoves(test.chessBoard);
            for (int j = 0; j < moves.size(); j++) {
                System.out.println(Arrays.toString(moves.get(j)));
            }
            System.out.println();
        }
        // List<int[]> moves = test.chessBoard[0][1].getPossibleMoves(test.chessBoard);
        // System.out.println(Arrays.toString(moves.get(0)));
        // System.out.println(Arrays.toString(moves.get(1)));
    }
}
