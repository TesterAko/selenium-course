package MyStore;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class SignInTest {
    private static WebDriver driver;
    private static Faker faker;

    @BeforeAll
    static void globalSetup() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        faker = new Faker();
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();

        }
    }

    @Test
    @DisplayName("Sign In Test")
    void signInTest() throws InterruptedException {
        //Arrange Block
        String email = faker.internet().emailAddress();
        String vorname = faker.name().firstName();
        String nachname = faker.name().lastName();
        String passwort = faker.internet().password();
        String expectedUserName = vorname + " " + nachname;

        //Act Block
        driver.get("http://www.automationpractice.pl/index.php");
        driver.manage().window().maximize();
        Thread.sleep(2000);

        driver.findElement(By.className("login")).click();
        Thread.sleep(2000);

        driver.findElement(By.id("email_create")).sendKeys(email);
        driver.findElement(By.id("SubmitCreate")).click();
        Thread.sleep(2000);

        driver.findElement(By.id("id_gender1")).click();
        driver.findElement(By.id("customer_firstname")).sendKeys(vorname);
        driver.findElement(By.id("customer_lastname")).sendKeys(nachname);
        driver.findElement(By.id("passwd")).sendKeys(passwort);

        Select select = new Select(driver.findElement(By.id("days")));
        select.selectByValue("5");
        select = new Select(driver.findElement(By.id("months")));
        select.selectByValue("8");
        select = new Select(driver.findElement(By.id("years")));
        select.selectByValue("1988");
        driver.findElement(By.id("newsletter")).click();
        driver.findElement(By.id("submitAccount")).click();

        Assertions.assertEquals(expectedUserName, driver.findElement(By.className("account")).getText());
    }

}

