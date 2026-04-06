package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Activity5 {
    public static void main(String[] args) throws InterruptedException {
         WebDriver driver = new FirefoxDriver();
        driver.get("https://training-support.net/webelements/dynamic-controls");
        //print title
        System.out.println("Page title: " + driver.getTitle());
        // Find the checkbox
        WebElement checkbox = driver.findElement(By.id("checkbox"));
        WebElement c = driver.findElement(By.xpath("//button[text()='Toggle Checkbox']"));

        c.click();
        Thread.sleep(3000);
        System.out.println("Checkbox is displayed: " + checkbox.isDisplayed());
        c.click();
        Thread.sleep(3000);
        System.out.println("Checkbox is displayed: " + checkbox.isDisplayed());
        driver.quit();

    }
}
