package automationfc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Button_Checkbox_Radio {
  WebDriver driver;
  JavascriptExecutor js;
  String projectPath = System.getProperty("user.dir");
  Common common = new Common();

  @BeforeClass
  public void beforeClass() {
    System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.manage().window().maximize();
  }

  @Test
  public void tc01Button() {
    driver.get("https://www.fahasa.com/customer/account/create");
    common.sleepInSecond(3);
    driver.findElement(By.className("popup-login-tab-login")).click();
    WebElement loginButton = driver.findElement(By.cssSelector(".fhs-btn-login"));
    Assert.assertFalse(loginButton.isEnabled());

    String loginButtonBackground = loginButton.getCssValue("background");
    Assert.assertTrue(loginButtonBackground.contains("rgb(224, 224, 224)"));

    driver.findElement(By.cssSelector("#login_username")).sendKeys("test@gmail.com");
    driver.findElement(By.cssSelector("#login_password")).sendKeys("123456");
    common.sleepInSecond(4);
    Assert.assertTrue(loginButton.isEnabled());
    loginButtonBackground = loginButton.getCssValue("background-color");
    Color loginButtonColor = Color.fromString(loginButtonBackground);
    Assert.assertTrue(loginButtonColor.asHex().toUpperCase().equals("#C92127"));

  }

  @Test
  public void tc02DefaultCheckboxOrRadioButtonSingle() {
    // Checkbox
    driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
    WebElement dualZoneAirCheckbox = driver
        .findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
    checkToCheckbox(dualZoneAirCheckbox);
    // Verify Dual-zone air conditioning checkbox is selected
    Assert.assertTrue(dualZoneAirCheckbox.isSelected());

    // Unchecked Dual-zone air conditioning checkbox
    uncheckToCheckbox(dualZoneAirCheckbox);
    Assert.assertFalse(dualZoneAirCheckbox.isSelected());

    // Radio button
    driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
    WebElement petrolRadio = driver
        .findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input"));
    petrolRadio.click();
    checkToCheckbox(petrolRadio);
  }

  @Test
  public void tc03DefaultCheckboxOrRadioButtonMultiple() {
    driver.get("https://material.angular.io/components/radio/examples");
    WebElement summerRadioButton = driver
        .findElement(By.xpath("//label[contains(text(), 'Summer')]/preceding-sibling::div/input"));
    checkToCheckbox(summerRadioButton);

    // Selected multiple checkbox
    driver.get("https://material.angular.io/components/checkbox/examples");
    List<String> listCheckedText = new ArrayList<String>(List.of("Checked", "Indeterminate"));
    List<WebElement> listConfigCheckbox = driver.findElements(
        By.xpath("//example-viewer[@id='checkbox-configurable']//input[@class='mdc-checkbox__native-control']"));
    for (WebElement checkbox : listConfigCheckbox) {
      if (listCheckedText
          .contains(checkbox.findElement(By.xpath(".//parent::div/following-sibling::label")).getText())) {
        // Checked
        js.executeScript("arguments[0].click()", checkbox);
      }
    }
    // Verify checkboxs are checked
    for (WebElement checkbox : listConfigCheckbox) {
      if (listCheckedText
          .contains(checkbox.findElement(By.xpath(".//parent::div/following-sibling::label")).getText())) {
        Assert.assertTrue(checkbox.isSelected());
      }
    }

    for (WebElement checkbox : listConfigCheckbox) {
      if (listCheckedText
          .contains(checkbox.findElement(By.xpath(".//parent::div/following-sibling::label")).getText())) {
        // Unchecked
        js.executeScript("arguments[0].click()", checkbox);
      }
    }

    // Verify checkboxs are unchecked
    for (WebElement checkbox : listConfigCheckbox) {
      if (listCheckedText
          .contains(checkbox.findElement(By.xpath(".//parent::div/following-sibling::label")).getText())) {
        Assert.assertFalse(checkbox.isSelected());
      }
    }
  }

  @Test
  public void tc04CustomCheckboxOrRadioButton() {
    driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
    WebElement registerForRalativeRadioElement = driver
        .findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input"));
    if (!registerForRalativeRadioElement.isSelected()) {
      js.executeScript("arguments[0].click();", registerForRalativeRadioElement);
    }
    // Verify radio button is selected
    Assert.assertTrue(registerForRalativeRadioElement.isSelected());
  }

  @Test
  public void tc05CustomCheckboxOrRadioButton() {
    driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
    common.sleepInSecond(2);
    WebElement ariaCanThoRadio = driver.findElement(By.cssSelector("div[aria-label='Cần Thơ']"));

    // Verify Can Tho radio button is not selected
    Assert.assertTrue(
        driver.findElement(By.cssSelector("div[aria-label='Cần Thơ'][aria-checked='false']")).isDisplayed());

    // Click Can Tho radio button
    js.executeScript("arguments[0].click();", ariaCanThoRadio);

    // Verify Can Tho radio button is selected
    Assert
        .assertTrue(driver.findElement(By.cssSelector("div[aria-label='Cần Thơ'][aria-checked='true']")).isDisplayed());

  }

  public void checkToCheckbox(WebElement checkbox) {
    if (!checkbox.isSelected()) {
      js.executeScript("arguments[0].click()", checkbox);
      // checkbox.click();
    }
  }

  public void uncheckToCheckbox(WebElement checkbox) {
    if (checkbox.isSelected()) {
      js.executeScript("arguments[0].click()", checkbox);
      // checkbox.click();
    }
  }

  @AfterClass
  public void afterClass() {
    driver.quit();
  }
}
