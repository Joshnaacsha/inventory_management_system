import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import java.util.concurrent.TimeUnit;

public class NewTest {

    WebDriver driver;
    boolean isLoggedIn = false;

    By loginBtnOnIndexPage = By.id("loginbutton");
    By userName = By.name("email");
    By passWord = By.name("password");
    By loginBtnOnLoginPage = By.id("login");
    By product = By.id("product");
    By productId = By.name("prodid");
    By productName = By.name("prodname");
    By quantity = By.name("prodqty");
    By price = By.name("prodprice");
    By supplierId = By.name("supid");
    By expiryDate = By.name("prodexp");
    By productadding = By.id("productadd");
    By productback = By.id("backbutton");
    By supplier = By.id("supplier");
    By supplier_add_name = By.id("suppliername");
    By supplier_id = By.id("supid");
    By supplier_email = By.id("email");
    By supplier_phone = By.name("phone");
    By supplier_address = By.name("address");
    By supplier_add = By.id("supplier");
    By supplierback = By.id("supplierback");
    By billgen = By.id("bill");
    By bill_id = By.id("bill-id");
    By bill_prod_id = By.id("product-id");
    By bill_prodname = By.id("productname");
    By bill_quantity = By.id("quantity");
    By billadd = By.id("add");
    By billclear = By.id("clear");
    By bill_back = By.className("back-button");

    @BeforeMethod
    public void setUp() throws InterruptedException {
        if (!isLoggedIn) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            String localhostUrl = "http://localhost:8080/index";
            driver.get(localhostUrl);
            Thread.sleep(7000);

            // Login only once before executing any test method
            System.out.println("Clicking on login button on index page");
            driver.findElement(loginBtnOnIndexPage).click();
            Thread.sleep(7000);
            Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Login page not loaded");
            System.out.println("Entering username and password");
            driver.findElement(userName).sendKeys("jo@gmail.com");
            driver.findElement(passWord).sendKeys("1234");
            System.out.println("Clicking on login button on login page");
            Thread.sleep(7000);
            driver.findElement(loginBtnOnLoginPage).click();
            Thread.sleep(7000);
            Assert.assertTrue(driver.getCurrentUrl().contains("home"), "Home page not loaded");
            isLoggedIn = true;
        }
    }

    @Test(description = "Product functionality test", priority = 0)
    public void testProductFunctionality() throws InterruptedException {
        System.out.println("Navigating to product page");
        Thread.sleep(7000);
        driver.findElement(product).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("product"), "Product page not loaded");
        Thread.sleep(7000);
        // Assuming we are already on the product page
        System.out.println("Entering product details");
        driver.findElement(productId).sendKeys("210");
        driver.findElement(productName).sendKeys("Cheese");
        driver.findElement(quantity).sendKeys("2");
        driver.findElement(price).sendKeys("500");
        driver.findElement(expiryDate).sendKeys("10-05-2024");
        driver.findElement(supplierId).sendKeys("156");
        System.out.println("Clicking on add product button");
        Thread.sleep(7000);
        driver.findElement(productadding).click();
        Thread.sleep(7000);

        // Validate if the product is added successfully
        Assert.assertTrue(driver.getCurrentUrl().contains("products"), "Product not added successfully");
        System.out.println("Clicking on back product button");
        driver.findElement(productback).click();
        Thread.sleep(7000);
        Assert.assertTrue(driver.getCurrentUrl().contains("home"), "Error in back");
    }

    @Test(description = "Supplier functionality test", priority = 1)
    public void testSupplierFunctionality() throws InterruptedException {
        System.out.println("Navigating to supplier page");
        Thread.sleep(7000);
        driver.findElement(supplier).click();
        Thread.sleep(7000);
        System.out.println("Entering supplier details");
        driver.findElement(supplier_add_name).sendKeys("Yuvaraj");
        driver.findElement(supplier_id).sendKeys("156");
        driver.findElement(supplier_email).sendKeys("12b.mithra@gmail.com");
        driver.findElement(supplier_phone).sendKeys("7604981442");
        driver.findElement(supplier_address).sendKeys("No. 1A SP avenue tamilnadu chennai");
        System.out.println("Clicking on add supplier button");
        Thread.sleep(7000);
        driver.findElement(supplier_add).click();
        Thread.sleep(7000);
        Assert.assertTrue(driver.getCurrentUrl().contains("sup"), "Home page not loaded");
        System.out.println("Clicking on back supplier button");
        driver.findElement(supplierback).click();
        Thread.sleep(7000);
        Assert.assertTrue(driver.getCurrentUrl().contains("home"), "Error in back");
    }

    @Test(description = "Bill generation functionality test", priority = 2)
    public void testBillGeneration() throws InterruptedException {
        System.out.println("Navigating to bill generation page");
        driver.findElement(billgen).click();
        Thread.sleep(7000);

        // Validate if the bill page is loaded
        Assert.assertTrue(driver.getCurrentUrl().contains("bill"), "Bill Generation page not loaded");
        Thread.sleep(7000);
        // Assuming we are already on the bill page
        System.out.println("Entering bill details");
        driver.findElement(bill_id).sendKeys("21");
        driver.findElement(bill_prod_id).sendKeys("210");
        driver.findElement(bill_prodname).sendKeys("cheese");
        driver.findElement(bill_quantity).sendKeys("1");
        Thread.sleep(7000);
        System.out.println("Clicking on add bill button");
        driver.findElement(billadd).click();
        Thread.sleep(7000);
        Assert.assertTrue(driver.getCurrentUrl().contains("billprod"), "Bill page not loaded");
        System.out.println("Clicking on back bill button");
        Thread.sleep(7000);
        driver.findElement(bill_back).click();
        Thread.sleep(7000);
        Assert.assertTrue(driver.getCurrentUrl().contains("home"), "Error in back");
    }

    @Test(description = "Profile View", priority = 3)
    public void testProfile() throws InterruptedException {
        System.out.println("Navigating to profile page");
        driver.findElement(By.xpath("/html/body/nav/div[3]/a[1]")).click();
        Thread.sleep(7000);

        // Validate if the profile page is loaded
        Assert.assertTrue(driver.getCurrentUrl().contains("profile"), "Profile page not loaded");
        System.out.println("Clicking on add back button in profile");
        Thread.sleep(7000);
        driver.findElement(By.xpath("//*[@id=\"profileback\"]")).click();
        Thread.sleep(7000);
        Assert.assertTrue(driver.getCurrentUrl().contains("home"), "Error in back");
    }

    int i = 0;

    @AfterMethod
    public void closingTheApp() {
        System.out.println("Functionality Test with priotity " + i + " is executed!");
        i++;
    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            System.out.println("Closing the application after testing all functionalities");
            driver.quit();
        }
    }
}
