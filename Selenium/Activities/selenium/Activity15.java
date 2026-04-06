package selenium;



import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Activity15 {
    public static void main(String[] args) throws InterruptedException {
        // Create a new instance of the Firefox driver
        WebDriver driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Open the page
        driver.get("https://training-support.net/webelements/dynamic-attributes");
        // Print the title of the page
        System.out.println("Page title: " + driver.getTitle());
        WebElement firstname = driver.findElement(By.xpath("//input[starts-with(@id, 'full-name')]"));
        WebElement email = driver.findElement(By.xpath("//input[contains(@id, '-email')]"));
        WebElement date = driver.findElement(By.xpath("//input[contains(@name, '-event-date-')]"));
        WebElement details=driver.findElement(By.xpath("//textarea[contains(@id, '-additional-details-')]"));
        firstname.sendKeys("shivaprasad");
        email.sendKeys("shivaprasad@gmail.com");
        date.sendKeys("2003-08-23");
        details.sendKeys("how are you!!!!!!!!!!!!!");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"signInForm\"]/button")).click();
        String message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("action-confirmation")))
                .getText();
        System.out.println("Success message: " + message);
        Thread.sleep(5000);
        driver.quit();
    }
}
