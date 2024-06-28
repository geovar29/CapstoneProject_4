package stepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginStepDefinition{

	 WebDriver driver;

	
	 @Given("^user launch the browser$")
	 public void user_launch_the_browser(){
	 System.setProperty("webdriver.chrome.driver","/Users/george/Downloads/chromedriver");
	 driver = new ChromeDriver();
	 
	 }
	
	
	 @When("^Navigate to the URL and clicking Ab Testing link$")
	 public void Navigate_to_the_URL_and_clicking_Ab_Testing_link() throws InterruptedException{
		 driver.get("http://the-internet.herokuapp.com/");
		 WebDriverWait wait = new WebDriverWait(driver, 10);
		 wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[text()='A/B Testing']"))));
		 Thread.sleep(5000);
		 driver.findElement(By.xpath("//a[text()='A/B Testing']")).click();
		 driver.navigate().back();
		 wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[text()='A/B Testing']"))));
	 }
	
	
	 @Then("^Navigating back to the home page and clicking dropdown$")
	 public void Navigating_back_to_the_home_page_and_clicking_dropdown(){
		 WebDriverWait wait = new WebDriverWait(driver, 10);
		 driver.navigate().back();
		 wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[text()='A/B Testing']"))));
		 driver.findElement(By.xpath("//a[text()='Dropdown']")).click();
		 wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//select[@id='dropdown']"))));
		 
	 }
	
	 @Then("^selecting the option1 and validating the selected option$")
	 public void selecting_the_option1_and_validating_the_selected_option() throws InterruptedException {
		 WebDriverWait wait = new WebDriverWait(driver, 10);
		 wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//select[@id='dropdown']"))));
		 driver.findElement(By.xpath("//select[@id='dropdown']")).click();
		 Thread.sleep(3000);
		 Select dropdown = new Select(driver.findElement(By.xpath("//select[@id='dropdown']")));
		 dropdown.selectByVisibleText("Option 1");
		 Thread.sleep(3000);
		 String selectedOption = driver.findElement(By.xpath("//select[@id='dropdown']")).getText();
		 Assert.assertEquals(selectedOption, "Option 1");
		 driver.navigate().back();
	 }
	
	
	 @Then("^Navigate back to the home page and validating frame$")
	 public void Navigate_back_to_the_home_page_and_validating_frame() throws InterruptedException{
		 WebDriverWait wait = new WebDriverWait(driver, 10);
		 wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[text()='Frames']"))));
		 driver.findElement(By.xpath("//a[text()='Frames']")).click();
		 wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='example']/h3"))));
		 driver.findElement(By.xpath("//a[text()='iFrame']")).click();
		 Thread.sleep(3000);
		 driver.switchTo().frame("mce_0_ifr");
		 Assert.assertEquals(driver.findElement(By.xpath("//p[text()='Your content goes here.']")).getText(), "Your content goes here.");
		 driver.navigate().back();
		 Thread.sleep(3000);
		 driver.findElement(By.xpath("//a[text()='Nested Frames']")).click();
		 driver.switchTo().frame(1);
		 driver.switchTo().frame(1);
		 driver.switchTo().frame(1);
		 Assert.assertEquals(driver.findElement(By.xpath("//body[text()='     LEFT     ']")).toString().trim(), "LEFT");
	 }
	 
	 

	 @Then("^Close the browser$")
	 public void close_the_browser(){
		 driver.quit();
	 }
	
	
	

}
