package automationfc;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_12_Windows_Tab {
  WebDriver driver;
  Alert alert;
  WebDriverWait explicitWait;
  String projectPath = System.getProperty("user.dir");
  String osName = System.getProperty("os.name");
  Common common = new Common();

  @BeforeClass
  public void beforeClass() {
    System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
    Map<String, Object> prefs = new HashMap<String, Object>();
    prefs.put("profile.default_content_setting_values.notifications", 2);
    ChromeOptions options = new ChromeOptions();
    options.setExperimentalOption("prefs", prefs);
    driver = new ChromeDriver(options);
    explicitWait = new WebDriverWait(driver, 10);
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.manage().window().maximize();
  }

  @Test
  public void tc01WindowsTab() {
    driver.get("https://automationfc.github.io/basic-form/index.html");
    String basicFormWindowID = driver.getWindowHandle();
    driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
    common.sleepInSecond(2);
    // Switch to Google
    switchToWindowByID(basicFormWindowID);
    Assert.assertEquals(driver.getTitle(), "Google");
    String googleWindowID = driver.getWindowHandle();
    // Switch to basic form
    switchToWindowByID(googleWindowID);
    driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
    common.sleepInSecond(2);
    // Switch to Facebook
    switchToWindowByPageTitle("Facebook – log in or sign up");
    Assert.assertEquals(driver.getTitle(), "Facebook – log in or sign up");
    // Switch to basic form
    switchToWindowByPageTitle("Selenium WebDriver");
    driver.findElement(By.xpath("//a[text()='TIKI']")).click();
    common.sleepInSecond(2);
    // Switch to Tiki
    switchToWindowByPageTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
    Assert.assertEquals(driver.getTitle(), "Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
    closeAllWindowExceptWindow(basicFormWindowID);
    Assert.assertEquals(driver.getTitle(), "Selenium WebDriver");
  }

  @Test
  public void tc02WindowTab() {
    driver.get("http://live.techpanda.org/");
    driver.findElement(By.xpath("//a[text()='Mobile']")).click();
    common.sleepInSecond(2);
    driver.findElement(By.xpath(
        "//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']"))
        .click();
    common.sleepInSecond(2);
    // Verify success message is displayed
    Assert.assertTrue(driver
        .findElement(By.xpath(
            "//li[@class='success-msg']//span[text()='The product Sony Xperia has been added to comparison list.']"))
        .isDisplayed());

    driver.findElement(By.xpath(
        "//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']"))
        .click();
    common.sleepInSecond(2);
    // Verify success message is displayed
    Assert.assertTrue(driver
        .findElement(By.xpath(
            "//li[@class='success-msg']//span[text()='The product Samsung Galaxy has been added to comparison list.']"))
        .isDisplayed());

    driver.findElement(By.xpath("//span[text()='Compare']/parent::span/parent::button")).click();
    String mobileWindowID = driver.getWindowHandle();
    // Switch to Product Compare window
    switchToWindowByID(mobileWindowID);
    // Verify page title of Product Compare window
    Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
    closeAllWindowExceptWindow(mobileWindowID);
    driver.findElement(By.xpath("//a[text()='Clear All']")).click();
    alert = explicitWait.until(ExpectedConditions.alertIsPresent());
    alert.accept();
    // Verify success message is displayed
    Assert.assertTrue(
        driver.findElement(By.xpath("//li[@class='success-msg']//span[text()='The comparison list was cleared.']"))
            .isDisplayed());
  }

  // In case have only 2 windows/tabs
  public void switchToWindowByID(String otherWindowID) {
    Set<String> allWindowsID = driver.getWindowHandles();
    for (String windowID : allWindowsID) {
      if (!windowID.equals(otherWindowID)) {
        driver.switchTo().window(windowID);
        common.sleepInSecond(2);
      }
    }
  }

  // In case have greate than 2 windows/tabs
  public void switchToWindowByPageTitle(String expectedPageTitle) {
    Set<String> allWindowsID = driver.getWindowHandles();
    for (String windowID : allWindowsID) {
      driver.switchTo().window(windowID);
      if (driver.getTitle().equals(expectedPageTitle)) {
        break;
      }
    }
  }

  public void closeAllWindowExceptWindow(String exceptWindowID) {
    Set<String> allWindowsID = driver.getWindowHandles();
    for (String windowID : allWindowsID) {
      if (!windowID.equals(exceptWindowID)) {
        driver.switchTo().window(windowID);
        driver.close();
      }
    }
    driver.switchTo().window(exceptWindowID);
  }

  @AfterClass
  public void afterClass() {
    driver.quit();
  }
}
