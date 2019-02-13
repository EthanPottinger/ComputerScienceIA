package tools;

/**
 *
 * @author e.pottinger
 */
public class Coordinates {
    
    private int x, y, length, height;
    
    public Coordinates(int x, int y, int length, int height) {
        this.x = x;
        this.y = y;
        this.length = length;
        this.height = height;
    }
    
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")\nlength = " + length + ", height = " + height;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public int getLength() {
        return length;
    }
    
    public int getHeight() {
        return height;
    }
    
    public boolean isColliding(Coordinates object) {
        if(y + height >= object.getY()) return true;
        if(object.getX() + object.getLength() >= x) return true;
        if(x + length >= object.getX()) return true;
        if(object.getY() + object.getHeight() >= y) return true;
        return false;
    }
    
    public void moveLeft(int speed) {
        x -= speed;
    }
    
    public void moveRight(int speed) {
        x += speed;
    }
    
    public void moveUp(int speed) {
        y -= speed;
    }
    
    public void moveDown(int speed) {
        y += speed;
    }
    
}
