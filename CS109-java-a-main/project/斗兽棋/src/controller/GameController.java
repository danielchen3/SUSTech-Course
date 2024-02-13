package controller;


import listener.GameListener;
import model.*;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Controller is the connection between model and view,
 * when a Controller receive a request from a view, the Controller
 * analyzes and then hands over to the model for processing
 * [in this demo the request methods are onPlayerClickCell() and onPlayerClickChessPiece()]
 */
public class GameController implements GameListener {


    public Chessboard model;
    private ChessboardComponent view;
    public PlayerColor currentPlayer;
    public ArrayList<ChessboardPoint> canStepPoints;
    // Record whether there is a selected piece before
    private ChessboardPoint selectedPoint;
    private PlayerColor winer;
    JLabel statusLabel;
    private Thread thread;

    public GameController(ChessboardComponent view, Chessboard model) {
        this.view = view;
        this.model = model;
        this.currentPlayer = PlayerColor.BLUE;
        this.winer=null;
        view.registerController(this);
        view.initiateChessComponent(model);
        view.repaint();
    }


    public void swapColor() {
        currentPlayer = currentPlayer == PlayerColor.BLUE ? PlayerColor.RED : PlayerColor.BLUE;
       if (currentPlayer == PlayerColor.BLUE)
           statusLabel.setText("Turn " + (model.action.size() / 2 + 1) + ": BLUE");
        else
            statusLabel.setText("Turn " + (model.action.size() / 2 + 1) + ": RED");
    }


    public JLabel getStatusLabel() {
        return statusLabel;
    }

    public void setStatusLabel(JLabel statusLabel) {
        this.statusLabel = statusLabel;
    }
    public void win() {
        if (model.grid[8][3].piece != null || model.blueout.size() == 8) {
            this.winer = PlayerColor.RED;
        }
        if (model.grid[0][3].piece != null || model.redout.size() == 8) {
            this.winer = PlayerColor.BLUE;
        }
    }

    public void whenwin() {
        if (this.winer == PlayerColor.BLUE) {
            JOptionPane.showMessageDialog(view, "BLUE SIDE WIN!");

        }
        if (this.winer == PlayerColor.RED) {
            JOptionPane.showMessageDialog(view, "RED SIDE WIN!");

        }
    }

    // click an empty cell
    @Override
    public void onPlayerClickCell(ChessboardPoint point, CellComponent component) {
        if (selectedPoint != null && model.isValidMove(selectedPoint, point)) {
            model.moveChessPiece(selectedPoint, point);
            setgridmovefalse();
            canStepPoints = null;
            view.setChessComponentAtGrid(point, view.removeChessComponentAtGrid(selectedPoint));
            selectedPoint = null;
            swapColor();
            view.repaint();
            component.revalidate();
            win();
            if (winer != null) {
                whenwin();
                reset();
            }
        }
    }

    // click a cell with a chess
    @Override
    public void onPlayerClickChessPiece(ChessboardPoint point, ChessComponent component) {
        if (selectedPoint == null) {
            if (model.getChessPieceOwner(point).equals(currentPlayer)) {
               canStepPoints = Canmovelist(point);
                selectedPoint = point;
                component.setSelected(true);
                component.revalidate();
                component.repaint();
                view.repaint();
                view.revalidate();
            }
        } else if (selectedPoint.equals(point)) {
            selectedPoint = null;
            canStepPoints = null;
            setgridmovefalse();
            component.setSelected(false);
            component.repaint();
            component.revalidate();
            view.repaint();
            view.revalidate();
        } else if (model.isValidCapture(selectedPoint, point)) {
            model.captureChessPiece(selectedPoint, point);
            view.removeChessComponentAtGrid(point);
            ChessComponent chessComponent = view.removeChessComponentAtGrid(selectedPoint);
            view.setChessComponentAtGrid(point, chessComponent);
            selectedPoint = null;
            setgridmovefalse();
            swapColor();
            view.repaint();
            view.revalidate();
            component.revalidate();
            win();
            if (winer != null) {
                whenwin();
                reset();
            }
        }
    }
    public void reset() {
        model.initGrid();
        model.initPieces();
        view.removeChessComponent();
        view.initiateChessComponent(model);
        currentPlayer = PlayerColor.BLUE;
        selectedPoint = null;
        model.action = new ArrayList<>();
        model.redout = new ArrayList<>();
        model.blueout = new ArrayList<>();
        this.winer = null;
        statusLabel.setText("Turn 1: Blue");
        view.repaint();
        view.revalidate();
    }
    public ArrayList<ChessboardPoint> Canmovelist(ChessboardPoint src) {
        ArrayList<ChessboardPoint> list = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 7; j++) {
                ChessboardPoint dest = new ChessboardPoint(i, j);
                if (model.isValidMove(src, dest)){
                    view.gridComponents[i][j].isvalidmove = true;
                    list.add(dest);
                }
                if (model.isValidCapture(src, dest)){
                    view.gridComponents[i][j].isvalidmove = true;
                    list.add(dest);
                }
            }
        }
        return list;
    }
    public void regretstep() {
        if (model.action.size() > 0) {
            Move step = model.action.get(model.action.size() - 1);
            model.action.remove(model.action.size() - 1);
            if (step.getTarget() == null) {
                model.reversemoveChessPiece(step.src, step.dest);
                view.setChessComponentAtGrid(step.src, view.removeChessComponentAtGrid(step.dest));
                selectedPoint = null;
                swapColor();
                view.repaint();
            } else {
                model.reversecaptureChessPiece(step.src, step.dest);
                ChessComponent chessComponent = view.removeChessComponentAtGrid(step.dest);
                view.setChessComponentAtGrid(step.src, chessComponent);
                if (step.getTarget().getName().equals("Elephant")) {
                    view.setChessComponentAtGrid(step.dest, new ElephantChessComponent(step.getTarget().getOwner(), view.CHESS_SIZE));
                }
                if (step.getTarget().getName().equals("Lion")) {
                    view.setChessComponentAtGrid(step.dest, new LionChessComponent(step.getTarget().getOwner(), view.CHESS_SIZE));
                }
                if (step.getTarget().getName().equals("Tiger")) {
                    view.setChessComponentAtGrid(step.dest, new TigerChessComponent(step.getTarget().getOwner(), view.CHESS_SIZE));
                }
                if (step.getTarget().getName().equals("Leopard")) {
                    view.setChessComponentAtGrid(step.dest, new LeopardChessComponent(step.getTarget().getOwner(), view.CHESS_SIZE));
                }
                if (step.getTarget().getName().equals("Wolf")) {
                    view.setChessComponentAtGrid(step.dest, new WolfChessComponent(step.getTarget().getOwner(), view.CHESS_SIZE));
                }
                if (step.getTarget().getName().equals("Dog")) {
                    view.setChessComponentAtGrid(step.dest, new DogChessComponent(step.getTarget().getOwner(), view.CHESS_SIZE));
                }
                if (step.getTarget().getName().equals("Cat")) {
                    view.setChessComponentAtGrid(step.dest, new CatChessComponent(step.getTarget().getOwner(), view.CHESS_SIZE));
                }
                if (step.getTarget().getName().equals("Rat")) {
                    view.setChessComponentAtGrid(step.dest, new RatChessComponent(step.getTarget().getOwner(), view.CHESS_SIZE));
                }
                selectedPoint = null;
                swapColor();
                view.repaint();
                view.revalidate();
            }
        } else {
            JOptionPane.showMessageDialog(view, "No more regret!");
        }
    }
    public void playback() {
        ArrayList<Move> actions = model.action;
        reset();
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < actions.size(); i++) {
                    Move move = actions.get(i);
                    delay();
                    if (move.getTarget() == null) {
                        model.moveChessPiece(move.getSrc(), move.getDest());
                        view.setChessComponentAtGrid(move.getDest(), view.removeChessComponentAtGrid(move.getSrc()));
                        selectedPoint = null;
                        swapColor();
                        view.repaint();
                        view.revalidate();
                    } else {
                        model.captureChessPiece(move.getSrc(), move.getDest());
                        view.removeChessComponentAtGrid(move.getDest());
                        view.setChessComponentAtGrid(move.getDest(), view.removeChessComponentAtGrid(move.getSrc()));
                        selectedPoint = null;
                        swapColor();
                        view.repaint();
                        view.revalidate();
                    }
                }
            }
        });
        thread.start();
    }
    public void delay() {
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void setgridmovefalse() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 7; j++) {
                view.gridComponents[i][j].isvalidmove = false;
            }
        }
    }
    public void saveGame(String fileName) {
        String folderPath = "resou";
        String folderName = "游戏存档";
        File folder = new File(folderName);
        if (folder.mkdirs()) {
            System.out.println("文件夹已创建！");
        } else {
            System.out.println("文件夹已存在或创建失败！");
        }
        File file = new File(folder, fileName + ".txt");
        try {
            boolean fileCreated = file.createNewFile();
            if (fileCreated) {
                System.out.println("文件已创建！");
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                for (int i = 0; i < model.action.size(); i++) {
                    bufferedWriter.write(model.action.get(i).toString());
                    bufferedWriter.newLine();
                }
                if (currentPlayer.getColor() == PlayerColor.BLUE.getColor())
                    bufferedWriter.write("Current player: blue");
                else {
                    bufferedWriter.write("Current player: red");
                }
                bufferedWriter.newLine();
                for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
                    for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                        bufferedWriter.write(getabbr(model.grid[i][j].getPiece()));
                    }
                    bufferedWriter.newLine();
                }
                bufferedWriter.close();
            } else {
                System.out.println("文件已存在或创建失败！");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadgame(String filePath) {
        try {
            reset();
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            boolean judge = false;
            int count = 0;
            while (!judge) {
                String line = reader.readLine();
                count++;
                if (line.startsWith("Current")) {
                    judge = true;
                    String current_player_line = readPointLine(filePath, count);
                    String last_play_line = readPointLine(filePath, count - 1);
                    char[] cc = current_player_line.toCharArray();
                    char[] last = last_play_line.toCharArray();
                    if (last != null) {
                        if (last[0] == cc[16]) {
                            JOptionPane.showMessageDialog((Component) null, "行棋方错误", "Error", JOptionPane.ERROR_MESSAGE);
                            reset();
                        }
                    }
                    for (int i = 1; i <= 9; i++) {
                        String s = readPointLine(filePath, count + i);
                        if (s == null || s.length() != 7) {
                            JOptionPane.showMessageDialog((Component) null, "棋盘错误\n错误编码：102", "Error", JOptionPane.ERROR_MESSAGE);
                            reset();
                            break;
                        }
                    }
                    for (int i = 1; i <= 9; i++) {
                        String s = readPointLine(filePath, count + i);
                        char[] temp = s.toCharArray();
                        for (int j = 0; j < temp.length; j++) {
                            if (temp[j] != '*' && temp[j] != 'e' && temp[j] != 'L' && temp[j] != 't' && temp[j] != 'l' && temp[j] != 'w' && temp[j] != 'd' && temp[j] != 'c' && temp[j] != 'r') {
                                JOptionPane.showMessageDialog((Component) null, "棋子错误\n错误编码：103", "Error", JOptionPane.ERROR_MESSAGE);
                                reset();
                                break;
                            }
                        }
                    }
                }
                if (line.contains("*")) {
                    JOptionPane.showMessageDialog((Component) null, "缺少行棋方错误\n错误编码：104", "Error", JOptionPane.ERROR_MESSAGE);
                    reset();
                    judge = true;
                }
                if (!judge) processLine(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }
    private String getabbr(ChessPiece chess) {
        if (chess == null) return "*";
        else if (chess.getName().equals("Elephant")) return "e";
        else if (chess.getName().equals("Lion")) return "L";
        else if (chess.getName().equals("Tiger")) return "t";
        else if (chess.getName().equals("Leopard")) return "l";
        else if (chess.getName().equals("Wolf")) return "w";
        else if (chess.getName().equals("Dog")) return "d";
        else if (chess.getName().equals("Cat")) return "c";
        else if (chess.getName().equals("Rat")) return "r";
        else return "";
    }
    public void processLine(String line) {
        char[] arr = line.toCharArray();
        ChessboardPoint src = new ChessboardPoint(arr[5] - '0', arr[7] - '0');
        ChessboardPoint dest = new ChessboardPoint(arr[14] - '0', arr[16] - '0');
        if (arr.length > 20) {
            model.captureChessPiece(src, dest);
            view.removeChessComponentAtGrid(dest);
            view.setChessComponentAtGrid(dest, view.removeChessComponentAtGrid(src));
            selectedPoint = null;
            swapColor();
            view.repaint();
            view.revalidate();
        } else {
            model.moveChessPiece(src, dest);
            view.setChessComponentAtGrid(dest, view.removeChessComponentAtGrid(src));
            selectedPoint = null;
            swapColor();
            view.repaint();
            view.revalidate();
        }
    }
    public static String readPointLine(String fileName, int readLine) throws IOException {
        String line;
        try {
            BufferedReader br = Files.newBufferedReader(Paths.get(fileName));
            int i = 0;
            while ((line = br.readLine()) != null) {
                i++;
                if (i == readLine) {
                    return line;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}

