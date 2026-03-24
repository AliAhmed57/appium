package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.testng.Assert;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.support.ui.ExpectedConditions;
public class Login extends BasePage{


    public Login(AndroidDriver driver){
        super(driver);
    }
    // Locators
    private By username = By.xpath("//android.widget.EditText[@resource-id='Input_UsernameVal']");
    private By password = By.xpath("//android.widget.EditText[@resource-id='PasswordInputTextbox']");
    private By loginBtn = By.xpath("//android.widget.Button[@text='Login']");
    private By closeBtn = By.xpath("//android.widget.Image[@text='KFH_EG.settings_account_box_40dp_24604F_FILL0_wght200_GRA']");
    private By userInfo = By.xpath("//android.widget.TextView[@resource-id='b2-Title']");
    private By homeScreen = By.xpath("//android.webkit.WebView[@text='frmHomeScreen_PERF']");
    private By accountScreen = By.xpath("//android.widget.Button[@resource-id='b5-FlipContainer']");
    // Actions

    public void login(String user, String pass)
    {
        driver.findElement(username).sendKeys(user);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(loginBtn).click();
//        waitForElementToBeVisible(homeScreen);
//        waitForElementToBeVisible(accountScreen);
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

    public void closePopUp()
    {

        int x = 940 ;
        int y = 880;
        // Prefer explicit wait + element click; fall back to coordinate tap.
        try {
             Thread.sleep(20000);
//            waitForElementToBeClickable(closeBtn);
//            driver.findElement(closeBtn).click();
        } catch (Exception clickException) {
            try {
                PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
                Sequence tap = new Sequence(finger, 1);

                tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
                        PointerInput.Origin.viewport(),
                        x, y));
                tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
                tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

                driver.perform(Arrays.asList(tap));
                System.out.println("Tapped at coordinates (" + x + ", " + y + ")");
            } catch (Exception tapException) {
                System.out.println("Failed to close popup: " + tapException.getMessage());
            }
        }
    }
}