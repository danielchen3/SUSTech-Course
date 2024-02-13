package view;

import controller.GameController;
import model.Chessboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

/**
 * 这个类表示游戏过程中的整个游戏界面，是一切的载体
 */

    //    public final Dimension FRAME_SIZE ;


/**
 * 这个类表示游戏过程中的整个游戏界面，是一切的载体
 */
public class ChessGameFrame extends JFrame {
    //    public final Dimension FRAME_SIZE ;
    private final int WIDTH;
    private final int HEIGTH;
    JLabel statusLabel;
    JLabel timeLabel;
    public BeginComponent begin;
    public JLabel background;
    private final int ONE_CHESS_SIZE;

    private ChessboardComponent chessboardComponent;
    public GameController gameController;

    public ChessGameFrame(int width, int height) {
        setTitle("2023 CS109 Project-Jungle"); //设置标题
        this.WIDTH = width;
        this.HEIGTH = height;
        this.ONE_CHESS_SIZE = (HEIGTH * 4 / 5) / 9;

        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);
        addSaveButton();
        addLoadButton();

        addChessboard();
        addStatusLabel();
        addHelloButton();
        addResetButton();
        addregretbutton();
        addplaybackbutton();
        int a = 1100;
        int b = 800;
        Image image = new ImageIcon("resource/background/background.png").getImage();
        image = image.getScaledInstance(a, b, Image.SCALE_SMOOTH);
        ImageIcon img = new ImageIcon(image);
        background = new JLabel(img);
        background.setSize(a, b);
        add(background);

    }


    public ChessboardComponent getChessboardComponent() {
        return chessboardComponent;
    }

    //获取窗口的大小
    //  Dimension d = .getSize();

    //设置组件的大小
    //  button.setBounds(0, 0, d.width, height-20);

    public JLabel getStatusLabel() {
        return statusLabel;
    }

    public void setStatusLabel(JLabel statusLabel) {
        this.statusLabel = statusLabel;
    }


    public void setChessboardComponent(ChessboardComponent chessboardComponent) {
        this.chessboardComponent = chessboardComponent;
    }

    public void setGamecontroller(GameController gameController) {
        this.gameController = gameController;
    }

    /**
     * 在游戏面板中添加棋盘
     */
    private void addChessboard() {
        chessboardComponent = new ChessboardComponent(ONE_CHESS_SIZE, statusLabel, timeLabel);
        chessboardComponent.setLocation(HEIGTH / 5, HEIGTH / 10);

        add(chessboardComponent);

    }

    /**
     * 在游戏面板中添加标签
     */
    private void addStatusLabel() {
        statusLabel = new JLabel("Turn 1: Blue");
        statusLabel.setLocation(HEIGTH, HEIGTH / 10);
        statusLabel.setSize(200, 60);
        statusLabel.setFont(new Font("Algerian", Font.BOLD + Font.ITALIC, 25));
        statusLabel.setForeground(Color.PINK);
        add(statusLabel);
    }

    /**
     * 在游戏面板中增加一个按钮，如果按下的话就会显示Hello, world!
     */

    private void addHelloButton() {
        JButton button = new JButton("Change background");
        button.addActionListener((e) -> {

                }
        );
        button.setLocation(HEIGTH, HEIGTH / 10 + 120);
        button.setSize(200, 60);
        button.setFont(new Font("Courier New", Font.BOLD, 15));
        button.setForeground(Color.RED);
        button.setBackground(Color.PINK);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                button.setBackground(Color.GREEN);
                button.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                button.setBackground(Color.PINK);
                button.repaint();
            }
        });
        add(button);

    }

    private void addTimeLabel() {
        timeLabel = new JLabel("Time: 45");
        timeLabel.setLocation(HEIGHT + 140, HEIGHT / 10);
        timeLabel.setSize(200, 60);
        timeLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(timeLabel);
    }

    private void addResetButton() {
        JButton button = new JButton("Reset");
        button.addActionListener((e) -> {
            chessboardComponent.gameController.reset();
        });
        button.setLocation(HEIGTH, HEIGTH / 10 + 200);
        button.setSize(200, 60);
        button.setFont(new Font("Courier New", Font.BOLD, 30));
        button.setForeground(Color.RED);
        button.setBackground(Color.PINK);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                button.setBackground(Color.GREEN);
                button.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                button.setBackground(Color.PINK);
                button.repaint();
            }
        });
        add(button);
    }


    public void addregretbutton() {
        JButton button = new JButton("Regret");
        button.setLocation(HEIGTH, HEIGTH / 10 + 280);
        button.setSize(200, 60);
        button.setFont(new Font("Courier New", Font.BOLD, 30));
        button.setForeground(Color.RED);
        button.setBackground(Color.PINK);
        button.addActionListener(e -> {
            chessboardComponent.gameController.regretstep();
        });
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                button.setBackground(Color.GREEN);
                button.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                button.setBackground(Color.PINK);
                button.repaint();
            }
        });
        add(button);
    }

    private void addLoadButton() {
        JButton button = new JButton("Load");
        button.setLocation(HEIGTH, HEIGTH / 10 + 360);
        button.setSize(200, 60);
        button.setFont(new Font("Courier New", Font.BOLD, 30));
        button.setForeground(Color.RED);
        button.setBackground(Color.PINK);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                button.setBackground(Color.GREEN);
                button.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                button.setBackground(Color.PINK);
                button.repaint();
            }
        });
        add(button);

        button.addActionListener(e -> {
            JFrame frame = new JFrame("Please choose your file (txt only) ");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                if (selectedFile != null) {
                    if (selectedFile.getName().endsWith(".txt")) {
                        String filePath = selectedFile.getPath();
                        System.out.println(filePath);
                        gameController.loadgame(filePath);
                    } else {
                        JOptionPane.showMessageDialog(frame, "文件格式错误\n错误编码：101", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    private void addSaveButton() {
        JButton button = new JButton("Save");
        button.setLocation(HEIGTH, HEIGTH / 10 + 440);
        button.setSize(200, 60);
        button.setFont(new Font("Courier New", Font.BOLD, 30));
        button.setForeground(Color.RED);
        button.setBackground(Color.PINK);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                button.setBackground(Color.GREEN);
                button.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                button.setBackground(Color.PINK);
                button.repaint();
            }
        });
        add(button);
        button.addActionListener(e -> {
            System.out.println("Click Save");
            String path = JOptionPane.showInputDialog(this, "Input name here");
            chessboardComponent.gameController.saveGame(path);
        });
    }

    public void addplaybackbutton() {
        JButton button = new JButton("playback");
        button.setLocation(HEIGTH, HEIGTH / 10 + 520);
        button.setSize(200, 60);
        button.setFont(new Font("Courier New", Font.BOLD, 30));
        button.setForeground(Color.RED);
        button.setBackground(Color.PINK);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                button.setBackground(Color.GREEN);
                button.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                button.setBackground(Color.PINK);
                button.repaint();
            }
        });
        add(button);
        button.addActionListener(e -> {
            chessboardComponent.gameController.playback();
        });
    }
}


