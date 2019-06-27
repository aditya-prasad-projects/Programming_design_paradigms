package decoder;

import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Used to create a decoding tree. Can create decoding tree with any coding symbols.
 * It can decode a given message and return the decoded message.
 */
public class DecoderImpl implements Decoder {
  private final List<String> validCodeSymbols;
  private TreeNode root;
  private Map<String, String> map;

  /**
   * Takes in the valid code symbols that can be used to create the decoding tree.
   * @param codeSymbol contains the valid code symbols that can be used to create the decoding tree.
   */
  public DecoderImpl(String codeSymbol) {
    if (codeSymbol == null || codeSymbol.length() < 1  ) {
      throw new IllegalStateException("Invalid codeSymbols");
    }
    this.validCodeSymbols = new ArrayList<String>(Arrays.asList(codeSymbol.split("")));
    root = new LeafNode("", validCodeSymbols);
    map = new HashMap<String, String>();
  }

  @Override
  public void addCode(char ch, String code) {
    if (Character.toString(ch).equals("") || code == null
            || code.equals("")) {
      throw new IllegalStateException("Cant add empty code and symbol to a tree");
    }
    for (int i = 0; i < code.length(); i++) {
      if (!(validCodeSymbols.contains(code.substring(i, i + 1)))) {
        throw new IllegalStateException("Invalid code");
      }
    }
    for (int i = 0; i < code.length(); i++) {
      if (map.containsValue(code.substring(0, i + 1))) {
        throw new IllegalStateException("Prefix is already present");
      }
    }
    if (map.containsKey(Character.toString(ch))  ) {
      throw new IllegalStateException("Character already present in the tree");
    }

    map.put(Character.toString(ch), code);
    for (int i = 0; i < code.length(); i++) {
      TreeNode n = new LeafNode(code.substring(i, i + 1), validCodeSymbols);
      if (!isPresent(code.substring(0, i + 1))) {
        if (i == code.length() - 1) {
          //TreeNode n = new LeafNode(a)
          root = root.addNode(code, n, Character.toString(ch));
        } else {
          root = root.addNode(code.substring(0, i + 1), n, code.substring(i, i + 1));
        }
      }
    }
  }

  @Override
  public String decode(String encodedMessage) {
    if (encodedMessage == null || encodedMessage.equals("")) {
      throw new IllegalStateException("Decoding message is invalid");
    }
    if (root.decode(encodedMessage, 0).equals("")) {
      throw new IllegalStateException("Tree is empty");
    }
    int p = 0;
    String a = "";
    while (p < encodedMessage.length()) {
      String c = root.decode(encodedMessage, p);
      a = a + c;
      p = p + map.get(c).length();
    }
    return a;
  }

  @Override
  public String allCodes() {
    String s = "";
    for (Map.Entry<String, String> entry : map.entrySet()) {
      s = s + entry.getKey() + ":" + entry.getValue() + "\n";
    }
    return s;
  }

  @Override
  public boolean isCodeComplete() {
    boolean b = false;
    if (root.isLeafNode()) {
      return false;
    }
    try {
      b = root.isCodeComplete(validCodeSymbols);
    } catch (IllegalArgumentException e) {
      return false;
    }
    return b;
  }

  private boolean isPresent(String code) {
    return root.isPresent(code);
  }
}


