import controller.GameController;
import model.Chessboard;
import view.BeginComponent;
import model.music;
import view.ChessGameFrame;
import view.Register;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Register register=new Register();
            register.setVisible(true);
     new music().musicplay("resource\\Music\\贵族音乐ASMR-森林-_鸟鸣丶ASMR大自然白噪音_.wav");
        });
    }
}
