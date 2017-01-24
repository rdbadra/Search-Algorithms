import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

/**
 * Created by roberto on 22/01/17.
 */
public class MazePanel extends JPanel{
    private Board board;
    private JButton[][] maze;

    public MazePanel(){
        board = new Board();
        createMaze();

        //button1.setB
        // order(new MatteBorder(30, 0, 0, 0, Color.BLACK));
    }

    public void dfs() throws InterruptedException {
        Dfs depthSearch = new Dfs(board, maze);
        depthSearch.run();
    }

    public void bfs() throws InterruptedException {
        Bfs breadthSearch = new Bfs(maze, board);
        breadthSearch.run();
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
                if(board.getStartingPointRow() == i && board.getStartingPointColumn() == j){
                    maze[i][j].setName("Start");
                    maze[i][j].setBackground(Color.CYAN);
                    maze[i][j].setActionCommand(i+"-"+j);
                } else if(board.getEndingPointRow() == i && board.getEndingPointColumn() == j){
                    maze[i][j].setName("End");
                    maze[i][j].setBackground(Color.GREEN);
                    maze[i][j].setActionCommand(i+"-"+j);
                } else {
                    maze[i][j].setName("Not");
                    maze[i][j].setActionCommand(i+"-"+j);
                }
                if(cellBoard[i][j].getTag().equals("|")){
                    maze[i][j].setBorder(new MatteBorder(0, 10, 0, 0, Color.BLACK));
                    maze[i][j].setActionCommand(i+"|"+j);
                }

                this.add(maze[i][j]);
            }

        }
    }
}
