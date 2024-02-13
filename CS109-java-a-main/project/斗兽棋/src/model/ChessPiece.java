package model;


public class ChessPiece {
    // the owner of the chess
    private PlayerColor owner;

    // Elephant? Cat? Dog? ...
    private String name;
    int rank;

    public int getRank() {
        return rank;
    }

    public ChessPiece(PlayerColor owner, String name, int rank) {
        this.owner = owner;
        this.name = name;
        this.rank = rank;
    }

    /*public boolean canCapture(ChessPiece target) {
        if (!target.getOwner().equals(this.getOwner()) && target.rank < this.rank) {
            return true;
        }
        return false;
    }*/
    public String getabbr() {
        if (this.getName() == null) return "*";
        else if (this.getName().equals("Elephant")) return "E";
        else if (this.getName().equals("Lion")) return "L";
        else if (this.getName().equals("Tiger")) return "T";
        else if (this.getName().equals("Leopard")) return "l";
        else if (this.getName().equals("Wolf")) return "w";
        else if (this.getName().equals("Dog")) return "d";
        else if (this.getName().equals("Cat")) return "c";
        else if (this.getName().equals("Rat")) return "r";
        else return "";
    }

    public String getName() {
        return name;
    }

    public PlayerColor getOwner() {

        return owner;

    }
}
