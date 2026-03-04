package core;

import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.URL;

public class CapabilityFactory {
    public static UiAutomator2Options getOptions() {

        UiAutomator2Options options = new UiAutomator2Options();

        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        options.setDeviceName("Android Device");
        options.setUdid("ce11171b2344d82005");
        options.setAppPackage("com.aub.mobilebanking.EG.phone");
        options.setAppActivity("com.aub.mobilebanking.EG.phone.MainActivity");
        options.setNoReset(true);
        options.setAutoGrantPermissions(true);

        return options;
    }
}
