package cs5010.register;

import java.util.Collections;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Implementation of a cashRegister.
 */
public class SimpleRegister implements CashRegister {
  private NavigableMap<Integer, Integer> register;
  private String s = "";

  /**
   * Initiates the cashRegister.
   */
  public SimpleRegister() {
    register = new TreeMap<Integer, Integer>(Collections.reverseOrder());
  }

  @Override
  public void addPennies(int num) {
    addCash(1, num);
    if (num > 0) {
      s = s  + "Deposit: " + String.format("%.2f",0.01 * num) + "\n";
    }

  }

  @Override
  public void addNickels(int num) {
    addCash(5, num);
    if (num > 0) {
      s = s + "Deposit: " + String.format("%.2f",0.05 * num) +  "\n";
    }

  }

  @Override
  public void addDimes(int num) {
    addCash(10, num);
    if (num > 0) {
      s = s + "Deposit: " + String.format("%.2f", 0.10 * num) + "\n";
    }

  }

  @Override
  public void addQuarters(int num) {
    addCash(25, num);
    if (num > 0) {
      s = s + "Deposit: " + String.format("%.2f", 0.25 * num) + "\n";
    }

  }

  @Override
  public void addOnes(int num) {
    addCash(100, num);
    if (num > 0) {
      s = s  + "Deposit: " + String.format("%.2f", 1.00 * num) + "\n";
    }

  }

  @Override
  public void addFives(int num) {
    addCash(500, num);
    if (num > 0) {
      s = s  + "Deposit: " + String.format("%.2f",5.00 * num) + "\n";
    }

  }

  @Override
  public void addTens(int num) {
    addCash(1000, num);
    if (num > 0) {
      s = s + "Deposit: " + String.format("%.2f", 10.00 * num) + "\n";
    }
  }

  @Override
  public Map<Integer, Integer> withdraw(int dollars, int cents) throws InsufficientCashException {
    if (dollars < 0 || cents < 0 || cents > 99) {
      throw new IllegalArgumentException("Invalid arguments");
    }
    NavigableMap<Integer, Integer> register1 = new TreeMap<Integer, Integer>();
    long a = 0;
    int total = dollars * 100 + cents;
    int total1 = total;
    for (Map.Entry<Integer, Integer> entry: register.entrySet()) {
      a = a + entry.getKey() * entry.getValue();
    }
    if (a < total) {
      throw new InsufficientCashException("Insufficient funds");
    }
    NavigableMap<Integer, Integer> register2 =
            new TreeMap<Integer, Integer>(Collections.reverseOrder());
    for (NavigableMap.Entry<Integer, Integer> entry: register.entrySet()) {
      register2.put(entry.getKey(), entry.getValue());
    }
    for (NavigableMap.Entry<Integer, Integer> entry: register2.entrySet()) {
      if (total > 0) {
        int b = 0;
        for (int i = 1; i <= entry.getValue(); i++) {
          if (entry.getValue() > 0 && entry.getKey() <= total) {
            total -= entry.getKey();
            register1.put(entry.getKey(), i);
          }
          else {
            break;
          }
          b = i;
        }
        register2.put(entry.getKey(), entry.getValue() - b);
      }
      else {
        break;
      }
    }
    if (total > 0) {
      throw new InsufficientCashException("Not enough denominations");
    }
    for (NavigableMap.Entry<Integer, Integer> entry: register2.entrySet()) {
      register.put(entry.getKey(), entry.getValue());
    }
    s = s + "Withdraw: " + String.format("%.2f", total1 / 100.00) + "\n";
    return register1;
  }

  @Override
  public Map<Integer, Integer> getContents() {
    NavigableMap<Integer, Integer> register2 =
            new TreeMap<Integer, Integer>(Collections.reverseOrder());
    for (NavigableMap.Entry<Integer, Integer> entry: register.entrySet()) {
      register2.put(entry.getKey(), entry.getValue());
    }
    return register2;
  }

  @Override
  public String getAuditLog() {
    if (s.length() != 0 && s.substring(s.length() - 1).equals("\n")) {
      return s.substring(0,s.length() - 1);
    }
    return s;
  }

  /**
   * Private helper method, used to add cash to the register.
   * @param key the value of the bill to be added.
   * @param value the number of bills of type key.
   */
  private void addCash(int key, int value) {
    if (value < 0) {
      throw new IllegalArgumentException("cant add negative value");
    }
    if (register.containsKey(key)) {
      register.put(key, value + register.get(key));
    }
    else {
      register.put(key, value);
    }


  }
}
