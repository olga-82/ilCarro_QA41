package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class LetTheCarWorkPage extends BasePage{
    public LetTheCarWorkPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }
    @FindBy(xpath = "//input[@id='make']")
    WebElement makeField;
    @FindBy(xpath = "//input[@id='pickUpPlace']")
    WebElement inputLocation;
    @FindBy(xpath = "//div[@class='pac-item']")
    WebElement serchLocation;
    @FindBy(xpath = "//input[@id='model']")
    WebElement inputModel;
    @FindBy(xpath = "//input[@id='year']")
    WebElement inputYear;
    @FindBy(xpath = "//select[@id='fuel']")
    WebElement selectFuel;
    @FindBy(xpath = "//input[@id='seats']")
    WebElement inputSeats;
    @FindBy(xpath = "//input[@id='class']")
    WebElement inputCarClass;
    @FindBy(xpath = "//input[@id='serialNumber']")
    WebElement inputCarRegNumber;
    @FindBy(xpath = "//input[@id='price']")
    WebElement inputPrice;
    @FindBy(xpath = "//textarea[@id='about']")
    WebElement inputAbout;
    @FindBy(xpath = "//label[@for='photos']")
    WebElement addFoto;
    @FindBy(xpath = "//h1[contains(text(),' Let the car work ')]")
    WebElement textLetTheCarWorkExists;
    @FindBy (xpath = "//h1[. ='Car added']")
    WebElement textCarAddedSuccess;
   @FindBy(xpath = "//button[.='Add another car']")
    WebElement buttonAddAnotherCar;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement buttonSubmit;


    public LetTheCarWorkPage setManufacturer(String manufacturer) {
       sendKeys(makeField,manufacturer);
        return this;
    }
// Метод предназначен для добавления местоположения в поле ввода на странице.
    public LetTheCarWorkPage addLocation(String text){

        sendKeys(inputLocation,text);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
         serchLocation= wait
                .until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[@class='pac-item']")));
        serchLocation.click(); // выбираем этот элемент.
        return this;
    }

    public LetTheCarWorkPage photoAttachment(String filePath){
        //метод находит элемент на странице, который представляет собой поле ввода для загрузки фотографий.
        WebElement addPhoto = driver.findElement(By.id("photos"));
        /*
        JavascriptExecutor - это интерфейс, предоставляемый WebDriver,
            который позволяет выполнить JavaScript код в контексте браузера.
        * */
        ((JavascriptExecutor)driver)
                .executeScript("arguments[0].scrollIntoView(true);", addPhoto); // Метод scrollIntoView(true) прокручивает страницу так, чтобы элемент стал видимым.
        addPhoto.sendKeys(filePath); // используем метод sendKeys для отправки пути к файлу (filePath) на элемент (в данном случае, это поле ввода для загрузки фотографий).
        return  this;
    }


    /**
     * Метод предназначен для выбора случайного типа топлива в выпадающем списке на веб-странице
     * @return LetTheCarWorkPage
     */
    public LetTheCarWorkPage setFuelType(){
         // находим выпадающий список на веб-странице с помощью XPath-выражения,
        // которое ищет элемент с атрибутом id, равным "fuel".
        // Полученный элемент представляет собой элемент <select>.
        Select select = new Select(selectFuel); // Создаем объект класса Select, который предоставляет методы для взаимодействия с выпадающим списком
        List<WebElement> options = select.getOptions(); // Получаем список всех вариантов выбора в выпадающем списке.
        Random random = new Random();
        int index = random.nextInt(options.size());// Генерируем случайный индекс, который будет представлять номер варианта выбора в списке. Этот индекс выбирается из диапазона от
        // 0 (включительно) до количества вариантов выбора в списке
        select.selectByIndex(index); // Выбираем вариант выбора по индексу, который мы сгенерировали ранее.
        return this; //  Возвращаем текущий экземпляр страницы, чтобы обеспечить возможность цепочного вызова методов.
    }
    public LetTheCarWorkPage fillModelField(String text){
        sendKeys(inputModel,text);
        return this;
    }
    public LetTheCarWorkPage fillYearField(String text){
        sendKeys(inputYear,text);
        return this;
    }
    public LetTheCarWorkPage fillSeatsField(String text){
        sendKeys(inputSeats,text);
        return this;
    }
    public LetTheCarWorkPage fillCarClassField(String text){
        sendKeys(inputCarClass,text);
        return this;
    }
    public LetTheCarWorkPage fillCarRegNumberField(String text){
        sendKeys(inputCarRegNumber,text);
        return this;
    }
    public LetTheCarWorkPage fillCarPriceField(String text){
        sendKeys(inputPrice,text);
        return this;
    }
    public LetTheCarWorkPage fillAboutField(String text) {
        sendKeys(inputAbout, text);
        return this;
    }
    public  LetTheCarWorkPage clickButtonAddCar(){
        click(buttonAddAnotherCar);
        return this;
    }
    public  LetTheCarWorkPage clickButtonSubmit(){
        click(buttonSubmit);
        return this;
    }
    public boolean isCarAddedSuccess(){
       return shouldHave(textCarAddedSuccess,"Car added");

    }
    public boolean isLetTheCarPageExists(){
        return shouldHave(textLetTheCarWorkExists,"Let the car work");

    }

}
