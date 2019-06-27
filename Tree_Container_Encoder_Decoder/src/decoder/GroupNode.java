package decoder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a Group node in a tree. It contains a HashMap of its children.
 * With the key being the String representing the node and the value being the child node.
 * It also contains a boolean which is true if it is complete, false otherwise.
 */
public class GroupNode implements TreeNode {
  private Map<String, TreeNode> child;
  private static boolean b = false;

  /**
   * Used to initialize the HashMap representing the child nodes.
   */
  GroupNode() {
    child = new HashMap<String, TreeNode>();
  }

  @Override
  public TreeNode addNode(String path, TreeNode n, String value) {
    for (String s : child.keySet()) {
      if (s.equals(path.substring(0, 1))) {
        TreeNode node = child.get(s).addNode(path.substring(1), n, value);
        child.remove(s);
        child.put(s, node);
        return this;
      }
    }
    n.setData(value);
    child.put(path.substring(0, 1), n);
    return this;
  }

  @Override
  public String getData() {
    return null;
  }

  @Override
  public void setData(String data) {
    isLeafNode();
  }

  @Override
  public boolean isPresent(String code) {
    for (String s : child.keySet()) {
      if (s.equals(code.substring(0, 1))) {
        if (code.length() > 1) {
          return child.get(s).isPresent(code.substring(1));
        }
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean isLeafNode() {
    return false;
  }

  @Override
  public boolean isCodeComplete(List<String> validCodeSymbols) {
    if (child.size() != validCodeSymbols.size()) {
      return false;
    }
    for (String s : child.keySet()) {
      b = child.get(s).isCodeComplete(validCodeSymbols);
      if (!b) {
        throw new IllegalArgumentException();
      }
    }
    return b;
  }

  @Override
  public String decode(String encodedMessage, int p) {
    for (String s : child.keySet()) {
      if (s.equals(Character.toString(encodedMessage.charAt(p)))) {
        p++;
        return child.get(s).decode(encodedMessage, p);
      }
    }
    throw new IllegalStateException("Decoding message is invalid");
  }
}


