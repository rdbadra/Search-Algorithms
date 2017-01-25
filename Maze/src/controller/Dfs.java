package controller;

import model.Board;
import view.ServicePanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Stack;

/**
 * Created by roberto on 24/01/17.
 */
public class Dfs {
    private ArrayList<JButton> list = new ArrayList<>();
    private Stack<JButton> stack;
    private Board board;
    private JButton[][] maze;
    private int value;
    private ServicePanel servicePanel;

    public Dfs(Board board, JButton[][] maze, ServicePanel servicePanel) {
        stack = new Stack<>();
        this.board = board;
        this.maze = maze;
        value = 0;
        this.servicePanel = servicePanel;
    }

    public void run() throws InterruptedException {
        stack.push(maze[board.getStartingPointRow()][board.getStartingPointColumn()]);
        JButton currentNode;
        Date start = new Date();
        while(!stack.isEmpty()){
            if(value == 64) break;
            while((currentNode = (JButton) stack.pop())!=null || currentNode.getName().equals("End")==false){
                value++;
                currentNode.setText(""+value);
                //System.out.println("current node: "+currentNode.getActionCommand());
                currentNode.setName("Visited");
                expandNodes(currentNode);

                if(value == 64) break;

            }
        }
        Date end = new Date();
        double time = (end.getTime() - start.getTime())/(double)10;
        servicePanel.setTimeDfs(time);
//        int length = list.size();
//        for(int i = 0; i < length; i++){
//            System.out.println(list.get(i).getActionCommand()+" "+list.get(i).getName()+" ");
//        }
//        System.out.println("Stack:");
//        for(int i = 0; i < (stack.toArray()).length; i++){
//            System.out.println(((JButton)stack.toArray()[i]).getActionCommand());
//        }
        //System.out.println(maze[1][2].getActionCommand());
    }

    private void expandNodes(JButton cell){
        String position = cell.getActionCommand();
        int positionRow = getPositionRow(position, cell);
        int positionColumn = getPositionColumn(position, cell);
        getNearNodes(cell, positionRow, positionColumn);
    }

    private void getNearNodes(JButton cell, int positionRow, int positionColumn) {
//        int length = (stack.toArray().length);
        getNodeRight(cell, positionRow, positionColumn);
        //if(stack.toArray().length>0)System.out.println(((JButton)stack.toArray()[0]).getActionCommand());
        getNodeDown(cell, positionRow, positionColumn);
//        if(stack.toArray().length>0)System.out.println(((JButton)stack.toArray()[0]).getActionCommand());
        getNodeLeft(cell, positionRow, positionColumn);
//        if(stack.toArray().length>0)System.out.println(((JButton)stack.toArray()[0]).getActionCommand());
        getNodeUp(cell, positionRow, positionColumn);
//        if(stack.toArray().length>0)System.out.println(((JButton)stack.toArray()[0]).getActionCommand());



    }


    private void getNodeRight(JButton cell, int positionRow, int positionColumn) {
        if (checkItIsIn(maze, positionRow, positionColumn + 1)) {
            if ((!maze[positionRow][positionColumn + 1].getName().equals("Visited") && !checkCellHasLeftWall(maze[positionRow][positionColumn + 1]))) {
                if (!maze[positionRow][positionColumn + 1].getName().equals("Expanded")) {
                    list.add(maze[positionRow][positionColumn + 1]);
//                    System.out.println(maze[positionRow][positionColumn + 1].getActionCommand()+" expanded");
                    stack.add(maze[positionRow][positionColumn + 1]);
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
                    list.add(maze[positionRow][positionColumn - 1]);
//                    System.out.println(maze[positionRow][positionColumn - 1].getActionCommand()+" expanded");
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
            if (!maze[positionRow + 1][positionColumn].getName().equals("Visited") && !checkCellHasDownWall(maze[positionRow][positionColumn])) {
                if (!maze[positionRow + 1][positionColumn].getName().equals("Expanded")) {
                    list.add(maze[positionRow + 1][positionColumn]);
//                    System.out.println(maze[positionRow + 1][positionColumn].getActionCommand()+" expanded");
                    stack.add(maze[positionRow + 1][positionColumn]);
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
                    list.add(maze[positionRow - 1][positionColumn]);
//                    System.out.println(maze[positionRow - 1][positionColumn].getActionCommand()+" expanded");
                    stack.add(maze[positionRow - 1][positionColumn]);
                    if (!maze[positionRow - 1][positionColumn].getName().equals("End")) {
                        maze[positionRow - 1][positionColumn].setName("Expanded");
                    }

                }
            }
        }
    }

    private void printNode(JButton cell) {
        System.out.println(cell.getActionCommand());
    }

    private boolean checkCellHasLeftWall(JButton cell) {
        if (cell.getActionCommand().split("")[1].equals("|")) {
            return true;
        } else if(cell.getActionCommand().split("")[1].equals("*")){
            return true;
        }
        return false;
    }

    private boolean checkCellHasDownWall(JButton cell) {
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
