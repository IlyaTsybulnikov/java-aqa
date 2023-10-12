package aqa.course;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TweekWebSiteTest {

    private static WebDriver driver;
    private static WebDriverWait wait;

    private final String email = "ilya.tsybulnikau@gmail.com";
    private final String password = "somePASSWORDforTWEEKSsite";

    private final By startLoginButton = By.xpath("//button[@class='Button_root__j5CKF " +
            "Button_variantOutlined__myHKd']");
    private final By emailInput = By.xpath("//input[@type='email']");
    private final By passwordInput = By.xpath("//input[@type='password']");
    private final By loginButton = By.xpath("//button[@class='Button_root__j5CKF " +
            "Button_fullWidth__HrPFw']");
    private final By loginForm = By.xpath("//div[@class='Modal_content__gGb69']/form");
    private final By headerProfileIcon = By.xpath("//div[@class='Header_profile__vTxFi " +
            "tooltip tooltip--dark']");
    private final By profileNameHeading = By.xpath("//div[@class='AccountCalendars_heading__uMUmA']");
    private final By myCalendarButton = By.xpath("//div[@class='AccountCalendars_listTitle__azgSB']");
    private final By todayHeader = By.xpath("//div[@data-today='true']//div[@class='Todo_month__XsMYN']");
    private final By allTodayNotes = By.xpath("//div[@data-today='true']" +
            "//div[@class='Todo_body__9Kfmn']/div/div");
    private final By newTodayNoteInput = By.xpath("//div[@data-today='true']" +
            "//div[@class='TodoInput_root__1RGSM']/input");
    private final By headerBars = By.xpath("//div[@class='Header_bars__Cd5nm " +
            "tooltip tooltip tooltip--dark']");
    private final By searchButton = By.xpath("//div[span[text()='Поиск']]");
    private final By searchInput = By.xpath("//input[@class='TextField_input__Co8UC " +
            "TextField_sizeMedium__NfOf1']");
    private final By searchResults = By.xpath("//div[@class='TodoSearch_cell__8fzRw']");
    private final By todayNotes = By.xpath("//div[@data-today='true']//" +
            "div[starts-with(@class, 'Todo_cell')]");
    private final By noteToggle = By.xpath(".//div[@class='Todo_toggle__IvsmO']");
    private final By checkedNotes = By.xpath("//div[@class='Todo_cell__-MpzB Todo_done__1hz4C']");
    private final By changeLanguageSelect = By.xpath("//div[@class='Menu_locale__EApFk']/select");
    private final By loadScreen = By.xpath("//div[starts-with(@class, 'Loader')]");

    @BeforeAll
    @DisplayName("Set up test configuration")
    public static void setUp() {
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://tweek.so/ru");

        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @AfterAll
    public static void closeDriver() {
        driver.close();
        driver.quit();
    }

    @Test
    @Order(1)
    @DisplayName("Login into an account")
    void login() {
        driver.findElement(startLoginButton).click();
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();

        wait.until(ExpectedConditions.stalenessOf(driver.findElement(loginForm)));
        wait.until(ExpectedConditions.elementToBeClickable(headerProfileIcon)).click();

        String accountName = driver.findElement(profileNameHeading).getText();

        assertEquals("Ilya", accountName);
    }

    @Test
    @Order(2)
    @DisplayName("Creation of today\'s note")
    void createTodayNote() {
        driver.findElement(myCalendarButton).click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        int numberOfTodayNotes = driver.findElements(allTodayNotes).size();

        driver.findElement(newTodayNoteInput).sendKeys("some note for today");
        driver.findElement(todayHeader).click();

        List<WebElement> elems = driver.findElements(allTodayNotes);

        assertEquals(numberOfTodayNotes + 1, elems.size());
    }

    @Test
    @Order(3)
    @DisplayName("Search for created note")
    void noteSearch() {
        driver.findElement(headerBars).click();
        driver.findElement(searchButton).click();
        wait.until(ExpectedConditions.elementToBeClickable(searchInput)).sendKeys("some");

        int numberOfSearchResults = driver.findElements(searchResults).size();

        driver.findElement(searchInput).sendKeys(Keys.ESCAPE);

        assertNotEquals(0, numberOfSearchResults);
    }

    @Test
    @Order(5)
    @DisplayName("Change language and check if it worked correctly")
    void changeLanguage() {
        driver.findElement(headerBars).click();

        Select languageSelect = new Select(driver.findElement(changeLanguageSelect));
        languageSelect.selectByValue("en");

        wait.until(ExpectedConditions.stalenessOf(driver.findElement(loadScreen)));

        String currentPageLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");

        assertEquals("en", currentPageLanguage);
    }

    @Test
    @Order(4)
    @DisplayName("Created note check out")
    public void checkNote() {
        List<WebElement> todayNoteElements = driver.findElements(todayNotes);

        for (WebElement note : todayNoteElements) {
            if (note.getAttribute("class").contains("Todo_done__1hz4C")) {
                continue;
            }

            note.findElement(noteToggle).click();
        }

        List<WebElement> checkedTodayNotes = driver.findElements(checkedNotes);

        assertEquals(todayNoteElements.size(), checkedTodayNotes.size());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
