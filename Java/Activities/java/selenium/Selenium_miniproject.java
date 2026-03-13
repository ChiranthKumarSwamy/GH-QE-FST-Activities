package selenium;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Selenium_miniproject {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {

            driver.get("https://www.amazon.in/");
            driver.manage().window().maximize();
            Thread.sleep(2000);

            // Search box
            WebElement searchBox = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox"))
            );

            searchBox.sendKeys("iphone 17 pro");
            Thread.sleep(2000);

            driver.findElement(By.id("nav-search-submit-button")).click();
            Thread.sleep(3000);

            // Wait for result cards
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath("//div[@data-component-type='s-search-result']")
            ));

            List<WebElement> products = driver.findElements(
                    By.xpath("//div[@data-component-type='s-search-result']")
            );

            if (products.size() < 3) {
                System.out.println("Less than 3 products found.");
                return;
            }

            // Pick 3rd result
            WebElement thirdProduct = products.get(2);

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", thirdProduct);
            Thread.sleep(2000);

            // Product Name
            try {
                String productName = thirdProduct.findElement(By.xpath(".//h2")).getText();
                System.out.println("Product Name: " + productName);
            } catch (Exception e) {
                System.out.println("Product name not found.");
            }

            Thread.sleep(2000);

            // Price
            String wholePrice = "";
            String fractionPrice = "";

            List<WebElement> wholePriceList = thirdProduct.findElements(
                    By.xpath(".//span[contains(@class,'a-price-whole')]")
            );

            if (!wholePriceList.isEmpty()) {
                wholePrice = wholePriceList.get(0).getText();
            }

            List<WebElement> fractionPriceList = thirdProduct.findElements(
                    By.xpath(".//span[contains(@class,'a-price-fraction')]")
            );

            if (!fractionPriceList.isEmpty()) {
                fractionPrice = fractionPriceList.get(0).getText();
            }

            if (!wholePrice.isEmpty()) {
                System.out.println("Price: Rs. " + wholePrice +
                        (fractionPrice.isEmpty() ? "" : "." + fractionPrice));
            } else {
                System.out.println("Price not found.");
            }

            Thread.sleep(2000);

            // Delivery info
            String deliveryInfo = "";

            List<WebElement> deliveryList = thirdProduct.findElements(
                    By.xpath(".//*[contains(text(),'FREE delivery') or contains(text(),'Delivery') or contains(text(),'delivery')]")
            );

            for (WebElement element : deliveryList) {
                String text = element.getText().trim();
                if (!text.isEmpty()) {
                    deliveryInfo = text;
                    break;
                }
            }

            if (!deliveryInfo.isEmpty()) {
                System.out.println("Delivery Info: " + deliveryInfo);
            } else {
                System.out.println("Delivery info not found.");
            }

            Thread.sleep(2000);

        } finally {
            driver.quit();
        }
    }
}