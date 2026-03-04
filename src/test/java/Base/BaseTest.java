package Base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

    protected AndroidDriver driver;

    @BeforeClass
    public void setup() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        options.setDeviceName("Android Device");
        options.setUdid("ce11171b2344d82005");
        options.setAppPackage("com.aub.mobilebanking.EG.phone");
        options.setAppActivity("com.aub.mobilebanking.EG.phone.MainActivity");
        options.setNoReset(true);
        options.setAutoGrantPermissions(true);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
    }

    @AfterClass
    public void tearDown() {
        if(driver != null){
            driver.quit();
        }
    }
}