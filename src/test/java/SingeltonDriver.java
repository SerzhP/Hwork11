import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SingeltonDriver {
    private static WebDriver driverChrome;

    public static WebDriver getDriverChrome(){
        if (driverChrome == null){
            System.setProperty("webdriver.chrome.driver",Constants.CHROMEDRIVER_PATH);
            driverChrome = new ChromeDriver();
        }
        return driverChrome;
    }
}
