package SearchForColleges;


import java.time.Duration;
import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class driverSetup {
	
	public static WebDriver driver;
	@SuppressWarnings("resource")
	public static WebDriver getWebDriver() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter 1 for Chrome///Enter 2 for Firefox");
		int a = sc.nextInt();
		
		if(a==1) {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
		
		else if(a==2) {
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			return driver;
		}
		return driver;
	}
}
