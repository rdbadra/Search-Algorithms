package controller;

import view.BigPanel;
import view.MazePanel;
import view.ServicePanel;

import javax.swing.*;

/**
 * Created by roberto on 21/01/17.
 */
public class Main{
    JPanel panelito;
    ServicePanel servicePanel;

    public Main(){
        JFrame frame = new JFrame("Search Algorithms");
        BigPanel bigPanel = new BigPanel();
        frame.add(bigPanel);
        frame.setBounds(500, 200, 400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) throws InterruptedException {

        new Main();

        //model.Board ja = new model.Board();
        //ja.printBoard();

    }
}
