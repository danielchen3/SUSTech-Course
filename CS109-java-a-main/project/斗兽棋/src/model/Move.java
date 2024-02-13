package model;

public class Move {
    public ChessboardPoint src;
    public ChessboardPoint dest;

    public PlayerColor color;
    public ChessPiece target;
    public ChessPiece mover;

    public ChessboardPoint getSrc() {
        return src;
    }

    public ChessboardPoint getDest() {
        return dest;
    }

    public PlayerColor getColor() {
        return color;
    }

    public ChessPiece getTarget() {
        return target;
    }

    public ChessPiece getMover() {
        return mover;
    }

    public Move(ChessboardPoint src, ChessboardPoint dest, PlayerColor color, ChessPiece mover, ChessPiece target) {
        this.src = src;
        this.dest = dest;
        this.color = color;
        this.mover = mover;
        this.target = target;
    }

    public Move(ChessboardPoint src, ChessboardPoint dest, PlayerColor color, ChessPiece mover) {
        this.src = src;
        this.dest = dest;
        this.color = color;
        this.mover = mover;
        this.target = null;
    }

    public String toString() {
        if (target == null)
            return (color == PlayerColor.BLUE ? "b " : "r ") + mover.getabbr()+ " " + "(" + src.getRow() + "," + src.getCol() + ") " + "to " + "(" + dest.getRow() + "," + dest.getCol() + ") ";
        else
            return (color == PlayerColor.BLUE ? "b " : "r ") + mover.getabbr() + " " + "(" + src.getRow() + "," + src.getCol() + ") " + "to " + "(" + dest.getRow() + "," + dest.getCol() + ") " + "eat " + target.getabbr();
    }
}
