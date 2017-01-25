package view;

import javax.swing.*;

/**
 * Created by roberto on 25/01/17.
 */
public class BigPanel extends JPanel {

    public BigPanel(){
        MazePanel mazePanel = new MazePanel();
        ServicePanel servicePanel = new ServicePanel(mazePanel);
        mazePanel.addServicePanel(servicePanel);
        this.add(mazePanel);
        this.add(servicePanel);
    }

}
