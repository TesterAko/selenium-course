package PtvMapTest;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class PtvTourPlanTest {

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

    /*@AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }*/

    @Test
    @DisplayName("Test PTV Tour Plan")
    void testPTVPlan() throws InterruptedException {
        //Arrange
        String email = "testerakko12@gmail.com";
        String password = "TesterAkko**12";

        //Act
        driver.manage().window().maximize(); // maximieren des Fensters
        driver.get("https://mginter.mapandguide.com/v7.11/?language=de");//rufe website auf
        Thread.sleep(5000);
        driver.findElement(By.id("login")).sendKeys(email);
        driver.findElement(By.id("loginuserbtn")).click();
        driver.findElement(By.id("didomi-notice-agree-button")).click();
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("loginButton")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("$ok")).click();
        Thread.sleep(25000);
        Select vehicleSelect = new Select(driver.findElement(By.id("mgi-vehiclecombo2")));
        vehicleSelect.selectByValue("10");
        Thread.sleep(3000);
        driver.findElement(By.id("mgi-search-button")).click();









    }
}
