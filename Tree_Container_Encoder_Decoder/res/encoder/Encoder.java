package encoder;

import java.io.IOException;
import java.util.Map;

/**
 * Implements huffman encoding to encode messages.
 */
public interface Encoder {

  /**
   * Used to encode a given message using huffman encoding and returns a map of encoded messages.
   * @param message the message to be encoded.
   * @return a HashMap whose key is the String to be encoded and the value is the encoded message.
   */
  Map<String, String> encode(String message);

  /**
   * Generates a encoded String for a given message.
   * @param message the message to be encoded.
   * @return a encoded string.
   */
  String generateCode(String message);

  /**
   *Takes in a file location and returns a map of encoded messages.
   * @param fileName the file whose content is to be encoded
   * @return a HashMap whose key is the String to be encoded and the value is the encoded message.
   * @throws IOException If the file cannot be read.
   */
  Map<String,String>  encodeFile(String fileName) throws IOException;

  /**
   * Takes in a file location and Generates a encoded String for a given message.
   * @param fileName the file whose content is to be encoded
   * @return a encoded string.
   * @throws IOException If the file cannot be read.
   */
  String generateCodeFromFile(String fileName) throws IOException;
}
