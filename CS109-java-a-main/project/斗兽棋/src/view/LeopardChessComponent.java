package view;


import model.PlayerColor;

import javax.swing.*;
import java.awt.*;

/**
 * This is the equivalent of the ChessPiece class,
 * but this class only cares how to draw Chess on ChessboardComponent
 */
public class LeopardChessComponent extends ChessComponent {
    private PlayerColor owner;

    private boolean selected;

    public LeopardChessComponent(PlayerColor owner, int size) {
        this.owner = owner;
        this.selected = false;
        this.size = size;
        setSize(size/2, size/2);
        setLocation(0,0);
        setVisible(true);
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon picture = new ImageIcon("resource\\chesspiece\\blueleopard.png");
        if (owner == PlayerColor.RED){
            picture = new ImageIcon("resource\\chesspiece\\redleopoard.png");
        }
        Image image = picture.getImage();
        picture = new ImageIcon(image.getScaledInstance(size,size,Image.SCALE_SMOOTH));
        JLabel label = new JLabel(picture);
        label.setSize(size, size);
        add(label);
        }

}
