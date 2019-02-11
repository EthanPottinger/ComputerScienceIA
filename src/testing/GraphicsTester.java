package testing;

import javax.swing.*;
import tools.*;

/**
 *
 * @author e.pottinger
 */
public class GraphicsTester extends JFrame {
    
    JLabel j;
    
    public GraphicsTester() {
        Coordinates coordinates = new Coordinates(0, 0, 10, 10);
        this.setVisible(true);
        this.setSize(500, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        j = new JLabel("What", 10);
        j.setVisible(true);
    }
     
}
