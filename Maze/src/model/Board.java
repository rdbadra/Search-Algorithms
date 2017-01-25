package model;

import model.Cell;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by roberto on 21/01/17.
 */
public class Board {

    Cell board[][];
    private int startingPointRow;
    private int startingPointColumn;
    private int endingPointRow;
    private int endingPointColumn;

    public Board(){
        board = new Cell[8][8];
        fillWithDots();
        createBoard();
        //printBoard();

    }

    public void cleanBoard(){
        fillWithDots();
        createBoard();
    }

    private void printBoard() {
        for(int i = 0; i < board.length; i++){
            for( int j = 0; j < board[i].length; j++){
                System.out.print(board[i][j].getTag());
            }
            System.out.println();
        }
    }

    private void fillWithDots() {
        for(int i = 0; i < board.length; i++){
            for( int j = 0; j < board[i].length; j++){
                board[i][j] = new Cell(".", i, j);
            }
        }
    }

    public Cell[][] getBoard(){
        return board;
    }

    public void createBoard(){
        BufferedReader br = null;
        try {
            String currentLine;
            int boardRow = 0;
            int numberPositions = 0;
            String[] ja;
            br = new BufferedReader(new FileReader("/home/roberto/IdeaProjects/Search-Algorithms/Maze/src/Persistence/maze3.txt"));
            while ((currentLine = br.readLine()) != null) {
                if(currentLine.split(",")[0].equals("0") && numberPositions==0){
                    startingPointRow = Integer.parseInt(currentLine.split(",")[0]);
                    startingPointColumn = Integer.parseInt(currentLine.split(",")[1]);
                    numberPositions++;
                } else if(currentLine.split(",")[0].equals("0") && numberPositions==1){
                    ja = currentLine.split(",");
                    endingPointRow = Integer.parseInt(currentLine.split(",")[0]);
                    endingPointColumn = Integer.parseInt(currentLine.split(",")[1]);
                    numberPositions++;
                } else {
                    String array[] = currentLine.split("");
                    for(int i = 0; i < array.length; i++){
                        if(array[i].equals("0")){
                            board[boardRow][i].setTag("");
                        } else if(array[i].equals("1")) {
                            board[boardRow][i].setTag("|");
                        } else if(array[i].equals("2")) {
                            board[boardRow][i].setTag("-");
                        } else if(array[i].equals("3")) {
                            board[boardRow][i].setTag("*");
                        }
                    }
                }
                boardRow++;
            }
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("El fichero no existe" + e);
        }
    }

    public int getStartingPointRow() {
        return startingPointRow;
    }

    public int getStartingPointColumn() {
        return startingPointColumn;
    }

    public int getEndingPointRow() {
        return endingPointRow;
    }

    public int getEndingPointColumn() {
        return endingPointColumn;
    }
}
