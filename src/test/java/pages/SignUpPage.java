package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignUpPage extends BasePage{
    public SignUpPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }
    @FindBy(xpath = "//h1[contains(text(),'Registration')]")
    WebElement textRegistration;
   @FindBy(xpath = "//input[@id='name']")
   WebElement nameField;
    @FindBy(xpath = "//input[@id='lastName']")
    WebElement lastNameField;
    @FindBy(xpath = "//input[@id='email']")
    WebElement emailField;
    @FindBy(xpath = "//input[@id='password']")
    WebElement passwordField;
    @FindBy(xpath = "//input[@id='terms-of-use']")
    WebElement checkBox;
    @FindBy(xpath = "//a[1][@href='terms-of-use']")
    WebElement termsOfUseInput;
    @FindBy(xpath = "//a[contains(text(),'privacy')]")
    WebElement privacyPolicy;
    @FindBy(xpath = "//a[@class='navigation-link'][3]")
    WebElement navigationLinkSignUp;
    @FindBy(xpath = "//button[contains(text(),'Y’alla!')]")
    WebElement buttonRegistration;
    @FindBy(xpath = "//button[contains(text(),'Ok')]")
    WebElement buttonOk;
    @FindBy(xpath = "//h1[contains(text(),'Registered')]")
     WebElement  textSuccessRegistration;

    public SignUpPage fillNameField(String name){
    sendKeys(nameField, name);
    return this;
}
public SignUpPage fillLastNameField(String lastName){
       sendKeys(lastNameField, lastName);
        return this;
}
public SignUpPage fillEmailField(String email){
    sendKeys(emailField, email);
       return this;
}
public SignUpPage fillPasswordField(String password){
      sendKeys(passwordField, password);
        return this;
}
public SignUpPage signCheckBox(){
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("document.querySelector('#terms-of-use').click();");
        return this;
}


    public SignUpPage clickButtonRegistration(){
        click(buttonRegistration);
        return this;
}
    public  boolean shouldHave(){
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBePresentInElement(textSuccessRegistration,"Registered"));
    }


public SearchPage clickButtonOk(){
        click(buttonOk);
        return new SearchPage(driver);
}


}
