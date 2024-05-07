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

public class Task5 {
	
	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		
		// Perform a search (e.g., search for a specific product)
        WebElement searchInput = driver.findElement(By.id("twotabsearchtextbox"));
        searchInput.sendKeys("laptop");
        searchInput.sendKeys(Keys.RETURN);
        
         WebElement AddToCart=driver.findElement(By.id("a-autoid-1-announce"));
         AddToCart.click();
         Thread.sleep(2000);


       
		
		 // Click on the "Cart" icon to view the shopping cart
        WebElement cartIcon = driver.findElement(By.id("nav-cart"));
        cartIcon.click();

        // Proceed to checkout
        WebElement proceedToCheckoutButton = driver.findElement(By.xpath("//input[@name='proceedToRetailCheckout']"));
        proceedToCheckoutButton.click();

        // Wait for the checkout page to load
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h1.a-spacing-small")));

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
		

