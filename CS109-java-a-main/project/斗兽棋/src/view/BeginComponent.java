package view;

import controller.GameController;
import model.Chessboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class BeginComponent extends JFrame {
    private int h;
    private int a;
    ChessGameFrame gameFrame;
    GameController gameController;
    public JButton jButton = new JButton("Begin");
    public JButton jButton2 = new JButton("AI Mode");

    public BeginComponent() {
        this.a = 700;
        this.h = 700;
        setTitle("斗兽棋");
        setSize(a, h);
        String str = null;
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

       /* ChessGameFrame mainFrame = new ChessGameFrame(1100, 810);




        GameController gameController = new GameController(mainFrame.getChessboardComponent(), new Chessboard());
        this.gameFrame=mainFrame;
        this.gameController=gameController;*/
        addbeginbutton();

        Image picture = new ImageIcon("resource/background/img.png").getImage();
        picture = picture.getScaledInstance(a, h, Image.SCALE_SMOOTH);
        ImageIcon img = new ImageIcon(picture);
        JLabel jLabel = new JLabel(img);
        jLabel.setSize(a, h);
        add(jLabel);

    }

    public void addbeginbutton() {
        //  JButton jButton=new JButton("Begin");
        jButton.addActionListener((event) -> {
            ChessGameFrame mainFrame = new ChessGameFrame(1100, 810);
            GameController gameController = new GameController(mainFrame.getChessboardComponent(), new Chessboard());
            mainFrame.setGamecontroller(gameController);
            gameController.setStatusLabel(mainFrame.getStatusLabel());
            dispose();
            mainFrame.setVisible(true);
        });
        jButton.setLocation(250, 246);
        jButton.setSize(200, 60);
        jButton.setFont(new Font("Rockwell", Font.ITALIC, 25));
        add(jButton);
    }

    public void addaibuttton() {
        jButton2.addActionListener((e) -> {
            ChessGameFrame mainFrame = new ChessGameFrame(1100, 810);
            GameController gameController = new GameController(mainFrame.getChessboardComponent(), new Chessboard());
            mainFrame.setGamecontroller(gameController);
            gameController.setStatusLabel(mainFrame.getStatusLabel());
            gameFrame.setVisible(true);
        });
        jButton2.setLocation(250, 326);
        jButton2.setSize(200, 60);
        jButton2.setFont(new Font("Rockwell", Font.ITALIC, 25));
        add(jButton2);
    }

    public ChessGameFrame getGameFrame() {
        return gameFrame;
    }

    public void setjButtonsize(int a, int b) {
        jButton.setSize(a, b);
    }

    public void setjButton2size(int a, int b) {
        jButton2.setSize(a, b);
    }

    public void setGameFrame(ChessGameFrame gameFrame) {

        this.gameFrame = gameFrame;
    }


}
