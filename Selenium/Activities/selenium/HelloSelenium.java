package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HelloSelenium {

  public static void main(String[] args) throws InterruptedException {

    // Create a new instance of the Chrome driver
    WebDriver driver = new ChromeDriver();
    
    // Open the browser
    driver.navigate().to("https://training-support.net");
    System.out.println(driver.getTitle());
    Thread.sleep(3000);
    driver.findElement(By.linkText("About Us")).click();
    System.out.println(driver.getTitle());

    
  }
}