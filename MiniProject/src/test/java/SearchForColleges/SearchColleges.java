/*SEARCH FOR COLLEGES:
Open the browser
Enter the URL - https://www.eduvidya.com/
Click on "Colleges" link in the Menu bar
Click on "Course" dropdown and select a course (ex: Science)
Click on "City" dropdown and select “Chennai” as city
Click on "Search" button
Verify Search results is displayed or not
Close the browser*/

package SearchForColleges;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SearchColleges {
	public static WebDriver driver;
	
	public void createDriver() {
	  driver = driverSetup.getWebDriver();
	  String baseUrl = "https://www.eduvidya.com/";
	  driver.get(baseUrl);
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	  //Opening the link
	  try {
	  driver.findElement(By.id("details-button")).click();
	  driver.findElement(By.id("proceed-link")).click();
	  }
	  catch (Exception e) {
	  }
	}
	  
	public void setConds() throws IOException{
		//Select Colleges
		driver.findElement(By.xpath("//div[@id='cssmenu']/ul/li[2]/a")).click();
		
		//Try and Catch block to close the ads
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		try {
		
				WebElement f1 = driver.findElement(By.xpath("//div[@id=\"aswift_3_host\"]"));
				driver.switchTo().frame(f1.findElement(By.xpath("//iframe[@id='aswift_3']")));
				
				WebElement f2 = driver.findElement(By.xpath("//div[@id=\"ad_position_box\"]"));
				driver.switchTo().frame(f2.findElement(By.xpath("//iframe[@id=\"ad_iframe\"]")));
				
				WebElement cl = driver.findElement(By.xpath("//*[@id=\"dismiss-button\"]"));
				js.executeScript("arguments[0].click();",cl);
				driver.switchTo().defaultContent();
				System.out.println("Ad closed");
			
		}
		catch(Exception e) {
		}
		
		String file = System.getProperty("user.dir")+"\\src/test/resources\\Data.xlsx";
		

		//Select Science
		Select sc = new Select(driver.findElement(By.xpath("//*[@id=\"ddl_Category\"]")));
		sc.selectByVisibleText(ExcelUtils.getCellData(file,"Sheet1",1,0));

		//Select Chennai
		Select sci = new Select(driver.findElement(By.xpath("//*[@id=\"ddl_City\"]")));
		sci.selectByVisibleText(ExcelUtils.getCellData(file,"Sheet1",1,1));

		//Click Search
		WebElement s = driver.findElement(By.id("btnSearch"));
		js.executeScript("arguments[0].click();",s);
	}

	//Display of Results
	public void searchResults(){
		List<WebElement> results = driver.findElements(By.xpath("//div[@id='content']/div/div/div[2]/div/div/div/div[2]/ul/li/div[2]/a"));
		System.out.println("------------------------------");
		if(results.size() > 0){
		System.out.println("Search Results are Displayed");
		}
		else{
		System.out.println("Search Results are not Displayed");
		}
		System.out.println("------------------------------");
	}

	//Closing Browser
	public void closeBrowser(){
		driver.close();
	}
	
	public static void main(String[] args) throws Exception {
		
		//System.out.println("Enter chrome/edge");
		//Scanner sc = new Scanner(System.in);
		//String str = sc.nextLine();
		SearchColleges mp = new SearchColleges();
		mp.createDriver();
		mp.setConds();
		mp.searchResults();
		mp.closeBrowser();
			
	}
}