import decoder.DecoderImpl;
import encoder.Encoder;
import encoder.EncoderImpl;
import decoder.Decoder;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests EncodeImpl.
 */
public class TestEncoderImpl {
  private Encoder e;
  private Decoder d;
  private Encoder encoder;
  private Decoder decoder;

  /**
   * Initializes decoder and encoder objects.
   */
  @Before
  public void setup() {
    e = new EncoderImpl("01");
    d = new DecoderImpl("01");
    encoder = new EncoderImpl("0123456789abcdef");
    decoder = new DecoderImpl("0123456789abcdef");
  }

  /**
   * Tests if message is encoded correctly for binary coding when taken directly from a string.
   * It tests it by passing it to Decoder and checks for correctness.
   * @throws IOException If the file cannot be read.
   */
  @Test
  public void test1() throws IOException {
    try {
      checkFromString(e, d);
    }
    catch (IOException e) {
      fail();
    }
  }


  private void checkFromString(Encoder e, Decoder d) throws IOException {
    Map<String,String> map = new HashMap<String, String>();
    map = e.encode("abcdefbcddeefff");
    for (Map.Entry<String,String> entry : map.entrySet()) {
      d.addCode(entry.getKey().charAt(0), entry.getValue());
    }
    String s = e.generateCode("abcdefbcddeefff");
    assertEquals(d.decode(s), "abcdefbcddeefff");
  }

  /**
   * Tests if message is encoded correctly for hexadecimal coding when taken directly from a string.
   * It tests it by passing it to Decoder and checks for correctness.
   * @throws IOException If the file cannot be read.
   */
  @Test
  public void test2() throws IOException {
    try {
      checkFromString(encoder,decoder);
    }
    catch (IOException e) {
      fail();
    }
  }

  /**
   * Tests if message is encoded correctly for binary coding when taken from a file.
   * It tests it by passing it to Decoder and checks for correctness.
   * @throws IOException If the file cannot be read.
   */
  @Test
  public void test3() throws IOException {
    try {
      checkFromFile(e,d);
    }
    catch (IOException e) {
      fail();
    }
  }

  private void checkFromFile(Encoder e, Decoder d) throws IOException {
    Map<String,String> map = new HashMap<String, String>();
    try {
      map = e.encodeFile("/Users/adityaprasad/Documents/passage.txt");
    }
    catch (IOException c) {
      fail();
    }
    for (Map.Entry<String,String> entry : map.entrySet()) {
      d.addCode(entry.getKey().charAt(0), entry.getValue());
    }
    String s = "";
    try {
      s = e.generateCodeFromFile("/Users/adityaprasad/Documents/passage.txt");
    }
    catch (IOException c) {
      fail();
    }
    String data = "";
    data = new String(Files.readAllBytes(Paths.get("/Users/adityaprasad/Documents/passage.txt")));
    assertEquals(d.decode(s), data);
  }

  /**
   * Tests if message is encoded correctly for Hexadecimal coding when taken from a file.
   * It tests it by passing it to Decoder and checks for correctness.
   * @throws IOException If the file cannot be read.
   */
  @Test
  public void test4() throws IOException {
    try {
      checkFromFile(encoder,decoder);
    }
    catch (IOException e) {
      fail();
    }
  }

  /**
   * Tests for when the message to be encoded is smaller than the validCodeSymbols.
   * Checks if PriorityQueues is implemented correctly.
   * @throws IOException If the file cannot be read.
   */
  @Test
  public void test5() throws IOException {
    Encoder p = new EncoderImpl("0123456789abcdef");
    Decoder q = new DecoderImpl("0123456789abcdef");
    Map<String,String> map = new HashMap<String, String>();
    map = e.encode("ab");
    for (Map.Entry<String,String> entry : map.entrySet()) {
      d.addCode(entry.getKey().charAt(0), entry.getValue());
    }
    String s = e.generateCode("ab");
    assertEquals(d.decode(s), "ab");
  }
}
