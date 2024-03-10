package pages;

import helpers.Reader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.List;

import static org.openqa.selenium.By.xpath;

public class LogInPage extends BasePage {
    public LogInPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    @FindBy(xpath = "//h1[contains(text(),'Log in')]")
    WebElement logInText;
    @FindBy(xpath = "//input[@id='email']")
    WebElement inputEmail;
    @FindBy(xpath = "//input[@id='password']")
    WebElement inputPassword;

    @FindBy(xpath = "//button[contains(text(),'Y’alla!')]")
    WebElement buttonLogin;
    @FindBy (xpath = "//h2[contains(text(),'Logged in success')]")
    WebElement textSuccessLogin;
    @FindBy (xpath = "//button[contains(text(),'Ok')]")
    WebElement buttonOk;
    public void tryToFindAnElement() {
        List<WebElement> list = driver.findElements(xpath("//a"));
        System.out.println("SIZE : " + list.size());
    }
    public LogInPage fillFormLogin(){
        sendKeys(inputEmail, Reader.getProperty("email"));
        sendKeys(inputPassword, Reader.getProperty("password"));
        return this;
    }
    public  LogInPage clickLoginButton(){
        click(buttonLogin);
     return this;
    }
    public BasePage clickButtonOk() {
        click(buttonOk);
        return new SearchPage(driver);
    }
    public boolean isTextExist(){
     return shouldHave(logInText,"Log in");

    }
    public boolean isTextLoginSuccess(){
        return shouldHave(textSuccessLogin,"Logged in success");
    }
}
