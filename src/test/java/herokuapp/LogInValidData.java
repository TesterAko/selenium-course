package herokuapp;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LogInValidData {

    private static WebDriver driver;

    @BeforeAll
    static void globalSetup() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("LogInTestValidData")
    void LogInTestValidData() throws InterruptedException {
        String username = "tomsmith";
        String password = "SuperSecretPassword!";

        driver.get("https://the-internet.herokuapp.com/login");
        Thread.sleep(2000);
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        Thread.sleep(2000);
        Assertions.assertEquals("Secure Area", driver.findElement(By.cssSelector("h2")).getText());

    }
}
