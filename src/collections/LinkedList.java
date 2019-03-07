package collections;

import java.lang.reflect.Array;

/**
 * LinkedList.java - the LinkedList class has 2 nodes that reference other Nodes
 * creating a list that holds generic data
 * 
 * @author Ethan Pottinger
 * @since 12-Nov-2018
 */
public class LinkedList<T> {

    public static final int NOT_FOUND = -1;
    
    private int length;
    private Node head;
    private Node tail;
    
    /**
     * Constructor for the LinkedList sets length to 0 with no data 
     */
    public LinkedList() {
        finalize();
    }
    
    /**
     * Constructor for the LinkedList, creates a LinkedList using an array 
     * 
     * @param array The array to be turned into a LinkedList
     */
    public LinkedList(T[] array) {
        fromArray(array);
    }
    
    /**
     * Creates a LinkedList using a LinkedList
     * 
     * @param list The LinkedList to be turned into a LinkedList
     */
    public LinkedList(LinkedList list) {
        fromLinkedList(list);
    }
    
    /**
     * Converts the LinkedList into a string
     * 
     * @return The LinkedList as a sting formatted as [data1.toString(), data2.toString(),...,dataN.toString()]
     */
    @Override
    public String toString() {
        if(isEmpty()) return "Empty List";
        String text = "[";
        Node current = head;
        while(current.next != null) {
            text += current + ", ";
            current = current.next;
        }
        return text + current + "]";
    }
    
    /**
     * Checks if the size and all the data are equal
     * 
     * @param object The object to check
     * @return If the size and all the data are equal
     */
    @Override
    public boolean equals(Object object) {
        LinkedList<T> that = (LinkedList<T>)object;
        if(this.size() != that.size()) return false;
        Node current1 = this.head;
        Node current2 = that.getFirstNode();
        while(current1 != null) {
            if(!current1.equals(current2)) return false;
            current1 = current1.next;
            current2 = current2.next;
        }
        return true;
    }
    
    /**
     * Makes a clone of the LinkedList with all the same data
     * 
     * @return The clone of the LinkedList
     */
    @Override
    public LinkedList clone() {
        LinkedList<T> list = new LinkedList<>();
        for(int i = 0; i < length; i++) {
            list.addBack((T)this.getNode(i).data);
        }
        return list;
    }
    
    /**
     * Sets length to 0 and all data null
     */
    @Override
    public final void finalize() {
        length = 0;
        head = tail = null;
        System.gc();
    }
    
    /**
     * Returns if the LinkedList is empty
     * 
     * @return If the LinkedList is empty
     */
    public boolean isEmpty() {
        return length == 0;
    }
    
    /**
     * Gives the size of the LinkedList
     * 
     * @return The size of the LinkedList
     */
    public int size() {
        return length;
    } 
    
    /**
     * Adds data to the front of the linked list and adjusts length
     * 
     * @param data The data to be added
     * @return If adding it was successful
     */
    public boolean addFront(T data) {
        if(data == null) return false;
        Node<T> node = new Node<>(data);
        if(isEmpty()) head = tail = node;
        else {
            node.next = head;
            head.previous = node;
            head = node;
        }
        length++;
        return true;
    }
    
    /**
     * Adds data to the back of the linked list and adjusts length
     * 
     * @param data The data to be added
     * @return If adding it was successful
     */
    public boolean addBack(T data) {
        if(data == null) return false;
        Node<T> node = new Node<>(data);
        if(isEmpty()) head = tail = node;
        else {
            node.previous = tail;
            tail.next = node;
            tail = node;
        }
        length++;
        return true;
    }
    
    /**
     * Checks if the index value is in the range of the LinkedList
     * 
     * @param index The index to check
     * @return If the index value is in range
     */
    private boolean inRange(int index) {
        if(isEmpty()) return false;
        if(index < 0) return false;
        if(index >= length) return false;
        return true;
    }
    
    /**
     * Gets the first Node of the LinkedList
     * 
     * @return The first Node of the LinkedList
     */
    protected Node getFirstNode() {
        return head;
    }
    
    /**
     * Gets the last Node of the LinkedList
     * 
     * @return The last Node of the LinkedList
     */
    protected Node getLastNode() {
        return tail;
    }
    
    /**
     * Gets the Node at the spot index in the LinkedList
     * 
     * @param index The index of the Node to get
     * @return The Node at spot index
     */
    protected Node getNode(int index) {
        if(!inRange(index)) return null;
        if(index == 0) return getFirstNode();
        if(index == length - 1) return getLastNode();
        Node current = head;
        for(int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }
    
    /**
     * Gets the data from the Node at that index
     * 
     * @param index The index to get the data from
     * @return The data at the spot index
     */
    public T get(int index) {
        if(!inRange(index)) return null;
        return (T)getNode(index).data;
    }
    
    /**
     * Sets the current data of the LinkedList at spot index to the data parameter
     * 
     * @param index The index to change
     * @param data The data to change it to
     * @return If it was successful
     */
    public boolean set(int index, T data) {
        Node current = getNode(index);
        if(current == null) return false;
        current.data = data;
        return true;
    }
    
    /**
     * Gets the data from the front of the LinkedList
     * 
     * @return The data at the front of the LinkedList
     */
    public T front() {
        return get(0);
    }
    
    /**
     * Gets the data from the back of the LinkedList
     * 
     * @return The data at the back of the LinkedList
     */
    public T back() {
        return get(length - 1);
    }
    
    /**
     * Removes the Node at the front of the LinkedList
     * 
     * @return The data from the deleted Node
     */
    public T removeFront() {
        if(isEmpty()) return null;
        T data = (T)head.data;
        if(length == 1) finalize();
        else {
            head = head.next;
            head.previous.next = null;
            head.previous = null;
            length--;
            System.gc();
        }
        return data;
    }
    
    /**
     * Removes the Node at the back of the LinkedList
     * 
     * @return The data from the deleted Node
     */
    public T removeBack() {
        if(isEmpty()) return null;
        else {
            T data = (T)tail.data;
            if(length == 1) finalize();
            else {
                tail = tail.previous;
                tail.next.previous = null;
                tail.next = null;
                length--;
                System.gc();
            }
            return data;
        }
    }
    
    /**
     * Checks if the LinkedList contains the data
     * 
     * @param data The data to check
     * @return If the LinkedList contains the data
     */
    public boolean contains(T data) {
        Node current = head;
        while(current != null) {
            if(current.data.equals(data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    
    /**
     * Adds data after the index value and adjusts the size of the LinkedList
     *
     * @param data The data to add 
     * @param index The index to add it after
     * @return If it was successful
     */
    public boolean addAfter(T data, int index) {
        if(data == null) return false;
        if(!inRange(index)) return false;
        if(index == length - 1) addBack(data);
        else {    
            Node<T> node = new Node<>(data);
            Node current = getNode(index);
            node.next = current.next;
            current.next.previous = node;
            current.next = node;
            node.previous = current;
            length++;
        }
        return true;
    }
    
    /**
     * Adds data before the index value and adjusts the size of the LinkedList
     *
     * @param data The data to add 
     * @param index The index to add it before
     * @return If it was successful
     */
    public boolean addBefore(T data, int index) {
        if(data == null) return false;
        if(!inRange(index)) return false;
        if(index == 0) addFront(data);
        else {    
            Node<T> node = new Node<>(data);
            Node current = getNode(index);
            node.previous = current.previous;
            current.previous.next = node;
            current.previous = node;
            node.next = current;
            length++;
        }
        return true;
    }
    
    /**
     * Adds data to the back of the LinkedList
     * 
     * @param data The data to add
     * @return If it was successful
     */
    public boolean add(T data) {
        return addBack(data);
    }
    
    /**
     * Adds data after the index value and adjusts the size of the LinkedList
     *
     * @param data The data to add 
     * @param index The index to add it after
     * @return If it was successful
     */
    public boolean add(int index, T data) {
        return addAfter(data, index);
    }
    
    /**
     * Removes the data of the index spot and adjusts size
     * 
     * @param index The index to remove
     * @return The data that was removed
     */
    public T remove(int index) {
        if(!inRange(index)) return null;
        if(index == 0) return removeFront();
        else if(index == length - 1) return removeBack();
        Node current = getNode(index);
        current.previous.next = current.next;
        current.next.previous = current.previous;
        current.next = current.previous = null;
        length--;
        System.gc();
        return (T)current.data;
    }
    
    /**
     * Returns the index of the first index of the specified data
     * 
     * @param data The data to find
     * @return The first index of the data, if not found returns -1
     */
    public int firstIndexOf(T data) {
        Node current = head;
        int index = 0;
        while(current != null) {
            if(current.data.equals(data)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return NOT_FOUND;
    } 
    
    /**
     * Returns the index of the last index of the specified data
     * 
     * @param data The data to find
     * @return The last index of the data, if not found returns -1
     */
    public int lastIndexOf(T data) {
        Node current = tail;
        int index = length - 1;
        while(current != null) {
            if(current.data.equals(data)) {
                return index;
            }
            current = current.previous;
            index--;
        }
        return NOT_FOUND;
    }
    
    /**
     * Removes the first index of the specified data
     * 
     * @param data The data to remove
     * @return If it was successful or not
     */
    public boolean remove(T data) {
        if(data == null) return false;
        int index = firstIndexOf(data);
        if(index == NOT_FOUND) return false;
        remove(index);
        return true;
    }
    
    /**
     * Removes the last index of the specified data
     * 
     * @param data The data to remove
     * @return If it was successful or not
     */
    public boolean removeLast(T data) {
        if(data == null) return false;
        int index = lastIndexOf(data);
        if(index == NOT_FOUND) return false;
        remove(index);
        return true;
    }
    
    /**
     * Removes all instances of the specified data
     * 
     * @param data the data to remove
     */
    public void removeAll(T data) {
        while(contains(data)) remove(data);
    }
    
    /**
     * Removes all instances of all data in the array
     * 
     * @param items The items to remove
     */
    public void removeAll(T[] items) {
        for(T item : items) removeAll(item);
    }
    
    /**
     * Removes everything in the LinkedList and removes the LinkedList itself
     */
    public void clear() {
        Node current = head;
        while(current != null) {
            Node next = current.next;
            current.finalize();
            current = next;
        }
        finalize();
    }
    
    /**
     * Removes everything in the LinkedList except the itemsToRetain
     * 
     * @param itemsToRetain The items to retain
     */
    public void retainAll(T[] itemsToRetain) {
        clear();
        for(int i = 0; i < itemsToRetain.length; i++) {
            add(itemsToRetain[i]);
        }
    }
    
    /**
     * Checks if the LinkedList contains any of the times in the array
     * 
     * @param items The array of items to check
     * @return If the list contains the specified items
     */
    public boolean containsAll(T[] items) {
        if(items == null) return false;
        if(items.length == 0) return false;
        for(int i = 0; i < items.length; i++) {
            if(!contains(items[i])) return false;
        }
        return true;
    }
    
    /**
     * Gets the number of instances of the specified data
     * 
     * @param data The data to check
     * @return The amount of instances of the specified data
     */
    public int numberOf(T data) {
        int count = 0;
        Node current = head;
        while(current != null) {
            if(current.data.equals(data)) count++;
            current = current.next;
        }
        return count;
    }
    
    /**
     * Adds an array of items to the front of the list
     * 
     * @param items The array of items to add
     */
    public void addAll(T[] items) {
        for (T item : items) add(item); 
    }
    
    /**
     * Adds a LinkedList of items to the front of the list
     * 
     * @param list The LinkedList of items to add
     */
    public void addAll(LinkedList list) {
        for(int i = 0; i < list.size(); i++) add((T)list.get(i));
    }
    
    /**
     * Adds a LinkedList of items after the specified index
     * 
     * @param list The LinkedList of items to add
     * @param index The index to add the LinkedList of items after
     */
    public void addAll(LinkedList list, int index) {
        for(int i = 0; i < list.size(); i++) {
            addAfter((T)list.get(i), index);
            index++;
        }
    }
    
    /**
     * Adds an array of items after the specified index
     * 
     * @param items The array of items to add
     * @param index The index to add the array of items after
     */
    public void addAll(T[] items, int index) {
        for(T item : items) {
            add(item);
            index++;
        }
    }
    
    /**
     * Creates a LinkedList from two points in the LinkedList
     * 
     * @param from Where the LinkedList starts
     * @param to Where the LinkedList ends
     * @return The LinkedList created
     */
    public LinkedList subList(int from, int to) {
        if(!inRange(from) || !inRange(to) || from > to){
            return null;
        }
        LinkedList<T> list = new LinkedList<>();
        for(int i = from; i <= to; i++) {
            list.add(this.get(i));
        }
        return list;
    }
    
    /**
     * Takes data and returns an array of all indices where the data occurs
     * 
     * @param data The data to find
     * @return An array of all the indices where the data is found
     */
    public int[] addIndices(T data) {
        if(data == null) return null;
        if(!contains(data)) return null;
        int size = numberOf(data);
        int[] array = new int[size];
        Node current = head;
        int counter = 0;
        for (int i = 0; i < length; i++) {
            if(current.data.equals(data)) {
                array[counter] = i;
                counter++;
                if(counter >= size) return array;
            }
            current = current.next;
        }
        return array;
    }
    
    /**
     * Creates a new LinkedList from an array
     * 
     * @param array The array to change to a LinkedList
     */
    public final void fromArray(T[] array) {
        finalize();
        for(T data : array) add(data);
    }
    
    /**
     * Creates a new LinkedList from a LinkedList 
     * 
     * @param list The LinkedList to change to a LinkedList
     */
    public final void fromLinkedList(LinkedList list) {
        finalize();
        for(int i = 0; i < list.size(); i++) add((T)list.get(i));
    }
    
    /**
     * Creates an array with all the data in this LinkedList
     * 
     * @param data An array of the data of this LinkedList, any size
     * @return The LinkedList as an array
     */
    public T[] toArray(T[] data) {
        data = (T[])Array.newInstance(data.getClass().getComponentType(), length);
        for(int i = 0; i < length; i++) {
            data[i] = get(i);
        }
        return data;
    }

}
