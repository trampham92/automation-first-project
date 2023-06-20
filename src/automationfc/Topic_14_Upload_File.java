package automationfc;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Upload_File {
  WebDriver driver;
  JavascriptExecutor jsExecutor;
  WebDriverWait explicitWait;
  String projectPath = System.getProperty("user.dir");
  Common common = new Common();
  String imageName1 = "Image01.png";
  String imageName2 = "Image02.jpeg";
  String imageName3 = "Image03.jpeg";
  String image1Path = projectPath + "/FileUpload/" + imageName1;
  String image2Path = projectPath + "/FileUpload/" + imageName2;
  String image3Path = projectPath + "/FileUpload/" + imageName3;

  @BeforeClass
  public void beforeClass() {
    System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
    driver = new ChromeDriver();
    jsExecutor = (JavascriptExecutor) driver;
    explicitWait = new WebDriverWait(driver, 10);
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.manage().window().maximize();
  }

  @Test
  public void tc01UploadFileBySendkeys() {
    driver.get("https://blueimp.github.io/jQuery-File-Upload/");
    // Load files
    driver.findElement(By.cssSelector("input[type='file']"))
        .sendKeys(image1Path + "\n" + image2Path + "\n" + image3Path);
    // Verify load files success
    Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + imageName1 + "']")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + imageName2 + "']")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + imageName3 + "']")).isDisplayed());
    // Click start button of all image
    List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));
    for (WebElement startButton : startButtons) {
      startButton.click();
      common.sleepInSecond(4);
    }
    // Verify images are uploaded successfully
    Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + imageName1 + "']")).isDisplayed());
    Assert.assertTrue(isImageLoaded(driver.findElement(By.xpath("//img[contains(@src, '" + imageName1 + "')]"))));
    Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + imageName2 + "']")).isDisplayed());
    Assert.assertTrue(isImageLoaded(driver.findElement(By.xpath("//img[contains(@src, '" + imageName2 + "')]"))));
    Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + imageName3 + "']")).isDisplayed());
    Assert.assertTrue(isImageLoaded(driver.findElement(By.xpath("//img[contains(@src, '" + imageName3 + "')]"))));
  }

  public boolean isImageLoaded(WebElement element) {
    return (boolean) jsExecutor.executeScript(
        "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
        element);
  }

  @AfterClass
  public void afterClass() {
    driver.quit();
  }
}
