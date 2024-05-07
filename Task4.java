package Pranav;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task4 {
	
	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		
		driver.get("https://www.amazon.in/");
		
		// Perform a search (e.g., search for a specific product)
        WebElement searchInput = driver.findElement(By.id("twotabsearchtextbox"));
        searchInput.sendKeys("laptop");
        searchInput.sendKeys(Keys.RETURN);
        
         WebElement AddToCart=driver.findElement(By.id("a-autoid-1-announce"));
         AddToCart.click();
         Thread.sleep(2000);
        

		WebElement cartIcon = driver.findElement(By.id("nav-cart"));
        cartIcon.click();

        // Verify that the correct product and options are displayed in the shopping cart
        WebElement productName = driver.findElement(By.xpath("//span[@class='a-truncate sc-grid-item-product-title a-size-base-plus']"));
        WebElement productPrice = driver.findElement(By.xpath("//span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap sc-product-price a-text-bold']"));
        WebElement productQuantity = driver.findElement(By.xpath("//span[@id='a-autoid-0-announce']"));

        String expectedProductName = "Laptop"; 
        String expectedProductPrice = "₹3,69,990"; 
        int expectedProductQuantity = 1; 

        // Verify product details
        assert productName.getText().equals(expectedProductName) : "Incorrect product name";
        assert productPrice.getText().equals(expectedProductPrice) : "Incorrect product price";
        assert Integer.parseInt(productQuantity.getText()) == expectedProductQuantity : "Incorrect product quantity";

        System.out.println("Product details in the shopping cart verified successfully");

        // Proceed to checkout
        WebElement proceedToCheckoutButton = driver.findElement(By.xpath("//input[@name='proceedToRetailCheckout']"));
        proceedToCheckoutButton.click();

        // Verify that the checkout page is loaded
        WebElement checkoutHeader = driver.findElement(By.xpath("//h1[normalize-space()='Checkout']"));
        assert checkoutHeader.getText().contains("Checkout") : "Checkout page not loaded";

        System.out.println("Proceeded to checkout successfully");

        // Close the browser
        driver.quit();
    }
}
		
		

