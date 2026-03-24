package Base;

import engine.ScreenRecorder;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class BaseTest {

    protected AndroidDriver driver;

    @BeforeClass
    public void setup() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();

        Properties config = loadConfig();

        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        options.setDeviceName(config.getProperty("device.name", "Android Device"));
        options.setUdid(config.getProperty("device.udid"));
        options.setAppPackage(config.getProperty("app.package"));
        options.setAppActivity(config.getProperty("app.activity"));
        options.setNoReset(Boolean.parseBoolean(config.getProperty("app.noReset", "true")));
        options.setAutoGrantPermissions(Boolean.parseBoolean(config.getProperty("app.autoGrantPermissions", "true")));

        String serverUrl = config.getProperty("appium.serverUrl", "http://127.0.0.1:4723");
        driver = new AndroidDriver(new URL(serverUrl), options);
    }

    private Properties loadConfig() {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input != null) {
                properties.load(input);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
        return properties;
    }
    @BeforeMethod
    public void startRecording() {
        ScreenRecorder.startRecording(driver);
    }

    @AfterMethod
    public void stopRecording(ITestResult result) {

        boolean isFailed = result.getStatus() == ITestResult.FAILURE;

        ScreenRecorder.stopRecording(
                driver, result.getName(), isFailed   // attach ONLY if failed
        );
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}