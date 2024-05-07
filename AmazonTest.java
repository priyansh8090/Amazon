package Amazon;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonTest {
	
	public static void main(String[] args) throws InterruptedException {
		
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
                System.out.println(productFound);
                break;
            }
        }
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span.a-color-state.a-text-bold")));
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

        // Wait for search results to load
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span.a-color-state.a-text-bold")));

        // Click on the first product in the search results
        WebElement firstProductLink = driver.findElement(By.cssSelector("div[data-component-type='s-search-result'] a"));
        firstProductLink.click();

        Thread.sleep(2000);
        
        // Select desired options (e.g., size or color)
        // Example: Select size "Large" if available
        try {
            Select sizeDropdown = new Select(driver.findElement(By.id("native_dropdown_selected_size_name")));
            sizeDropdown.selectByVisibleText("Large");
        } catch (Exception e) {
            System.out.println("Size selection not available for this product.");
        }
        

        // Add the product to the shopping cart
        WebElement addToCartButton = driver.findElement(By.xpath("//button[@id='a-autoid-1-announce']"));
        addToCartButton.click();
        Thread.sleep(2000);
        
Set<String>allwindow=driver.getWindowHandles();
String parentwindow = driver.getWindowHandle();
		
		for(String s: allwindow) {
			
			if(!parentwindow.equals(s));
			
			driver.switchTo().window(s);
			Thread.sleep(2000);
    
        

        WebElement AddToCart=driver.findElement(By.xpath("//button[@id='a-autoid-1-announce']"));
        AddToCart.click();
        Thread.sleep(2000);

        // Wait for the "Added to Cart" confirmation message
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='nav-cart-icon nav-sprite']")));

        System.out.println("Product added to cart successfully");
        
       WebElement shoppingCart= driver.findElement(By.xpath("//div[@id='nav-cart-count-container']"));
       shoppingCart.click();
       
        
        // Proceed to checkout
        WebElement proceedToCheckoutButton = driver.findElement(By.xpath("//input[@name='proceedToRetailCheckout']"));
        proceedToCheckoutButton.click();

       

		WebElement cartIcon = driver.findElement(By.xpath("//input[@aria-labelledby='attach-sidesheet-view-cart-button-announce']"));
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

      
       // Verify that the checkout page is loaded
       WebElement checkoutHeader = driver.findElement(By.xpath("//h1[normalize-space()='Checkout']"));
       assert checkoutHeader.getText().contains("Checkout") : "Checkout page not loaded";

       System.out.println("Proceeded to checkout successfully");
       
       
      


       WebElement AddNewaddress=  driver.findElement(By.id("add-new-address-popover-link"));
       AddNewaddress.click();

      // Fill out the checkout form with personal information
      WebElement fullNameInput = driver.findElement(By.id("enterAddressFullName"));
      fullNameInput.sendKeys("ABCD");

      WebElement phoneNumberInput = driver.findElement(By.id("enterAddressPhoneNumber"));
      phoneNumberInput.sendKeys("1234567890");

      WebElement addressLine1Input = driver.findElement(By.id("enterAddressAddressLine1"));
      addressLine1Input.sendKeys("123 Street");

      WebElement cityInput = driver.findElement(By.id("enterAddressCity"));
      cityInput.sendKeys("Shahjahanpur");

      WebElement stateDropdown = driver.findElement(By.id("enterAddressStateOrRegion"));
      Select stateSelect = new Select(stateDropdown);
      stateSelect.selectByVisibleText("Uttar Pardesh"); // Replace with your state

      WebElement postalCodeInput = driver.findElement(By.id("enterAddressPostalCode"));
      postalCodeInput.sendKeys("123456");

      // Fill out payment information
      WebElement cardNumberInput = driver.findElement(By.id("pmcc_number"));
      cardNumberInput.sendKeys("1234567890123456");

      WebElement expMonthDropdown = driver.findElement(By.id("pmcc_exp_month"));
      Select expMonthSelect = new Select(expMonthDropdown);
      expMonthSelect.selectByVisibleText("01");

      WebElement expYearDropdown = driver.findElement(By.id("pmcc_exp_year"));
      Select expYearSelect = new Select(expYearDropdown);
      expYearSelect.selectByVisibleText("2025");

      WebElement cvvInput = driver.findElement(By.id("pmcc_cvv"));
      cvvInput.sendKeys("123");

      // Submit the form
      WebElement placeYourOrderButton = driver.findElement(By.id("placeYourOrder1"));
      placeYourOrderButton.click();

      // Wait for order confirmation page to load
      wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h1.a-spacing-small")));

      System.out.println("Checkout form submitted successfully");

      // Close the browser
      driver.quit();
       
       

 
		
		
		
	}

}
}
