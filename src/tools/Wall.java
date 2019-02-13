package tools;

/**
 *
 * @author e.pottinger
 */
public class Wall {
    
    Coordinates coordinates;
    
    public Wall(int x, int y, int length, int height) {
        coordinates = new Coordinates(x, y, length, height);
    }
    
}
