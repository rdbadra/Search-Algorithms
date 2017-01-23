import javax.swing.*;
import java.awt.*;

/**
 * Created by roberto on 21/01/17.
 */
public class Main{

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JPanel panel = new MazePanel();
        frame.add(panel);
        frame.setBounds(500, 200, 400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        //Board ja = new Board();
        //ja.printBoard();

    }
}
