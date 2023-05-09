package automationfc;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_04_Run_On_Browsers {
  WebDriver driver;
  String projectPath = System.getProperty("user.dir");

  @Test
  public void runOnChrome() {
    System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
    driver = new ChromeDriver();
    driver.get("https://www.facebook.com/");
    driver.quit();
  }

  @Test
  public void runOnFirefox() {
    System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
    driver = new FirefoxDriver();
    driver.get("https://www.facebook.com/");
    driver.quit();
  }
}
