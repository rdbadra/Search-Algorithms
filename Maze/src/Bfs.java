import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by roberto on 23/01/17.
 */
public class Bfs {
    private Board board;
    private JButton[][] maze;
    //private ArrayList<JButton> list;
    private Queue queue = new LinkedList();
    private int value;

    public Bfs(JButton[][] maze, Board board){
        this.board = board;
        this.value = 1;
        //this.list = new ArrayList<>();
        this.maze = maze;
    }

    public void run(){
        queue.add(maze[board.getStartingPointRow()][board.getStartingPointColumn()]);
        while(!queue.isEmpty()){
            JButton currentNode = (JButton)queue.remove();
            currentNode.setText(""+value);
            if(value == 15) break;
            while(currentNode.getName().equals("End")==false){
                currentNode.setName("Visited");
                if(!isOriginal(currentNode)) currentNode.setBackground(Color.pink);
                //printNode(currentNode);
                expandNodes(currentNode);
                //currentNode = (JButton)queue.remove();
                break;
            }
        }
    }

    private boolean isOriginal(JButton cell){
        String position = cell.getActionCommand();
        int positionRow;
        int positionColumn;
        if(checkCellHasWall(cell)==false){
            positionRow = Integer.parseInt(position.split("-")[0]);
            positionColumn = Integer.parseInt(position.split("-")[1]);
        } else {
            positionRow = Integer.parseInt(position.split("\\|")[0]);
            positionColumn = Integer.parseInt(position.split("\\|")[1]);
        }
        if(positionRow == board.getEndingPointRow() && positionColumn == board.getStartingPointColumn()){
            return true;
        }
        return false;

    }

    private void expandNodes(JButton cell){
        String position = cell.getActionCommand();
        //System.out.println(position);
        int positionRow;
        int positionColumn;
        if(checkCellHasWall(cell)==false){
            positionRow = Integer.parseInt(position.split("-")[0]);
            positionColumn = Integer.parseInt(position.split("-")[1]);
        } else {
            positionRow = Integer.parseInt(position.split("\\|")[0]);
            positionColumn = Integer.parseInt(position.split("\\|")[1]);
        }
        getNearNodes(cell, positionRow, positionColumn);
    }

    private void getNearNodes(JButton cell, int positionRow, int positionColumn) {
        getNodesInOrder(cell, positionRow, positionColumn);
    }

    private void getNodesInOrder(JButton cell, int positionRow, int positionColumn) {
        getNodeUp(cell, positionRow, positionColumn);
        getNodeDown(cell, positionRow, positionColumn);
        getNodeLeft(cell, positionRow, positionColumn);
        getNodeRight(cell, positionRow, positionColumn);
    }

    private void getNodeRight(JButton cell, int positionRow, int positionColumn) {
        if (checkItIsIn(maze, positionRow, positionColumn+1)) {
            System.out.println("right");
            if((!maze[positionRow][positionColumn+1].getName().equals("Visited") || !maze[positionRow][positionColumn+1].getName().equals("Expanded")) && !checkCellHasWall(maze[positionRow][positionColumn+1])){
                value++;
                System.out.println(maze[positionRow][positionColumn+1].getActionCommand());
                System.out.println(value);
                queue.add(maze[positionRow][positionColumn+1]);
                //maze[positionRow][positionColumn+1].setName("Expanded");
                maze[positionRow][positionColumn+1].setText(""+value);
            }
        } else {
            //System.out.println("pues no right");
        }

    }

    private void getNodeLeft(JButton cell, int positionRow, int positionColumn) {
        if (checkItIsIn(maze, positionRow, positionColumn-1)) {
            System.out.println("right");
            if(!maze[positionRow][positionColumn-1].getName().equals("Visited") && !checkCellHasWall(maze[positionRow][positionColumn])){
                value++;
                System.out.println(maze[positionRow][positionColumn-1].getActionCommand());
                System.out.println(value);
                queue.add(maze[positionRow][positionColumn-1]);
                maze[positionRow][positionColumn-1].setText(""+value);
            }
        } else {
            //System.out.println("pues no right");
        }

    }

    private void getNodeDown(JButton cell, int positionRow, int positionColumn) {
        if (checkItIsIn(maze, positionRow + 1, positionColumn)) {
            System.out.println("down");
            if(!maze[positionRow + 1][positionColumn].getName().equals("Visited")){
                value++;
                System.out.println(maze[positionRow + 1][positionColumn].getActionCommand());
                System.out.println(value);
                queue.add(maze[positionRow + 1][positionColumn]);
                maze[positionRow + 1][positionColumn].setText(""+value);
            }
        } else {
            //System.out.println("pues no down");
        }
    }

    private void getNodeUp(JButton cell, int positionRow, int positionColumn) {
        if (checkItIsIn(maze, positionRow - 1, positionColumn)) {
            if(!maze[positionRow - 1][positionColumn].getName().equals("Visited")){
                value++;
                System.out.println(maze[positionRow - 1][positionColumn].getActionCommand());
                System.out.println(value);
                queue.add(maze[positionRow - 1][positionColumn]);
                maze[positionRow - 1][positionColumn].setText(""+value);
            }
        } else {
            //System.out.println("pues no up");
        }

    }

    private void printNode(JButton cell){
        System.out.println(cell.getActionCommand());
    }

    private boolean checkCellHasWall(JButton cell){
        if(cell.getActionCommand().split("")[1].equals("|")){
            return true;
        }
        return false;
    }

    private static boolean checkItIsIn(JButton[][] boton, int pos1, int pos2){
        int end = boton.length;
        if(pos1>=end || pos2>=end){
            return false;
        }
        if(pos1<0 || pos2<0){
            return false;
        }
        return true;
    }
}
