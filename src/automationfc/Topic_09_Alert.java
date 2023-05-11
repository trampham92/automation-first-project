package automationfc;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Alert {
  WebDriver driver;
  WebDriverWait explicitWait;
  Alert alert;
  String projectPath = System.getProperty("user.dir");
  Common common = new Common();

  @BeforeClass
  public void beforeClass() {
    System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
    driver = new ChromeDriver();
    explicitWait = new WebDriverWait(driver, 10);
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.manage().window().maximize();
  }

  @Test
  public void tc01AcceptAlert() {
    driver.get("https://automationfc.github.io/basic-form/index.html");
    driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
    alert = explicitWait.until(ExpectedConditions.alertIsPresent());
    // Verify display message in alert
    Assert.assertEquals(alert.getText(), "I am a JS Alert");
    // Accept alert
    alert.accept();
    // Verify display result message
    Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");

  }

  @Test
  public void tc02ConfirmAlert() {
    driver.get("https://automationfc.github.io/basic-form/index.html");
    driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
    alert = explicitWait.until(ExpectedConditions.alertIsPresent());
    // Verify display message in alert confirm
    Assert.assertEquals(alert.getText(), "I am a JS Confirm");
    // Cancel alert
    alert.dismiss();
    // Verify display result message
    Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");

  }

  @Test
  public void tc03PromptAlert() {
    driver.get("https://automationfc.github.io/basic-form/index.html");
    driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
    alert = explicitWait.until(ExpectedConditions.alertIsPresent());
    // Verify display message in alert prompt
    Assert.assertEquals(alert.getText(), "I am a JS prompt");
    // Input into prompt alert
    String inputTextString = "test";
    alert.sendKeys(inputTextString);
    // Accept alert
    alert.accept();
    // Verify display result message
    Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: " + inputTextString);
  }

  @Test
  public void tc04Alert() {
    driver.get("https://netbanking.hdfcbank.com/netbanking/");
    explicitWait.until(ExpectedConditions
        .frameToBeAvailableAndSwitchToIt(driver.findElement(By.cssSelector("frame[name='login_page']"))));
    driver.findElement(By.xpath("//a[text()='CONTINUE']")).click();
    alert = explicitWait.until(ExpectedConditions.alertIsPresent());
    // Verify display message in alert
    Assert.assertEquals(alert.getText(), "Customer ID  cannot be left blank.");
    // Accept alert
    alert.accept();
  }

  @Test
  public void tc05AuthenticationAlert() {
    driver.get(passUsernameAndPasswordtoURL("http://the-internet.herokuapp.com/basic_auth", "admin", "admin"));
    common.sleepInSecond(2);
    Assert.assertEquals(driver.findElement(By.cssSelector("div#content p")).getText(),
        "Congratulations! You must have the proper credentials.");
  }

  public String passUsernameAndPasswordtoURL(String url, String userName, String password) {
    String[] arrayURL = url.split("//");
    return arrayURL[0] + "//" + userName + ":" + password + "@" + arrayURL[1];
  }

  @AfterClass
  public void afterClass() {
    driver.quit();
  }
}
