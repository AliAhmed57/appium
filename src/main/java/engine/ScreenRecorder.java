package engine;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;

import java.io.ByteArrayInputStream;
import java.util.Base64;

public class ScreenRecorder {
    public static void startRecording(AndroidDriver driver) {
        driver.startRecordingScreen();
    }

    public static void stopRecording(AndroidDriver driver, String testName, boolean attach) {

        String base64Video = driver.stopRecordingScreen();

        if (attach && base64Video != null) {

            byte[] videoBytes = Base64.getDecoder().decode(base64Video);

            Allure.addAttachment(
                    testName,
                    "video/mp4",
                    new ByteArrayInputStream(videoBytes),
                    ".mp4"
            );
        }
    }
}
