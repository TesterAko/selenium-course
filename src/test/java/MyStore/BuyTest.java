package MyStore;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class BuyTest {
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
        String address = faker.address().fullAddress();
        String telefon = faker.phoneNumber().cellPhone();
        String expectedResult = "Your order on My Shop is complete.";


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

        driver.findElement(By.className("sf-with-ul")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//img[contains(@title,'Blouse')]")).click();
        Thread.sleep(2000);

        driver.findElement(By.id("add_to_cart")).click();
        Thread.sleep(2000);

        driver.findElement(By.className("button-container")).click();
        Thread.sleep(2000);

        driver.findElement(By.linkText("Proceed to checkout")).click();
        Thread.sleep(4000);

        driver.findElement(By.id("address1")).sendKeys(address);
        driver.findElement(By.id("city")).sendKeys(faker.address().city());
        WebElement dropdownElement = driver.findElement(By.id("id_state"));
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText("Alabama");
        driver.findElement(By.id("postcode")).sendKeys("00000");
        driver.findElement(By.id("phone_mobile")).sendKeys(telefon);
        driver.findElement(By.id("submitAddress")).click();
        Thread.sleep(3000);

        WebElement submitButton = driver.findElement(By.name("processAddress"));
        submitButton.submit();
        Thread.sleep(3000);

        driver.findElement(By.id("cgv")).click();
        WebElement submitButton2 = driver.findElement(By.name("processCarrier"));
        submitButton2.submit();
        Thread.sleep(3000);

        driver.findElement(By.className("bankwire")).click();
        Thread.sleep(2000);

        WebElement confirmOrderButton = driver.findElement(By.xpath("//span[text()='I confirm my order']"));
        confirmOrderButton.click();
        Thread.sleep(2000);

        WebElement element = driver.findElement(By.cssSelector(".alert.alert-success"));
        String actualResult = element.getText();
        Assertions.assertEquals(expectedResult, actualResult);

    }
}

