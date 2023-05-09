package automationfc;

public class Common {
  public void sleepInSecond(long second) {
    try {
      Thread.sleep(second * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
