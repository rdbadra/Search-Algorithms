import javax.swing.*;
import java.awt.*;

/**
 * Created by roberto on 21/01/17.
 */
public class Main{
    JPanel panelito;
    ServicePanel servicePanel;

    public Main(){
        MazePanel mazePanel = new MazePanel();
        servicePanel = new ServicePanel(this, mazePanel);
        JFrame frame = new JFrame();
        panelito = new JPanel();

        panelito.add(mazePanel);
        panelito.add(servicePanel);
        frame.add(panelito);
        frame.setBounds(500, 200, 400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void addMazePanel(MazePanel mazePanel){
        panelito.add(mazePanel);
    }

    public static void main(String[] args) throws InterruptedException {

        new Main();

        //Board ja = new Board();
        //ja.printBoard();

    }
}
