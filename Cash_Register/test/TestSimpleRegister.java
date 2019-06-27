import java.util.Map;
import java.util.TreeMap;

import cs5010.register.CashRegister;
import cs5010.register.InsufficientCashException;
import cs5010.register.SimpleRegister;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests SimpleRegister.
 */
public class TestSimpleRegister {
  private CashRegister cashRegister;

  @Before
  public void setup() {
    cashRegister = new SimpleRegister();
  }

  /**
   * Tests the operations of a cash register.
   */
  @Test
  public void test1() throws InsufficientCashException {
    cashRegister.addPennies(5);
    cashRegister.addDimes(2);
    cashRegister.addDimes(3);
    cashRegister.addFives(10);
    cashRegister.addNickels(4);
    cashRegister.addOnes(1);
    cashRegister.addQuarters(1);
    cashRegister.addTens(5);
    Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
    map.put(1000, 5);
    map.put(500, 10);
    map.put(100, 1);
    map.put(25, 1);
    map.put(10, 5);
    map.put(5, 4);
    map.put(1, 5);
    assertEquals(map, cashRegister.getContents());
    Map<Integer, Integer> map1 = new TreeMap<Integer, Integer>();
    map1.put(1, 5);
    map1.put(5, 4);
    map1.put(10, 5);
    map1.put(25, 1);
    map1.put(100, 1);
    map1.put(1000, 2);
    try {
      assertEquals(map1, cashRegister.withdraw(22, 0));
    } catch (InsufficientCashException e) {
      fail();
    }
    map.put(1000, 3);
    map.put(500, 10);
    map.put(100, 0);
    map.put(25, 0);
    map.put(10, 0);
    map.put(5, 0);
    map.put(1, 0);
    assertEquals(map, cashRegister.getContents());
    String s = "Deposit: 0.05\n"
            + "Deposit: 0.20\n"
            + "Deposit: 0.30\n"
            + "Deposit: 50.00\n"
            + "Deposit: 0.20\n"
            + "Deposit: 1.00\n"
            + "Deposit: 0.25\n"
            + "Deposit: 50.00\n"
            + "Withdraw: 22.00";
    assertEquals(s, cashRegister.getAuditLog());
  }

  /**
   * Tests when cashRegister has cash but not enough of small denominations.
   */
  @Test
  public void test2() {
    cashRegister.addTens(5);
    try {
      cashRegister.withdraw(0, 5);
      fail();
    } catch (InsufficientCashException e) {
      assertEquals(e.getMessage(), "Not enough denominations");
    }
  }

  /**
   * Tests getContents when cashRegister is empty.
   */
  @Test
  public void test3() {
    Map<Integer, Integer> map1 = new TreeMap<Integer, Integer>();
    assertEquals(map1, cashRegister.getContents());
  }

  /**
   * Tests withdraw multiple times till cashRegister is empty.
   */
  @Test
  public void test4() {
    Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
    cashRegister.addPennies(99);
    cashRegister.addDimes(2);
    cashRegister.addDimes(3);
    cashRegister.addFives(10);
    cashRegister.addNickels(4);
    cashRegister.addOnes(1);
    cashRegister.addQuarters(1);
    cashRegister.addTens(5);
    map.put(5, 1);
    map.put(1000, 1);
    try {
      assertEquals(map, cashRegister.withdraw(10, 5));
    } catch (InsufficientCashException e) {
      fail();
    }
    map.put(1, 9);
    map.put(5, 3);
    map.put(10, 5);
    map.put(25, 1);
    map.put(1000, 4);
    try {
      assertEquals(map, cashRegister.withdraw(40, 99));
    } catch (InsufficientCashException e) {
      fail();
    }
    Map<Integer, Integer> map1 = new TreeMap<Integer, Integer>();
    map1.put(1000, 0);
    map1.put(500, 10);
    map1.put(100, 1);
    map1.put(25, 0);
    map1.put(10, 0);
    map1.put(5, 0);
    map1.put(1, 90);
    Map<Integer, Integer> map2 = new TreeMap<Integer, Integer>();
    map2.put(1, 90);
    map2.put(500, 10);
    assertEquals(map1, cashRegister.getContents());
    try {
      assertEquals(map2, cashRegister.withdraw(50, 90));
    } catch (InsufficientCashException e) {
      fail();
    }
    try {
      assertEquals(map2, cashRegister.withdraw(50, 90));
    } catch (InsufficientCashException e) {
      assertEquals(e.getMessage(), "Insufficient funds");
    }
  }

  /**
   * Tests if withdraw can take more than 99 cents.
   */
  @Test
  public void test5() throws InsufficientCashException {
    Map<Integer, Integer> map1 = new TreeMap<Integer, Integer>();
    map1.put(1000, 0);
    map1.put(500, 10);
    map1.put(100, 1);
    map1.put(25, 0);
    map1.put(10, 0);
    map1.put(5, 0);
    map1.put(1, 99);
    try {
      assertEquals(map1, cashRegister.withdraw(50, 100));
    } catch (InsufficientCashException e) {
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Invalid arguments");
    }
  }

  /**
   * Tests if we can call withdraw when cashRegistry is empty.
   */
  @Test
  public void test6() {
    Map<Integer, Integer> map1 = new TreeMap<Integer, Integer>();
    try {
      assertEquals(map1, cashRegister.withdraw(50, 100));
    } catch (InsufficientCashException e) {
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Invalid arguments");
    }
    assertEquals("", cashRegister.getAuditLog());
  }

  /**
   * Tests audit log for deposit.
   */
  @Test
  public void test7() {
    cashRegister.addPennies(99);
    cashRegister.addDimes(2);
    cashRegister.addDimes(3);
    cashRegister.addFives(10);
    cashRegister.addNickels(4);
    cashRegister.addOnes(1);
    cashRegister.addQuarters(1);
    cashRegister.addTens(5);
    String s = "Deposit: 0.99\n"
            + "Deposit: 0.20\n"
            + "Deposit: 0.30\n"
            + "Deposit: 50.00\n"
            + "Deposit: 0.20\n"
            + "Deposit: 1.00\n"
            + "Deposit: 0.25\n"
            + "Deposit: 50.00";
    assertEquals(s, cashRegister.getAuditLog());
  }

  /**
   * Tests auditLog when cashRegistry is empty.
   */
  @Test
  public void test8() {
    String s = "";
    assertEquals(s, cashRegister.getAuditLog());
  }

  /**
   * Tests add for negative.
   */
  @Test
  public void test9() {
    try {
      cashRegister.addPennies(-2);
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "cant add negative value");
    }
    try {
      cashRegister.addDimes(-1);
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "cant add negative value");
    }
    try {
      cashRegister.addDimes(-1);
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "cant add negative value");
    }
    try {
      cashRegister.addFives(-3);
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "cant add negative value");
    }
    try {
      cashRegister.addNickels(-100);
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "cant add negative value");
    }
    try {
      cashRegister.addNickels(-1);
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "cant add negative value");
    }
    try {
      cashRegister.addOnes(-1);
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "cant add negative value");
    }
    try {
      cashRegister.addQuarters(-5);
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "cant add negative value");
    }
    try {
      cashRegister.addTens(-5);
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "cant add negative value");
    }
  }

  /**
   * Tests if all the add functions works correctly.
   */
  @Test
  public void test10() {
    Map<Integer, Integer> map1 = new TreeMap<Integer, Integer>();
    cashRegister.addPennies(199);
    map1.put(1,199);
    assertEquals(map1, cashRegister.getContents());
    cashRegister.addDimes(2);
    map1.put(10,2);
    assertEquals(map1, cashRegister.getContents());
    cashRegister.addDimes(3);
    map1.put(10,5);
    assertEquals(map1, cashRegister.getContents());
    cashRegister.addFives(10);
    map1.put(500,10);
    assertEquals(map1, cashRegister.getContents());
    cashRegister.addNickels(4);
    map1.put(5,4);
    assertEquals(map1, cashRegister.getContents());
    cashRegister.addOnes(1);
    map1.put(100,1);
    assertEquals(map1, cashRegister.getContents());
    cashRegister.addQuarters(1);
    map1.put(25,1);
    assertEquals(map1, cashRegister.getContents());
    cashRegister.addTens(5);
    map1.put(1000,5);
    assertEquals(map1, cashRegister.getContents());
  }

  /**
   * Tests if we can add zero.
   */
  @Test
  public void test11() {
    cashRegister.addPennies(0);
    assertEquals("", cashRegister.getAuditLog());
    cashRegister.addDimes(0);
    assertEquals("", cashRegister.getAuditLog());
    cashRegister.addDimes(0);
    assertEquals("", cashRegister.getAuditLog());
    cashRegister.addFives(0);
    assertEquals("", cashRegister.getAuditLog());
    cashRegister.addNickels(0);
    assertEquals("", cashRegister.getAuditLog());
    cashRegister.addOnes(0);
    assertEquals("", cashRegister.getAuditLog());
    cashRegister.addQuarters(0);
    assertEquals("", cashRegister.getAuditLog());
    cashRegister.addTens(0);
    assertEquals("", cashRegister.getAuditLog());
  }

  /**
   * Checks if we can getContent returns a new map.
   */
  @Test
  public void test12() {
    cashRegister.addPennies(99);
    cashRegister.addDimes(2);
    cashRegister.addDimes(3);
    cashRegister.addFives(10);
    cashRegister.addNickels(4);
    cashRegister.addOnes(1);
    cashRegister.addQuarters(1);
    cashRegister.addTens(5);
    Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
    map.put(1000,5);
    map.put(500,10);
    map.put(100,1);
    map.put(25,1);
    map.put(10,5);
    map.put(5,4);
    map.put(1,99);

    assertEquals(map,cashRegister.getContents());
    Map<Integer, Integer> map1 = new TreeMap<Integer, Integer>();
    map1 = cashRegister.getContents();
    map1.put(0,1);
    assertEquals(map,cashRegister.getContents());
  }
}
