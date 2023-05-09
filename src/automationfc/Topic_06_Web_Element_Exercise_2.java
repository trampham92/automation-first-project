package automationfc;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_Exercise_2 extends Common {
	WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    Common common = new Common();
    
    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
    
    @Test
    public void tc01EmptyEmailAndPassword() {
    	driver.get("http://live.techpanda.org/");
    	driver.findElement(By.xpath("//div[@class='footer']//a[text() = 'My Account']")).click();
    	common.sleepInSecond(2);
    	driver.findElement(By.id("send2")).click();
    	common.sleepInSecond(2);
    	Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
    	Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");
    	
    }
    
    @Test
    public void tc02InvalidEmail() {
    	driver.get("http://live.techpanda.org/");
    	driver.findElement(By.xpath("//div[@class='footer']//a[text() = 'My Account']")).click();
    	common.sleepInSecond(2);
    	driver.findElement(By.id("email")).sendKeys("12344@423523.24235");
    	driver.findElement(By.id("pass")).sendKeys("123456");
    	driver.findElement(By.id("send2")).click();
    	common.sleepInSecond(2);
    	Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
    }
    
    @Test
    public void tc03PasswordLessThan6() {
    	driver.get("http://live.techpanda.org/");
    	driver.findElement(By.xpath("//div[@class='footer']//a[text() = 'My Account']")).click();
    	common.sleepInSecond(2);
    	driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
    	driver.findElement(By.id("pass")).sendKeys("123");
    	driver.findElement(By.id("send2")).click();
    	common.sleepInSecond(2);
    	Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
    }
    
    @Test
    public void tc04IncorrectEmailAndPassword() {
    	driver.get("http://live.techpanda.org/");
    	driver.findElement(By.xpath("//div[@class='footer']//a[text() = 'My Account']")).click();
    	common.sleepInSecond(2);
    	driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
    	driver.findElement(By.id("pass")).sendKeys("123456789");
    	driver.findElement(By.id("send2")).click();
    	common.sleepInSecond(2);
    	Assert.assertEquals(driver.findElement(By.cssSelector(".error-msg span")).getText(), "Invalid login or password.");
    }

    @AfterClass
    public void afterClass() {
    	driver.quit();
    }
}
