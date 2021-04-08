package fi.utu.tech.gui.javafx;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ArrayDeque;
import java.util.Deque;


public class WordIterator implements Iterator<String> {

    // A default dictionary for testing combinations. Includes numbers, latin
    // alphabet + some nordic characters, space and newlines
    // No other "special characters" included.
    public final static char[] DEFAULT_DICT = { ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C',
            'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
            't', 'u', 'v', 'w', 'x', 'y', 'z', 'Ä', 'Ö', 'ä', 'ö', '\n', '\r' };

    private Deque<String> q = new ArrayDeque<>();
    private final char[] dict;
    private final int depth;
    private final int size;
    private int currentIndex = 0;

    /**
     * Crete an iterator that will generate every combination of characters given
     * until the depth (word size) limit has been reached
     * <p>
     * Please be aware that the amount of combinations follows the geometric
     * progression S=a(1-q^n)/(1-q)
     * </p>
     * 
     * @param dictionary Array of characters to be used for generation
     * @param depth      The maximum length of the words
     */
    public WordIterator(char[] dictionary, int depth) {
        q.addFirst("");
        this.dict = dictionary;
        this.depth = depth;
        // Size is a constant during the lifetime of the object
        // no need to recalculate on every request of size
        this.size = calculateSize(dict.length, depth);
    }

    private int calculateSize(int dictSize, int depth) {
        // Calculate geometric sequence using iterative algorthm
        int sum = 0;
        for (int k = 0; k <= depth; k++) {
            sum += Math.pow(dictSize, k);
        }
        return sum;

    }

    public String next() {
        if (q.isEmpty()) {
            throw new NoSuchElementException();
        }
        String path = q.removeFirst();
        if (path.length() < depth) {
            for (int i = 0; i < dict.length; i++) {
                q.addFirst(path + dict[i]);
            }
        }
        currentIndex++;
        return path;

    }

    public boolean hasNext() {
        return !q.isEmpty();
    }

    /**
     * Returns the current "index" of the word traversal as an simple integer. Does
     * not carry any depth information Useful for determining the current progress
     * (getCurrentIndex()/size())
     * 
     * @return The current index
     */
    public int getCurrentIndex() {
        return this.currentIndex;
    }

    /**
     * The total amount of combinations that will be generated at maximum.
     * <p>
     * This amount is determined by the size of the provided dictionary and the
     * requested depth. Value is pre-calculated.
     * </p>
     * 
     * @return The limit of combinations to generate
     */
    public int size() {
        return this.size;
    }

}