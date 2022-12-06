import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.*;

public class ViewControl extends JFrame implements ActionListener {

    private ChessSquare[][] chessboardGridButtons = new ChessSquare[8][8];
    private JPanel infoPanel;

    private ChessSquare clickedFirst;
    private List<int[]> possibleMoves;
    private ChessSquare clickedSecond;

    private int moveNr = 0;

    private Model model = new Model();

    private static HashMap<Integer, String> piecesMap;
    static {
        piecesMap = new HashMap<>();
        piecesMap.put(0, "whitePawn");
        piecesMap.put(1, "whiteKnight");
        piecesMap.put(2, "whiteBishop");
        piecesMap.put(3, "whiteTower");
        piecesMap.put(4, "whiteQueen");
        piecesMap.put(5, "whiteKing");
        piecesMap.put(6, "blackPawn");
        piecesMap.put(7, "blackKnight");
        piecesMap.put(8, "blackBishop");
        piecesMap.put(9, "blackTower");
        piecesMap.put(10, "blackKing");
        piecesMap.put(11, "blackQueen");
    }
    private static HashMap<Integer, String> toMoveMap;
    static {
        toMoveMap = new HashMap<>();
        toMoveMap.put(0, "White");
        toMoveMap.put(1, "Black");
    }
    private static HashMap<String, Integer> infoPanelMap;
    static {
        infoPanelMap = new HashMap<>();
        infoPanelMap.put("toMove", 0);
        infoPanelMap.put("msg", 1);
    }

    public ViewControl() {
        super("Chess");
        setLayout(new BorderLayout(2, 1));
        setSize(new Dimension(700, 800));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        add(createChessboardGrid());
        add(BorderLayout.SOUTH, createInfoPanel());

        pack();
        setVisible(true);
    }

    private JPanel createChessboardGrid() { // make a seperate class ?
        JPanel chessboardPanel = new JPanel();
        chessboardPanel.setLayout(new GridLayout(8, 8));
        chessboardPanel.setPreferredSize(new Dimension(700, 700));
        chessboardPanel.setMinimumSize(new Dimension(700, 700));

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                chessboardGridButtons[r][c] = new ChessSquare(r, c);
                chessboardGridButtons[r][c].addActionListener(this);
                chessboardPanel.add(chessboardGridButtons[r][c]);
            }
        }
        return (chessboardPanel);
    } // LÄS PÅ: SwingUtilities.invokeLater(Runnable r)

    private JPanel createInfoPanel() { // make a seperate class ?
        infoPanel = new JPanel();
        infoPanel.setPreferredSize(new Dimension(700, 100));

        infoPanel.setLayout(new GridLayout(1, 2));
        JLabel msgLabel = new JLabel(" ", SwingConstants.CENTER);
        JLabel toMoveLabel = new JLabel("To Move: White", SwingConstants.CENTER);

        infoPanel.add(toMoveLabel);
        infoPanel.add(msgLabel);

        return infoPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ChessSquare clickedSquare = (ChessSquare) e.getSource();
        registerClick(clickedSquare);
    }

    private void registerClick(ChessSquare clickedSquare) {
        if (clickedFirst == null) {
            this.clickedFirst = clickedSquare;
            if (!(checkFirstClick())) {
                showErrorMessage();
                this.clickedFirst = null;
            } else {
                removeErrorMessage();
            }
        } else {
            if (clickedSquare == clickedFirst) {
                resetPossibleMoves();
                removeErrorMessage();
                this.clickedFirst = null;
            } else {
                this.clickedSecond = clickedSquare;
                if (checkSecondClick()) {
                    checkIfCheck();
                    checkIfpromotion();
                    this.clickedFirst = null;
                    this.clickedSecond = null;
                    removeErrorMessage();
                    updateToMove();
                } else {
                    showErrorMessage();
                    this.clickedSecond = null;
                }
            }
        }
    }

    public void updateToMove() {
        moveNr++;
        JLabel toMoveLabel = (JLabel) infoPanel.getComponent(infoPanelMap.get("toMove"));
        toMoveLabel.setText("To Move: " + toMoveMap.get(moveNr % 2));
    }

    public void showErrorMessage() {
        JLabel label = (JLabel) this.infoPanel.getComponent(infoPanelMap.get("msg"));
        label.setText("Incorrect action");
    }

    public void removeErrorMessage() {
        JLabel label = (JLabel) this.infoPanel.getComponent(infoPanelMap.get("msg"));
        label.setText("");
    }

    public boolean checkFirstClick() {
        boolean correctClick = clickedFirst.getPiece() >= 0 && (6 * (moveNr % 2) <= clickedFirst.getPiece())
                && (6 * (moveNr % 2) + 6 > clickedFirst.getPiece());
        if (correctClick
                && Boolean.TRUE.equals(model.checkFirstClick(clickedFirst.getRow(), clickedFirst.getColumn()))) {
            clickedFirst.setClickedColor();

            this.possibleMoves = model.getPossibleMoves(clickedFirst.getRow(), clickedFirst.getColumn());
            for (int j = 0; j < possibleMoves.size(); j++) {
                chessboardGridButtons[possibleMoves.get(j)[0]][possibleMoves.get(j)[1]].setPossibleColor();
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean checkSecondClick() {
        if (model.checkAllowedMove(clickedFirst.getRow(), clickedFirst.getColumn(), clickedSecond.getRow(),
                clickedSecond.getColumn())) {

            this.clickedSecond.changePiece(this.clickedFirst.getPiece());
            this.clickedFirst.removePiece();
            resetPossibleMoves();
            return true;
        } else {
            return false;
        }

    }

    public void resetPossibleMoves() {
        for (int j = 0; j < possibleMoves.size(); j++) {
            chessboardGridButtons[possibleMoves.get(j)[0]][possibleMoves.get(j)[1]].setBaseColor();
        }
        this.clickedFirst.setBaseColor();
        this.possibleMoves = null;
    }

    public void checkIfCheck() {
        ArrayList<Integer> king = model.getKingsCoordinates((moveNr + 1) % 2);
        if (model.checkInCheck((moveNr + 1) % 2)) {
            chessboardGridButtons[king.get(0)][king.get(1)].setCheckColor();
        } else {
            chessboardGridButtons[king.get(0)][king.get(1)].setBaseColor();
        }
    }

    public void checkIfpromotion() {
        if (model.checkPromotion(clickedSecond.getRow(), clickedSecond.getColumn())) {
            chessboardGridButtons[clickedSecond.getRow()][clickedSecond.getColumn()]
                    .changePiece(4 + clickedSecond.getPiece());
        }
    }

    public static void main(String[] args) {
        new ViewControl();
    }
}