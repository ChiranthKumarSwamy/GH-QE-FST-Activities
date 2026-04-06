package selenium;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;

public class TargetPractice {

    public static void main(String[] args) throws InterruptedException {

        // Open browser
        WebDriver driver = new ChromeDriver();

        // Open the website
        driver.get("https://training-support.net/webelements/target-practice");

        // Print page title
        System.out.println("Page Title: " + driver.getTitle());

        // Find 3rd header and print text
        String thirdHeaderText = driver.findElement(By.xpath("//h3[contains(text(), 'Heading #3')]")).getText();
        System.out.println(thirdHeaderText);
        Color fifthHeaderColor = Color.fromString(driver.findElement(By.xpath("//h5[contains(text(), 'Heading #5')]")).getCssValue("color"));
        System.out.println("Color as RGB: " + fifthHeaderColor.asRgb());
        System.out.println("Color as hexcode: " + fifthHeaderColor.asHex());
        String purpleButtonClass = driver.findElement(By.xpath("//button[text()='Purple']")).getDomAttribute("class");
        System.out.println(purpleButtonClass);
        Thread.sleep(7000);
        String slateButtonText = driver.findElement(By.xpath("//button[contains(@class, 'slate')]")).getText();
        System.out.println(slateButtonText);
        Thread.sleep(5000);

        // Close browser
        driver.quit();
    }
}