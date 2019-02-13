package testing;

import javax.swing.*;
import tools.*;

/**
 *
 * @author e.pottinger
 */
public class GraphicsTester extends JFrame {
    
    CanvasThing c;
    
    public GraphicsTester() {
        this.setSize(1000, 700);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        c = new CanvasThing(5, 5, 10, 10);
        this.add(c);
    }
     
}
