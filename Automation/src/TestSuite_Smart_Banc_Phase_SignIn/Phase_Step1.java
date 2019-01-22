package TestSuite_Smart_Banc_Phase_SignIn;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Phase_Step1 {
	
	private WebDriver driver;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "D:/Automation/chromedriver.exe");
		this.driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		this.driver.get("https://smartappbanc.bancuat.com");
		driver.findElement(By.xpath("*//input[@id='iapplication_btn']")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	@Test
	public void TC_03() throws Exception {
		
		String inside_step1 = "ข้อมูลผู้เอาประกัน";
		
		String varify = driver.findElement(By.xpath("*//span[@id='ContentPlaceHolder1_lblPersonalInfo']")).getText();
		assertEquals(inside_step1, varify);
	}
	
	@After
	public void tearDown() throws Exception {
		this.driver.quit();
	}
}
