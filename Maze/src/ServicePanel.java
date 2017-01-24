import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by roberto on 23/01/17.
 */
public class ServicePanel extends JPanel {

    public ServicePanel(MazePanel mazePanel){
        GridLayout layout = new GridLayout(1, 1, 2, 2);
        this.setLayout(layout);
        JButton bfs = new JButton("BFS");
        bfs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mazePanel.bfs();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        });
        this.add(bfs);
        JButton dfs = new JButton("DFS");
        dfs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mazePanel.dfs();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        });
        this.add(dfs);
    }
}
