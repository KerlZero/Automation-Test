package Pack_Framework_Junit;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestJunit4 {

private WebDriver driver;
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "D:/Automation/chromedriver.exe");
		this.driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		this.driver.get("https://smartappbanc.bancuat.com");
	}
	
	@Test
	public void TestRun() throws Exception {
		assertEquals("Sig InS", this.driver.getTitle());
	}
	
	@After
	public void tearDown() throws Exception {
		this.driver.quit();
		System.out.println("Junit4 Run Success");
	}
	
}
