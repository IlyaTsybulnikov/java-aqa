package aqa.course;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AppTest {

    private WebDriver driver;
    private WebDriverWait wait;

    private final String email = "ilya.tsybulnikau@gmail.com";
    private final String password = "somePASSWORDforTWEEKSsite";

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        options.headless = true;

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://tweek.so/ru");

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @After
    public void closeDriver() {
        driver.close();
        driver.quit();
    }

    @Test
    public void loginTest() {
        driver.findElement(By.xpath("//div[@class='Welcome_actions__s-XVJ']" +
                "/button[@class='Button_root__j5CKF Button_variantOutlined__myHKd']")).click();
        driver.findElement(By.xpath("//input[@type='email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
        driver.findElement(By.xpath("//div[@class='Actions_root__XyFQM']" +
                "/button[@class='Button_root__j5CKF Button_fullWidth__HrPFw']")).click();

        wait.until(ExpectedConditions.stalenessOf(driver.findElement(
                By.xpath("//div[@class='Modal_content__gGb69']/form"))));
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='Header_toolbar__HRK2N']" +
                        "/div[@class='Header_profile__vTxFi tooltip tooltip--dark']"))).click();

        String accountName = driver.findElement(By.xpath("//div[@class='AccountCalendars_header__GXXjs']" +
                "/div[@class='AccountCalendars_heading__uMUmA']")).getText();

        Assert.assertEquals("Ilya", accountName);
    }

    @Test
    public void createTodayNoteTest() {
        driver.findElement(By.xpath("//div[@class='Welcome_actions__s-XVJ']" +
                "/button[@class='Button_root__j5CKF']")).click();

        int numberOfTodayNotes = driver.findElements(By.xpath("//div[@data-today='true']" +
                "/*//div[@class='Todo_body__9Kfmn']/div/div")).size();

        driver.findElement(By.xpath("//div[@data-today='true']" +
                "/*//div[@class='TodoInput_root__1RGSM']/input")).sendKeys("some note for today");
        driver.findElement(By.xpath("//div[@class=" +
                "'Header_heading__2Hj7V Header_headingPointer__8DKVU']")).click();

        List<WebElement> elems = driver.findElements(By.xpath("//div[@data-today='true']" +
                "/*//div[@class='Todo_body__9Kfmn']/div/div"));

        Assert.assertEquals(numberOfTodayNotes + 1, elems.size());
    }

    @Test
    public void noteSearchTest() {
        driver.findElement(By.xpath("//div[@class='Welcome_actions__s-XVJ']" +
                "/button[@class='Button_root__j5CKF Button_variantOutlined__myHKd']")).click();
        driver.findElement(By.xpath("//input[@type='email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
        driver.findElement(By.xpath("//div[@class='Actions_root__XyFQM']" +
                "/button[@class='Button_root__j5CKF Button_fullWidth__HrPFw']")).click();

        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//div[@class=" +
                "'Modal_content__gGb69']/form"))));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-today='true']" +
                "/*//div[@class='TodoInput_root__1RGSM']/input"))).sendKeys("some note for today");

        driver.findElement(By.xpath("//div[@class='Header_toolbar__HRK2N']" +
                "/div[@class='Header_bars__Cd5nm tooltip tooltip tooltip--dark']")).click();
        driver.findElement(
                By.xpath("//span[text()='Поиск']")).findElement(By.xpath("./..")).click();
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@class='TextField_input__Co8UC " +
                        "TextField_sizeMedium__NfOf1']"))).sendKeys("some");

        int numberOfSearchResults = driver.findElements(By.xpath("//div[@class=" +
                "'TodoSearch_container__1zpfl']/div[@class='TodoSearch_cell__8fzRw']")).size();

        driver.findElement(By.xpath("//input[@class='TextField_input__Co8UC " +
                "TextField_sizeMedium__NfOf1']")).sendKeys(Keys.ESCAPE);
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@data-today='true']/*//div[@role='button']"))).click();
        driver.findElement(By.xpath("//div[@class='Task_actions__MqGxg']/*" +
                "//div[@class='Task_action__uspJX']")).click();

        Assert.assertNotEquals(0, numberOfSearchResults);
    }

    @Test
    public void changeLanguageTest() {
        driver.findElement(By.xpath("//div[@class='Welcome_actions__s-XVJ']" +
                "/button[@class='Button_root__j5CKF']")).click();
        driver.findElement(By.xpath("//div[@class='Header_toolbar__HRK2N']" +
                "/div[@class='Header_bars__Cd5nm tooltip tooltip tooltip--dark']")).click();

        wait.until(ExpectedConditions.presenceOfNestedElementsLocatedBy(
                By.xpath("//div[@class='Menu_locale__EApFk']/select"), By.tagName("option")));

        Select languageSelect = new Select(driver.findElement(
                By.xpath("//div[@class='Menu_locale__EApFk']/select")));

        languageSelect.selectByValue("en");

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='Welcome_actions__s-XVJ']/button[@class='Button_root__j5CKF']")));

        String currentPageLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");

        Assert.assertEquals("en", currentPageLanguage);
    }

    @Test
    public void checkNoteTest() {
        driver.findElement(By.xpath("//div[@class='Welcome_actions__s-XVJ']" +
                "/button[@class='Button_root__j5CKF']")).click();

        driver.findElement(By.xpath("//div[@data-today='true']" +
                "/*//div[@class='TodoInput_root__1RGSM']/input")).sendKeys("some note for today");
        driver.findElement(By.xpath("//div[@class=" +
                "'Header_heading__2Hj7V Header_headingPointer__8DKVU']")).click();

        List<WebElement> todayNotes = driver.findElements(By.xpath("//div[@data-today='true']/*" +
                "//div[@class='Todo_body__9Kfmn']/div/div[starts-with(@class, 'Todo_cell')]"));

        for (WebElement note : todayNotes) {
            note.findElement(By.xpath(".//div[@class='Todo_toggle__IvsmO']")).click();
        }

        List<WebElement> checkedTodayNotes = driver.findElements(By.xpath("//div[@data-today='true']/*" +
                "//div[@class='Todo_body__9Kfmn']/div/div[@class='Todo_cell__-MpzB Todo_done__1hz4C']"));

        Assert.assertEquals(todayNotes.size(), checkedTodayNotes.size());
    }

}
