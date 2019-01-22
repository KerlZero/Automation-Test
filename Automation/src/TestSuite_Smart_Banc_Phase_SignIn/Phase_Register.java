package TestSuite_Smart_Banc_Phase_SignIn;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Phase_Register {
	
	private WebDriver driver;
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "D:/Automation/chromedriver.exe");
		this.driver = new ChromeDriver();
		driver.get("https://smartappbanc.bancuat.com");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("*//a[text()='ลงทะเบียนใช้งานครั้งแรก ']")).click();	
		
	}
	
	@Test
	public void Validate_non_input_Data() throws Exception {
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,635)", "");
				
		driver.findElement(By.xpath("*//input[@name='ctl00$ContentPlaceHolder1$btnRegister']")).click();
		String a = driver.findElement(By.xpath("/html/body/div[2]/div[2]/h2")).getText();
		String b = driver.findElement(By.xpath("*//div[@class='swal2-content']")).getText();
		
		String MsgErr1 = "เกิดข้อผิดพลาด";
		String MsgErr2 = "กรุณาอ่านข้อตกลงและเงื่อนไขการใช้บริการ และกดยอมรับในเงื่อนไข ก่อนลงทะเบียน";
		
		assertEquals(MsgErr1, a);
		assertEquals(MsgErr2, b);
	}
	
	@After
	public void tearDown() throws Exception {
//		this.driver.quit();
	}
	
}




//*[@id="viewer"]/div[1]/div[2]/div[4]