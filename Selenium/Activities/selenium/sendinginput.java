package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class sendinginput {
      public static void main(String[] args) throws InterruptedException {

    // Create a new instance of the Chrome driver
    WebDriver driver = new ChromeDriver();
    
    // Open the browser
    driver.navigate().to("https://training-support.net/webelements/login-form/");
    System.out.println(driver.getTitle());
    
    WebElement username = driver.findElement(By.id("username"));
    username.sendKeys("admin");
    
    Thread.sleep(7000);
    WebElement password = driver.findElement(By.id("password"));
    password.sendKeys("password");
    WebElement loginButton = driver.findElement(By.xpath("//button[text()='Submit']"));
    loginButton.click();
    driver.quit();
    
  }
}
