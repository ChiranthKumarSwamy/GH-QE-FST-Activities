package Appium;

import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Activity4 {

    AndroidDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() throws Exception {

        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        options.setDeviceName("emulator-5554");
        options.setAppPackage("com.google.android.contacts");
        options.setNoReset(true);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Test
    public void contactsTest() {

        // Wait for app to open
        wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.xpath("//android.widget.FrameLayout")
        ));

        // Click Create/Add contact
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().descriptionContains(\"contact\")"
        )).click();

        // Wait for contact form
        wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.className("android.widget.EditText")
        ));

        // Fill first name and last name
        List<WebElement> fields = driver.findElements(AppiumBy.className("android.widget.EditText"));
        fields.get(0).sendKeys("Aaditya");
        fields.get(1).sendKeys("Varma");

        // Click current country dropdown: United States +1
        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.xpath("//*[contains(@text,'United States') or contains(@text,'+1')]")
        )).click();

        // Scroll and select India +91 directly from the list
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                ".scrollIntoView(new UiSelector().textContains(\"India +91\"))"
        )).click();

        // Wait until India +91 is selected back on contact form
        wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.xpath("//*[contains(@text,'India') or contains(@text,'+91')]")
        ));

        // Re-fetch fields after dropdown closes
        List<WebElement> updatedFields = driver.findElements(AppiumBy.className("android.widget.EditText"));

        // Phone field is after Company, so index 3
        updatedFields.get(3).sendKeys("999148292");

        // Click Save
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().textContains(\"Save\")"
        )).click();

        // Validate saved contact name
        WebElement savedName = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Aaditya Varma\")")
        ));

        String contactName = savedName.getText();
        System.out.println("Saved Contact: " + contactName);

        Assert.assertTrue(contactName.contains("Aaditya Varma"));
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}