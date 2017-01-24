import javax.swing.*;
import java.awt.*;

/**
 * Created by roberto on 21/01/17.
 */
public class Main{

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JPanel panelito = new JPanel();
        JPanel panel = new MazePanel();
        panelito.add(panel);
        panelito.add(new ServicePanel(panel));
        frame.add(panelito);
        frame.setBounds(500, 200, 400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        //Board ja = new Board();
        //ja.printBoard();

    }
}
