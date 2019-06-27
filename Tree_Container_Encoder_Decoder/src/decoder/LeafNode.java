package decoder;

import java.util.List;

public class LeafNode implements TreeNode {
  private String data;
  private List<String> validCodeSymbols;

  LeafNode(String data, List<String> validCodeSymbols) {
    this.data = data;
    this.validCodeSymbols = validCodeSymbols;
  }

  @Override
  public String getData() {
    return this.data;
  }

  @Override
  public void setData(String data) {
    this.data = data;
  }

  @Override
  public boolean isPresent(String code) {
    return false;
  }

  @Override
  public boolean isLeafNode() {
    return true;
  }

  @Override
  public boolean isCodeComplete(List<String> validCodeSymbols) {
    return true;
  }

  @Override
  public String decode(String encodedMessage, int p) {
    return this.data;
  }

  @Override
  public TreeNode addNode(String path, TreeNode n, String value) {
    if (this.validCodeSymbols.contains(n.getData())) {
      GroupNode newSelf = new GroupNode();
      n.setData(value);
      newSelf.addNode(path, n, value);

      this.data = value;
      return newSelf;
    }
    //this.data = value;
    return this;
  }
}


