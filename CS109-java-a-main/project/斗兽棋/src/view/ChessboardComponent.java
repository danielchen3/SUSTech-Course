package view;


import controller.GameController;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;


import static model.Constant.CHESSBOARD_COL_SIZE;
import static model.Constant.CHESSBOARD_ROW_SIZE;

/**
 * This class represents the checkerboard component object on the panel
 */
public class ChessboardComponent extends JComponent {
    public  CellComponent[][] gridComponents = new CellComponent[CHESSBOARD_ROW_SIZE.getNum()][CHESSBOARD_COL_SIZE.getNum()];
    public final int CHESS_SIZE;
    private  Set<ChessboardPoint> riverCell = new HashSet<>();
    public JLabel timeLabel;
    public JLabel statusLabel;
    public GameController gameController;

    public ChessboardComponent(int chessSize, JLabel statusLabel, JLabel timeLabel) {
        this.statusLabel = statusLabel;
        this.timeLabel = timeLabel;
        CHESS_SIZE = chessSize;
        int width = CHESS_SIZE * 7;
        int height = CHESS_SIZE * 9;
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);// Allow mouse events to occur
        setLayout(null); // Use absolute layout.
        setSize(width, height);


        System.out.printf("chessboard width, height = [%d : %d], chess size = %d\n", width, height, CHESS_SIZE);

        initiateGridComponents();
    }




    public void initiateChessComponent(Chessboard chessboard) {
        Cell[][] grid = chessboard.getGrid();
        for (int i = 0; i < CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < CHESSBOARD_COL_SIZE.getNum(); j++) {

                if (grid[i][j].getPiece() != null) {
                    ChessPiece chessPiece = grid[i][j].getPiece();

                    if (chessPiece.getName().equals("Elephant")){
                        gridComponents[i][j].add(new ElephantChessComponent(chessPiece.getOwner(), CHESS_SIZE));
                    }
                    if (chessPiece.getName().equals("Lion")){
                        gridComponents[i][j].add(new LionChessComponent(chessPiece.getOwner(), CHESS_SIZE));
                    }
                    if (chessPiece.getName().equals("Tiger")){
                        gridComponents[i][j].add(new TigerChessComponent(chessPiece.getOwner(), CHESS_SIZE));
                    }
                    if (chessPiece.getName().equals("Leopard")){
                        gridComponents[i][j].add(new LeopardChessComponent(chessPiece.getOwner(), CHESS_SIZE));
                    }
                    if (chessPiece.getName().equals("Wolf")){
                        gridComponents[i][j].add(new WolfChessComponent(chessPiece.getOwner(), CHESS_SIZE));
                    }
                    if (chessPiece.getName().equals("Dog")){
                        gridComponents[i][j].add(new DogChessComponent(chessPiece.getOwner(), CHESS_SIZE));
                    }
                    if (chessPiece.getName().equals("Cat")){
                        gridComponents[i][j].add(new CatChessComponent(chessPiece.getOwner(), CHESS_SIZE));
                    }
                    if (chessPiece.getName().equals("Rat")){
                        gridComponents[i][j].add(new RatChessComponent(chessPiece.getOwner(), CHESS_SIZE));
                    }
                }
            }
        }
    }

    public void initiateGridComponents() {

       riverCell.add(new ChessboardPoint(3, 1));
        riverCell.add(new ChessboardPoint(3, 2));
        riverCell.add(new ChessboardPoint(4, 1));
        riverCell.add(new ChessboardPoint(4, 2));
        riverCell.add(new ChessboardPoint(5, 1));
        riverCell.add(new ChessboardPoint(5, 2));

        riverCell.add(new ChessboardPoint(3, 4));
        riverCell.add(new ChessboardPoint(3, 5));
        riverCell.add(new ChessboardPoint(4, 4));
        riverCell.add(new ChessboardPoint(4, 5));
        riverCell.add(new ChessboardPoint(5, 4));
        riverCell.add(new ChessboardPoint(5, 5));

        for (int i = 0; i < CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < CHESSBOARD_COL_SIZE.getNum(); j++) {
                ChessboardPoint temp = new ChessboardPoint(i, j);
                CellComponent cell;
             if (riverCell.contains(temp)) {
                  ImageIcon picture = new ImageIcon("resource\\background\\river1.png");
                   cell = new CellComponent(Color.CYAN, calculatePoint(i, j), CHESS_SIZE);
                   this.add(cell);

                } else {
                 if(i==0&&j==3){

                     cell=new CellComponent(Color.RED,calculatePoint(i,j),CHESS_SIZE);

                     this.add(cell);

                 }else if(i==8&&j==3){
                     cell=new CellComponent(Color.BLUE,calculatePoint(i,j),CHESS_SIZE);
                     this.add(cell);
                 }else if(i==0&&(j==2||j==4)){
                     cell=new CellComponent(Color.LIGHT_GRAY,calculatePoint(i,j),CHESS_SIZE);
                     this.add(cell);
                 }else if(i==1&&j==3){
                     cell=new CellComponent(Color.LIGHT_GRAY,calculatePoint(i,j),CHESS_SIZE);
                     this.add(cell);
                 }else if(i==7&&j==3){
                     cell=new CellComponent(Color.LIGHT_GRAY,calculatePoint(i,j),CHESS_SIZE);
                     this.add(cell);
                 }else if(i==8&&(j==2||j==4)){
                     cell=new CellComponent(Color.LIGHT_GRAY,calculatePoint(i,j),CHESS_SIZE);
                     this.add(cell);
                 }

                     else {
                     cell = new CellComponent(Color.DARK_GRAY, calculatePoint(i, j), CHESS_SIZE);

                     this.add(cell);
                 }
              }
                gridComponents[i][j] = cell;
            }
        }
    }
    public void removeChessComponent() {
        for (int i = 0; i < CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < CHESSBOARD_COL_SIZE.getNum(); j++) {
                try {
                    gridComponents[i][j].remove(0);
                } catch (Exception e) {
                }
            }
        }

    }

    public void registerController(GameController gameController) {
        this.gameController = gameController;
    }

    public void setChessComponentAtGrid(ChessboardPoint point, ChessComponent chess) {
        getGridComponentAt(point).add(chess);
    }

    public ChessComponent removeChessComponentAtGrid(ChessboardPoint point) {
        // Note re-validation is required after remove / removeAll.
        ChessComponent chess = (ChessComponent) getGridComponentAt(point).getComponents()[0];
        getGridComponentAt(point).removeAll();
        getGridComponentAt(point).revalidate();
        chess.setSelected(false);
        return chess;
    }

    private CellComponent getGridComponentAt(ChessboardPoint point) {
        return gridComponents[point.getRow()][point.getCol()];
    }

    private ChessboardPoint getChessboardPoint(Point point) {
        System.out.println("[" + point.y / CHESS_SIZE + ", " + point.x / CHESS_SIZE + "] Clicked");
        return new ChessboardPoint(point.y / CHESS_SIZE, point.x / CHESS_SIZE);
    }

    private Point calculatePoint(int row, int col) {
        return new Point(col * CHESS_SIZE, row * CHESS_SIZE);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }
public void setgridmousefalse(){
    for (int i = 0; i <9 ; i++) {
        for (int j = 0; j <7 ; j++) {
            gridComponents[i][j].mouseEnter =false;
        }
    }
}

    @Override
    protected void processMouseEvent(MouseEvent e) {
        if (e.getID() == MouseEvent.MOUSE_PRESSED) {
            JComponent clickedComponent = (JComponent) getComponentAt(e.getX(), e.getY());
            if (clickedComponent.getComponentCount() == 0) {
                System.out.print("None chess here and ");
                gameController.onPlayerClickCell(getChessboardPoint(e.getPoint()), (CellComponent) clickedComponent);
            } else {
                System.out.print("One chess here and ");
                gameController.onPlayerClickChessPiece(getChessboardPoint(e.getPoint()), (ChessComponent) clickedComponent.getComponents()[0]);
            }
        }
  if(e.getID()==MouseEvent.MOUSE_PRESSED){
new Clickmusic().clickplay("resource\\Music\\mouse.wav");
            CellComponent cellenter=(CellComponent) getComponentAt(e.getX(),e.getY());
            setgridmousefalse();
            cellenter.mouseEnter=true;
        cellenter.repaint();
        cellenter.revalidate();
        repaint();
        revalidate();

        }

    }


}
