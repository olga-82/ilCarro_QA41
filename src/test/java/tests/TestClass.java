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
        HomePage hp = new HomePage(getDriver());
        LogInPage logInPage = hp.openTopMenu(TopMenuItem.LOGIN);
        logInPage.tryToFindAnElement();
        LetTheCarWorkPage lcwp = logInPage.openTopMenu(TopMenuItem.LET_THE_CAR_WORK);
        lcwp.setManufacturer("BMW").addLocation("Montana")
                .setFuelType().photoAttachment("/Users/olgakolchina/Downloads/1707474857096-22222222.jpg");


        Thread.sleep(3000);
    }
    @Test
    @Description("Test Positive")
    public void test002() throws InterruptedException {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
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
        Allure.description("Login Success");
        Allure.step("Navigate to Logout Page");
        LogInPage loginPage = signUpPage.openTopMenu(TopMenuItem.LOGIN);
        Assert.assertTrue(loginPage.isTextExist());
        Thread.sleep(3000);
        Allure.step("Fill Login form");
        loginPage.fillFormLogin();
        Allure.step("click by login button");
        loginPage.clickLoginButton();
        Thread.sleep(3000);
        Assert.assertTrue(loginPage.isTextLoginSuccess());
        loginPage.clickButtonOk();
        Allure.description("Login Success");
        Thread.sleep(5000);
        TakeScreen.takeScreenshot("screen");
        Allure.description("Serch car Success");
        Allure.step("Navigate to Serch Page");
        SearchPage searchPage = loginPage.openTopMenu(TopMenuItem.SEARCH);
        searchPage.textSearchExists();
        Allure.step("Fill search form");
        searchPage.fillFormSearch("Tel Aviv", "3/14/2024", "3/22/2024");
        Thread.sleep(5000);
        Assert.assertTrue(searchPage.isCarPresent());
        Allure.description("Search car Success");
        TakeScreen.takeScreenshot("screen");
        Allure.description("Add new car Success");
        Allure.step("Navigate to Let the car work Page");
        LetTheCarWorkPage lcwp = searchPage.openTopMenu(TopMenuItem.LET_THE_CAR_WORK);
        Allure.step("Fill Add new car Form");
        lcwp.addLocation("Tel Aviv").setManufacturer("Mercedes")
                .fillModelField("Vito").setFuelType().
                fillYearField("2015").fillSeatsField("4").
                fillCarClassField("B").fillCarRegNumberField("110-120-"+i)
                .fillCarPriceField("100").fillAboutField("test")
                .photoAttachment("/Users/olgakolchina/Downloads/1707474857096-22222222.jpg")
                .clickButtonSubmit();
        Thread.sleep(2000);
        Assert.assertTrue(lcwp.isCarAddedSuccess());
        lcwp.clickButtonAddCar();
        Allure.description("Car added successfully");
        Thread.sleep(5000);
        Allure.step("Navigate to Home Page");
        lcwp.openTopMenu(TopMenuItem.LOGOUT);
        Thread.sleep(5000);
        TakeScreen.takeScreenshot("screen");
    }

}
