package selenium;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class amazon {

    public static void main(String[] args) {

        WebDriver driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://www.amazon.in/");
        System.out.println("Page Title is: " + driver.getTitle());

        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("iphone 17 pro");
        driver.findElement(By.id("nav-search-submit-button")).click();

        String productName = driver.findElement(
                By.xpath("(//div[@data-component-type='s-search-result']//h2/span)[1]"))
                .getText();

        String productPrice = driver.findElement(
                By.xpath("(//span[@class='a-price-whole'])[1]"))
                .getText();

        String shippingDate = driver.findElement(
                By.xpath("(//span[contains(text(),'Delivery')])[1]"))
                .getText();

        System.out.println("Product Name: " + productName);
        System.out.println("Price: ₹" + productPrice);
        System.out.println("Shipping Info: " + shippingDate);

        driver.quit();
    }
}