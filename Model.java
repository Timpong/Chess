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
        List<int[]> moves = test.chessBoard[1][0].getPossibleMoves(test.chessBoard);
        System.out.println(Arrays.toString(moves.get(0)));
        System.out.println(Arrays.toString(moves.get(1)));
    }
}
