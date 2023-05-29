package automationfc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_12_Iframe_Frame {
  WebDriver driver;
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
  public void tc01Iframe() {
    driver.get("https://skills.kynaenglish.vn/");
    By fbIframe = By.xpath("//iframe[contains(@src, 'facebook.com')]");
    // Verify Facebook iframe is displayed
    Assert.assertTrue(driver.findElement(fbIframe).isDisplayed());
    driver.switchTo().frame(driver.findElement(fbIframe));
    // Verify number of follower of Facebook page is 165K followers
    Assert.assertEquals(
        driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText(),
        "165K followers");
    // Back to main page from facebook iframe
    driver.switchTo().defaultContent();
    // Switch to Chat iframe
    driver.switchTo().frame("cs_chat_iframe");
    // Click to Chat iframe
    driver.findElement(By.cssSelector("div.button_bar")).click();
    // Back to main page from Chat iframe
    driver.switchTo().defaultContent();
    // Input to search box
    driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys("Excel");
    driver.findElement(By.cssSelector("button.search-button")).click();
    // Verify result
    List<WebElement> cardList = driver.findElements(By.cssSelector("li.k-box-card div>h4"));
    for (WebElement cardItem : cardList) {
      Assert.assertTrue(cardItem.getText().contains("Excel"));
    }
  }

  @Test
  public void tc02Frame() {
    driver.get("https://netbanking.hdfcbank.com/netbanking/");
    driver.switchTo().frame("login_page");
    driver.findElement(By.cssSelector("input[name='fldLoginUserId']")).sendKeys("john2022");
    driver.findElement(By.cssSelector("a.login-btn")).click();
  }

  @AfterClass
  public void afterClass() {
    driver.quit();
  }
}
