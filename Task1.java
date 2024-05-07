package Pranav;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task1 {
	
	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		
		// Find the search input element
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        
        // Enter the product name to search for
        String laptop = "laptop"; 
        searchBox.sendKeys(laptop);
        
        // Submit the search by pressing Enter
        searchBox.sendKeys(Keys.RETURN);
        
        // Wait for search results to load
        try {
            Thread.sleep(3000); // You can adjust the wait time as needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Find all the search result elements
        List<WebElement> searchResults = driver.findElements(By.cssSelector("div[data-component-type='s-search-result']"));
        
        // Check if the search results contain the product name
        boolean productFound = false;
        for (WebElement result : searchResults) {
            WebElement titleElement = result.findElement(By.cssSelector("span.a-text-normal"));
            if (titleElement.getText().toLowerCase().contains(laptop.toLowerCase())) {
                productFound = true;
                break;
            }
        }
		
	}

}
