package controller;

import model.Board;
import model.SearchQueue;
import view.ServicePanel;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Created by roberto on 23/01/17.
 */
public class Bfs {
    private Board board;
    private JButton[][] maze;
    private Queue queue = new LinkedList<>();
    private int value;
    private ServicePanel servicePanel;

    public Bfs(JButton[][] maze, Board board, ServicePanel servicePanel) {
        this.board = board;
        this.value = 0;
        this.maze = maze;
        this.servicePanel = servicePanel;
    }

    public void run(){

        queue.add(maze[board.getStartingPointRow()][board.getStartingPointColumn()]);
        JButton currentNode;
        Date start = new Date();
        while (!queue.isEmpty()) {
            while ((currentNode = (JButton) queue.remove()) != null || currentNode.getName().equals("End") == false) {
                value++;
                //System.out.println(currentNode.getActionCommand());
                //System.out.println(value);
                currentNode.setText("" + value);
                currentNode.setName("Visited");
                expandNodes(currentNode);
                if(value == 64){
                    break;
                }
            }
        }
        Date end = new Date();
        double time = (end.getTime() - start.getTime())/(double)10;
        servicePanel.setTimeBfs(time);
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

    private void expandNodes(JButton cell) {
        String position = cell.getActionCommand();
        int positionRow = getPositionRow(position, cell);
        int positionColumn = getPositionColumn(position, cell);
        getNearNodes(cell, positionRow, positionColumn);
    }

    private void getNearNodes(JButton cell, int positionRow, int positionColumn) {
        getNodesInOrder(cell, positionRow, positionColumn);
    }

    private void getNodesInOrder(JButton cell, int positionRow, int positionColumn) {
        getNodeUp(cell, positionRow, positionColumn);
        getNodeLeft(cell, positionRow, positionColumn);
        getNodeDown(cell, positionRow, positionColumn);
        getNodeRight(cell, positionRow, positionColumn);
    }

    private void getNodeRight(JButton cell, int positionRow, int positionColumn) {
        if (checkItIsIn(maze, positionRow, positionColumn + 1)) {
            if ((!maze[positionRow][positionColumn + 1].getName().equals("Visited") && !checkCellHasLeftWall(maze[positionRow][positionColumn + 1]))) {
                if (!maze[positionRow][positionColumn + 1].getName().equals("Expanded")) {
                    queue.add(maze[positionRow][positionColumn + 1]);
                    if (!maze[positionRow][positionColumn + 1].getName().equals("End")) {
                        maze[positionRow][positionColumn + 1].setName("Expanded");
                    }
                }
            }
        }
    }

    private void getNodeLeft(JButton cell, int positionRow, int positionColumn) {
        if (checkItIsIn(maze, positionRow, positionColumn - 1)) {
            if (!maze[positionRow][positionColumn - 1].getName().equals("Visited") && !checkCellHasLeftWall(maze[positionRow][positionColumn])) {
                if (!maze[positionRow][positionColumn - 1].getName().equals("Expanded")) {
                    queue.add(maze[positionRow][positionColumn - 1]);
                    if (!maze[positionRow][positionColumn - 1].getName().equals("End")) {
                        maze[positionRow][positionColumn - 1].setName("Expanded");
                    }
                }
            }
        }
    }

    private void getNodeDown(JButton cell, int positionRow, int positionColumn) {
        if (checkItIsIn(maze, positionRow + 1, positionColumn)) {
            if (!maze[positionRow + 1][positionColumn].getName().equals("Visited") && !checkCellHasDownWall(maze[positionRow][positionColumn])) {
                if (!maze[positionRow + 1][positionColumn].getName().equals("Expanded")) {
                    queue.add(maze[positionRow + 1][positionColumn]);
                    if (!maze[positionRow + 1][positionColumn].getName().equals("End")) {
                        maze[positionRow + 1][positionColumn].setName("Expanded");
                    }
                }
            }
        }
    }

    private void getNodeUp(JButton cell, int positionRow, int positionColumn) {
        if (checkItIsIn(maze, positionRow - 1, positionColumn)) {
            if (!maze[positionRow - 1][positionColumn].getName().equals("Visited") && !checkCellHasDownWall(maze[positionRow - 1][positionColumn])) {
                if (!maze[positionRow - 1][positionColumn].getName().equals("Expanded")) {
                    queue.add(maze[positionRow - 1][positionColumn]);
                    if (!maze[positionRow - 1][positionColumn].getName().equals("End")) {
                        maze[positionRow - 1][positionColumn].setName("Expanded");
                    }
                }
            }
        }
    }

    //private void printNode(JButton cell) {
    //    System.out.println(cell.getActionCommand());
    //}

    private boolean checkCellHasLeftWall(JButton cell) {
        if (cell.getActionCommand().split("")[1].equals("|")) {
            return true;
        } else if(cell.getActionCommand().split("")[1].equals("*")){
            return true;
        }
        return false;
    }

    private boolean checkCellHasDownWall(JButton cell) {
        //System.out.println(cell.getActionCommand().split("-")[1]);
        if (cell.getActionCommand().split("")[1].equals("-")) {
            return true;
        } else if(cell.getActionCommand().split("")[1].equals("*")){
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
        if ((checkCellHasLeftWall(cell) == false) && (checkCellHasDownWall(cell) == false)) {
            positionRow = Integer.parseInt(position.split("\\+")[0]);
        } else {
            if (cell.getActionCommand().split("")[1].equals("|")) {
                positionRow = Integer.parseInt(position.split("\\|")[0]);
            } else if (cell.getActionCommand().split("")[1].equals("-")) {
                positionRow = Integer.parseInt(position.split("-")[0]);
            } else {
                positionRow = Integer.parseInt(position.split("\\*")[0]);
            }
        }
        return positionRow;
    }

    private int getPositionColumn(String position, JButton cell) {
        int positionColumn;
        if ((checkCellHasLeftWall(cell) == false) && (checkCellHasDownWall(cell) == false)) {
            positionColumn = Integer.parseInt(position.split("\\+")[1]);
        } else {
            if (cell.getActionCommand().split("")[1].equals("|")) {
                positionColumn = Integer.parseInt(position.split("\\|")[1]);
            } else if (cell.getActionCommand().split("")[1].equals("-")) {
                positionColumn = Integer.parseInt(position.split("-")[1]);
            } else {
                positionColumn = Integer.parseInt(position.split("\\*")[1]);
            }
        }
        return positionColumn;
    }
}




