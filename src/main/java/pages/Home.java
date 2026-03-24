package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class Home extends BasePage{

    public Home(AndroidDriver driver){
        super(driver);
    }

    //Locators
    private By homeTitle = By.xpath("//android.widget.TextView[@resource-id='b2-Title']");
    private By transfereBtn = By.xpath("//android.widget.Image[@text='KFH_EG.transfersKFHNew']");


    //Actions
    public void navigateToTransfers() throws InterruptedException {
    waitForElementToBeClickable(transfereBtn);
    driver.findElement(transfereBtn).click();
    }

    public void returnToHomePage()
    {
        waitForElementToBeClickable(transfereBtn);

    }
    //Validations
    public void validateThatHomePageIsDisplayed()
    {
        driver.findElement(homeTitle).isDisplayed();

    }

}
