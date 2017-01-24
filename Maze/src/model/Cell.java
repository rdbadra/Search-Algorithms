package model;

/**
 * Created by roberto on 21/01/17.
 */
public class Cell {

    private String tag;
    private boolean visited;
    private int row;
    private int column;

    public Cell(String tag, int row, int column){
        this.tag = tag;
        visited = false;
        this.row = row;
        this.column = column;
    }

    public int getRow(){
        return row;
    }

    public int getColumn(){
        return column;
    }

    public boolean getVisited(){
        return visited;
    }

    public void setVisited(){
        visited = true;
    }

    public String getTag(){
        return tag;
    }

    public void setTag(String tag){
        this.tag = tag;
    }
}
