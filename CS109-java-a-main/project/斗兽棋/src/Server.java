import model.Chessboard;
import view.ChessGameFrame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345); // 指定服务器端口

            System.out.println("等待玩家加入...");

            // 创建棋盘
            ChessGameFrame chessboard = new ChessGameFrame(1100,800);

            // 等待两个玩家加入
            Socket player1Socket = serverSocket.accept();
            System.out.println("玩家1已加入：" + player1Socket.getInetAddress());
            PlayerHandler player1Handler = new PlayerHandler(player1Socket, chessboard, 1);
            new Thread(player1Handler).start();

            Socket player2Socket = serverSocket.accept();
            System.out.println("玩家2已加入：" + player2Socket.getInetAddress());
            PlayerHandler player2Handler = new PlayerHandler(player2Socket, chessboard, 2);
            new Thread(player2Handler).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class PlayerHandler implements Runnable {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private ChessGameFrame chessboard;
    private int playerNumber;

    public PlayerHandler(Socket socket, ChessGameFrame chessboard, int playerNumber) {
        this.socket = socket;
        this.chessboard = chessboard;
        this.playerNumber = playerNumber;
    }

    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            // 向玩家发送欢迎消息
            out.println("欢迎加入象棋游戏！");

            // 游戏开始
          /*  while (!chessboard.isGameOver()) {
                // 向玩家发送棋盘状态
                out.println(chessboard.getBoardState());

                // 读取玩家动作
                String playerAction = in.readLine();
                System.out.println("玩家 " + playerNumber + " 的动作：" + playerAction);

                // 解析并处理玩家动作
                String response = chessboard.processPlayerAction(playerAction, playerNumber);

                // 向玩家发送结果
                out.println(response);
            }*/

            // 发送游戏结束消息
            out.println("游戏结束！");

            // 关闭连接
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}