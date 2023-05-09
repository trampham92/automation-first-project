package automationfc;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_08_Textbox_TextArea_Exercise extends Common {
	WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    Common common = new Common();
    Random rand = new Random();
    
    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void tc01() {
    	String Email = "abcd" + rand.nextInt(9999) + "@gmail.com";
    	String firstName = "tram";
    	String lastName = "pham";
    	driver.get("http://live.techpanda.org/");
    	driver.findElement(By.xpath("//div[@class='footer']//a[text() = 'My Account']")).click();
    	common.sleepInSecond(2);
    	driver.findElement(By.cssSelector("form[id='login-form'] a[title='Create an Account']")).click();
    	common.sleepInSecond(2);
    	driver.findElement(By.id("firstname")).sendKeys(firstName);
    	driver.findElement(By.id("lastname")).sendKeys(lastName);
    	driver.findElement(By.id("email_address")).sendKeys(Email);
    	driver.findElement(By.id("password")).sendKeys("123456");
    	driver.findElement(By.id("confirmation")).sendKeys("123456");
    	driver.findElement(By.cssSelector("button[title='Register']")).click();
    	Assert.assertEquals(driver.findElement(By.cssSelector("li[class='success-msg'] span")).getText(), "Thank you for registering with Main Website Store.");
    	String contactInformation = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
    	Assert.assertTrue(contactInformation.contains(firstName));
    	Assert.assertTrue(contactInformation.contains(lastName));
    	Assert.assertTrue(contactInformation.contains(Email));
    	//Logout
    	driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
    	driver.findElement(By.cssSelector("#header-account li > a[title='Log Out']")).click();
    	common.sleepInSecond(3);
    	System.out.println(driver.findElement(By.cssSelector(".page-title > h2")).getText());
    	Assert.assertTrue(driver.findElement(By.cssSelector(".page-title > h2")).getText().contains("THIS IS DEMO SITE FOR"));
    }
    
    @Test
    public void tc02() {
    	String firstName = "tram";
    	String lastName = "pham";
    	driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    	driver.findElement(By.cssSelector("input[name='username'")).sendKeys("Admin");
    	driver.findElement(By.cssSelector("input[name='password'")).sendKeys("admin123");
    	driver.findElement(By.cssSelector(".orangehrm-login-button")).click();
    	driver.findElement(By.xpath("//li[@class='oxd-main-menu-item-wrapper']//span[text()='PIM']")).click();
    	driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
    	driver.findElement(By.cssSelector("input[name='firstName'")).sendKeys(firstName);
    	driver.findElement(By.cssSelector("input[name='lastName'")).sendKeys(lastName);
    	String employeeId = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value");
    	driver.findElement(By.xpath("//p[text()='Create Login Details']/following-sibling::div//span")).click();
    	String userName = "admin" + rand.nextInt(99999);
    	String password = "Abcd123456@";
    	driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys(userName);
    	driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys(password);
    	driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys(password);
    	driver.findElement(By.xpath("//button[text() = ' Save ']")).click();
    	common.sleepInSecond(3);
    	Assert.assertEquals(driver.findElement(By.cssSelector("input[name='firstName']")).getAttribute("value"), firstName);
    	Assert.assertEquals(driver.findElement(By.cssSelector("input[name='lastName']")).getAttribute("value"), lastName);
    	Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeId);
    	
    	driver.findElement(By.xpath("//a[text()='Immigration']")).click();
    	driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/following-sibling::button[text()=' Add ']")).click();
    	common.sleepInSecond(3);
    	String passportNumber = "123-456-777";
    	String comments = "This is comment\nfor testing";
    	driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys(passportNumber);
    	driver.findElement(By.cssSelector("textarea[placeholder='Type Comments here']")).sendKeys(comments);
    	driver.findElement(By.xpath("//button[contains(string(), 'Save')]")).click();
    	common.sleepInSecond(5);
    	
    	driver.findElement(By.className("bi-pencil-fill")).click();
    	common.sleepInSecond(5);
    	Assert.assertEquals(driver.findElement(By.xpath("//label[text() = 'Number']/parent::div/following-sibling::div/input")).getAttribute("value"), passportNumber);
    	Assert.assertEquals(driver.findElement(By.xpath("//label[text() = 'Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"), comments);
    	
    	driver.findElement(By.className("oxd-userdropdown-name")).click();
    	driver.findElement(By.xpath("//a[text()='Logout']")).click();
    	common.sleepInSecond(3);
    	driver.findElement(By.cssSelector("input[name='username'")).sendKeys(userName);
    	driver.findElement(By.cssSelector("input[name='password'")).sendKeys(password);
    	driver.findElement(By.cssSelector(".orangehrm-login-button")).click();
    	common.sleepInSecond(3);
    	driver.findElement(By.xpath("//span[text()='My Info']")).click();
    	common.sleepInSecond(5);
    	Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), firstName);
    	Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), lastName);
    	Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeId);
    	
    	driver.findElement(By.xpath("//a[text()='Immigration']")).click();
    	common.sleepInSecond(3);
    	driver.findElement(By.className("bi-pencil-fill")).click();
    	common.sleepInSecond(5);
    	Assert.assertEquals(driver.findElement(By.xpath("//label[text() = 'Number']/parent::div/following-sibling::div/input")).getAttribute("value"), passportNumber);
    	Assert.assertEquals(driver.findElement(By.xpath("//label[text() = 'Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"), comments);
    }
    
    @AfterClass
    public void afterClass() {
    	driver.quit();
    }
}
