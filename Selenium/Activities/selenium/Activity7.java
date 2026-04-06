package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Activity7 {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver= new FirefoxDriver();
        driver.get("https://training-support.net/webelements/dynamic-controls");
        System.out.println("Page title :"+driver.getTitle());
        WebElement sp = driver.findElement(By.id("textInput"));
        System.out.println("is Enabled :"+sp.isEnabled());
        Thread.sleep(3000);
        WebElement c = driver.findElement(By.id("textInputButton"));
        c.click();
        sp.sendKeys("shivu");
        System.out.println("is Enabled :"+sp.isEnabled());
        Thread.sleep(3000);
        driver.quit();

    
}
}
