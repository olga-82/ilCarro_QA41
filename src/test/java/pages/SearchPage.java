package pages;

import config.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class SearchPage extends BasePage {
    public SearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    @FindBy(xpath = "//h1[contains(text(),'Find your car now!')]")
    WebElement textFindCar;
    @FindBy(xpath = "//div[@class='pac-item'][1]")
    WebElement searchCity;
    @FindBy(xpath = "//input[@id='city']")
    WebElement inputCity;
    @FindBy(xpath = "//input[@id='dates']")
    WebElement inputDate;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement buttonSearch;
    @FindBy(xpath = "//button[@aria-label='Next month']")
    WebElement buttonNextMonth;

    public SearchPage selectPeriodDaysDatePicker(String daysFrom, String daysTo) {
        String[] startDate = daysFrom.split("/");
        String[] endDate = daysTo.split("/");
        click(inputDate);
        String locatorStartDate = String.format("//div[.=' %s ']", startDate[1]);
        String LocatorEndDate = String.format("//div[.=' %s ']", endDate[1]);
        driver.findElement(By.xpath(locatorStartDate)).click(); ;
        driver.findElement(By.xpath(LocatorEndDate)).click();
        return this;
    }
    public SearchPage fillCity(String city) {
        sendKeys(inputCity, city);
        click(searchCity);
        return this;
    }
    public SearchPage clickButtonSearch(){
        click(buttonSearch);
        return this;
    }
    public SearchPage fillFormSearch(String city, String from, String to) throws InterruptedException {
        fillCity(city);
        selectPeriodDaysDatePicker(from, to);
        Thread.sleep(5000);
        clickButtonSearch();
        return this;
    }
    public boolean textSearchExists(){
       return shouldHave(textFindCar,"Find your car now!");
    }
    public boolean isCarPresent() {
        return isElementPresent(By.xpath("//span[text()='Chevrolet Comaro']"));

    }




}