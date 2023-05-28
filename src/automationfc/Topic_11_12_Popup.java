package automationfc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_12_Popup {
  WebDriver driver;
  JavascriptExecutor jsExecutor;
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
    jsExecutor = (JavascriptExecutor) driver;
    explicitWait = new WebDriverWait(driver, 10);
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.manage().window().maximize();
  }

  @Test
  public void tc01FixedPopupInDOM1() {
    driver.get("https://ngoaingu24h.vn/");
    driver.findElement(By.cssSelector("button.login_")).click();
    common.sleepInSecond(2);
    By loginPopup = By.cssSelector("div#modal-login-v1");
    Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
    driver.findElement(By.cssSelector("div#modal-login-v1.in input#account-input")).sendKeys("automationfc");
    driver.findElement(By.cssSelector("div#modal-login-v1.in input#password-input")).sendKeys("automationfc");
    driver.findElement(By.cssSelector("div#modal-login-v1.in button.btn-login-v1")).click();
    common.sleepInSecond(2);
    Assert.assertEquals(driver.findElement(By.cssSelector("div#modal-login-v1.in div.error-login-panel")).getText(),
        "Tài khoản không tồn tại!");
    driver.findElement(By.cssSelector("div#modal-login-v1.in button.close")).click();
    common.sleepInSecond(2);
    Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
  }

  @Test
  public void tc02FixedPopupInDOM2() {
    driver.get("https://skills.kynaenglish.vn/");
    driver.findElement(By.cssSelector("a.login-btn")).click();
    common.sleepInSecond(2);
    By loginPopup = By.cssSelector("div#k-popup-account-login-mb");

    // Verify popup is displayed
    Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
    driver.findElement(By.cssSelector("input#user-login")).sendKeys("automation@gmail.com");
    driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456");
    driver.findElement(By.cssSelector("button#btn-submit-login")).click();
    common.sleepInSecond(2);
    // Verify message
    Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(),
        "Sai tên đăng nhập hoặc mật khẩu");

    // Close popup
    driver.findElement(By.cssSelector("button.k-popup-account-close")).click();
    common.sleepInSecond(2);
    Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
  }

  @Test
  public void tc03FixedPopupNotInDOM() {
    driver.get("https://tiki.vn/");
    driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']")).click();
    By loginPopup = By.cssSelector("div.ReactModal__Content");
    // Verify popup is displayed
    Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
    driver.findElement(By.cssSelector("p.login-with-email")).click();
    common.sleepInSecond(2);
    driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
    // Verify message are displayed
    Assert.assertTrue(driver.findElement(By.xpath("//span[@class='error-mess' and text()='Email không được để trống']"))
        .isDisplayed());
    Assert.assertTrue(driver
        .findElement(By.xpath("//span[@class='error-mess' and text()='Mật khẩu không được để trống']")).isDisplayed());
    // CLose popup
    driver.findElement(By.cssSelector("img.close-img")).click();
    common.sleepInSecond(2);
    // Verify popup is closed
    Assert.assertEquals(driver.findElements(loginPopup).size(), 0);
  }

  @Test
  public void tc04FixedPopupNotInDOM2() {
    driver.get("https://www.facebook.com/");
    driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
    By registerPopup = By.xpath("//div[text()='Sign Up']/parent::div/parent::div");
    // Verify register popup is displayed
    Assert.assertTrue(driver.findElement(registerPopup).isDisplayed());
    // CLose popup
    driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
    common.sleepInSecond(2);
    // Verify popup is closed
    Assert.assertEquals(driver.findElements(registerPopup).size(), 0);

  }

  @Test
  public void tc06RandomPopup() {
    driver.get("https://vnk.edu.vn/");
    By tvePopup = By.cssSelector("div#tve_editor");
    if (driver.findElement(tvePopup).isDisplayed()) {
      driver.findElement(By.cssSelector("div.tve_ea_thrive_leads_form_close")).click();
      common.sleepInSecond(3);
    }
    driver.findElement(By.cssSelector("ul#menu-main-menu > li.menu-item>a[title='Trở thành giảng viên VNK']")).click();
    common.sleepInSecond(3);
    // Verify
    Assert.assertTrue(driver
        .findElement(By.xpath("//div[@class='title-content']/h1[text()='Trở thành giảng viên VNK']")).isDisplayed());
  }

  @Test
  public void tc07RandomPopupNotInDOM() {
    driver.get("https://dehieu.vn/");
    common.sleepInSecond(5);
    By popup = By.cssSelector("div.popup-content");
    if (driver.findElements(popup).size() > 0 && driver.findElements(popup).get(0).isDisplayed()) {
      // Close popup
      jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.cssSelector("button.close")));
      common.sleepInSecond(2);
    }
    driver.findElement(By.xpath("//li/a[text()='Về VNK EDU']")).click();
    common.sleepInSecond(2);
    // Verify
    Assert.assertTrue(
        driver.findElement(By.xpath("//div[@class='content-rich-text']/h2[text()='VNK EDU']")).isDisplayed());
  }

  @AfterClass
  public void afterClass() {
    driver.quit();
  }
}
