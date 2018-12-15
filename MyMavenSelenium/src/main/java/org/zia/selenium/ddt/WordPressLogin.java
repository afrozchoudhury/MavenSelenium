package org.zia.selenium.ddt;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class WordPressLogin {
	
	WebDriver driver;
	
//	@BeforeClass
//	  public void setUp() throws Exception {
//		System.setProperty("webdriver.chrome.driver", "C:/chromedriver_win32/chromedriver.exe");
//			
//		driver = new ChromeDriver();
//		driver.manage().window().maximize();
//	    baseUrl = "https://s1.demo.opensourcecms.com/wordpress/wp-login.php";
//	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//	  }
	
	@Test(dataProvider="wordpressData")
	public void loginToWordpress(String username, String password) throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "C:/chromedriver_win32/chromedriver.exe");
		//*[@id="wp-submit"]
		driver = new ChromeDriver();
		//WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://s1.demo.opensourcecms.com/wordpress/wp-login.php");
		driver.findElement(By.name("log")).sendKeys(username);
		driver.findElement(By.name("pwd")).sendKeys(password);
		driver.findElement(By.xpath("//*[@id=\"wp-submit\"]")).click();
		Thread.sleep(5000);
		System.out.println(driver.getTitle());
		
		Assert.assertTrue(driver.getTitle().contains("Dashboard"), "User is not able to login. Invalid credentials");
		System.out.println("Page title verified. User is able to successfully login");
		
		
	}
	
	@AfterMethod
	public void teardown() {
		
		driver.quit();
		
	}

	@DataProvider(name="wordpressData")
	public Object[][] passData() {
		
		Object[][] data = new Object[3][2];
		data[0][0] = "admin1";
		data[0][1] = "demo1";

		data[1][0] = "opensourcecms";
		data[1][1] = "opensourcecms";
		
		data[2][0] = "admin";
		data[2][1] = "demo3";
		
		return data;
		
	
	}
}
