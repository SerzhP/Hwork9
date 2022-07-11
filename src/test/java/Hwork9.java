import org.apache.commons.io.FileUtils;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.util.List;
import org.openqa.selenium.support.ui.Select;

public class Hwork9 {
    private static WebDriver driverChrome;

    @BeforeClass
    public static void Start(){
        System.setProperty("webdriver.chrome.driver", DriversHwork9.CHROMEDRIVER_PATH);
        driverChrome = new ChromeDriver();
    }

    @Test
    public static void T1_Calculator()throws InterruptedException {
        driverChrome.get("https://dgotlieb.github.io/WebCalculator/");
        System.out.println(driverChrome.findElement(By.id("seven")).getSize());
        System.out.println(driverChrome.findElement(By.id("six")).isDisplayed());
        String result = "20";
        driverChrome.findElement(By.id("three")).click();
        Thread.sleep(1000);
        driverChrome.findElement(By.id("add")).click();
        Thread.sleep(1000);
        driverChrome.findElement(By.id("nine")).click();
        Thread.sleep(1000);
        driverChrome.findElement(By.id("equal")).click();
        Assert.assertEquals(driverChrome.findElement(By.id("screen")).getText(),result);

    }
    @Test
    public static void T2_AssertWebSite(){
        driverChrome.get("https://www.haaretz.co.il/");
        String haretzURL = "https://www.haaretz.co.il/";
        Assert.assertEquals(driverChrome.getCurrentUrl(),haretzURL);
    }

    @Test
    public static void T3_AssertWebTitle(){
        String haaretzTitle = "הארץ - חדשות, ידיעות מהארץ והעולם - עיתון הארץ";
        driverChrome.get("https://www.haaretz.co.il/");
        driverChrome.navigate().refresh();
        Assert.assertEquals(driverChrome.getTitle(),haaretzTitle);
    }

    @Test
    public static void T4_BrowserExtension(){
        ChromeOptions extension = new ChromeOptions();
        extension.addArguments("--disable-extensions");
    }

    @Test
    public void getScreenShot(){
        driverChrome.get("https://dgotlieb.github.io/Actions");
        T5_01ScreenShot(driverChrome.findElement(By.id("drag1")));
    }
    public static void T5_01ScreenShot(WebElement element){

        File screenEl = element.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenEl,new File("screenEl.png"));
        }catch (IOException a){
            a.printStackTrace();
        }
    }

    @Test
    public static void T5_02DoubleClick(){
        driverChrome.get("https://dgotlieb.github.io/Actions");
        WebElement doubleEl =  driverChrome.findElement(By.xpath("/html/body/p[2]"));
        Actions doubleAction = new Actions(driverChrome);
        doubleAction.doubleClick(doubleEl);
        doubleAction.build().perform();

        String result = driverChrome.findElement(By.id("demo")).getText();
        Assert.assertEquals("You double clicked",result);

    }
    @Test
    public void T5_03mouseHover() throws InterruptedException {
        driverChrome.get("https://dgotlieb.github.io/Actions");
        WebElement howerEl = driverChrome.findElement(By.id("close"));
        Actions hoverAct = new Actions(driverChrome);

        hoverAct.moveToElement(howerEl);
        Thread.sleep(3000);
    }

    @Test
    public void T5_04foodList() throws InterruptedException {
        driverChrome.get("https://dgotlieb.github.io/Actions");
        List<WebElement> elementList = driverChrome.findElements(By.name("kind"));
        Actions food = new Actions(driverChrome);
        food.clickAndHold(elementList.get(2)).clickAndHold(elementList.get(1)).click();
        food.build().perform();
        Thread.sleep(3000);
    }
    @Test
    public void T5_05uploadFile(){
        driverChrome.get("https://dgotlieb.github.io/Actions");
        driverChrome.findElement(By.name("pic")).sendKeys("C:\\Users\\Sergii\\Desktop\\certificate.jpg");

    }
    @Test
    public void T5_06scrollDownElm()throws InterruptedException{
        driverChrome.get("https://dgotlieb.github.io/Actions");
        WebElement scrollElm = driverChrome.findElement(By.id("clickMe"));
        ((JavascriptExecutor)driverChrome).executeScript("arguments[0].scrollIntoView(true);",scrollElm);
        Thread.sleep(3000);

    }
    @Test
    public void T5_07scrollDownLoct()throws InterruptedException{
        driverChrome.get("https://dgotlieb.github.io/Actions");
        JavascriptExecutor locate = (JavascriptExecutor) driverChrome;
        locate.executeScript("javascript:window.scrollBy(240,340)");
        Thread.sleep(3000);

    }

    @Test
    public static void T6_Controllers() throws InterruptedException {
        driverChrome.get("https://dgotlieb.github.io/Controllers/");
        List<WebElement>list = driverChrome.findElements(By.name("group1"));
        for (WebElement CheeseEl : list){
            if(CheeseEl.getAttribute("value").equals("Cheese")){
                CheeseEl.click();
            }
        }
        Thread.sleep(2000);

        Select cheese = new Select(driverChrome.findElement(By.name("dropdownmenu")));
        cheese.selectByValue("Cheese");
        for (int i = 0; i < cheese.getOptions().size();i++){
            System.out.println(cheese.getOptions().get(i).getText());
        }

    }


    @Test
    public static void T7_PrintButton(){
    driverChrome.get("https://dgotlieb.github.io/WebCalculator/");
        System.out.println(driverChrome.findElement(By.id("two")).getRect().getHeight());
        System.out.println(driverChrome.findElement(By.id("six")).getRect().getWidth());

    }
    @AfterClass

    public void Finish(){
        driverChrome.close();

    }

}

