package automationfc;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_15_16_Selenium_WebDriver_Wait {
  WebDriver driver;
  JavascriptExecutor jsExecutor;
  WebDriverWait explicitWait;
  FluentWait<WebDriver> fluentDriver;
  FluentWait<WebElement> fluentElement;
  String projectPath = System.getProperty("user.dir");
  Common common = new Common();
  String imageName1 = "Image01.png";
  String imageName2 = "Image02.jpeg";
  String image1Path = projectPath + "/FileUpload/" + imageName1;
  String image2Path = projectPath + "/FileUpload/" + imageName2;

  @BeforeClass
  public void beforeClass() {
    System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
    driver = new ChromeDriver();
//    explicitWait = new WebDriverWait(driver, 10);
//    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.manage().window().maximize();
  }

  @Test
  public void implicitWait() {
    driver.get("https://automationfc.github.io/dynamic-loading/");
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    driver.findElement(By.cssSelector("div#start>button")).click();
    Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
  }

  @Test
  public void staticWait() {
    driver.get("https://automationfc.github.io/dynamic-loading/");
    driver.findElement(By.cssSelector("div#start>button")).click();
    common.sleepInSecond(5);
    Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
  }

  @Test
  public void explicitWaitInvisible() {
    driver.get("https://automationfc.github.io/dynamic-loading/");
    explicitWait = new WebDriverWait(driver, 5);
    explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#start>button")));
    driver.findElement(By.cssSelector("div#start>button")).click();
    explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
    Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
  }

  @Test
  public void explicitWaitVisible() {
    driver.get("https://automationfc.github.io/dynamic-loading/");
    explicitWait = new WebDriverWait(driver, 5);
    explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#start>button")));
  }

  @Test
  public void tc06ExplicitWait() {
    driver
        .get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
    explicitWait = new WebDriverWait(driver, 10);
    // Wait for date time is visible
    explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.calendarContainer")));
    Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(),
        "No Selected Dates to display.");
    // Select a current date
    LocalDate currentdate = LocalDate.now();
    // Getting the current day
    int currentDay = currentdate.getDayOfMonth();
    // Getting the current month
    String currentMonth = currentdate.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());
    // Getting the current year
    int currentYear = currentdate.getYear();
    // Getting the day of week
    String dayOfWeek = currentdate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());
    driver.findElement(By.xpath("//a[text()='" + currentDay + "']")).click();
    // Wait ajax loading icon is invisible
    explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']")));
    // Wait selected date is selected (using visible)
    String expectedText = dayOfWeek + ", " + currentMonth + " " + currentDay + ", " + currentYear;
    explicitWait.until(ExpectedConditions
        .visibilityOfElementLocated(By.xpath("//a[text()='" + currentDay + "']/parent::td[@class='rcSelected']")));
    Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(),
        expectedText);
  }

  @Test
  public void tc07ExplicitWait() {
    driver.get("https://gofile.io/uploadFiles");
    explicitWait = new WebDriverWait(driver, 15);
    // Wait for upload button is visible
    explicitWait
        .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#filesUpload button.filesUploadButton")));
    // Upload files
    driver.findElement(By.cssSelector("input[type='file']")).sendKeys(image1Path + "\n" + image2Path);
    // Wait for progress bar are invisible
    explicitWait
        .until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress"))));
    // Wait and verify upload success message is visible
    Assert.assertTrue(explicitWait
        .until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[@id='mainUpload']//div[text()='Your files have been successfully uploaded']")))
        .isDisplayed());
    // Wait and click to upload link
    explicitWait
        .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.mainUploadSuccessLink a.ajaxLink")))
        .click();
    // Verify download icon is displays by each file
    Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
        "//span[text()='" + imageName1 + "']/parent::a/parent::div/following-sibling::div//span[text()='Download']")))
        .isDisplayed());
    Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
        "//span[text()='" + imageName2 + "']/parent::a/parent::div/following-sibling::div//span[text()='Download']")))
        .isDisplayed());
    // Verify play icon is displays by each file
    Assert.assertTrue(explicitWait
        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
            "//span[text()='" + imageName1 + "']/parent::a/parent::div/following-sibling::div//span[text()='Play']")))
        .isDisplayed());
    Assert.assertTrue(explicitWait
        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
            "//span[text()='" + imageName2 + "']/parent::a/parent::div/following-sibling::div//span[text()='Play']")))
        .isDisplayed());
  }

  @Test
  public void tc08FluentWait() {
    driver.get("https://automationfc.github.io/fluent-wait/");
    WebElement countDownTimeElement = findElementByFluentWait(By.cssSelector("div#javascript_countdown_time"), 2, 1000);
    fluentElement = new FluentWait<WebElement>(countDownTimeElement);
    fluentElement.withTimeout(Duration.ofSeconds(15)).pollingEvery(Duration.ofMillis(100))
        .ignoring(NoSuchElementException.class);
    fluentElement.until(new Function<WebElement, Boolean>() {
      @Override
      public Boolean apply(WebElement countDownTimeElement) {
        // TODO Auto-generated method stub
        return countDownTimeElement.getText().endsWith("00");
      }
    });
  }

  @Test
  public void tc09FluentWait() {
    driver.get("https://automationfc.github.io/dynamic-loading/");
    findElementByFluentWait(By.cssSelector("div#start>button"), 2, 100).click();
    checkElementDisplay(By.xpath("//div[@id='finish']/h4[text()='Hello World!']"), 5, 100);
  }

  public Boolean checkElementDisplay(By locator, long allTime, long pollingTime) {
    fluentDriver = new FluentWait<WebDriver>(driver);
    // Set tổng thời gian và tần số
    fluentDriver.withTimeout(Duration.ofSeconds(allTime)).pollingEvery(Duration.ofMillis(pollingTime))
        .ignoring(NoSuchElementException.class);
    // Apply điều kiện
    return fluentDriver.until(new Function<WebDriver, Boolean>() {
      @Override
      public Boolean apply(WebDriver driver) {
        // TODO Auto-generated method stub
        return driver.findElement(locator).isDisplayed();
      }
    });
  }

  public WebElement findElementByFluentWait(By locator, long allTime, long pollingTime) {
    fluentDriver = new FluentWait<WebDriver>(driver);
    // Set tổng thời gian và tần số
    fluentDriver.withTimeout(Duration.ofSeconds(allTime)).pollingEvery(Duration.ofMillis(pollingTime))
        .ignoring(NoSuchElementException.class);
    // Apply điều kiện
    return fluentDriver.until(new Function<WebDriver, WebElement>() {
      @Override
      public WebElement apply(WebDriver driver) {
        // TODO Auto-generated method stub
        return driver.findElement(locator);
      }
    });
  }

  @Test
  public void tc10PageReady() {
    driver.get("https://admin-demo.nopcommerce.com");
    WebElement emailTextBox = driver.findElement(By.cssSelector("input#Email"));
    WebElement passwordTextbox = driver.findElement(By.cssSelector("input#Password"));
    clearTextbox(emailTextBox);
    clearTextbox(passwordTextbox);
    driver.findElement(By.cssSelector("input#Email")).sendKeys("admin@yourstore.com");
    driver.findElement(By.cssSelector("input#Password")).sendKeys("admin");
    driver.findElement(By.xpath("//button[text()='Log in']")).click();
    Assert.assertTrue(isPageLoadedSuccess());
    // Click logout
    driver.findElement(By.xpath("//a[text()='Logout']")).click();
    Assert.assertTrue(isPageLoadedSuccess());
    // Verify redirect to login page
    Assert.assertTrue(
        driver.findElement(By.xpath("//div[@class='title']/strong[text()='Welcome, please sign in!']")).isDisplayed());
  }
  
  public void clearTextbox(WebElement element) {
    element.clear();
  }

  public Boolean isPageLoadedSuccess() {
    explicitWait = new WebDriverWait(driver, 10);
    jsExecutor = (JavascriptExecutor) driver;
    ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
      @Override
      public Boolean apply(WebDriver arg0) {
        // TODO Auto-generated method stub
        return (Boolean) jsExecutor.executeScript("return (window.jQuery!= null) && (jQuery.active === 0);");
      }
    };
    ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
      @Override
      public Boolean apply(WebDriver arg0) {
        // TODO Auto-generated method stub
        return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
      }
    };
    return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
  }

  @AfterClass
  public void afterClass() {
    driver.quit();
  }
}
