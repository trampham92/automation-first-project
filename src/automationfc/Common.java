package automationfc;

import java.util.Random;

public class Common {
  public void sleepInSecond(long second) {
    try {
      Thread.sleep(second * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  
  public int randomInt() {
    return new Random().nextInt(9999);
  }
}
