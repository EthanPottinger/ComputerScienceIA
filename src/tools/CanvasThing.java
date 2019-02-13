package tools;

import java.awt.*;

/**
 *
 * @author e.pottinger
 */
public class CanvasThing extends Canvas {
    
    Coordinates coordinates;
    
    public CanvasThing(int x, int y, int length, int height) {
        coordinates = new Coordinates(x, y, length, height);
    }
    
    @Override
    public void paint(Graphics g) {
        g.drawRect(coordinates.getX(), coordinates.getY(), coordinates.getLength(), coordinates.getHeight());
    }
    
}
