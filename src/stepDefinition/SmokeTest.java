package stepDefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;



public class SmokeTest {

	WebDriver driver = null;

	//Scenario to test the 5 day weather forecast for a city

	@Given("^I am on the http://localhost:3000/ page$") 
	public void openWeatherApp() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://localhost:3000/");
		driver.manage().window().maximize();
	}

	@When("^I enter the city name as (.*) and click Enter$") 
	public void enterCity(String city) throws Throwable	{
		driver.findElement(By.xpath("//*[@data-test='city']")).clear();
		driver.findElement(By.xpath("//*[@data-test='city']")).sendKeys(city);
		driver.findElement(By.xpath("//*[@data-test='city']")).submit();
	}
	//
	@Then("^I should get (\\d+) day weather forecast for (.*)\\.$")
	public void testdayForecast(Integer noofdays,String city) throws Throwable	{
		for(int i=1; i<=noofdays;i++){
			assertTrue(isElementPresent(By.xpath("//*[@data-test='day-"+i+"']")));
			
		}
		
	}

	//Scenario to test summarized 3 hour data is displayed on daily forecast for a city
	
	@Given("^I am on the weather forecast page for a (.*)$")
	public void get5dayForecast(String city) throws Throwable	{
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://localhost:3000/");
		driver.manage().window().maximize();	
	}

	@When("^I see the daily forecast for that (.*)$")
	public void getDailyForecast(String city) throws InterruptedException {
		driver.findElement(By.xpath("//*[@data-test='city']")).clear();
		driver.findElement(By.xpath("//*[@data-test='city']")).sendKeys(city);
		driver.findElement(By.xpath("//*[@data-test='city']")).submit();

	}

	@Then("^I should see summarised three hour data\\.$")
	public void testThreeHourlySummary() {
		for(int i=1; i<=5;i++){
			assertTrue(isElementPresent(By.xpath("//*[@data-test='description-"+i+"']")));
			assertTrue(isElementPresent(By.xpath("//*[@data-test='maximum-"+i+"']")));
			assertTrue(isElementPresent(By.xpath("//*[@data-test='minimum-"+i+"']")));
			assertTrue(isElementPresent(By.xpath("//*[@data-test='speed-"+i+"']")));
			assertTrue(isElementPresent(By.xpath("//*[@data-test='direction-"+i+"']")));
			assertTrue(isElementPresent(By.xpath("//*[@data-test='rainfall-"+i+"']")));
		}
	}
	
	//Scenario to test 3 hour forecast data displayed on clicking a day
	
	@Given("^I am on the forecast page$")
	public void renderWeatherApp() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://localhost:3000/");
		driver.manage().window().maximize();
	 
	}

	@And("^I see the five day weather forecast for (.*)$")
	public void view_daily_forecast(String city) throws InterruptedException{
		driver.findElement(By.xpath("//*[@data-test='city']")).clear();
		driver.findElement(By.xpath("//*[@data-test='city']")).sendKeys(city);
		driver.findElement(By.xpath("//*[@data-test='city']")).submit();

	}

	@When("^I click day (\\d+)$")
	public void select_a_day(Integer daynum){
	   driver.findElement(By.xpath("//*[@data-test='day-"+daynum+"']")).click();
	}

	@Then("^I should get (\\d+) hourly weather forecast for day (\\d+)\\.$")
	public void get_3hour_forecast(Integer hourspan,int daynum) {
		WebElement table = driver.findElement(By.xpath("//*[@id='root']/div/div["+daynum+"]/div[2]")); // get table
		int hrRows = table.findElements(By.className("hour")).size();
		if(hrRows>1) {
			for(int hrValue = 1;hrValue<hrRows;hrValue++){
				int currHour = Integer.parseInt(driver.findElement(By.xpath("//*[@data-test='hour-"+daynum+"-"+hrValue+"']")).getText());
				int nextHour = Integer.parseInt(driver.findElement(By.xpath("//*[@data-test='hour-"+daynum+"-"+(hrValue+1)+"']")).getText());
				assertEquals(hourspan*100, (nextHour-currHour));
			}
		}
	}
	
	private boolean isElementPresent(By by)	{ 
		try { 
			driver.findElement(by); 
		return true; 
		} 

		catch (NoSuchElementException e) { 
			return false; 
		}
	}

	}
