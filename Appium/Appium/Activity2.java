package Appium;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URL;

public class Activity2 {

    AndroidDriver driver;

    @BeforeClass
    public void setUp() throws Exception {

        UiAutomator2Options options = new UiAutomator2Options();

        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        options.setDeviceName("Android Device");

        options.setAppPackage("com.android.chrome");
        options.setAppActivity("com.google.android.apps.chrome.Main");
        options.setNoReset(true);

        // :white_check_mark: Appium 2 default URL (NO /wd/hub)
        URL serverURL = new URL("http://localhost:4723");

        driver = new AndroidDriver(serverURL, options);

        driver.get("https://training-support.net");
    }

    @Test
    public void chromeTest() {

        String heading = driver.findElement(
                AppiumBy.xpath("//android.widget.TextView[@text='Training Support']")
        ).getText();

        System.out.println("Heading: " + heading);

        driver.findElement(AppiumBy.accessibilityId("About Us")).click();

        String aboutHeading = driver.findElement(
                AppiumBy.xpath("//android.widget.TextView[@text='About Us']")
        ).getText();

        System.out.println("About page heading: " + aboutHeading);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}