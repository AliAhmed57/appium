package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.Arrays;

public class Transfers extends BasePage{


    public Transfers(AndroidDriver driver){
        super(driver);
    }

    //Locators
    private By transferBetweenMyAccountBtn = By.xpath("(//android.widget.Image[@text='KFH_EG.arrowForwardHomeKFHNew'])[2]");
    private By transferBetweenKFHAccountsBtn = By.xpath("(//android.widget.Image[@text='KFH_EG.arrowForwardHomeKFHNew'])[3]");
    private By selectBeneficiaryField =By.xpath("//android.view.View[@text='Select Beneficiary']");
    private By selectBeneficiary =By.xpath("//android.widget.TextView[@text='nnTRF1641727478111']");
    private By fromAccountField = By.xpath("//android.view.View[@text='From Account']");
//    private By fromAccountField = By.xpath("//android.view.View[@resource-id=''Dropdown1']");
//    private By toAccountField = By.xpath("//android.view.View[@resource-id=''Dropdown']");
//    private By selectCurrencyField = By.xpath("//android.view.View[@resource-id=''Dropdown2']");
    private By toAccountField = By.xpath("//android.view.View[@text='To Account']");
    private By fromAccount = By.xpath("//android.widget.TextView[@text='0001302626001']");
    private By toAccount = By.xpath("//android.widget.TextView[@text='0001302626109']");
    private By selectCurrencyField = By.xpath("//android.view.View[@text='Select Currency']");
    private By selectCurrency = By.xpath("//android.widget.TextView[@text='EGP - Egyptian Pound']");
    private By amountField = By.xpath("//android.widget.EditText[@resource-id='InputvarAmount2']");
    private By continueBtn = By.xpath("//android.widget.Button[@text='Continue']");
    private By confirmBtn = By.xpath("//android.widget.Button[@resource-id='btnTransfer']");
    private By successMsg = By.xpath("//android.widget.TextView[@resource-id='b1-Title']");
    private By makeAnotherTransferBtn = By.xpath("//android.widget.Button[@text='Make Another Transfer']");
    private By tokenOTPField = By.xpath("//android.widget.EditText[@resource-id='Input_TextVar2']");
    private By amountField2 = By.xpath("//android.widget.EditText[@resource-id='InputTextVar2']");
    //Actions

    private void switchToWebView() {
        for (String contextName : driver.getContextHandles()) {
            if (contextName.contains("WEBVIEW")) {
                driver.context(contextName);
                System.out.println("Switched to WebView: " + contextName);
                break;
            }
        }
    }

    private void waitForWebViewAndSwitch() {
        // Use the AndroidDriver field directly so we can access getContextHandles
        wait.until(ignored -> driver.getContextHandles().stream().anyMatch(c -> c.contains("WEBVIEW")));
        switchToWebView();
    }
    public void navigateToTransfersBetweenMyAccount() throws InterruptedException {
        waitForElementToBeClickable(transferBetweenMyAccountBtn);
        waitForWebViewAndSwitch();
        driver.findElement(transferBetweenMyAccountBtn).click();
    }
    //Validations
    public void fillFormTransferBetweenMyAccount(String amount) throws InterruptedException {
        // Thread.sleep(12000);
        waitForWebViewAndSwitch();
        waitForElementToBeClickable(fromAccountField);
        driver.findElement(fromAccountField).click();
        driver.findElement(fromAccount).click();
        driver.findElement(toAccountField).click();
        driver.findElement(toAccount).click();
        driver.findElement(selectCurrencyField).click();
        driver.findElement(selectCurrency).click();
        driver.findElement(amountField).sendKeys(amount);
        scroll();
        waitForElementToBeClickable(continueBtn);
        driver.findElement(continueBtn).click();

    }
    public void scroll()
    {
        int startX = 540; // middle of screen
        int startY = 1500; // start swipe from lower-middle
        int endY = 800; // swipe up

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(),
                startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(700),
                PointerInput.Origin.viewport(),
                startX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(swipe));

    }
    public void confirmTransferApproval()
    {

        waitForElementToBeClickable(confirmBtn);
        driver.findElement(confirmBtn).click();
    }

    public void transferredSuccessfullyMsg()
    {
        waitForElementToBeClickable(successMsg);
        driver.findElement(successMsg).isDisplayed();
    }

    public void makeAnotherTransfer()
    {
        waitForElementToBeClickable(makeAnotherTransferBtn);
        driver.findElement(makeAnotherTransferBtn).click();

    }
    public void navigateToTransfersBetweenKFHAccounts() throws InterruptedException {
        waitForElementToBeClickable(transferBetweenKFHAccountsBtn);
        waitForWebViewAndSwitch();
        driver.findElement(transferBetweenKFHAccountsBtn).click();
    }

    public void fillTransfersBetweenKFHAccountsForm(String amount) throws InterruptedException {
        // Thread.sleep(10000);
        waitForWebViewAndSwitch();
        waitForElementToBeClickable(fromAccountField);
        driver.findElement(fromAccountField).click();
        driver.findElement(fromAccount).click();
        driver.findElement(selectBeneficiaryField).click();
        driver.findElement(selectBeneficiary).click();
        driver.findElement(selectCurrencyField).click();
        driver.findElement(selectCurrency).click();
        driver.findElement(amountField2).sendKeys(amount);
        scroll();
        waitForElementToBeClickable(continueBtn);
        driver.findElement(continueBtn).click();
        waitForElementToBeClickable(tokenOTPField);
        driver.findElement(tokenOTPField).sendKeys("123456");
        driver.findElement(confirmBtn).click();
    }








}
