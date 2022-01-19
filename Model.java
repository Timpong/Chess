import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Model {
    protected Piece[][] chessBoard = new Piece[8][8];
    private int moveNr = 0;

    public Model(){
        for (int r=0;r<8;r++){
            for (int c=0; c<8; c++){
                chessBoard[r][c]=Piece.create(r, c);
            }
        }

    }

    private int toMoveColor(){
        return (moveNr % 2);
    }
    public boolean checkPlayersPiece(int row, int column){
        Piece piece = chessBoard[row][column];
        return (piece != null && piece.getColor()==toMoveColor());
    }
    public List<int[]> possibleMoves(int row, int col){
        return chessBoard[row][col].getPossibleMoves(chessBoard);
    }
    private void updateModelChessboard(int startRow, int startColumn, int targetRow, int targetColumn){
        chessBoard[targetRow][targetColumn] = chessBoard[startRow][startColumn];
        chessBoard[startRow][startColumn] = null;
        chessBoard[targetRow][targetColumn].updateCoordinates(targetRow, targetColumn);
    }
    public boolean movePiece(int startRow, int startColumn, int targetRow, int targetColumn){
        List<int[]> moves = possibleMoves(startRow, startColumn);

        int[] moveTo = new int[]{targetRow, targetColumn};
        Iterator<int[]> movesIt = moves.iterator();

        while (movesIt.hasNext()){
            if(Arrays.equals(movesIt.next(), moveTo)){
                updateModelChessboard(startRow, startColumn, targetRow, targetColumn);
                return true;
            }
        } 
        return false;
    }
    public static void main(String[] args) {
        // FOR TESTING
        Model test = new Model();
        Piece[][] chessBoard = test.chessBoard;
        int row = 1;
        int column = 1;
        System.out.println("first klick ok: "+test.checkPlayersPiece(row, column));
        List<int[]> moves = test.possibleMoves(row, column);
        for (int j=0; j<moves.size();j++){
            System.out.println(Arrays.toString(moves.get(j)));
        }
        System.out.println("move ok: "+test.movePiece(row, column, row, column+1));
        System.out.println(chessBoard[row][column]);
        System.out.println(chessBoard[row][column+1].getClass());

        
        

        // System.out.println(test.firstClick(1, 1));

        // System.out.println(test.chessBoard[0][1].getClass());
        // System.out.println(test.chessBoard[0][1].getPossibleMoves(test.chessBoard));
        // List<int[]> moves = test.chessBoard[0][1].getPossibleMoves(test.chessBoard);
        // System.out.println(Arrays.toString(moves.get(0)));
        // System.out.println(Arrays.toString(moves.get(1)));
    }
}
