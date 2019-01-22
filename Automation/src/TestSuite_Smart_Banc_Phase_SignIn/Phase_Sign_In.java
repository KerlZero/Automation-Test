package TestSuite_Smart_Banc_Phase_SignIn;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Phase_Sign_In {
	
	private WebDriver driver;
	private WebElement inputUsername;
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "D:/Automation/chromedriver.exe");
		this.driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		this.driver.get("https://smartappbanc.bancuat.com");
	}
	
	@Test
	public void TC_01() throws Exception {
		String para1 = "Kodomo";
		String ErrMsg = "Enter your password." ;
		
		inputUsername = driver.findElement(By.xpath("*//input[@id='userNameInput']"));
		inputUsername.sendKeys(para1);
		inputUsername.sendKeys(Keys.ENTER);

		String varifyErr = driver.findElement(By.xpath("*//label[@id='errorText']")).getText();
		assertEquals(ErrMsg, varifyErr);	
		
		System.out.println("Caes TC_01 Run Success");
	}
	
	@Test
	public void TC_02() throws Exception {
		
		String ErrMsg = "Enter your user ID in the format \"domain\\user\" or \"user@domain\"." ;
		
		inputUsername = driver.findElement(By.xpath("*//input[@id='userNameInput']"));
		inputUsername.sendKeys(Keys.ENTER);

		String varifyErr = driver.findElement(By.xpath("*//label[@id='errorText']")).getText();
		assertEquals(ErrMsg, varifyErr);		
		
		System.out.println("Caes TC_02 Run Success");
	}
	
	
	
	@After
	public void tearDown() throws Exception {
//		this.driver.quit();
	}
}
