package tests;

import Base.BaseTest;
import engine.JsonFileManager;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Login;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    Login loginPage;
    JsonFileManager login = new JsonFileManager("C://Users//EGYPT//IdeaProjects//untitled8//src//test//resources//TestData//login.json");

    @Test(description = "Verify that a user can login successfully")
    @Description("Login Test with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    public void loginTest(){

        // Validate button is displayed
        loginPage.validateLoginButtonDisplayed();
        loginPage.validateUsernameDisplayed();
        loginPage.validatePasswordDisplayed();
//        loginPage.login("shadowaccountuser1","P@ssw0rd1");
        // Perform login
        loginPage.login(login.getTestData("username"), login.getTestData("password"));
    }
    @Test(description = "Verify that a user can login successfully")


    @Description("Login Test with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    public void validateUserLoggedInSuccessfully()
    {

        loginPage.loggedInSuccessfully();
    }

    @BeforeClass
    public void beforClass()
    {
         loginPage = new Login(driver);

    }

}