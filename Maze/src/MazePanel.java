import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

/**
 * Created by roberto on 22/01/17.
 */
public class MazePanel extends JPanel{
    private Board board;
    private JButton[][] maze;
    private int value;

    public MazePanel(){
        board = new Board();
        createMaze();
        //button1.setBorder(new MatteBorder(30, 0, 0, 0, Color.BLACK));
    }

    public void bfs(){

    }

    private void createMaze(){
        maze = new JButton[8][8];
        GridLayout layout = new GridLayout(8, 8, 2, 2);
        this.setLayout(layout);
        board = new Board();
        Cell[][] cellBoard = board.getBoard();
        //board.printBoard();
        for(int i = 0; i < cellBoard.length; i++){
            for(int j = 0; j < cellBoard[i].length; j++){
                maze[i][j] = new JButton();
                if(cellBoard[i][j].getTag().equals("|")){
                    maze[i][j].setBorder(new MatteBorder(0, 10, 0, 0, Color.BLACK));
                }
                if(board.getStartingPointRow() == i && board.getStartingPointColumn() == j){
                    value = 1;
                    maze[i][j].setName("Start");
                    maze[i][j].setText(""+value);
                    maze[i][j].setBackground(Color.RED);
                } else if(board.getEndingPointRow() == i && board.getEndingPointColumn() == j){
                    maze[i][j].setName("Start");
                    maze[i][j].setBackground(Color.GREEN);
                } else {
                    maze[i][j].setName("Not");
                }
                this.add(maze[i][j]);
            }

        }
    }
}
