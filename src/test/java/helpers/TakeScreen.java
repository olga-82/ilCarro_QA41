package helpers;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static config.BaseTest.getDriver;


public class TakeScreen {

@Attachment(value="Failure screenshot",type="image/png")
    public static byte[] takeScreenshot(String testName) {
    File screenshotFile=((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
try {
    String screenshotName = testName+" "+System.currentTimeMillis()+".png";
    FileUtils.copyFile(screenshotFile,new File("screenshots/"+screenshotName));
    return Files.readAllBytes(Paths.get("screenshots/"+screenshotName));


} catch (IOException e) {
    return null;
}

}
}
