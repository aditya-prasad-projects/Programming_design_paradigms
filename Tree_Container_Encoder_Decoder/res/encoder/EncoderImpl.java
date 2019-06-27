package encoder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Performs huffman encoding on a file or a string input.
 */
public class EncoderImpl implements Encoder {
  private final List<String> validCodeSymbols;

  /**
   * Takes in a String of validSymbols.
   * @param validSymbols Represents the valid codeSymbols.
   */
  public EncoderImpl(String validSymbols) {
    this.validCodeSymbols = new ArrayList<String>(Arrays.asList(validSymbols.split("")));
  }

  @Override
  public Map<String, String> encode(String message) {
    Map<String, Long> frequency = new HashMap<String, Long>();
    Map<String, String> encoded = new HashMap<String, String>();
    PriorityQueue<String> characterQueue;
    String[] c = message.split("");
    frequency = Stream.of(c)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    for (String d:frequency.keySet()) {
      encoded.put(d,"");
    }
    characterQueue = new PriorityQueue<String>(frequency.size(),
            new EncoderComparator(frequency));
    characterQueue.addAll(frequency.keySet());
    while (characterQueue.size() > 1) {
      List<String> popped = new ArrayList<String>();
      long frequencyCount = 0;
      for (int i = 0; i < validCodeSymbols.size() && characterQueue.size() > 0; i++) {
        popped.add(characterQueue.poll());
      }
      for (int i = 0;i < popped.size(); i++) {
        for (int j = 0;j < popped.get(i).length(); j++) {
          String a = encoded.get(Character.toString(popped.get(i).charAt(j)));
          a = validCodeSymbols.get(i) + a;
          encoded.put(Character.toString(popped.get(i).charAt(j)), a);
        }
        frequencyCount = frequencyCount + frequency.get(popped.get(i));
      }
      String q = String.join("",popped);
      frequency.put(q, frequencyCount);
      characterQueue.add(q);
    }
    return encoded;
  }

  @Override
  public String generateCode(String message) {
    Map<String,String> map = new HashMap<String, String>();
    map = encode(message);
    String s = "";
    for (int i = 0;i < message.length(); i++) {
      s = s + map.get(Character.toString(message.charAt(i)));
    }
    return s;
  }

  @Override
  public Map<String, String> encodeFile(String fileName) throws IOException {
    String data = "";
    data = new String(Files.readAllBytes(Paths.get(fileName)));

    return encode(data);
  }

  @Override
  public String generateCodeFromFile(String fileName) throws IOException {
    Map<String,String> map = new HashMap<String, String>();
    String data = "";
    data = new String(Files.readAllBytes(Paths.get(fileName)));
    map = encode(data);
    String s = "";
    for (int i = 0;i < data.length(); i++) {
      s = s + map.get(Character.toString(data.charAt(i)));
    }
    return s;
  }
}
