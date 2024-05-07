package Pranav;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Task2 {

	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		
		 WebElement searchInput = driver.findElement(By.id("twotabsearchtextbox"));
	        searchInput.sendKeys("laptops");
	        searchInput.sendKeys(Keys.RETURN);

	        // Wait for search results to load
	        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span.a-color-state.a-text-bold")));
	        
	        // Filter search results by price range (e.g., ₹20,000 - ₹40,000)
	        WebElement priceFilter = driver.findElement(By.xpath("//span[text()='₹20,000 – ₹30,000']"));
	        priceFilter.click();

	        // Wait for filtered results to load
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[data-component-type='s-search-result']")));

	        // Verify filtered results
	        java.util.List<WebElement> productPrices = driver.findElements(By.cssSelector("span.a-price-whole"));
	        int minPrice = 20000;
	        int maxPrice = 40000;
	        for (WebElement priceElement : productPrices) {
	            int price = Integer.parseInt(priceElement.getText().replace(",", ""));
	            assert minPrice <= price && price <= maxPrice : "Price " + price + " is not within the specified range";
	        }

	        System.out.println("Filtered search results verified successfully");

	        // Close the browser
	        driver.quit();
	    }
	}
		
			


	
		
		

