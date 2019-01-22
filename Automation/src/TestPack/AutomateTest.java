package TestPack;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AutomateTest {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "D:/Automation/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://adfsuat.blauat.co.th/adfs/ls?wa=wsignin1.0&wtrealm=urn%3aagentportal.blauat.co.th%3asp2013&wctx=https%3a%2f%2fagentportal.blauat.co.th%2f_layouts%2f15%2fAuthenticate.aspx%3fSource%3d%252FPages%252FPageNotFoundError%252Easpx%253FrequestUrl%253Dhttps%253A%252F%252Fagentportal%252Eblauat%252Eco%252Eth%252Fagent");
		driver.manage().window().maximize();
		String Username = "agbla01";
		String Password = "P@ssw0rd";
		
		driver.findElement(By.xpath("*//input[@id='userNameInput']")).sendKeys(Username);
		driver.findElement(By.xpath("*//input[@id='passwordInput']")).sendKeys(Password);
		driver.findElement(By.xpath("*//span[@id='submitButton']")).click();		
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
		
		String name = "Saran";
		
		driver.findElement(By.xpath("*//input[@id='ContentPlaceHolder1_txt_Name']")).sendKeys(name);
		
	}

}

