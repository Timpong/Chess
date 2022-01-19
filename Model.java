import java.util.Arrays;
import java.util.List;

public class Model {
    public Piece[][] chessBoard = new Piece[8][8];
    
    public Model(){
        for (int r=0;r<8;r++){
            for (int c=0; c<8; c++){
                chessBoard[r][c]=Piece.create(r, c);
            }
        }

    }
    // public Model(ViewControl uiView){
    //     //TODO
    // }
    public static void main(String[] args) {
        // FOR TESTING
        Model test = new Model();
        // System.out.println(test.chessBoard[0][1].getClass());
        // System.out.println(test.chessBoard[0][1].getPossibleMoves(test.chessBoard));
        for (int i=0;i<8;i++){
            List<int[]> moves = test.chessBoard[i][0].getPossibleMoves(test.chessBoard);
            for (int j=0; j<moves.size();j++){
                System.out.println(Arrays.toString(moves.get(j)));
            }
            System.out.println();
        }
        // List<int[]> moves = test.chessBoard[0][1].getPossibleMoves(test.chessBoard);
        // System.out.println(Arrays.toString(moves.get(0)));
        // System.out.println(Arrays.toString(moves.get(1)));
    }
}
