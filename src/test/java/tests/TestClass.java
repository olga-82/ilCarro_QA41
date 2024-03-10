package tests;

import config.BaseTest;
import helpers.EmailGenerate;
import helpers.NameAndLastNameGenerator;
import helpers.PasswordStringGenerator;
import helpers.TakeScreen;
import io.qameta.allure.Allure;
import jdk.jfr.Description;
import model.Car;
import model.User;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class TestClass extends BaseTest {

    @Test
    public void test001() throws InterruptedException {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        HomePage hp = new HomePage(getDriver());
        LogInPage logInPage = hp.openTopMenu(TopMenuItem.LOGIN);
        logInPage.tryToFindAnElement();
        LetTheCarWorkPage lcwp = logInPage.openTopMenu(TopMenuItem.LET_THE_CAR_WORK);
        lcwp.addLocation("New York").setManufacturer("Mercedes")
                .fillModelField("Vito").setFuelType().
                fillYearField("2015").fillSeatsField("4").
                fillCarClassField("B").fillCarRegNumberField("110-120-"+i)
                .fillCarPriceField("100").fillAboutField("test")
                .photoAttachment("/Users/olgakolchina/Downloads/1707474857096-22222222.jpg")
                .clickButtonSubmit();
        Thread.sleep(5000);
        Assert.assertTrue(lcwp.isCarAddedSuccess());
        lcwp.clickButtonAddCar();


        Thread.sleep(3000);
    }

    @Test
    @Description("Registration Positive")
    public void test002() throws InterruptedException {
        Allure.description("Registration Positive");
        BasePage bp = new HomePage(getDriver());
        User user = new User(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                EmailGenerate.generateEmail(5, 4, 2),
                PasswordStringGenerator.generateString());
        System.out.println(user.getEmail() + " " + user.getPassword());
        Allure.step("Navigate to Sign Up Page");
        SignUpPage signUpPage = bp.openTopMenu(TopMenuItem.SIGN_UP);
        signUpPage.fillNameField(user.getName());
        signUpPage.fillLastNameField(user.getLastName());
        signUpPage.fillEmailField(user.getEmail());
        signUpPage.fillPasswordField(user.getPassword());
        Thread.sleep(3000);
        signUpPage.signCheckBox();
        signUpPage.clickButtonRegistration();
        Assert.assertTrue(signUpPage.shouldHave());
        signUpPage.clickButtonOk();
        TakeScreen.takeScreenshot("screen");
        BasePage.openTopMenu(TopMenuItem.LOGOUT);
        LogInPage loginPage = bp.openTopMenu(TopMenuItem.LOGIN);
        Assert.assertTrue(loginPage.isTextExist());
        Thread.sleep(3000);
        loginPage.fillFormLogin();
        loginPage.clickLoginButton();
        Thread.sleep(3000);
        Assert.assertTrue(loginPage.isTextLoginSuccess());
        loginPage.clickButtonOk();
        Thread.sleep(5000);
        TakeScreen.takeScreenshot("screen");
        SearchPage searchPage = bp.openTopMenu(TopMenuItem.SEARCH);
        searchPage.textSearchExists();
        searchPage.fillFormSearch("Tel Aviv", "3/14/2024", "3/22/2024");
        TakeScreen.takeScreenshot("screen");
        Thread.sleep(7000);
        Assert.assertTrue(searchPage.isCarPresent());
        TakeScreen.takeScreenshot("screen");
    }

}
