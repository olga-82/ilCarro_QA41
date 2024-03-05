package tests;

import config.BaseTest;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LogInPage;
import pages.TopMenuItem;

public class TestClass extends BaseTest {

    @Test
    public void test001() throws InterruptedException {
       HomePage hp = new HomePage(getDriver());
       LogInPage logInPage = hp.openTopMenu(TopMenuItem.LOGIN);
       logInPage.openTopMenu(TopMenuItem.SEARCH);
       Thread.sleep(3000);
    }
}
