import java.awt.*;
import javax.swing.*;

public class ChessboardApp {

    private static final int BOARD_SIZE = 8;
    private static final int SQUARE_SIZE = 50;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Resizable Chessboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.WHITE);

        JPanel chessboardPanel = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE));

        // 创建棋盘格子
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                JPanel square = new JPanel();
                square.setBackground((row + col) % 2 == 0 ? Color.WHITE : Color.GRAY);
                chessboardPanel.add(square);
            }
        }

        frame.add(chessboardPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
