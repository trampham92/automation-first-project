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

public class Topic_06_Web_Element_Exercise {
	WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    
    By emailTextbox = By.id("mail");
    By under18Radio = By.id("under_18");
    By educationTextarea = By.id("edu");
    By nameUser5Text = By.xpath("//h5[text() = 'Name: User5']");

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
    
    @Test
    public void tc01IsDisplayed() {
    	driver.get("https://automationfc.github.io/basic-form/index.html");
    	if (driver.findElement(emailTextbox).isDisplayed()) {
			driver.findElement(emailTextbox).sendKeys("tram@gmail.com");
			System.out.println("Element Email textbox is displayed");
		} else {
			System.out.println("Element Email textbox is not displayed");
		}
    	
    	if (driver.findElement(educationTextarea).isDisplayed()) {
    		driver.findElement(educationTextarea).sendKeys("test");
    		System.out.println("Element Education is displayed");
    	} else {
    		System.out.println("Element Education is not displayed");
    	}
    	
    	if (driver.findElement(under18Radio).isDisplayed()) {
    		driver.findElement(under18Radio).click();
    		System.out.println("Element Age (Under 18) is displayed");
		} else {
			System.out.println("Element Age (Under 18) is not displayed");
		}
    	
    	if (driver.findElement(nameUser5Text).isDisplayed()) {
			System.out.println("Element Name:User5 is displayed");
		} else {
			System.out.println("Element Name:User5 is not displayed");
		}
    }
    
    @Test
    public void tc02EnableAndDisable() {
    	driver.get("https://automationfc.github.io/basic-form/index.html");
    	if (driver.findElement(emailTextbox).isEnabled()) {
			System.out.println("Element Email textbox is enabled");
		} else {
			System.out.println("Element Email textbox is disabled");
		}
    	
    	if (driver.findElement(educationTextarea).isEnabled()) {
    		System.out.println("Element Education is enabled");
    	} else {
    		System.out.println("Element Education is disabled");
    	}
    	
    	if (driver.findElement(under18Radio).isEnabled()) {
    		System.out.println("Element Age (Under 18) is enabled");
		} else {
			System.out.println("Element Age (Under 18) is disabled");
		}
    	
    	if (driver.findElement(By.id("job1")).isEnabled()) {
			System.out.println("Element Job Role 01 is enabled");
		} else {
			System.out.println("Element Job Role 01 is disabled");
		}
    	
    	if (driver.findElement(By.id("job2")).isEnabled()) {
			System.out.println("Element Job Role 02 is enabled");
		} else {
			System.out.println("Element Job Role 02 is disabled");
		}
    	
    	if (driver.findElement(By.id("development")).isEnabled()) {
			System.out.println("Element Interests:Development is enabled");
		} else {
			System.out.println("Element Interests:Development is disabled");
		}
    	
    	if (driver.findElement(By.id("slider-1")).isEnabled()) {
			System.out.println("Element Slider 01 is enabled");
		} else {
			System.out.println("Element Slider 01 is disabled");
		}
    	
    	if (driver.findElement(By.id("disable_password")).isEnabled()) {
			System.out.println("Element Password is enabled");
		} else {
			System.out.println("Element Password is disabled");
		}
    	
    	if (driver.findElement(By.id("radio-disabled")).isEnabled()) {
			System.out.println("Element 'Radio button is disabled' is enabled");
		} else {
			System.out.println("Element 'Radio button is disabled' is disabled");
		}
    	
    	if (driver.findElement(By.id("radio-disabled")).isEnabled()) {
			System.out.println("Element 'Radio button is disabled' is enabled");
		} else {
			System.out.println("Element 'Radio button is disabled' is disabled");
		}
    	
    	if (driver.findElement(By.id("bio")).isEnabled()) {
			System.out.println("Element Biography is enabled");
		} else {
			System.out.println("Element Biography is disabled");
		}
    	
    	if (driver.findElement(By.id("job3")).isEnabled()) {
			System.out.println("Element Job Role 03 is enabled");
		} else {
			System.out.println("Element Job Role 03 is disabled");
		}
    	
    	if (driver.findElement(By.id("check-disbaled")).isEnabled()) {
			System.out.println("Element 'Interests:Checkbox is disabled' is enabled");
		} else {
			System.out.println("Element 'Interests:Checkbox is disabled' is disabled");
		}
    	
    	if (driver.findElement(By.id("slider-2")).isEnabled()) {
			System.out.println("Element Slider 02 is enabled");
		} else {
			System.out.println("Element Slider 02 is disabled");
		}
    }
    
    @Test
    public void tc03IsSelected() {
	   driver.get("https://automationfc.github.io/basic-form/index.html");
	   driver.findElement(under18Radio).click();
	   By javascriptCheckbox = By.id("javascript");
	   driver.findElement(javascriptCheckbox).click();
	   Assert.assertTrue(driver.findElement(under18Radio).isSelected());
	   Assert.assertTrue(driver.findElement(javascriptCheckbox).isSelected());
	   
	   driver.findElement(javascriptCheckbox).click();
	   Assert.assertFalse(driver.findElement(javascriptCheckbox).isSelected());
   }
    
   @Test
   public void tc04RegisterMailChimp() {
	   driver.get("https://login.mailchimp.com/signup/");
	   driver.findElement(By.id("email")).sendKeys("trampham@gmail.com");
	   WebElement passwordTextbox = driver.findElement(By.id("new_password"));
	   
	   passwordTextbox.sendKeys("123");
	   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
	   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
	   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
	   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
	   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
	   
	   passwordTextbox.clear();
	   passwordTextbox.sendKeys("abcd");
	   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
	   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
	   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
	   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
	   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
	   
	   passwordTextbox.clear();
	   passwordTextbox.sendKeys("ABCD");
	   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
	   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
	   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
	   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
	   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
	   
	   passwordTextbox.clear();
	   passwordTextbox.sendKeys("!@#$");
	   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
	   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
	   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
	   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
	   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
	   
	   passwordTextbox.clear();
	   passwordTextbox.sendKeys("Abcd12345");
	   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
	   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
	   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
	   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
	   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
   }
    
    @AfterClass
    public void afterClass() {
    	driver.quit();
    }
}
