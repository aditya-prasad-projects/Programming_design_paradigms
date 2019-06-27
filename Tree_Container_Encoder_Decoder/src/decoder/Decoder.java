package decoder;

/**
 * Used to create a decoding tree, and decode a message. Provides methods to add codes to the
 * decoding tree, decode a message, return all the codes entered till now and find out if the tree
 * is complete or not.
 */
public interface Decoder {

  /**
   * Used to add character and its respective codes to a tree.
   * It does not add characters or codes to the tree if its already present.
   * It throws an IllegalStateException if code is invalid.
   * @param ch the character to be decoded
   * @param code the code for the character
   */
  void addCode(char ch, String code);

  /**
   * Takes in a message to be decoded and returns the decoded message.
   * It throws a IllegalStateException if the message is not correct,
   * or searches for code not present in the leaf.
   * @param encodedMessage The message to be decoded
   * @return the decoded message
   */
  String decode(String encodedMessage);

  /**
   * Returns all the codes added to the tree till now in the form x:yyy.
   * Returns empty string if tree is empty.
   * @return A string of the form x:yyy
   */
  String allCodes();

  /**
   * Checks if the tree is complete or not. If it is complete,
   * more characters cannot be added to the tree.
   * @return returns true if the tree is complete, false otherwise.
   */
  boolean isCodeComplete();

}
