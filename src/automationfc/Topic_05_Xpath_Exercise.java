package automationfc;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Xpath_Exercise {
	WebDriver driver;
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
    
    @Test
    public void tc01RegWithEmptyData() {
    	driver.get("https://alada.vn/tai-khoan/dang-ky.html");
    	driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
    	Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
    	Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
    	Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
    	Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
    	Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
    	Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");
    }
    
    @Test
    public void tc02RegWithInvalidEmail() {
    	driver.get("https://alada.vn/tai-khoan/dang-ky.html");
    	driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Phạm Trâm");
    	driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("test@test@test");
    	driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("test@test@test");
    	driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("abcd1234");
    	driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("abcd1234");
    	driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0900000000");
    	driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
    	Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
    	Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
    }
    
    @Test
    public void tc03RegWithIncorrectCEmail() {
    	driver.get("https://alada.vn/tai-khoan/dang-ky.html");
    	driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Phạm Trâm");
    	driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("test@test.com");
    	driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("test@test.vn");
    	driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("abcd1234");
    	driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("abcd1234");
    	driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0900000000");
    	driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
    	Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
    }
    
    @Test
    public void tc04RegWithPWLessThan6Char() {
    	driver.get("https://alada.vn/tai-khoan/dang-ky.html");
    	driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Phạm Trâm");
    	driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("test@test.com");
    	driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("test@test.com");
    	driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("abc");
    	driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("abc");
    	driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0900000000");
    	driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
    	Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
    	Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
    }
    
   @Test
   public void tc05RegWithIncorrectConfirmPW() {
	   driver.get("https://alada.vn/tai-khoan/dang-ky.html");
   	   driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Phạm Trâm");
   	   driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("test@test.com");
   	   driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("test@test.com");
   	   driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("abcdef");
	   driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456");
   	   driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0900000000");
   	   driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
   	   Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");
   }
   
   @Test
   public void tc06RegWithInvalidPhoneNumber() {
	   driver.get("https://alada.vn/tai-khoan/dang-ky.html");
   	   driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Phạm Trâm");
	   driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("test@test.com");
	   driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("test@test.com");
   	   driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("abcdef");
   	   driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("abcdef");
   	   
   	   //Verify in case phone number less than 10 digits
   	   driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("090000000");
   	   driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
   	   Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");
   	   
   	   //Verify in case phone number greater than 11 digits
   	   driver.findElement(By.xpath("//input[@id='txtPhone']")).clear();
   	   driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("090000000000");
   	   driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
	   Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");
	   
	   //Verify in case prefix phone number incorrect
   	   driver.findElement(By.xpath("//input[@id='txtPhone']")).clear();
   	   driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0688888888");
   	   driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
	   Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
   }

   @AfterClass
   public void afterClass() {
	   driver.quit();
   }
}
