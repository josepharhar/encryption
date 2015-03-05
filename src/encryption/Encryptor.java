package encryption;

public class Encryptor {
    public static String encrypt(String inputText, long shift) {
        // Get an array of chars from the string to encrypt
        char[] charArray = inputText.toCharArray();
        
        // Shift each char in the string
        for (int i = 0; i < charArray.length; i++) {
            // Prevent overflow
            shift %= 1 << 16;
            // Shift this char
            charArray[i] += shift;
        }
        
        // Make a new string from the modified chars and return it
        return new String(charArray);
    }
    
    public static String decrypt(String inputText, long shift) {
        return encrypt(inputText, -shift);
    }
}
