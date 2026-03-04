package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.testng.Assert;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.support.ui.ExpectedConditions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
public class Login {

    private AndroidDriver driver;

    public Login(AndroidDriver driver){
        this.driver = driver;
    }

    // Locators
    private By username = By.xpath("//android.widget.EditText[@resource-id='Input_UsernameVal']");
    private By password = By.xpath("//android.widget.EditText[@resource-id='PasswordInputTextbox']");
    private By loginBtn = By.xpath("//android.widget.Button[@text='Login']");
    private By userInfo = By.xpath("//android.widget.TextView[@resource-id='b2-Title']");

    // Actions

    public void login(String user, String pass)
    {
        driver.findElement(username).sendKeys(user);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(loginBtn).click();

    }
    //Validations
    public void validateLoginButtonDisplayed(){
        Assert.assertTrue(driver.findElement(loginBtn).isDisplayed(), "Login button is not displayed!");
    }
    public void validateUsernameDisplayed(){
        Assert.assertTrue(driver.findElement(username).isDisplayed(), "username button is not displayed!");
    }
    public void validatePasswordDisplayed(){
        Assert.assertTrue(driver.findElement(password).isDisplayed(), "Password button is not displayed!");
    }

    public void loggedInSuccessfully()
    {
        int x = 942;
        int y = 896;

        // Perform tap using W3C Actions
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(),
                x, y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(tap));

        // Wait up to 1 minute for the element to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userInfoId"))); // locate fresh

        // Assert element is displayed
        Assert.assertTrue(driver.findElement(By.id("userInfoId")).isDisplayed(), "Logged In");
    }
}