package collections;

/**
 * Node.java - The Node class holds a piece of generic data and has references 
 * to other nodes
 * 
 * @author Ethan Pottinger
 * @since 12-Nov-2018
 */
public class Node<T> {
    
    public T data;
    public Node next;
    public Node previous;
    
    /**
     * Creates a Node
     * 
     * @param data The data for the Node
     * @param next The reference for the next Node
     * @param previous The reference for the previous Node
     */
    public Node(T data, Node next, Node previous) {
        this.data = data;
        this.next = next;
        this.previous = previous;
    }
    
    /**
     * Creates a Node with no previous Node reference
     * 
     * @param data The data for the Node
     * @param next The reference for the next Node
     */
    public Node(T data, Node next) {
        this(data, next, null);
    }
    
    /**
     * Creates a Node with no references
     * 
     * @param data The data for the Node
     */
    public Node(T data) {
        this(data, null, null);
    }
    
    /**
     * Creates a Node with no data or References
     */
    public Node() {
        this(null, null, null);
    }

    /**
     * Creates a String using the data version of toString()
     * 
     * @return The data as a String
     */
    @Override
    public String toString() {
        if(data == null) return "Null";
        return data.toString();
    }
   
    /**
     * Checks if the object has the same data and references
     * 
     * @param object The object to check
     * @return If the object is equal to this Node
     */
    @Override
    public boolean equals(Object object) {
        Node node = (Node)object;
        return this.data.equals(node.data);
    }
    
    /**
     * Makes a new Node with the exact same properties as this node
     * 
     * @return The new Node
     */
    @Override
    public Node clone() {
        return new Node(this.data, this.next, this.previous);
    }
    
    /**
     * Sets the data and references to null
     */
    @Override
    public void finalize() {
        data = null;
        next = previous = null;
        System.gc();
    }

}
