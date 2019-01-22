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

public class TC_02 {
	
	private String classname = this.getClass().getSimpleName();
	private WebDriver driver;
	private WebElement inputUsername;
	private String ErrMsg = "Enter your user ID in the format \"domain\\user\" or \"user@domain\"." ;
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "D:/Automation/chromedriver.exe");
		this.driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		this.driver.get("https://smartappbanc.bancuat.com");
	}
	
	@Test
	public void TestRun() throws Exception {
		inputUsername = driver.findElement(By.xpath("*//input[@id='userNameInput']"));
		inputUsername.sendKeys(Keys.ENTER);

		String varifyErr = driver.findElement(By.xpath("*//label[@id='errorText']")).getText();
		assertEquals(ErrMsg, varifyErr);		
	}
	
	@After
	public void tearDown() throws Exception {
		
		this.driver.quit();
		System.out.println(classname + " Run Success");
	}
}
