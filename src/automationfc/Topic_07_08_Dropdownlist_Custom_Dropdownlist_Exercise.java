package automationfc;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.lang.model.element.Element;
import javax.lang.model.util.Elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_08_Dropdownlist_Custom_Dropdownlist_Exercise extends Common {
	WebDriver driver;
	WebDriverWait explicitWait;
    String projectPath = System.getProperty("user.dir");
    Common common = new Common();
    Random rand = new Random();
    
    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
        driver = new ChromeDriver();
        explicitWait = new WebDriverWait(driver, 30);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
    
    @Test
    public void tc01() {
    	driver.get("https://demo.nopcommerce.com");
    	driver.findElement(By.className("ico-register")).click();
    	common.sleepInSecond(2);
    	driver.findElement(By.id("gender-male")).click();
    	driver.findElement(By.id("FirstName")).sendKeys("tram");
    	driver.findElement(By.id("LastName")).sendKeys("pham");
    	String day = "1";
    	String month = "May";
    	String year = "1980";
    	Select dayOfBirth = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']")));
    	dayOfBirth.selectByVisibleText(day);
    	Assert.assertEquals(dayOfBirth.getOptions().size(), 32);
    	Select monthOfBirth = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']")));
    	monthOfBirth.selectByVisibleText(month);
    	Assert.assertEquals(monthOfBirth.getOptions().size(), 13);
    	Select yearOfBirth = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']")));
    	yearOfBirth.selectByVisibleText(year);
    	Assert.assertEquals(yearOfBirth.getOptions().size(), 112);
    	String email = "test" + rand.nextInt(9999) +"@gmail.com";
    	String password = "123456";
    	driver.findElement(By.id("Email")).sendKeys(email);
    	driver.findElement(By.id("Password")).sendKeys(password);
    	driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
    	driver.findElement(By.id("register-button")).click();
    	common.sleepInSecond(3);
    	Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
    	
    	driver.findElement(By.className("ico-login")).click();
    	driver.findElement(By.id("Email")).sendKeys(email);
    	driver.findElement(By.id("Password")).sendKeys(password);
    	driver.findElement(By.className("login-button")).click();
    	common.sleepInSecond(2);
    	driver.findElement(By.className("ico-account")).click();
    	common.sleepInSecond(2);
    	Select dayOfBirthUser = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']")));
    	Select monthOfBirthUser = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']")));
    	Select yearOfBirthUser = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']")));
    	Assert.assertEquals(dayOfBirthUser.getFirstSelectedOption().getText(), day);
    	Assert.assertEquals(monthOfBirthUser.getFirstSelectedOption().getText(), month);
    	Assert.assertEquals(yearOfBirthUser.getFirstSelectedOption().getText(), year);
    }
    
    @Test
    public void tc02() {
    	driver.get("https://www.rode.com/wheretobuy");
    	Select countrySelect = new Select(driver.findElement(By.id("country")));
    	Assert.assertFalse(countrySelect.isMultiple());
    	countrySelect.selectByVisibleText("Vietnam");
    	Assert.assertEquals(countrySelect.getFirstSelectedOption().getText(), "Vietnam");
    }
    
    @Test
    public void tc03JQuery() {
    	driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
    	common.sleepInSecond(2);
    	selectItem(By.id("speed-button"), By.cssSelector("#speed-menu div[role='option']"), "Medium");
    	Assert.assertEquals(driver.findElement(By.cssSelector("#speed-button span.ui-selectmenu-text")).getText(), "Medium");
    	selectItem(By.id("speed-button"), By.cssSelector("#speed-menu div[role='option']"), "Slower");
    	Assert.assertEquals(driver.findElement(By.cssSelector("#speed-button span.ui-selectmenu-text")).getText(), "Slower");
    	selectItem(By.id("speed-button"), By.cssSelector("#speed-menu div[role='option']"), "Faster");
    	Assert.assertEquals(driver.findElement(By.cssSelector("#speed-button span.ui-selectmenu-text")).getText(), "Faster");
    }
    
    @Test
    public void tc04ReactJs() {
    	driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
    	common.sleepInSecond(3);
    	selectItem(By.cssSelector("div[role='listbox']"), By.cssSelector("div[role='option']"), "Jenny Hess");
    	Assert.assertEquals(driver.findElement(By.cssSelector("div[class='divider text']")).getText(), "Jenny Hess");
    	common.sleepInSecond(4);
    	selectItem(By.cssSelector("div[role='listbox']"), By.cssSelector("div[role='option']"), "Christian");
    	Assert.assertEquals(driver.findElement(By.cssSelector("div[class='divider text']")).getText(), "Christian");
    }
    
    @Test
    public void tc05VueJs() {
    	driver.get("https://mikerodham.github.io/vue-dropdowns/");
    	common.sleepInSecond(3);
    	selectItem(By.cssSelector("#app div>li"), By.cssSelector("ul[data-v-3ec2ada6] a[data-v-3ec2ada6]"), "Second Option");
    	Assert.assertEquals(driver.findElement(By.cssSelector("#app div>li")).getText(), "Second Option");
    }
    
    @Test
    public void tc06Editable() {
    	driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
    	common.sleepInSecond(3);
    	inputAndSelectItem(By.cssSelector("input[class='search']"), By.cssSelector("#root div[role='option'] span.text"), "Belgium");
    	Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Belgium");
    }
    
    public void selectItem(By dropdownButton, By itemList, String itemSelectText) {
    	driver.findElement(dropdownButton).click();
    	explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(itemList));
    	List<WebElement> items = driver.findElements(itemList);
    	for (WebElement item : items) {
			if (item.getText().trim().equals(itemSelectText)) {
				item.click();
				break;
			}
		}
    }

    public void inputAndSelectItem(By inputTextbox, By itemList, String itemSelectText) {
    	driver.findElement(inputTextbox).sendKeys(itemSelectText);
    	explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(itemList));
    	List<WebElement> items = driver.findElements(itemList);
    	for (WebElement item : items) {
			if (item.getText().trim().equals(itemSelectText)) {
				item.click();
				break;
			}
		}
    }

    @AfterClass
    public void afterClass() {
    	driver.quit();
    }
}
