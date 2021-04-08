package fi.utu.tech.gui.javafx.assignment2;

import fi.utu.tech.gui.javafx.WordIterator;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;


public class HashCrack {

    private final String hashHexString;
    private final int depth;
    private final char[] dictionary;
    private final String hashAlgorithm;
    private final String encoding;

    /**
     * 
     * @param hashHexString The hashed input value as a String representing
     *                      hexadecimal values. @.pre hashHexString.length() % 2 == 0
     * @param depth         The maximum length of combinations to try
     * @param dictionary    The characters to use in combinations
     * @param hashAlgorithm The hash algorithm used to hash the hashHexString (e.g.
     *                      md5)
     * @param encoding      The encoding used for the hashed text (e.g. utf-8)
     * @return The reversed hash or null if no match was found
     */
    public HashCrack(String hashHexString, int depth, char[] dictionary, String hashAlgorithm, String encoding) {
        this.hashHexString = hashHexString;
        this.depth = depth;
        this.dictionary = dictionary;
        this.hashAlgorithm = hashAlgorithm;
        this.encoding = encoding;
    }

    /**
     * Reverse the hash value by using a brute force algorithm
     * 
     * @throws NoSuchAlgorithmException     If the requested hash algorithm is not
     *                                      available
     * @throws UnsupportedEncodingException If the requested encoding is not
     *                                      supported
     */
    public String bruteForce() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        // Convert the "human readable" string to bytes for easier comparison
        var hashBytes = prepareHash(hashHexString);

        // Create a new WordIterator that will generate all the word combinations possible with dictionary and the given length
        WordIterator wi = new WordIterator(dictionary, depth);
        // Create an object that will hash our generated words
        MessageDigest md = MessageDigest.getInstance(hashAlgorithm);

        // Let's test all the combinations possible with given dictionary and depth
        // and see if they match to the given string
        while (wi.hasNext()) {
            var testString = wi.next();
            boolean match = Arrays.equals(md.digest(testString.getBytes(encoding)), hashBytes);
            // If the testString bytes are equal with the input hash bytes, we've got a
            // match
            if (match) {
                return testString;
            }
        }
        // If no match, return null
        return null;

    }

    /**
     * This will convert the human readable hex representation to bytes
     * No need to study this method
     * 
     * @param hashHexString
     * @return
     */
    private byte[] prepareHash(String hashHexString) {
        byte[] hashBytes = new byte[hashHexString.length() / 2];
        char[] hashChars = hashHexString.toCharArray();
        // Parse the characters into real bytes
        for (int i = 0; i < hashChars.length; i += 2) {
            hashBytes[i
                    / 2] = (byte) ((Character.digit(hashChars[i], 16) << 4) | (Character.digit(hashChars[i + 1], 16)));
        }

        return hashBytes;
    }

}
