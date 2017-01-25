package controller;

import model.Board;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

/**
 * Created by roberto on 24/01/17.
 */
public class Dfs {
    private Stack<JButton> stack;
    private Board board;
    private JButton[][] maze;
    private int value;

    public Dfs(Board board, JButton[][] maze) {
        stack = new Stack<>();
        this.board = board;
        this.maze = maze;
        value = 0;
    }

    public void run() throws InterruptedException {
        stack.push(maze[board.getStartingPointRow()][board.getStartingPointColumn()]);
        JButton currentNode;
        while(!stack.isEmpty()){

            while((currentNode = (JButton) stack.pop())!=null || currentNode.getName().equals("End")==false){
                value++;
                System.out.println(value);
                currentNode.setText(""+value);
                //currentNode.setBackground(Color.YELLOW);
                //if(!isOriginal(currentNode) || !currentNode.getName().equals("End")) currentNode.setBackground(Color.pink);
                currentNode.setName("Visited");
                expandNodes(currentNode);

            }
        }
    }

    private void expandNodes(JButton cell){
        String position = cell.getActionCommand();
        //System.out.println(position);
        int positionRow = getPositionRow(position, cell);
        int positionColumn = getPositionColumn(position, cell);
        getNearNodes(cell, positionRow, positionColumn);
    }

    private void getNearNodes(JButton cell, int positionRow, int positionColumn) {
        getNodeRight(cell, positionRow, positionColumn);
        getNodeDown(cell, positionRow, positionColumn);
        getNodeLeft(cell, positionRow, positionColumn);
        getNodeUp(cell, positionRow, positionColumn);


    }


    private void getNodeRight(JButton cell, int positionRow, int positionColumn) {
        if (checkItIsIn(maze, positionRow, positionColumn + 1)) {
            if ((!maze[positionRow][positionColumn + 1].getName().equals("Visited") && !checkCellHasWall(maze[positionRow][positionColumn + 1]))){
                if (!maze[positionRow][positionColumn + 1].getName().equals("Expanded")) {
                    stack.push(maze[positionRow][positionColumn + 1]);
                    if(!maze[positionRow][positionColumn + 1].getName().equals("End")){
                        maze[positionRow][positionColumn + 1].setName("Expanded");
                    }
                    //maze[positionRow][positionColumn + 1].setText("" + value);
                }
            }
        }
    }

    private void getNodeLeft(JButton cell, int positionRow, int positionColumn) {
        if (checkItIsIn(maze, positionRow, positionColumn - 1)) {
            if (!maze[positionRow][positionColumn - 1].getName().equals("Visited") && !checkCellHasWall(maze[positionRow][positionColumn])) {
                if (!maze[positionRow][positionColumn - 1].getName().equals("Expanded")) {
                    stack.push(maze[positionRow][positionColumn - 1]);
                    if(!maze[positionRow][positionColumn-1].getName().equals("End")){
                        maze[positionRow][positionColumn-1].setName("Expanded");
                    }
                    //maze[positionRow][positionColumn - 1].setText("" + value);
                }
            }
        }
    }

    private void getNodeDown(JButton cell, int positionRow, int positionColumn) {
        if (checkItIsIn(maze, positionRow + 1, positionColumn)) {
            if (!maze[positionRow + 1][positionColumn].getName().equals("Visited")) {
                if (!maze[positionRow + 1][positionColumn].getName().equals("Expanded")) {
                    stack.push(maze[positionRow + 1][positionColumn]);
                    if(!maze[positionRow + 1][positionColumn].getName().equals("End")){
                        maze[positionRow + 1][positionColumn].setName("Expanded");
                    }
                    //maze[positionRow + 1][positionColumn].setText("" + value);
                }
            }
        }
    }

    private void getNodeUp(JButton cell, int positionRow, int positionColumn) {
        if (checkItIsIn(maze, positionRow - 1, positionColumn)) {
            if (!maze[positionRow - 1][positionColumn].getName().equals("Visited")) {
                if (!maze[positionRow - 1][positionColumn].getName().equals("Expanded")) {
                    stack.push(maze[positionRow - 1][positionColumn]);
                    if(!maze[positionRow - 1][positionColumn].getName().equals("End")){
                        maze[positionRow - 1][positionColumn].setName("Expanded");
                    }
                    //maze[positionRow - 1][positionColumn].setText("" + value);
                }
            }
        }
    }

    private void printNode(JButton cell) {
        System.out.println(cell.getActionCommand());
    }

    private boolean checkCellHasWall(JButton cell) {
        if (cell.getActionCommand().split("")[1].equals("|")) {
            return true;
        }
        return false;
    }

    private boolean checkItIsIn(JButton[][] boton, int pos1, int pos2) {
        int end = boton.length;
        if (pos1 >= end || pos2 >= end) {
            return false;
        }
        if (pos1 < 0 || pos2 < 0) {
            return false;
        }
        return true;
    }

    public int getPositionRow(String position, JButton cell) {
        int positionRow;
        if (checkCellHasWall(cell) == false) {
            positionRow = Integer.parseInt(position.split("-")[0]);
        } else {
            positionRow = Integer.parseInt(position.split("\\|")[0]);
        }
        return positionRow;
    }

    private int getPositionColumn(String position, JButton cell) {
        int positionColumn;
        if (checkCellHasWall(cell) == false) {
            positionColumn = Integer.parseInt(position.split("-")[1]);
        } else {
            positionColumn = Integer.parseInt(position.split("\\|")[1]);
        }
        return positionColumn;
    }

    private boolean isOriginal(JButton cell) {
        String position = cell.getActionCommand();
        int positionRow = getPositionRow(position, cell);
        int positionColumn = getPositionColumn(position, cell);
        if (positionRow == board.getEndingPointRow() && positionColumn == board.getStartingPointColumn()) {
            return true;
        }
        return false;

    }
}
