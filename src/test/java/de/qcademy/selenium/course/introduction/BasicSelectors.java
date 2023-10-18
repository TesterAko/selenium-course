package de.qcademy.selenium.course.introduction;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

public class BasicSelectors {
    //Testklasse
    //SetUp Beginn (Vorbedingungen)________________________________________
    private static WebDriver driver;
    //Browser instanziierung  - statisch gesetzt, weil LogIn für alle Browser gilt

    //+ SetUp Schritte implementieren,
    //Vorbedingungen von Webdriver definieren
    @BeforeAll//Annotation, vor allen Tests wird dieser Setup konfiguriert bzw. Methode ausgeführt
    static void globalSetup() {
        WebDriverManager.edgedriver().setup();
    }

    @BeforeEach
        //vorbedingung vor jedem Testfall
    void setup() {
        driver = new EdgeDriver();
    }

    @AfterEach
    void teardown() {//aufräumen, was passiert nach dem Test?
        if (driver != null) {
            driver.quit();//Browser wird nach der Ausführung des Tests geschlossen
        }
    }
//JAVAFaker probieren!
//Setup beendet __________________________________________________________
// Actions _______________________________________________________________
// führen wir die User Interaktionen durch
    //SUT: https://www.automationpractice.pl/index.php
    @Test
    @DisplayName("Should register a new user")
    //Annotation - Deklaration eines Tests
    void test1() throws InterruptedException { //+3 Schritte Arrange Act Assert, ist kein Muss
        //Arrange - Vorbereiten//----------- gehört noch zum Setup
        String email = "test2@gmail.com";
        String vorname = "John";
        String nachname = "Wayne";
        String password = "abcdefg";
        String expectedUsername = "John Wayne";//Ich kann den erwarteten Testergebnis in diesem Fall Username auch in Vorbedingungen definieren
//Seite wurde verschoben Test fail!
        //Act - agieren als Nutzer
        driver.get("http://www.automationpractice.pl/index.php");//rufe website auf
        Thread.sleep(3000);
        driver.findElement(By.className("login")).click();
        //classname mit Entwicklertools gefunden (Selector)
        //damit Selenium das Element SignIn finden kann was als Button auf der Seite hinterlegt ist
        //"by" - Selector //Bei HTML wird ID immer fest definiert/eindeutig
        driver.findElement(By.id("email_create")).sendKeys(email);//sendkeys, damit kann ich davor definierte variablen verwenden in diesem Fall email
        driver.findElement(By.id("SubmitCreate")).click();
        Thread.sleep(4000);
        driver.findElement(By.id("uniform-id_gender1")).click();
        driver.findElement(By.id("customer_firstname")).sendKeys(vorname);
        driver.findElement(By.id("customer_lastname")).sendKeys(nachname);
        driver.findElement(By.id("passwd")).sendKeys(password);
        Select daySelect = new Select(driver.findElement(By.id("days")));//Select datentyp ermöglicht uns eine option zu selektieren bspw. drop down
        daySelect.selectByIndex(10);
        Select monthSelect = new Select(driver.findElement(By.id("months")));//Select datentyp ermöglicht uns eine option zu selektieren bspw. drop down
        monthSelect.selectByIndex(3);//auswahl vom Index also Liste angegebenen Monaten, Jahren, Tagen und etc.
        Select yearSelect = new Select(driver.findElement(By.id("years")));//Select datentyp ermöglicht uns eine option zu selektieren bspw. drop down
        yearSelect.selectByIndex(5);
        driver.findElement(By.id("submitAccount")).click();
        Thread.sleep(3000);
        String actualUsername = driver.findElement(By.className("header_user_info")).getText().trim();


        //Asserts
        Assertions.assertEquals(expectedUsername, actualUsername);//Vergleich definierte Vorname bei Vorbedingung und Anzeige aktueller Username nach der Registrierung


    }
}
