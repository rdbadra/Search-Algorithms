package view;

import controller.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by roberto on 23/01/17.
 */
public class ServicePanel extends JPanel {
    private JLabel timeBfs = new JLabel("Time BFS: ");
    private JLabel timeDfs = new JLabel("Time DFS: ");

    public ServicePanel(MazePanel mazePanel){
        GridLayout layout = new GridLayout(4, 4, 2, 2);
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
        JButton cleanButton = new JButton("Clean");
        cleanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mazePanel.clean();
                //main.addMazePanel(new view.MazePanel());
            }
        });
        this.add(cleanButton);
        JButton paintButton = new JButton("Paint");
        paintButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mazePanel.paintMaze();
            }
        });
        this.add(paintButton);
        JButton compareTimes = new JButton("Compare Times");
        compareTimes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mazePanel.bfs();
                    mazePanel.clean();
                    mazePanel.dfs();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        });
        this.add(compareTimes);
        this.add(timeBfs);
        this.add(timeDfs);
    }

    public void setTimeBfs(double time){
        this.timeBfs.setText("Time: "+time);
    }

    public void setTimeDfs(double time){
        this.timeDfs.setText("Time: "+time);
    }
}
