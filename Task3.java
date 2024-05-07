package Pranav;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Task3 {
	
	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		
		driver.get("https://www.amazon.in/");

        // Perform a search (e.g., search for a specific product)
        WebElement searchInput = driver.findElement(By.id("twotabsearchtextbox"));
        searchInput.sendKeys("laptop");
        searchInput.sendKeys(Keys.RETURN);

        // Wait for search results to load
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span.a-color-state.a-text-bold")));

        // Click on the first product in the search results
        WebElement firstProductLink = driver.findElement(By.cssSelector("div[data-component-type='s-search-result'] a"));
        firstProductLink.click();

        // Wait for the product page to load
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("productTitle")));

        // Select desired options (e.g., size or color)
        // Example: Select size "Large" if available
        try {
            Select sizeDropdown = new Select(driver.findElement(By.id("native_dropdown_selected_size_name")));
            sizeDropdown.selectByVisibleText("Large");
        } catch (Exception e) {
            System.out.println("Size selection not available for this product.");
        }

        // Add the product to the shopping cart
        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-button"));
        addToCartButton.click();

        // Wait for the "Added to Cart" confirmation message
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='nav-cart-icon nav-sprite']")));

        System.out.println("Product added to cart successfully");

        // Close the browser
        driver.quit();
    }
}
		
		
