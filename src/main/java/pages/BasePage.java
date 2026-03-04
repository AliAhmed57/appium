package pages;

import io.appium.java_client.AppiumDriver;
import core.DriverManager;

public class BasePage {
    protected AppiumDriver driver;

    public BasePage(){
        this.driver = DriverManager.getDriver();
    }
}
