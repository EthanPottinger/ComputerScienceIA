package globalmethods;

import collections.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author e.pottinger
 */
public class GlobalMethods {
    
    /**
     * This will take an input and convert it to an integer
     * 
     * @param text The message to display
     * @return The users input as an integer
     */
    public static int inputInt(String text) {
        String input = "";
        String warning = "";
        boolean valid = true;
        do {
            do {
                input = JOptionPane.showInputDialog(warning + text);
                valid = true;
                if(input == null || input.equals("")) {
                    
                    valid = false; 
                    warning = "Please input something\n\n";
                }  
            }
            while(valid == false);
            for(int i = 0; i < input.length(); i++) {
                char character = input.charAt(i);
                if(!Character.isDigit(character)) {
                    valid = false;
                    warning = "Input has to be a number\n\n";
                }
            }
        }
        while(valid == false);
        int integer = Integer.parseInt(input);
        return integer;
    }
    
    /**
     * This will take an input and convert it to an integer
     * 
     * @param text The message to display
     * @param lo The lowest value it can be
     * @param hi The highest value it can be
     * @return The users input
     */
    public static int inputInt(String text, int lo, int hi) {
        String input = "";
        String warning = "";
        boolean valid = true;
        int integer = 0;
        do {
            do {
                do {
                    input = JOptionPane.showInputDialog(warning + text);
                    valid = true;
                    if(input == null || input.equals("")) {
                    valid = false; 
                    warning = "Please input something\n\n";
                    }  
                }
                while(valid == false);
                for(int i = 0; i < input.length(); i++) {
                    char character = input.charAt(i);
                    if(!Character.isDigit(character)) {
                        valid = false;
                        warning = "Input has to be a number\n\n";
                    }
                }
            }
            while(valid == false);
            integer = Integer.parseInt(input);
            if(integer < lo || integer > hi) {
                valid = false;
                warning = "Input must be between " + lo + " and " + hi + "\n\n";
            } 
        }
        while(valid == false);
        return integer;
    } 
    
    /**
     * This takes an input and converts it to a character
     * 
     * @param text The text to display
     * @return the users input as a char
     */
    public static char inputChar(String text) {
        String input = "";
        String warning = "";
        boolean valid = true;
        do {
            input = JOptionPane.showInputDialog(warning + text);
            valid = true;
            if(input == null || input.equals("")) {
                valid = false; 
                warning = "Please input something\n\n";
            }
            else if(input.length() > 1) {
                valid = false;
                warning = "There should be only one character\n\n";
            }
        }
        while(valid == false);
        char character = input.charAt(0);
        return character;
    }
    
    public static String inputString(String dialog, String title) {
        String input = "";
        String warning = "";
        boolean valid = true;
        do {
            input = JOptionPane.showInputDialog(null, warning + dialog, title, JOptionPane.INFORMATION_MESSAGE);
            valid = true;
            if(input == null || input.equals("")) {
                valid = false;
                warning = "Please input something!";
            }
        }
        while(valid == false);
        return input;
    }
    
    public static int randInt(int lo, int hi) {
        return (int)(Math.random() * (hi - lo + 1) + lo);
    }
    
    public static void output(String text) {
        JOptionPane.showMessageDialog(null, text);
    }
    
    public static int minimum(int[] array) {
        return minimum(array, array.length - 1, array[0]);
    }
    private static int minimum(int[] array, int i, int min) {
        if(array[i] < min) min = array[i];
        if(i == 0) return min;       
        else return minimum(array, i - 1, min);
    }
    
    public static int maximum(int[] array) {
        return maximum(array, array.length - 1, array[0]);
    }
    private static int maximum(int[] array, int i, int max) {
        if(array[i] > max) max = array[i];
        if(i == 0) return max;
        else return maximum(array, i - 1, max);
    }
    public static int minimumIndex(int[] array) {
        return minimumIndex(array, array.length - 1, 0, array[0]);
    }
    private static int minimumIndex(int[] array, int i, int minIndex, int min) {
        if(array[i] < min) {
            minIndex = i;
            min = array[i];
        }
        if(i == 0) return minIndex;       
        else return minimumIndex(array, i - 1, minIndex, min);
    }
    
    public static int maximumIndex(int[] array) {
        return maximumIndex(array, array.length - 1, 0, array[0]);
    }
    private static int maximumIndex(int[] array, int i, int maxIndex, int max) {
        if(array[i] > max) {
            maxIndex = i;
            max = array[i];
        }
        if(i == 0) return maxIndex;       
        else return minimumIndex(array, i - 1, maxIndex, max);
    }
    
    public static void outputArray(int[] array) {
        String sad = "";
        for(int i = 0; i < array.length; i++) {
            sad += array[i] + ", ";
        }
        System.out.println(sad);
    }
    
    public static int[] sort(int[] array) {
        int[] sort = new int[array.length];
        final int DONE = maximum(array) + 1;
        for(int i = 0; i < array.length; i++) {
            sort[i] = minimum(array);
            array[minimumIndex(array)] = DONE; 
        }
        return sort;
    }
    
    public static String choose(String dialog, String title, String[] options) {
        boolean valid = true;
        String error = "";
        Object choice = "";
        do {
            valid = true;
            choice = JOptionPane.showInputDialog(null, error + dialog, title,
                    JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
            if(choice == null) {
                valid = false;
                error = "Please input a value!\n\n";
            }
        }
        while(valid == false);
        return choice.toString();
    }
    
    public static String choose(String dialog, String title, LinkedList<String> options) {
        String[] array = new String[0];
        array = options.toArray(array);
        boolean valid = true;
        String error = "";
        Object choice = "";
        do {
            valid = true;
            choice = JOptionPane.showInputDialog(null, error + dialog, title,
                    JOptionPane.PLAIN_MESSAGE, null, array, array[0]);
            if(choice == null) {
                valid = false;
                error = "Please input a value!\n\n";
            }
        }
        while(valid == false);
        return choice.toString();
    }
    
}
