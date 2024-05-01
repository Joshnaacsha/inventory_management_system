
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import java.util.concurrent.TimeUnit;

public class RegTest {
    WebDriver driver;

    By register = By.id("registerbutton");
    By fullName = By.name("username");
    By email = By.name("email");
    By password = By.name("password");
    By dob = By.name("dateofbirth");
    By company = By.name("companyname");
    By location = By.name("location");
    By registerButton = By.id("register");

    @BeforeMethod
    public void setUp() {
        System.out.println("Open a Chrome Web Browser");
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Load the application running on local host:8080
        String localhostUrl = "http://localhost:8080/index";
        driver.get(localhostUrl);
        System.out.println("Open the application");
    }

    @Test(description = "Verify user registration functionality", priority = 0)
    public void testUserRegistration() {
        System.out.println("Clicking on register link");
        driver.findElement(register).click();
        // Validate if the registration page is loaded after clicking register link
        Assert.assertTrue(driver.getCurrentUrl().contains("register"), "Registration page not loaded");

        // Assuming we are already on the registration page
        System.out.println("Entering user details");
        driver.findElement(fullName).sendKeys("Mith");
        driver.findElement(email).sendKeys("12b.mithra@gmail.com");
        driver.findElement(password).sendKeys("password123");
        driver.findElement(dob).sendKeys("09-05-2003");
        driver.findElement(company).sendKeys("LICET");
        driver.findElement(location).sendKeys("Tambaram");
        System.out.println("Clicking on register button");
        driver.findElement(registerButton).click();
        // Validate if the registration is successful and redirected to login page
        Assert.assertTrue(driver.getCurrentUrl().contains("home"), "Registration unsuccessful");
    }

    @AfterMethod
    public void teardown() {
        System.out.println("Close the application");
        driver.quit();
    }
}
