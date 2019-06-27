package decoder;

import java.util.List;

/**
 * Represents each node in the tree.
 */
interface TreeNode {

  /**
   * Used to add a Node to the tree.
   * @param path Specifies the path where the node is supposed to be added.
   * @param n Represents the node to be added to the specific node.
   * @param value Represents the node's value
   * @return a TreeNode after adding it to the tree
   */
  TreeNode addNode(String path, TreeNode n, String value);

  /**
   * Returns the data stored in the node.
   * @return the data stored in the node.
   */
  String getData();

  /**
   * Used to set the data stored in the node.
   * @param data The data to replace the current data
   */
  void setData(String data);

  /**
   * Checks if the child is present in its list.
   * @param code represents the code to be checked was its presence in the tree.
   * @return true if code is present in the tree.
   */
  boolean isPresent(String code);

  /**
   * Checks if current node is a LeafNode.
   * @return true if it is LeafNode, false otherwise
   */
  boolean isLeafNode();

  /**
   * Checks if current node is complete, if it is it calls the method on its children.
   * @param validCodeSymbols the valid set of symbols.
   * @return true if node is complete.
   */
  boolean isCodeComplete(List<String> validCodeSymbols);

  /**
   * Used to decode the message passed to it.
   * @param encodedMessage the message to be decoded
   * @param p the length already decoded in the string
   * @return the decoded string
   */
  String decode(String encodedMessage, int p);

}


