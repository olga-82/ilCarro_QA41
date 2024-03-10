package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected static WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public static  <T extends BasePage> T openTopMenu(TopMenuItem topMenuItem) {
        String menuItemName = topMenuItem.getLabel();
        WebElement menuItem = driver.findElement(By
                .xpath("//div[@class='header']//a[contains(text(),'" + menuItemName + "')]"));
        menuItem.click();

        switch (topMenuItem) {
            case SEARCH, DELETE_ACCOUNT, LOGOUT:
                return (T) new SearchPage(driver);
            case TERMS_OF_USE:
                return (T) new TermsOfUsePage(driver);
            case LOGIN:
                return (T) new LogInPage(driver);
            case SIGN_UP:
                return (T) new SignUpPage(driver);
            case LET_THE_CAR_WORK:
                return (T) new LetTheCarWorkPage(driver);
            case PRIVACY_POLICE:
                return (T) new PrivacyPolicePage(driver);


            default:
                throw new IllegalArgumentException("Something's wrong " + topMenuItem);
        }

    }
    public static String GetText(String locator) {
        WebElement text = driver.findElement(By.xpath(locator));
        return text.getText();

    }


    public static void click(WebElement locator){
        locator.click();
    }
    public void sendKeys(WebElement element, String text){
       // element.click();
        element.clear();
        element.sendKeys(text);
    }

    public boolean shouldHave(WebElement locator,String text) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBePresentInElement(locator,text));
    }
    public boolean isElementPresent(By locator) {
        return  driver.findElements(locator).size() > 0;
    }

    }

