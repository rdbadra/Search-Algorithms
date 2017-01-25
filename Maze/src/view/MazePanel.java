package view;

import controller.Bfs;
import controller.Dfs;
import model.Board;
import model.Cell;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by roberto on 22/01/17.
 */
public class MazePanel extends JPanel{
    private Board board;
    private JButton[][] maze;

    public MazePanel(){
        maze = new JButton[8][8];
        board = new Board();
        createMaze();
    }

    public void clean(){
        board.cleanBoard();
        cleanMaze();
    }

    public void paintMaze(){
        Cell[][] cellBoard = board.getBoard();
        for(int i = 0; i < cellBoard.length; i++){
            for(int j = 0; j < cellBoard[i].length; j++){
                maze[i][j].setText("    ");
                maze[i][j].setBackground(Color.white);
                maze[i][j].setBorder(new MatteBorder(0, 0, 0, 0, Color.BLACK));
                maze[i][j].setActionCommand(i+"|"+j);
                maze[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton cell = (JButton) e.getSource();
                        cell.setBorder(new MatteBorder(0, 10, 0, 0, Color.BLACK));
                        String position = cell.getActionCommand();
                        cell.setActionCommand(position.split("-")[0]+"|"+position.split("-")[1]);
                    }
                });
                if(0 == i && 0 == j){
                    maze[i][j].setName("Start");
                    maze[i][j].setBackground(Color.CYAN);
                    maze[i][j].setActionCommand(i+"-"+j);
                } else if(0 == i && 4 == j){
                    maze[i][j].setName("End");
                    maze[i][j].setBackground(Color.GREEN);
                    maze[i][j].setActionCommand(i+"-"+j);
                } else {
                    maze[i][j].setName("Not");
                    maze[i][j].setActionCommand(i+"-"+j);
                }

            }

        }
    }

    private void cleanMaze() {
        Cell[][] cellBoard = board.getBoard();
        for(int i = 0; i < cellBoard.length; i++){
            for(int j = 0; j < cellBoard[i].length; j++){
                maze[i][j].setText("   ");
                maze[i][j].setBackground(Color.white);
                if(board.getStartingPointRow() == i && board.getStartingPointColumn() == j){
                    maze[i][j].setName("Start");
                    maze[i][j].setBackground(Color.CYAN);
                    maze[i][j].setActionCommand(i+"+"+j);
                } else if(board.getEndingPointRow() == i && board.getEndingPointColumn() == j){
                    maze[i][j].setName("End");
                    maze[i][j].setBackground(Color.GREEN);
                    maze[i][j].setActionCommand(i+"+"+j);
                } else {
                    maze[i][j].setName("Not");
                    maze[i][j].setActionCommand(i+"+"+j);
                }
                //left wall
                if(cellBoard[i][j].getTag().equals("|")){
                    maze[i][j].setBorder(new MatteBorder(0, 10, 0, 0, Color.BLACK));
                    maze[i][j].setActionCommand(i+"|"+j);
                    //wall down
                } else if(cellBoard[i][j].getTag().equals("-")){
                    maze[i][j].setBorder(new MatteBorder(0, 0, 10, 0, Color.BLACK));
                    maze[i][j].setActionCommand(i+"-"+j);
                    //wall left and down
                } else if(cellBoard[i][j].getTag().equals("*")){
                    maze[i][j].setBorder(new MatteBorder(0, 10, 10, 0, Color.BLACK));
                    maze[i][j].setActionCommand(i+"*"+j);
                }
            }

        }
    }

    private void editCell(){

    }

    public void dfs() throws InterruptedException {
        Dfs depthSearch = new Dfs(board, maze);
        depthSearch.run();
    }

    public void bfs() throws InterruptedException {
        Bfs breadthSearch = new Bfs(maze, board);
        breadthSearch.run();
    }

    private void initComponents(){
        maze = new JButton[8][8];
        GridLayout layout = new GridLayout(8, 8, 2, 2);
        this.setLayout(layout);
        board = new Board();
    }

    private void createMaze(){
        initComponents();
        Cell[][] cellBoard = board.getBoard();
        for(int i = 0; i < cellBoard.length; i++){
            for(int j = 0; j < cellBoard[i].length; j++){
                maze[i][j] = new JButton();
                maze[i][j].setText("   ");
                maze[i][j].setBackground(Color.white);
                if(board.getStartingPointRow() == i && board.getStartingPointColumn() == j){
                    maze[i][j].setName("Start");
                    maze[i][j].setBackground(Color.CYAN);
                    maze[i][j].setActionCommand(i+"+"+j);
                } else if(board.getEndingPointRow() == i && board.getEndingPointColumn() == j){
                    maze[i][j].setName("End");
                    maze[i][j].setBackground(Color.GREEN);
                    maze[i][j].setActionCommand(i+"+"+j);
                } else {
                    maze[i][j].setName("Not");
                    maze[i][j].setActionCommand(i+"+"+j);
                }
                //left wall
                if(cellBoard[i][j].getTag().equals("|")){
                    maze[i][j].setBorder(new MatteBorder(0, 10, 0, 0, Color.BLACK));
                    maze[i][j].setActionCommand(i+"|"+j);
                    //wall down
                } else if(cellBoard[i][j].getTag().equals("-")){
                    maze[i][j].setBorder(new MatteBorder(0, 0, 10, 0, Color.BLACK));
                    maze[i][j].setActionCommand(i+"-"+j);
                    //wall left and down
                } else if(cellBoard[i][j].getTag().equals("*")){
                    maze[i][j].setBorder(new MatteBorder(0, 10, 10, 0, Color.BLACK));
                    maze[i][j].setActionCommand(i+"*"+j);
                }

                this.add(maze[i][j]);
            }

        }
    }
}
