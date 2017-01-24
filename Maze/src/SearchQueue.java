import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by roberto on 24/01/17.
 */
public class SearchQueue {
    private ArrayList<JButton> queue;

    public SearchQueue(){
        queue = new ArrayList<>();
    }

    public void add(JButton cell){
        queue.add(cell);
    }

    public int getQueueSize(){
        return queue.size();
    }

    public JButton remove(){
        System.out.println(queue.size());
        JButton cell = queue.get(0);
        System.out.println(cell.getActionCommand());
        System.out.println(queue.size());
        queue.remove(0);
        return cell;
    }

    public boolean isEmpty(){
        if(queue.size()==0){
            return true;
        }
        return false;
    }
}
