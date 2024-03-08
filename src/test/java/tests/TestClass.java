package tests;

import config.BaseTest;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LetTheCarWorkPage;
import pages.LogInPage;
import pages.TopMenuItem;

public class TestClass extends BaseTest {

    @Test
    public void test001() throws InterruptedException {
       HomePage hp = new HomePage(getDriver());
       LogInPage logInPage = hp.openTopMenu(TopMenuItem.LOGIN);
       logInPage.tryToFindAnElement();
       LetTheCarWorkPage lcwp = logInPage.openTopMenu(TopMenuItem.LET_THE_CAR_WORK);
       lcwp.setManufacturer("BMW").addLocation("Montana")
               .setFuelType().photoAttachment("C:\\Pictures\\BlueCar.jpg");


       Thread.sleep(3000);
    }
}
