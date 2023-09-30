package de.qcademy.selenium.course.introduction;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestGruyere { //Besser
    //Testklasse
    //SetUp Beginn (Vorbedingungen)________________________________________
    private static WebDriver driver;
    //Browser instanziierung  - statisch gesetzt, weil LogIn für alle Browser gilt

    //+ SetUp Schritte implementieren,
    //Vorbedingungen von Webdriver definieren
    @BeforeAll//Annotation, vor allen Tests wird dieser Setup konfiguriert bzw. Methode ausgeführt
    static void globalSetup() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
        //vorbedingung vor jedem Testfall
    void setup() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void teardown() {//aufräumen, was passiert nach dem Test?
        if (driver != null) {
            driver.quit();//Browser wird nach der Ausführung des Tests geschlossen
        }
    }

    //Setup beendet __________________________________________________________
// Actions _______________________________________________________________
// führen wir die User Interaktionen durch
    //SUT: https://google-gruyere.appspot.com/652510093833423267713756979883987092815/
    @Test
    @DisplayName("Should register a new user")
//Annotation - Deklaration eines Tests
    void test1() throws InterruptedException { //+3 Schritte Arrange Act Assert, ist kein Muss
        //Arrange - Vorbereiten//----------- gehört noch zum Setup
        String username = "Testuser123";
        String password = "abcd";
        String expectedUsername = "Testuser123";//Ich kann den erwarteten Testergebnis in diesem Fall Username auch in Vorbedingungen definieren
//Seite wurde verschoben Test fail!
        //Act - agieren als Nutzer
        driver.get("https://google-gruyere.appspot.com/652510093833423267713756979883987092815/");//rufe website auf
        Thread.sleep(3000);
        //classname mit Entwicklertools gefunden (Selector)
        //damit Selenium das Element SignIn finden kann was als Button auf der Seite hinterlegt ist
        //"by" - Selector //Bei HTML wird ID immer fest definiert/eindeutig
        driver.findElement(By.linkText("Sign up")).click();
        Thread.sleep(3000);
        WebElement usernameField = driver.findElement(By.name("uid"));
        usernameField.sendKeys(username);
        WebElement passwordField = driver.findElement(By.name("pw"));
        passwordField.sendKeys(password);
        driver.findElement(By.name("pw")).sendKeys(password);//sendkeys, damit kann ich davor definierte variablen verwenden in diesem Fall username und password
        WebElement homeLink = driver.findElement(By.linkText("Home"));
        homeLink.click();
        Thread.sleep(3000);

        // Finde das Element mit dem gesamten Text
        WebElement fullTextElement = driver.findElement(By.id("menu-right"));//vollen Text aus Menu rigth finden

        // Extrahiere den Text aus dem gesamten Text des Elements
        String fullText = fullTextElement.getText();//vollen Text aus Menu rigth holen

        // Finden des Index von '<' und '>'
        int startIndex = fullText.indexOf("<");//Inhalt zwischen den Tags finden
        int endIndex = fullText.indexOf(">");
        /**
         * hier wird überprüft ob < und > auftreten,
         * wenn ja dann wird der Index zurückgegeben
         * also ist es ein Indiz dafür, dass Tag Klammern vorhanden sind
         * sonst startet und endet der Index mit -1
         * Ein Indiz dafür, dass keine Klammern vorhanden sind
         * quasi keine tag Klammern vorhanden
         */

        // Überprüfen, ob sowohl '<' als auch '>' im Text vorhanden sind
        if (startIndex != -1 && endIndex != -1) {
            // Extrahieren des Benutzernamens zwischen '<' und '>'
            String actualUsername = fullText.substring(startIndex + 1, endIndex -1).trim();
            /**
             * if/wenn StadtIndex und Endindex nicht -1/also wenn Klammern vorher gefunden wurden
             * if (startIndex != -1 && endIndex != -1) {
             * hole aktuellen Usernamen indem du die Teilzeichenkette vom vollen Text
             * String actualUsername
             * dieser befindet sich ein Zeichen neben Startindex '<' und vor dem '>' Endindex
             * startIndex + 1, endIndex -1
             * danach entferne Leerzeichen vor und nach dem extrahierten Bereich
             * .trim()
             */

            //Asserts
            Assertions.assertEquals(expectedUsername, actualUsername);//Vergleich definierte Username bei Vorbedingung und Anzeige aktueller Username nach der Registrierung
        }
    }
}