package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class store the real chess information.
 * The Chessboard has 9*7 cells, and each cell has a position for chess
 */
public class Chessboard {
    public Cell[][] grid;
    public List<ChessPiece> blueout = new ArrayList<>();
    public List<ChessPiece> redout = new ArrayList<>();
    public ArrayList<Move> action = new ArrayList<>();

    public Chessboard() {
        this.grid =
                new Cell[Constant.CHESSBOARD_ROW_SIZE.getNum()][Constant.CHESSBOARD_COL_SIZE.getNum()];//19X19

        initGrid();
        initPieces();
    }

    public void initGrid() {
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                grid[i][j] = new Cell();
            }
        }
    }

    public void initPieces() {
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                grid[i][j].removePiece();
            }
        }
        grid[2][6].setPiece(new ChessPiece(PlayerColor.RED, "Elephant", 8));
        grid[6][0].setPiece(new ChessPiece(PlayerColor.BLUE, "Elephant", 8));
        grid[0][0].setPiece(new ChessPiece(PlayerColor.RED, "Lion", 7));
        grid[8][6].setPiece(new ChessPiece(PlayerColor.BLUE, "Lion", 7));
        grid[0][6].setPiece(new ChessPiece(PlayerColor.RED, "Tiger", 6));
        grid[8][0].setPiece(new ChessPiece(PlayerColor.BLUE, "Tiger", 6));
        grid[2][2].setPiece(new ChessPiece(PlayerColor.RED, "Leopard", 5));
        grid[6][4].setPiece(new ChessPiece(PlayerColor.BLUE, "Leopard", 5));
        grid[2][4].setPiece(new ChessPiece(PlayerColor.RED, "Wolf", 4));
        grid[6][2].setPiece(new ChessPiece(PlayerColor.BLUE, "Wolf", 4));
        grid[1][1].setPiece(new ChessPiece(PlayerColor.RED, "Dog", 3));
        grid[7][5].setPiece(new ChessPiece(PlayerColor.BLUE, "Dog", 3));
        grid[1][5].setPiece(new ChessPiece(PlayerColor.RED, "Cat", 2));
        grid[7][1].setPiece(new ChessPiece(PlayerColor.BLUE, "Cat", 2));
        grid[2][0].setPiece(new ChessPiece(PlayerColor.RED, "Rat", 1));
        grid[6][6].setPiece(new ChessPiece(PlayerColor.BLUE, "Rat", 1));

    }

    public ChessPiece getChessPieceAt(ChessboardPoint point) {
        return getGridAt(point).getPiece();
    }

    private Cell getGridAt(ChessboardPoint point) {
        return grid[point.getRow()][point.getCol()];
    }

    private int calculateDistance(ChessboardPoint src, ChessboardPoint dest) {
        return Math.abs(src.getRow() - dest.getRow()) + Math.abs(src.getCol() - dest.getCol());
    }

    private ChessPiece removeChessPiece(ChessboardPoint point) {
        ChessPiece chessPiece = getChessPieceAt(point);
        getGridAt(point).removePiece();
        return chessPiece;
    }

    private void setChessPiece(ChessboardPoint point, ChessPiece chessPiece) {
        getGridAt(point).setPiece(chessPiece);
        if (enmytrap(point, chessPiece.getOwner())) {
            chessPiece.rank = 0;
        } else {
            if (chessPiece.getName().equals("Elephant")) {
                chessPiece.rank = 8;
            }
            if (chessPiece.getName().equals("Lion")) {
                chessPiece.rank = 7;
            }
            if (chessPiece.getName().equals("Tiger")) {
                chessPiece.rank = 6;
            }
            if (chessPiece.getName().equals("Leopard")) {
                chessPiece.rank = 5;
            }
            if (chessPiece.getName().equals("Wolf")) {
                chessPiece.rank = 4;
            }
            if (chessPiece.getName().equals("Dog")) {
                chessPiece.rank = 3;
            }
            if (chessPiece.getName().equals("Cat")) {
                chessPiece.rank = 2;
            }
            if (chessPiece.getName().equals("Rat")) {
                chessPiece.rank = 1;
            }

        }
    }

    public void moveChessPiece(ChessboardPoint src, ChessboardPoint dest) {

        if (!isValidMove(src, dest)) {
            throw new IllegalArgumentException("Illegal chess move!");
        }
        ChessPiece chess = removeChessPiece(src);
        setChessPiece(dest, chess);
        Move mov1 = new Move(src, dest, chess.getOwner(), chess);
        action.add(mov1);

    }

    public void captureChessPiece(ChessboardPoint src, ChessboardPoint dest) {
        if (!isValidCapture(src, dest)) {
            throw new IllegalArgumentException("Illegal chess capture!");
        }
        ChessPiece capturer = removeChessPiece(src);
        ChessPiece captured = removeChessPiece(dest);
        setChessPiece(dest, capturer);
        if (Objects.equals(captured.getOwner(), PlayerColor.RED)) {
            redout.add(captured);
        } else {
            blueout.add(captured);
        }
        Move newmov = new Move(src, dest, capturer.getOwner(), captured);
        action.add(newmov);
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public PlayerColor getChessPieceOwner(ChessboardPoint point) {
        return getGridAt(point).getPiece().getOwner();
    }

    public boolean isValidMove(ChessboardPoint src, ChessboardPoint dest) {
        ChessPiece selectchess = getChessPieceAt(src);
        if ((getChessPieceAt(dest) != null) || selectchess == null) {
            return false;
        }
        if (Owndens(dest, selectchess.getOwner())) {
            return false;
        }
        if (selectchess.getName().equals("Elephant")) {
            if (calculateDistance(src, dest) == 1 && !isRiver(dest)) {
                return true;
            } else return false;

        }
        if (selectchess.getName().equals("Lion")) {
            if (!isRiver(dest) && calculateDistance(src, dest) == 1 || JumpRiver(src, dest)) {
                return true;
            } else return false;
        }
        if (selectchess.getName().equals("Wolf")) {
            if (calculateDistance(src, dest) == 1 && !isRiver(dest)) {
                return true;
            } else return false;
        }
        if (selectchess.getName().equals("Tiger")) {
            if (!isRiver(dest) && calculateDistance(src, dest) == 1 || JumpRiver(src, dest)) {
                return true;
            } else return false;
        }
        if (selectchess.getName().equals("Dog")) {
            if (calculateDistance(src, dest) == 1 && !isRiver(dest)) {
                return true;
            } else return false;
        }
        if (selectchess.getName().equals("Cat")) {
            if (calculateDistance(src, dest) == 1 && !isRiver(dest)) {
                return true;
            } else return false;
        }
        if (selectchess.getName().equals("Leopard")) {
            if (!isRiver(dest) && calculateDistance(src, dest) == 1) {
                return true;
            } else return false;
        }
        if (selectchess.getName().equals("Rat")) {
            boolean judge = false;
            if (calculateDistance(src, dest) == 1) {
                judge = true;
            }
            return judge;
        }
        return false;
    }

    public void reversemoveChessPiece(ChessboardPoint src, ChessboardPoint dest) {
        ChessPiece chess = removeChessPiece(dest);
        setChessPiece(src, chess);
    }

    public void reversecaptureChessPiece(ChessboardPoint src, ChessboardPoint dest) {
        ChessPiece capturer = removeChessPiece(dest);
        setChessPiece(src, capturer);
        if (capturer.getOwner().equals(PlayerColor.BLUE)) {
            setChessPiece(dest, redout.get(redout.size() - 1));
            redout.remove(redout.size() - 1);
        }
        if (capturer.getOwner().equals(PlayerColor.RED)) {
            setChessPiece(dest, blueout.get(blueout.size() - 1));
            blueout.remove(blueout.size() - 1);
        }
    }

    public boolean isValidCapture(ChessboardPoint src, ChessboardPoint dest) {
        ChessPiece capturer = getChessPieceAt(src);
        ChessPiece captured = getChessPieceAt(dest);
        if (capturer == null || captured == null) {
            return false;
        }
        if (capturer.getOwner() == captured.getOwner()) {
            return false;
        }
        boolean judge1 = calculateDistance(src, dest) == 1;
        if (capturer.getName().equals("Elephant")) {
            boolean judge2 = (!isRiver(dest)) && (captured.rank != 1);
            return judge2 && judge1;
        }
        if (capturer.getName().equals("Lion")) {
            if ((judge1 && !isRiver(dest)) || JumpRiver(src, dest) && captured.rank <= 7) {
                return true;
            } else return false;
        }
        if (capturer.getName().equals("Leopard")) {
            if (judge1 && captured.rank <= 5 && !isRiver(dest)) {
                return true;
            } else return false;
        }
        if (capturer.getName().equals("Tiger")) {
            if (captured.rank <= 6 && ((!isRiver(dest) && judge1) || JumpRiver(src, dest))) {
                return true;
            } else return false;
        }
        if (capturer.getName().equals("Rat")) {
            boolean judge2 = captured.rank == 1 || captured.rank == 8 || captured.rank == 0;
            boolean judge3 = !(isRiver(src) && !isRiver(dest));
            if (judge3 && judge2 && judge1) {
                return true;
            } else return false;
        }
        if (capturer.getName().equals("Wolf")) {
            if (judge1 && !isRiver(dest) && captured.rank <= 4) {
                return true;
            } else return false;

        }
        if (capturer.getName().equals("Dog")) {
            if (judge1 && captured.rank <= 3 && !isRiver(dest)) {
                return true;
            } else return false;
        }
        if (capturer.getName().equals("Cat")) {
            if (judge1 && captured.rank <= 2 && !isRiver(dest)) {
                return true;
            } else return false;
        }

        return false;
    }


    private boolean isRiver(ChessboardPoint coordiante) {
        boolean judge1 = coordiante.getRow() <= 5 && coordiante.getRow() >= 3;
        boolean judge2 = coordiante.getCol() == 2 || coordiante.getCol() == 1 || coordiante.getCol() == 5 || coordiante.getCol() == 4;
        if (judge2 && judge1) {
            return true;
        } else return false;

    }

    private boolean Owndens(ChessboardPoint coordinate, PlayerColor color) {
        if (color == PlayerColor.RED) {
            boolean judge = false;
            if (coordinate.getRow() == 0 && coordinate.getCol() == 3) {
                judge = true;
            }
            return judge;
        } else {

            boolean judge = false;
            if (coordinate.getRow() == 8 && coordinate.getCol() == 3) {
                judge = true;
            }
            return judge;
        }
    }

    private boolean enmytrap(ChessboardPoint coordinate, PlayerColor color) {
        if (color == PlayerColor.BLUE) {
            boolean judge = false;
            if (coordinate.getRow() == 1 && coordinate.getCol() == 3 || (coordinate.getRow() == 0 && coordinate.getCol() == 2) || (coordinate.getRow() == 0 && coordinate.getCol() == 4)) {
                judge = true;
            }
            return judge;
        } else {
            boolean judge1 = coordinate.getCol() == 3 && coordinate.getRow() == 7;
            boolean judge2 = coordinate.getRow() == 8 && coordinate.getCol() == 4;
            boolean judge3 = coordinate.getCol() == 2 && coordinate.getRow() == 8;
            if (judge2 || judge3 || judge1) {
                return true;
            } else return false;
        }
    }

    private boolean JumpRiver(ChessboardPoint src, ChessboardPoint dest) {
        boolean situation1 = (src.getRow() == 3 && dest.getRow() == 3 && src.getCol() == 0 && dest.getCol() == 3) || (src.getRow() == 3 && src.getCol() == 3 && dest.getRow() == 3 && dest.getCol() == 0);
        boolean situation2 = (src.getRow() == 4 && src.getCol() == 0 && dest.getRow() == 4 && dest.getCol() == 3) || (src.getRow() == 4 && src.getCol() == 3 && dest.getRow() == 4 && dest.getCol() == 0);
        boolean situation3 = (src.getRow() == 5 && src.getCol() == 0 && dest.getRow() == 5 && dest.getCol() == 3) || (src.getRow() == 5 && src.getCol() == 3 && dest.getRow() == 5 && dest.getCol() == 0);
        boolean situation4 = (src.getRow() == 3 && src.getCol() == 3 && dest.getRow() == 3 && dest.getCol() == 6) || (src.getRow() == 3 && src.getCol() == 6 && dest.getRow() == 3 && dest.getCol() == 3);
        boolean situation5 = (src.getRow() == 4 && src.getCol() == 3 && dest.getRow() == 4 && dest.getCol() == 6) || (src.getRow() == 4 && src.getCol() == 6 && dest.getRow() == 4 && dest.getCol() == 3);
        boolean situation6 = (src.getRow() == 5 && src.getCol() == 3 && dest.getRow() == 5 && dest.getCol() == 6) || (src.getRow() == 5 && src.getCol() == 6 && dest.getRow() == 5 && dest.getCol() == 3);
        boolean situation7 = (src.getRow() == 2 && src.getCol() == 1 && dest.getRow() == 6 && dest.getCol() == 1) || (src.getRow() == 6 && src.getCol() == 1 && dest.getRow() == 2 && dest.getCol() == 1);
        boolean situation8 = (src.getRow() == 2 && src.getCol() == 2 && dest.getRow() == 6 && dest.getCol() == 2) || (src.getRow() == 6 && src.getCol() == 2 && dest.getRow() == 2 && dest.getCol() == 2);
        boolean situation9 = (src.getRow() == 2 && src.getCol() == 4 && dest.getRow() == 6 && dest.getCol() == 4) || (src.getRow() == 6 && src.getCol() == 4 && dest.getRow() == 2 && dest.getCol() == 4);
        boolean situation10 = (src.getRow() == 2 && src.getCol() == 5 && dest.getRow() == 6 && dest.getCol() == 5) || (src.getRow() == 6 && src.getCol() == 5 && dest.getRow() == 2 && dest.getCol() == 5);
        if (situation1) {
            if (getChessPieceAt(new ChessboardPoint(3, 1)) == null && getChessPieceAt(new ChessboardPoint(3, 2)) == null) {
                return true;
            } else return false;
        }
        if (situation2) {
            if (getChessPieceAt(new ChessboardPoint(4, 1)) == null && getChessPieceAt(new ChessboardPoint(4, 2)) == null) {
                return true;
            } else return false;
        }
        if (situation3) {
            if (getChessPieceAt(new ChessboardPoint(5, 1)) == null && getChessPieceAt(new ChessboardPoint(5, 2)) == null) {
                return true;
            } else return false;
        }
        if (situation4) {
            if (getChessPieceAt(new ChessboardPoint(3, 4)) == null && getChessPieceAt(new ChessboardPoint(3, 5)) == null) {
                return true;
            } else return false;
        }
        if (situation5) {
            if (getChessPieceAt(new ChessboardPoint(4, 4)) == null && getChessPieceAt(new ChessboardPoint(4, 5)) == null) {
                return true;
            } else return false;
        }
        if (situation6) {
            if (getChessPieceAt(new ChessboardPoint(5, 4)) == null && getChessPieceAt(new ChessboardPoint(5, 5)) == null) {
                return true;
            } else return false;
        }

        if (situation7) {
            if (getChessPieceAt(new ChessboardPoint(3, 1)) == null && getChessPieceAt(new ChessboardPoint(5, 1)) == null && getChessPieceAt(new ChessboardPoint(4, 1)) == null) {
                return true;
            } else return false;
        }
        if (situation8) {
            if (getChessPieceAt(new ChessboardPoint(3, 2)) == null && getChessPieceAt(new ChessboardPoint(4, 2)) == null) {
                return true;
            } else return false;
        }
        if (situation9) {
            if (getChessPieceAt(new ChessboardPoint(3, 4)) == null && getChessPieceAt(new ChessboardPoint(4, 4)) == null & getChessPieceAt(new ChessboardPoint(5, 4)) == null) {
                return true;
            } else return false;
        }
        if (situation10) {
            if (getChessPieceAt(new ChessboardPoint(3, 5)) == null && getChessPieceAt(new ChessboardPoint(4, 5)) == null && getChessPieceAt(new ChessboardPoint(5, 5)) == null) {
                return true;
            } else return false;
        }
        return false;
    }
}
