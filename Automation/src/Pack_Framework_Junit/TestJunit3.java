package Pack_Framework_Junit;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestJunit3 {
	
	private WebDriver driver;	
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "D:/Automation/chromedriver.exe");
		this.driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		this.driver.get("https://smartappbanc.bancuat.com");
	}
	
	
	@Test
	public void setUpWebDriver() {
		
		driver.findElement(By.xpath("*//a[text()='ŧ����¹��ҹ�����á ']")).click();
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,635)", "");

		driver.findElement(By.xpath("*//input[@name='ctl00$ContentPlaceHolder1$btnRegister']")).click();
		String a = driver.findElement(By.xpath("/html/body/div[2]/div[2]/h2")).getText();
		String b = driver.findElement(By.xpath("*//div[@class='swal2-content']")).getText();
		
		String c = "�Դ��ͼԴ��Ҵ";
		String d = "��س���ҹ��͵�ŧ������͹䢡�����ԡ�� ��С�����Ѻ����͹� ��͹ŧ����¹";

		assertEquals(a, d);
		assertEquals(b, d);
	}
	
	@After
	public void tearDown() throws Exception {
		this.driver.quit();
		System.out.println("Junit3 Run Success");
	}
	

}
