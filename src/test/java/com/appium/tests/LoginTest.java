package com.appium.tests;

import com.values.Author;
import com.appium.config.UserBaseTest;
import com.appium.config.UserCredentials;
import com.appium.pages.AccountsPage;
import com.appium.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends UserBaseTest {

    LoginPage loginPage;
    AccountsPage accountsPage;
    UserCredentials credentials;

    @Test(groups = "smoke")
    @Author(name = "Manjunatha")
    public void loginWithValidUser() throws InterruptedException {
        loginPage = new LoginPage(driver);
        credentials = new UserCredentials("sellerappsqa@gmail.com", "sellersnapdeal");
        String userNameLoggedIn =
            loginPage.enterValidCredentails(credentials.getUserName(), credentials.getPassWord())
                .waitForWelcomePage().verifyUserIsLoggedIn();
        Assert.assertEquals(userNameLoggedIn, "Testing");
    }

    @Test
    public void loginWithInValidUser() throws InterruptedException {
        loginPage = new LoginPage(driver);
        credentials = new UserCredentials("xxxxxx@gmail.com", "Hello12342225678");
        loginPage.enterValidCredentails(credentials.getUserName(), credentials.getPassWord());
        String userNameLoggedIn = loginPage.validateErrorMessage();
        Assert.assertEquals(userNameLoggedIn, "The username or password you entered is incorrect");
    }

    @Test public void logOutTest() throws InterruptedException {
        loginPage = new LoginPage(driver);
        accountsPage = new AccountsPage(driver);
        credentials = new UserCredentials("sellerappsqa@gmail.com", "sellersnapdeal");
        Boolean validateUserIsLoggedOut =
            loginPage.enterValidCredentails(credentials.getUserName(), credentials.getPassWord())
                .waitForWelcomePage().moveToDisconnect().logOut()
                .validateUserNameFieldIsDisplayed();
        Assert.assertTrue(validateUserIsLoggedOut, "Failed to log out users");
    }

}
