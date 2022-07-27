import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.w3c.dom.Document;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;


public class Hwork11 {
private static WebDriver driverChrome;
private String openNT = "";


    @BeforeClass
public void beforeClass(){
driverChrome = SingeltonDriver.getDriverChrome();

    }
//    1


    @Test
    public void T1_iFrameText(){
        driverChrome.get("https://dgotlieb.github.io/Navigation/Navigation.html");
        WebElement frameEL =driverChrome.findElement(By.id("my-frame"));
        driverChrome.switchTo().frame(frameEL);
        System.out.println(driverChrome.findElement(By.id("iframe_container")).getText());
        driverChrome.switchTo().defaultContent();
    }


//    3

    @Test
    public void T3_XML(){
        try {
            driverChrome.get(getURL("URL"));
                    } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private static String getURL(String keyName) throws Exception{
        File dataXML = new File("C:\\Users\\Sergii\\IdeaProjects\\Hwork11\\src\\main\\resources\\data.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(dataXML);
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName(keyName).item(0).getTextContent();

    }
// 4

    @Test
    public void T4_01alert(){
        driverChrome.get("https://dgotlieb.github.io/Navigation/Navigation.html");
        driverChrome.findElement(By.id("MyAlert")).click();
        Alert alert = driverChrome.switchTo().alert();
        System.out.println(alert.getText());
    }

    @Test
    public void T4_02prompt(){
        driverChrome.get("https://dgotlieb.github.io/Navigation/Navigation.html");
        driverChrome.findElement(By.id("MyPrompt")).click();
        Alert prompt = driverChrome.switchTo().alert();
        prompt.sendKeys("Serzh");
        prompt.accept();
        String result = "Serzh";
        Assert.assertEquals(result,driverChrome.findElement(By.id("output")).getText());

    }

    @Test
    public void T4_03confirmBox(){
        driverChrome.get("https://dgotlieb.github.io/Navigation/Navigation.html");
        driverChrome.findElement(By.id("MyConfirm")).click();
        Alert confBox = driverChrome.switchTo().alert();
        confBox.accept();
        String result = "Confirmed";
        Assert.assertEquals(result,driverChrome.findElement(By.id("output")).getText());
    }
    @Test
    public void T4_04openNewTab() throws InterruptedException {
        driverChrome.get("https://dgotlieb.github.io/Navigation/Navigation.html");
        driverChrome.findElement(By.id("openNewTab")).click();
        openNT = driverChrome.getWindowHandle();
        Thread.sleep(1500);
        driverChrome.switchTo().window(openNT);
    }
    @Test
    public void T4_05openNewWindow() throws InterruptedException {
        driverChrome.get("https://dgotlieb.github.io/Navigation/Navigation.html");
        driverChrome.findElement(By.xpath("/html/body/div[2]/div/div/div/div/a")).click();
        openNT = driverChrome.getWindowHandle();
        Thread.sleep(1500);
        driverChrome.switchTo().window(openNT);
    }

    @AfterClass

    public void afterClass(){
        driverChrome.close();

    }
}
