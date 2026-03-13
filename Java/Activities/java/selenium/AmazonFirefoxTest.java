package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Keys;
import java.util.List;

public class AmazonFirefoxTest {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new FirefoxDriver();

        driver.get("https://www.amazon.in");
        driver.manage().window().maximize();

        // Search iphone 17 pro
        WebElement search = driver.findElement(By.id("twotabsearchtextbox"));
        search.sendKeys("iphone 17 pro");
        search.sendKeys(Keys.RETURN);

        Thread.sleep(3000);

        // Get search results
        List<WebElement> results = driver.findElements(
                By.xpath("//div[@data-component-type='s-search-result']")
        );

        // Click 3rd product
        results.get(2).findElement(By.tagName("h2")).click();

        Thread.sleep(3000);

        // Switch tab
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }

        // Get price
        String price = driver.findElement(By.xpath("//span[@class='a-price-whole']")).getText();

        // Get delivery
        String delivery = driver.findElement(By.id("delivery-message")).getText();

        System.out.println("Price: " + price);
        System.out.println("Delivery: " + delivery);

        driver.quit();
    }
}