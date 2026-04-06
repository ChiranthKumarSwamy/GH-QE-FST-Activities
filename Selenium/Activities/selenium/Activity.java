package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Activity {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver= new FirefoxDriver();
        driver.get("https://training-support.net/webelements/dynamic-controls");
        System.out.println("Page title :"+driver.getTitle());
        WebElement checkbox = driver.findElement(By.id("checkbox"));
        checkbox.click();
        Thread.sleep(3000);
        System.out.println("Checkbox is selected :"+checkbox.isSelected());
        checkbox.click();
        Thread.sleep(3000);
        System.out.println("Checkbox is selected :"+checkbox.isSelected());
        driver.quit();;
    }}
