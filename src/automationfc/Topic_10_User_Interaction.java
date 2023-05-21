package automationfc;

import org.openqa.selenium.support.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_User_Interaction {
  WebDriver driver;
  Actions actions;
  WebDriverWait explicitWait;
  JavascriptExecutor jsExecutor;
  String projectPath = System.getProperty("user.dir");
  String osName = System.getProperty("os.name");
  Common common = new Common();

  @BeforeClass
  public void beforeClass() {
    System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
    driver = new ChromeDriver();
    actions = new Actions(driver);
    jsExecutor = (JavascriptExecutor) driver;
    explicitWait = new WebDriverWait(driver, 10);
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.manage().window().maximize();
  }

//  @Test
  public void tc01HoverToElement() {
    driver.get("https://automationfc.github.io/jquery-tooltip/");
    actions.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();
    common.sleepInSecond(2);
    Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(),
        "We ask for your age only for statistical purposes.");
  }

//  @Test
  public void tc04ClickAndHoverElement() {
    driver.get("https://automationfc.github.io/jquery-selectable/");
    List<String> arrayText = new ArrayList<String>();
    List<WebElement> listSelectOptions = driver.findElements(By.cssSelector("ol#selectable>li"));
    for (int i = 0; i < listSelectOptions.size(); i++) {
      if (i <= 3) {
        arrayText.add(listSelectOptions.get(i).getText());
      }
    }
    actions.clickAndHold(listSelectOptions.get(0)).moveToElement(listSelectOptions.get(3)).release().perform();

    List<WebElement> listSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
    List<String> arraySelectedText = new ArrayList<String>();
    for (WebElement selectedOption : listSelected) {
      arraySelectedText.add(selectedOption.getText());
    }
    // Verify
    Assert.assertTrue(arraySelectedText.equals(arrayText));
  }

//  @Test
  public void tc05ClickAndSelecRandomElement() {
    driver.get("https://automationfc.github.io/jquery-selectable/");
    List<String> randomItem = new ArrayList<String>(Arrays.asList("1", "3", "6", "11"));
    List<String> arrayText = new ArrayList<String>();
    List<WebElement> listSelectOptions = driver.findElements(By.cssSelector("ol#selectable>li"));
    Keys key = null;
    if (osName.contains("Windows")) {
      key = Keys.CONTROL;
    } else if (osName.contains("Mac OS")) {
      key = Keys.COMMAND;
    }
    actions.keyDown(key).perform();
    for (WebElement selectOption : listSelectOptions) {
      if (randomItem.contains(selectOption.getText())) {
        actions.click(selectOption).perform();
        arrayText.add(selectOption.getText());
      }
    }
    actions.keyUp(key).perform();
    List<WebElement> listSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
    List<String> arraySelectedText = new ArrayList<String>();
    for (WebElement selectedOption : listSelected) {
      arraySelectedText.add(selectedOption.getText());
    }
    // Verify
    Assert.assertTrue(arraySelectedText.equals(arrayText));
  }

//  @Test
  public void tc06DoubleClick() {
    driver.get("https://automationfc.github.io/basic-form/index.html");
    WebElement doubleClickButton = driver.findElement(By.xpath("//button[text()='Double click me']"));
    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", doubleClickButton);
    actions.doubleClick(doubleClickButton).perform();
    // Verify
    Assert.assertEquals(driver.findElement(By.id("demo")).getText(), "Hello Automation Guys!");
  }

//  @Test
  public void tc07RightClick() {
    driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
    actions.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
    // Verify Quit menu is displayed
    WebElement quitMenu = driver.findElement(By.cssSelector("li.context-menu-icon-quit"));
    Assert.assertTrue(quitMenu.isDisplayed());

    // Hover to Quit menu
    actions.moveToElement(quitMenu).perform();
    // Verify Quit menu visible and hover
    Assert.assertTrue(
        driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover"))
            .isDisplayed());
  }

  @Test
  public void tc08DragAndDropHTML4() {
    driver.get("https://automationfc.github.io/kendo-drag-drop/");
    WebElement smallCircle = driver.findElement(By.cssSelector("#draggable"));
    WebElement bigCircle = driver.findElement(By.cssSelector("#droptarget"));
    actions.dragAndDrop(smallCircle, bigCircle).perform();
    // Verify
    Assert.assertEquals(bigCircle.getText(), "You did great!");
    Color bgColorOfDropTarget = Color.fromString(bigCircle.getCssValue("background-color"));
    Assert.assertTrue(bgColorOfDropTarget.asHex().toUpperCase().equals("#03A9F4"));
  }

  @AfterClass
  public void afterClass() {
    driver.quit();
  }
}
