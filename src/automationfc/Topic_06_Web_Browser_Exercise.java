package automationfc;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Browser_Exercise extends Common {
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
    public void tc01VerifyURL() {
    	driver.get("http://live.techpanda.org/");
    	driver.findElement(By.xpath("//div[@class='footer']//a[text() = 'My Account']")).click();
    	common.sleepInSecond(2);
    	Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
    	
    	driver.findElement(By.cssSelector("form[id='login-form'] a[title='Create an Account']")).click();
    	common.sleepInSecond(2);
    	Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
    }
    
    @Test
    public void tc02VerifyTitle() {
    	driver.get("http://live.techpanda.org/");
    	driver.findElement(By.xpath("//div[@class='footer']//a[text() = 'My Account']")).click();
    	common.sleepInSecond(2);
    	Assert.assertEquals(driver.getTitle(), "Customer Login");
    	
    	driver.findElement(By.cssSelector("form[id='login-form'] a[title='Create an Account']")).click();
    	common.sleepInSecond(2);
    	Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
    }
    
    @Test
    public void tc03NavigateFunction() {
    	driver.get("http://live.techpanda.org/");
    	driver.findElement(By.xpath("//div[@class='footer']//a[text() = 'My Account']")).click();
    	driver.findElement(By.cssSelector("form[id='login-form'] a[title='Create an Account']")).click();
    	common.sleepInSecond(2);
    	Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
    	
    	driver.navigate().back();
    	common.sleepInSecond(2);
    	Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
    	
    	driver.navigate().forward();   
    	common.sleepInSecond(2);
    	Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
    }
    
    @Test
    public void tc04GetPageSourceCode() {
    	driver.get("http://live.techpanda.org/");
    	driver.findElement(By.xpath("//div[@class='footer']//a[text() = 'My Account']")).click();
    	common.sleepInSecond(2);
    	Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
    	
    	driver.findElement(By.cssSelector("form[id='login-form'] a[title='Create an Account']")).click();
    	common.sleepInSecond(2);
    	Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
    }
    
   @AfterClass
   public void afterClass() {
	   driver.quit();
   }
}
