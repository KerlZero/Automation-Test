package Automate_SmartApp;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class Intro_smartApp {

	private WebDriver driver ;
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "D:/Automation/chromedriver.exe");
		this.driver = new ChromeDriver();
		driver.get("https://adfsuat.blauat.co.th/adfs/ls?wa=wsignin1.0&wtrealm=urn%3aagentportal.blauat.co.th%3asp2013&wctx=https%3a%2f%2fagentportal.blauat.co.th%2f_layouts%2f15%2fAuthenticate.aspx%3fSource%3d%252FPages%252FPageNotFoundError%252Easpx%253FrequestUrl%253Dhttps%253A%252F%252Fagentportal%252Eblauat%252Eco%252Eth%252Fagent");
		driver.manage().window().maximize();
		String Username = "agbla01";
		String Password = "P@ssw0rd";
		
		driver.findElement(By.xpath("*//input[@id='userNameInput']")).sendKeys(Username);
		driver.findElement(By.xpath("*//input[@id='passwordInput']")).sendKeys(Password);
		driver.findElement(By.xpath("*//span[@id='submitButton']")).click();	
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("*//a[contains(@href, 'https://smartapp.blauat.co.th/')]")).click();
		
		/* Switch To New Tab */
		String currentTab = driver.getWindowHandle();
		for (String tab : driver.getWindowHandles()) {
		    if (!tab.equals(currentTab)) {
		        driver.switchTo().window(tab); 
		    }       
		}
	
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("*//div[@id='iapplication_btn']")).click();
		
	}
	
//	@Test
//	public void TC_ID_XX() throws Exception {
//	
//			Thread.sleep(500);			
//				
//			String id_card = "656895792642";
//			String ErrMsg = "เลขบัตรประชาชนไม่ถูกต้อง";
//			
//			//ID Progressbar = ContentPlaceHolder1_updateProgress
//			driver.findElement(By.xpath("*//input[@name='ctl00$ContentPlaceHolder1$txt_IDcard_No']")).sendKeys(id_card);
//			driver.findElement(By.xpath("//span[@id='ContentPlaceHolder1_lblPersonalInfo']")).click();
//			
//			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//			String AcErrMsg = driver.findElement(By.xpath("*//span[@id='ContentPlaceHolder1_lbl_ShowMsg']")).getText();
//			
//			assertEquals(AcErrMsg, ErrMsg);
//			
//			String Methodname = new Object() {}
//			.getClass()
//			.getEnclosingMethod()
//			.getName();
//			System.out.println(AcErrMsg);
//			System.out.println("Run Case " + Methodname + " Success");
//	}
	
//	@Test
//	public void TC_ID_02() throws Exception {
//		
//			Thread.sleep(300);
//			
//			String Errmsg = "กรุณากรอกวันเกิด ของผู้เอาประกัน";
//			
//			JavascriptExecutor jse = (JavascriptExecutor)driver;
//			jse.executeScript("window.scrollBy(0,335)", "");
//			
//			driver.findElement(By.xpath("*//input[@id='ContentPlaceHolder1_btn_Search']")).click();
//			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//			String AcErrMsg = driver.findElement(By.xpath("*//span[@id='ContentPlaceHolder1_lbl_ShowMsg']")).getText();
//			assertEquals(Errmsg, AcErrMsg);
//			
//			String Methodname = new Object() {}
//			.getClass()
//			.getEnclosingMethod()
//			.getName();
//			
//			System.out.println(AcErrMsg);
//			System.out.println("Run Case " + Methodname + " Success");
//			
//			
//	}
	
//	@Test
//	public void TC_ID_03() throws Exception {
//		
//		Thread.sleep(300);
//		
//		String Errmsg = "กรุณาเลือกอาชีพ ผู้เอาประกัน";
//		String birthday = "22/09/2537";
//		
//		driver.findElement(By.xpath("*//input[@id='ContentPlaceHolder1_txt_Birth_Dt']")).sendKeys(birthday);
//		driver.findElement(By.xpath("//span[@id='ContentPlaceHolder1_lblPersonalInfo']")).click();
//		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//		
//		JavascriptExecutor jse = (JavascriptExecutor)driver;
//		jse.executeScript("window.scrollBy(0,335)", "");
//		
//		driver.findElement(By.xpath("*//input[@id='ContentPlaceHolder1_btn_Search']")).click();
//		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//		driver.findElement(By.xpath("*//input[@name='ctl00$ContentPlaceHolder1$gvPolicy$ctl03$btnSelectPlan']")).click();
//
//		Thread.sleep(500);
//		System.out.println("C Plssssssssssssssssssssssssssssssssssss1");
//		
//		driver.findElement(By.xpath("*//input[@id='ContentPlaceHolder1_btn_FinalCalculate']")).click();
//		System.out.println("Log Plssssssssssssssssssssssssssssssssssss");
//		String AcErrMsg = driver.findElement(By.xpath("*//span[@id='ContentPlaceHolder1_lbl_ShowMsg']")).getText();
//				
//		assertEquals(Errmsg, AcErrMsg);
//		
//		String Methodname = new Object() {}
//		.getClass()
//		.getEnclosingMethod()
//		.getName();
//		
//		System.out.println(AcErrMsg);
//		System.out.println("Run Case " + Methodname + " Success");
//	}
	
	
	@Test
	public void TC_ID_04() throws Exception {
		
		Thread.sleep(300);
		
		String birthday = "22/09/2537";
		
		driver.findElement(By.xpath("*//input[@id='ContentPlaceHolder1_txt_Birth_Dt']")).sendKeys(birthday);
		driver.findElement(By.xpath("//span[@id='ContentPlaceHolder1_lblPersonalInfo']")).click();
		Thread.sleep(300);
		
		WebElement element = driver.findElement(By.id("ContentPlaceHolder1_txt_Age"));
		String elementval = element.getAttribute("value");
		String bb = driver.getTitle();
		
		System.out.println(bb);
		System.out.println(elementval);
		
		String Methodname = new Object() {}
		.getClass()
		.getEnclosingMethod()
		.getName();
		
		System.out.println("Run Case " + Methodname + " Success");
	}
	
	@After
	public void tearDown() throws Exception {
		
//		driver.quit();
		
	}
	

	
}
