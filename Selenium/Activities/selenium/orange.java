package selenium;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class orange {

    static WebDriver driver;
    static WebDriverWait wait;

    static String baseUrl = "https://alchemy.hguy.co/orangehrm/";
    static String username = "orange";
    static String password = "orangepassword123";

    static String savedFirstName = "John";
    static String savedLastName = "Doe" + UUID.randomUUID().toString().substring(0, 4);
    static String savedEmployeeId = "";

    public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--allow-insecure-localhost");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();

        try {
            verifyWebsiteTitle();
            getHeaderImageUrl();
            loginToSite();
            addNewEmployeeAndVerify();
            editPersonalInfo();
            verifyDirectoryMenu();
            addQualifications();
            applyForLeave();
            retrieveEmergencyContacts();
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    public static void openSite() {
        driver.get(baseUrl);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtUsername")));
    }

    public static void login() {
        openSite();

        WebElement usernameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("txtUsername")));
        usernameField.clear();
        usernameField.sendKeys(username);

        WebElement passwordField = driver.findElement(By.id("txtPassword"));
        passwordField.clear();
        passwordField.sendKeys(password);

        driver.findElement(By.id("btnLogin")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("welcome")));
        System.out.println("Login successful");
    }

    public static void verifyWebsiteTitle() {
        openSite();

        String actualTitle = driver.getTitle();
        System.out.println("Website title: " + actualTitle);

        if (actualTitle.equals("OrangeHRM")) {
            System.out.println("Title matched successfully");
        } else {
            System.out.println("Title did not match");
        }
    }

    public static void getHeaderImageUrl() {
        openSite();

        WebElement logo = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='divLogo']//img")));

        String imageUrl = logo.getDomProperty("src");
        System.out.println("Header image URL: " + imageUrl);
    }

    public static void loginToSite() {
        login();
        System.out.println("Current URL after login: " + driver.getCurrentUrl());
    }

    public static void addNewEmployeeAndVerify() {
        login();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("menu_pim_viewPimModule"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("menu_pim_addEmployee"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstName"))).sendKeys(savedFirstName);
        driver.findElement(By.id("lastName")).sendKeys(savedLastName);

        savedEmployeeId = driver.findElement(By.id("employeeId")).getDomProperty("value");

        driver.findElement(By.id("btnSave")).click();

        System.out.println("Employee added successfully");
        System.out.println("Employee Name: " + savedFirstName + " " + savedLastName);
        System.out.println("Employee ID: " + savedEmployeeId);

        wait.until(ExpectedConditions.elementToBeClickable(By.id("menu_pim_viewEmployeeList"))).click();

        WebElement empIdField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("empsearch_id")));
        empIdField.clear();
        empIdField.sendKeys(savedEmployeeId);

        driver.findElement(By.id("searchBtn")).click();

        WebElement resultIdCell = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//table[@id='resultTable']/tbody/tr/td[2]")));

        String resultId = resultIdCell.getText();

        if (resultId.equals(savedEmployeeId)) {
            System.out.println("Employee found in employee list with ID: " + resultId);
        } else {
            System.out.println("Employee verification failed");
        }
    }

    public static void editPersonalInfo() {
        login();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("menu_pim_viewMyDetails"))).click();

        WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSave")));
        editButton.click();

        WebElement firstNameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("personal_txtEmpFirstName")));
        firstNameField.clear();
        firstNameField.sendKeys("Chiranth");

        WebElement lastNameField = driver.findElement(By.id("personal_txtEmpLastName"));
        lastNameField.clear();
        lastNameField.sendKeys("Kumar");

        WebElement maleRadio = driver.findElement(By.id("personal_optGender_1"));
        if (!maleRadio.isSelected()) {
            maleRadio.click();
        }

        Select nationality = new Select(driver.findElement(By.id("personal_cmbNation")));
        nationality.selectByVisibleText("Indian");

        WebElement dobField = driver.findElement(By.id("personal_DOB"));
        dobField.clear();
        dobField.sendKeys("1999-08-15");

        driver.findElement(By.id("btnSave")).click();
        System.out.println("Personal information updated successfully");
    }

    public static void verifyDirectoryMenu() {
        login();

        WebElement directoryMenu = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("menu_directory_viewDirectory")));

        if (directoryMenu.isDisplayed() && directoryMenu.isEnabled()) {
            System.out.println("Directory menu is visible and clickable");
            directoryMenu.click();

            WebElement heading = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Search Directory']")));
            System.out.println("Directory page heading: " + heading.getText());
        } else {
            System.out.println("Directory menu is not visible");
        }
    }

    public static void addQualifications() {
        login();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("menu_pim_viewMyDetails"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Qualifications"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("addWorkExperience"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("experience_employer"))).sendKeys("ABC Company");
        driver.findElement(By.id("experience_jobtitle")).sendKeys("QA Tester");
        driver.findElement(By.id("experience_from_date")).clear();
        driver.findElement(By.id("experience_from_date")).sendKeys("2022-01-01");
        driver.findElement(By.id("experience_to_date")).clear();
        driver.findElement(By.id("experience_to_date")).sendKeys("2023-12-31");
        driver.findElement(By.id("experience_comments")).sendKeys("Worked as QA tester");

        driver.findElement(By.id("btnWorkExpSave")).click();
        System.out.println("Qualification added successfully");
    }

    public static void applyForLeave() {
        login();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("menu_leave_viewLeaveModule"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("menu_leave_applyLeave"))).click();

        Select leaveType = new Select(wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("applyleave_txtLeaveType"))));
        leaveType.selectByIndex(1);

        WebElement fromDate = driver.findElement(By.id("applyleave_txtFromDate"));
        fromDate.clear();
        fromDate.sendKeys("2026-03-25");

        WebElement toDate = driver.findElement(By.id("applyleave_txtToDate"));
        toDate.clear();
        toDate.sendKeys("2026-03-26");

        driver.findElement(By.id("applyleave_txtComment")).sendKeys("Personal work");
        driver.findElement(By.id("applyBtn")).click();

        System.out.println("Leave applied successfully");

        wait.until(ExpectedConditions.elementToBeClickable(By.id("menu_leave_viewMyLeaveList"))).click();
        System.out.println("Navigated to My Leave page");

        WebElement statusCell = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//table[@id='resultTable']/tbody/tr[1]/td[6]")));
        System.out.println("Leave status: " + statusCell.getText());
    }

    public static void retrieveEmergencyContacts() {
        login();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("menu_pim_viewMyDetails"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Emergency Contacts"))).click();

        List<WebElement> rows = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//table[@id='emgcontact_list']/tbody/tr")));

        System.out.println("Emergency Contacts:");

        if (rows.size() == 0) {
            System.out.println("No emergency contacts found");
        } else {
            for (WebElement row : rows) {
                List<WebElement> cols = row.findElements(By.tagName("td"));
                for (int i = 1; i < cols.size(); i++) {
                    System.out.print(cols.get(i).getText() + " | ");
                }
                System.out.println();
            }
        }
    }
}