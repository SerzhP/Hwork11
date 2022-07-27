import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ExtentReport {

    private static ChromeDriver driver;
    private static ExtentReports EXreports;
    private static ExtentTest EXtest;


    @BeforeClass
    public void beforeClass(){
        ExtentSparkReporter  htmlReport = new ExtentSparkReporter("C:\\Users\\Sergii\\IdeaProjects\\Hwork11\\src\\main\\resources\\extent.html");
        EXreports = new ExtentReports();
        EXreports.attachReporter(htmlReport);
        EXtest = EXreports.createTest("Test_01","First");
        EXreports.setSystemInfo("Studying","QAExperts");
        driver = (ChromeDriver) SingeltonDriver.getDriverChrome();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        EXtest.log(Status.INFO,"Driver is working");
    }

    @Test
    public void  translationField(){
        driver.get("https://translate.google.com/");
        String timeNow = String.valueOf(System.currentTimeMillis());
        EXtest.info("details", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(timeNow)).build());
        driver.findElement(By.className("er8xn")).click();
        EXtest.log(Status.PASS,"Press on translation field");

    }
    @AfterClass
    public void afterClass(){
        EXtest.log(Status.INFO,"Test finished");
        driver.close();
        EXreports.flush();
    }
    private static String takeScreenShot(String ImagesPath){
        TakesScreenshot makeScreenS = (TakesScreenshot) driver;
        File screenShotFile = makeScreenS.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(ImagesPath +".png");
        try {
            FileUtils.copyFile(screenShotFile,destinationFile);
        }catch (IOException a){
            System.out.println(a.getMessage());
        }
        return ImagesPath + ".png";
    }

}
