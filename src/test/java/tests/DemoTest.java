package tests;

import Base.BaseTest;
import engine.JsonFileManager;
import engine.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Home;
import pages.Login;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pages.Transfers;

@Listeners({io.qameta.allure.testng.AllureTestNg.class})
public class DemoTest extends BaseTest {
    private static final Logger log = LoggerFactory.getLogger(DemoTest.class);

    private Login loginPage;
    private Home home;
    private Transfers transfers;
    private JsonFileManager loginData;

    @BeforeClass
    public void beforeClass() {
        loginPage = new Login(driver);
        home = new Home(driver);
        transfers = new Transfers(driver);
        loginData = new JsonFileManager("src/test/resources/TestData/login.json");
    }

    @Test(description = "Verify that a user can login successfully", priority = 1, retryAnalyzer = Retry.class)
    @Description("Login Test with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    public void loginTest() {
        loginPage.validateLoginButtonDisplayed();
        loginPage.validateUsernameDisplayed();
        loginPage.validatePasswordDisplayed();
        loginPage.login(loginData.getTestData("username"), loginData.getTestData("password"));
        loginPage.closePopUp();
    }

    @Test(description = "Navigate to transfer", priority = 2, dependsOnMethods = "loginTest", retryAnalyzer = Retry.class)
    @Description("Navigate to transfer screen after login")
    @Severity(SeverityLevel.CRITICAL)
    public void validateUserNavigateToTransfer() throws InterruptedException {
        home.navigateToTransfers();
    }

    @Test(description = "Transfer Between My Accounts with valid data", priority = 3, retryAnalyzer = Retry.class, dependsOnMethods = "validateUserNavigateToTransfer")
    public void transferBetweenMyAccount() throws InterruptedException {
        transfers.navigateToTransfersBetweenMyAccount();
        transfers.fillFormTransferBetweenMyAccount("1000");
        transfers.confirmTransferApproval();
    }

    @Test(description = "Transfer Between KFH Accounts with valid data ", priority = 4, retryAnalyzer = Retry.class, dependsOnMethods = "transferBetweenMyAccount")
    public void transferBetweenKFHAccountWithValidData() throws InterruptedException {
        transfers.makeAnotherTransfer();
        transfers.navigateToTransfersBetweenKFHAccounts();
        transfers.fillTransfersBetweenKFHAccountsForm("1000");
    }

    @Test(description = "Transfer Between My Accounts with invalid data ", priority = 5, retryAnalyzer = Retry.class, dependsOnMethods = "transferBetweenKFHAccountWithValidData")
    public void transferBetweenMyAccountWithInvalidData() throws InterruptedException {
        transfers.makeAnotherTransfer();
        transfers.navigateToTransfersBetweenMyAccount();
        transfers.fillFormTransferBetweenMyAccount("-1000");
    }

    @Test(description = "Verify that a user can transfer between my accounts successfully", priority = 1, retryAnalyzer = Retry.class)
    @Description("Transfer Between My Account")
    @Severity(SeverityLevel.CRITICAL)
    public void transferBetweenMyAccountWithValidDataE2E() throws InterruptedException {
        loginPage.login(loginData.getTestData("username"), loginData.getTestData("password"));
        loginPage.closePopUp();
        home.navigateToTransfers();
        transfers.navigateToTransfersBetweenMyAccount();
        transfers.fillFormTransferBetweenMyAccount("1000");
        transfers.confirmTransferApproval();
    }


    @Test(description = "Verify that a user can transfer between my KFH accounts successfully", priority = 1, retryAnalyzer = Retry.class)
    @Description("Transfer Between KFH Accounts")
    @Severity(SeverityLevel.CRITICAL)
    public void transferBetweenKFHAccountWithValidDataE2E() throws InterruptedException {
        loginPage.login(loginData.getTestData("username"), loginData.getTestData("password"));
        loginPage.closePopUp();
        home.navigateToTransfers();
        transfers.navigateToTransfersBetweenKFHAccounts();
        transfers.fillTransfersBetweenKFHAccountsForm("1000");
        transfers.confirmTransferApproval();
    }
}