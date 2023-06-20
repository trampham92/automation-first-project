package automationfc;

import java.util.Random;
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

public class Topic_13_Javascript_Executor {
  WebDriver driver;
  JavascriptExecutor jsExecutor;
  Random random;
  WebDriverWait explicitWait;
  String projectPath = System.getProperty("user.dir");
  Common common = new Common();
  Random rand = new Random();

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
  public void tc01JE() {
    navigateToURLByJE("http://live.techpanda.org/");
    common.sleepInSecond(5);
    // Verify domain page
    Assert.assertEquals((String) executeScriptCommand("return document.domain"), "live.techpanda.org");
    // Verify URL page
    Assert.assertEquals((String) executeScriptCommand("return document.URL"), "http://live.techpanda.org/");
    // Open MOBILE page
    WebElement mobileTextLink = driver.findElement(By.xpath("//a[text()='Mobile']"));
    hightlightElement(mobileTextLink);
    clickToElementByJE(mobileTextLink);
    common.sleepInSecond(3);
    // Add item Samsung galaxy to cart
    WebElement samsungAddCartButton = driver.findElement(By.xpath(
        "//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//span[text()='Add to Cart']"));
    hightlightElement(samsungAddCartButton);
    clickToElementByJE(samsungAddCartButton);
    // Get inner text
    Assert.assertTrue(executeScriptCommand("return document.documentElement.innerText;").toString()
        .contains("Samsung Galaxy was added to your shopping cart."));

    // Open customer service page
    WebElement customerServiceTextLink = driver.findElement(By.xpath("//a[text()='Customer Service']"));
    hightlightElement(customerServiceTextLink);
    clickToElementByJE(customerServiceTextLink);
    common.sleepInSecond(3);
    // Verify customer service title page
    Assert.assertEquals(executeScriptCommand("return document.title").toString(), "Customer Service");
    // Scroll to element Newsletter text box
    WebElement newsletterTextbox = driver.findElement(By.id("newsletter"));
    scrollToElement(newsletterTextbox, false);
    // Send keys
    sendKeysByJE(newsletterTextbox, "test" + rand.nextInt(9999) + "@gmail.com");
    clickToElementByJE(driver.findElement(By.cssSelector("button[title='Subscribe']")));
    common.sleepInSecond(2);
    // Get inner text
    Assert.assertTrue(executeScriptCommand("return document.documentElement.innerText;").toString()
        .contains("Thank you for your subscription."));
    navigateToURLByJE("http://demo.guru99.com/v4/");
    common.sleepInSecond(3);
    // Verify domain
    Assert.assertEquals((String) executeScriptCommand("return document.domain"), "demo.guru99.com");
  }

  @Test
  public void tc02VerifyHTML5ValidationMsg() {
    navigateToURLByJE("https://automationfc.github.io/html5/index.html");
    common.sleepInSecond(5);
    WebElement submitButton = driver.findElement(By.cssSelector("input[name='submit-btn']"));
    hightlightElement(submitButton);
    clickToElementByJE(submitButton);
    // Verify validate message of Name field
    WebElement nameTextbox = driver.findElement(By.cssSelector("input#fname"));
    Assert.assertEquals(getValidationMsg(nameTextbox), "Please fill out this field.");
    // Input data into Name field
    sendKeysByJE(nameTextbox, "tram");
    // Verify validate message of Password field
    WebElement passwordTextbox = driver.findElement(By.cssSelector("input#pass"));
    Assert.assertEquals(getValidationMsg(passwordTextbox), "Please fill out this field.");
    // Input data into Password field
    sendKeysByJE(passwordTextbox, "123456");
    // Verify validate message of Email field
    WebElement emailTextbox = driver.findElement(By.cssSelector("input#em"));
    Assert.assertEquals(getValidationMsg(emailTextbox), "Please fill out this field.");
    // Input invalid value into Email textbox
    String invalidEmail = "abc";
    sendKeysByJE(emailTextbox, invalidEmail);
    // Verify validate message of Email field
    Assert.assertEquals(getValidationMsg(emailTextbox),
        "Please include an '@' in the email address. '" + invalidEmail + "' is missing an '@'.");
    // Input valid value into Email textbox
    String validEmail = "abc@gmail.com";
    sendKeysByJE(emailTextbox, validEmail);
    // Verify validate message of Address field
    Assert.assertEquals(
        getValidationMsg(
            driver.findElement(By.xpath("//b[text()='✱ ADDRESS ']/parent::label/following-sibling::select"))),
        "Please select an item in the list.");
  }

  public Object executeScriptCommand(String command) {
    return jsExecutor.executeScript(command);
  }

  public void navigateToURLByJE(String url) {
    jsExecutor.executeScript("window,location='" + url + "'");
  }

  public void hightlightElement(WebElement element) {
    String originStyle = element.getAttribute("style");
    jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element,
        "border: 2px solid red; border-style: dashed;");
    common.sleepInSecond(2);
    jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originStyle);
  }

  public Object clickToElementByJE(WebElement element) {
    return jsExecutor.executeScript("arguments[0].click();", element);
  }

  public Object scrollToElement(WebElement element, boolean isAboveElement) {
    return jsExecutor.executeScript("arguments[0].scrollIntoView(" + isAboveElement + ")", element);
  }

  public Object sendKeysByJE(WebElement element, String value) {
    return jsExecutor.executeScript("arguments[0].setAttribute('value','" + value + "')", element);
  }

  public String getValidationMsg(WebElement element) {
    return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", element);
  }

  @AfterClass
  public void afterClass() {
    driver.quit();
  }
}
