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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by roberto on 22/01/17.
 */
public class MazePanel extends JPanel{
    private Board board;
    private JButton[][] maze;
    ServicePanel servicePanel;

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
                maze[i][j].addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        System.out.println("JA");
                        JButton cell = (JButton) e.getSource();
                        if(e.getModifiersEx()==MouseEvent.BUTTON1_DOWN_MASK){
                            if(cell.getActionCommand().split("")[1].equals("+")){
                                cell.setBorder(new MatteBorder(0, 10, 0, 0, Color.BLACK));
                                String position = cell.getActionCommand();
                                cell.setActionCommand(position.split("\\+")[0]+"|"+position.split("\\+")[1]);
                            } else if(cell.getActionCommand().split("")[1].equals("-")){
                                cell.setBorder(new MatteBorder(0, 10, 10, 0, Color.BLACK));
                                String position = cell.getActionCommand();
                                cell.setActionCommand(position.split("-")[0]+"*"+position.split("-")[1]);
                            }
                        }
                        if(e.getModifiersEx()==MouseEvent.BUTTON3_DOWN_MASK){
                            if(cell.getActionCommand().split("")[1].equals("+")){
                                cell.setBorder(new MatteBorder(0, 0, 10, 0, Color.BLACK));
                                String position = cell.getActionCommand();
                                cell.setActionCommand(position.split("\\+")[0]+"-"+position.split("\\+")[1]);
                            } else if(cell.getActionCommand().split("")[1].equals("|")){
                                cell.setBorder(new MatteBorder(0, 10, 10, 0, Color.BLACK));
                                String position = cell.getActionCommand();
                                cell.setActionCommand(position.split("\\|")[0]+"*"+position.split("\\|")[1]);
                            }
                        }

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });
                if(0 == i && 0 == j){
                    maze[i][j].setName("Start");
                    maze[i][j].setBackground(Color.CYAN);
                    maze[i][j].setActionCommand(i+"+"+j);
                } else if(0 == i && 4 == j){
                    maze[i][j].setName("End");
                    maze[i][j].setBackground(Color.GREEN);
                    maze[i][j].setActionCommand(i+"+"+j);
                } else {
                    maze[i][j].setName("Not");
                    maze[i][j].setActionCommand(i+"+"+j);
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

    public void dfs() throws InterruptedException {
        Dfs depthSearch = new Dfs(board, maze, servicePanel);
        depthSearch.run();
    }

    public void bfs() throws InterruptedException {
        Bfs breadthSearch = new Bfs(maze, board, servicePanel);
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

    public void addServicePanel(ServicePanel servicePanel){
        this.servicePanel = servicePanel;
    }
}
