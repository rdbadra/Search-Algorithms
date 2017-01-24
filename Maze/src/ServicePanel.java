import javax.swing.*;
import java.awt.*;

/**
 * Created by roberto on 23/01/17.
 */
public class ServicePanel extends JPanel {

    public ServicePanel(JPanel mazePanel){
        GridLayout layout = new GridLayout(1, 1, 2, 2);
        this.setLayout(layout);
        this.add(new JButton("BFS"));
        this.add(new JButton("DFS"));
    }
}
