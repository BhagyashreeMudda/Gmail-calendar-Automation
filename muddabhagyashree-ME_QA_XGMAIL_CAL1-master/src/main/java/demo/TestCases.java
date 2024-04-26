package demo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.ArrayList;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;


public class TestCases 
{
    ChromeDriver driver;


    public TestCases()
    {
        System.out.println("Constructor: TestCases");

        
    


        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);


        String userDataDirectory = System.getProperty("user.dir") + "/data";

    options.addArguments("--user-data-dir=" + userDataDirectory);

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }






    public void endTest()
    {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }








    
    public  void testCase01() throws InterruptedException
    {
        System.out.println("Start Test case: testCase01");

        //Verifying calemdar home page
        driver.get("https://calendar.google.com/calendar/u/0/r");
        Thread.sleep(3000);

        String calUrl=driver.getCurrentUrl();
        if(calUrl.contains("calendar"))
        {
            System.out.println("URL contains calendar");
        }
        else
        {
            System.out.println("URL does NOT contains calendar");
        }


        System.out.println("end Test case: testCase01");
    }




     public  void testCase02() throws InterruptedException
     {

        System.out.println("Start Test case: testCase02");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement calenderView = driver.findElement(By.xpath("//span[@class='VfPpkd-vQzf8d'][normalize-space()='Month']"));
        js.executeScript("arguments[0].click();", calenderView);

        Thread.sleep(3000);

        WebElement monthViewEl = driver.findElement(By.xpath("//*[@id=\"ucc-1\"]/ul/li[3]"));
        js.executeScript("arguments[0].click();", monthViewEl);
        Thread.sleep(3000);

        WebElement selectorEl = driver.findElement(By.xpath("//*[@id=\"gb\"]/div[2]/div[2]/div[3]/div/div/div[5]/div/div[1]/div[1]/div/button/span"));
        String text = selectorEl.getText();
        System.out.println(text);
        if(text.equals("Month")){
            System.out.println("Pass : Ensured that you are switching to the month view");
        }
        Thread.sleep(2000);
        WebElement taskTab =driver.findElement(By.xpath("//div[@class='lYYbjc']/child::div/child::div/child::div/child::div[2]/child::div[2]/child::div[1]/child::div[3]")); 
        Actions act=new Actions(driver);
        act.moveToElement(taskTab).perform();
        act.click().perform();






        Thread.sleep(2000);     
      //  js.executeScript("arguments[0].click();", taskTab);                                                             
       // taskTab.click();
        Thread.sleep(2000);

        WebElement taskButton = driver.findElement(By.xpath("//*[@id='tabTask']/div[2]"));
        taskButton.click();

        WebElement tittleElement = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/div[2]/span/div/div[1]/div[2]/div[1]/div[2]/div/div[2]/label/input"));
        tittleElement.sendKeys("Crio INTV Task Automation");
        Thread.sleep(2000);

        WebElement descriptionElement = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/div[2]/span/div/div[1]/div[2]/div[1]/div[3]/div[3]/span/div/div[2]/div[2]/div/label/textarea"));
        descriptionElement.sendKeys("Crio INTV Calendar Task Automation");
        Thread.sleep(2000);
        WebElement saveButton =  driver.findElement(By.xpath("/html/body/div[4]/div/div/div[2]/span/div/div[1]/div[2]/div[2]/div[4]/button"));
        saveButton.click();

        System.out.println("Verify: The Calendar switched to month view, and a task was created.");
        System.out.println("End Test case: testCase02");
        System.out.println();

       
     }


     public void testCase03() throws InterruptedException
      {
        System.out.println("Start Test case: testCase03");
        WebElement taskTab2 =driver.findElement(By.xpath("//div[@class='lYYbjc']/child::div/child::div/child::div/child::div[2]/child::div[2]/child::div[1]/child::div[3]")); 
        Actions act2=new Actions(driver);
        act2.moveToElement(taskTab2).perform();
        act2.click().perform();
        Thread.sleep(2000);


        //clicking on Edit
        WebElement editButon=driver.findElement(By.xpath("(//span[@class='VfPpkd-kBDsod meh4fc KU3dEf'])[3]"));
        editButon.click();
        Thread.sleep(2000);


    //updating description
    WebElement updateDes=driver.findElement(By.xpath("(//label[@jsname='vhZMvf'])[2]/child::span[1]/following-sibling::input"));
        updateDes.clear();
        Thread.sleep(2000);
        updateDes.sendKeys("Crio INTV Task Automation is a test suite designed for automating various tasks on the Google Calendar web application");
    Thread.sleep(2000);

        //saving updayes
        WebElement saveButton=driver.findElement(By.xpath("//span[normalize-space()='Save']"));
        saveButton.click();
        Thread.sleep(2000);



        //verifying update descrio is correct
        String updateToDes="Crio INTV Task Automation is a test suite designed for automating various tasks on the Google Calendar web application";
        WebElement taskTab3 =driver.findElement(By.xpath("//div[@class='lYYbjc']/child::div/child::div/child::div/child::div[2]/child::div[2]/child::div[1]/child::div[3]")); 
        Actions act3=new Actions(driver);
        act3.moveToElement(taskTab3).perform();
        act3.click().perform();
        Thread.sleep(2000);

        WebElement Desc=driver.findElement(By.xpath("/html[1]/body[1]/div[4]/div[1]/div[1]/div[2]/span[1]/div[1]/div[2]/div[1]/div[2]/span[1]"));
        String UpdateText=Desc.getText();
        System.out.println("updated desc = "+UpdateText);

        WebElement closeBut=driver.findElement(By.xpath("//button[@id='xDetDlgCloseBu']//span[@class='VfPpkd-kBDsod meh4fc KU3dEf']//*[name()='svg']"));
        closeBut.click();



        if(UpdateText.contains("Crio INTV Task Automation is a test suite designed for automating various tasks on the Google Calendar web application"))
    {
        System.out.println("Upation successful");
    }
    else
    {
        System.out.println("Upation NOT successful");

    }


        System.out.println("End Test Case : TestCase03");


    }

    public void testCase04() throws InterruptedException
    {
        System.out.println("Start Test case: testCase04");

        //opening the task
        WebElement taskTab9 =driver.findElement(By.xpath("(//div[@class='qOxDcf']/child::div)[2]/child::div[3]")); 
        Actions act9=new Actions(driver);
      
       act9.moveToElement(taskTab9).perform();
        Thread.sleep(1000);
        act9.click().perform();
    
        Thread.sleep(2000);


        //verifying title of the task
     //   String updateToDes5="Crio INTV Task Automation is a test suite designed for automating various tasks on the Google Calendar web application";
     //   WebElement taskTab6 =driver.findElement(By.xpath("(//div[@class='qOxDcf']/child::div)[2]/child::div[3]")); 
     //   String UpdateText4=taskTab6.getText();
     //   if(UpdateText4.contains("Crio INTV Task Automation is a test suite designed for automating various tasks on the Google Calendar web application"))
       //   {
      //          System.out.println("Title of the task for deletion is correct");
      //   }
      //   else
      //       {
      //           System.out.println("Title of the task for deletion is NOT correct");

       //       }

        //deleting taks
        WebElement delButton=driver.findElement(By.xpath("(//button[@class='VfPpkd-Bz112c-LgbsSe yHy1rc eT1oJ mN1ivc m2yD4b HfYfLe'])[2]"));
        delButton.click();
        


        //verifying 'Event deleted' text
        WebElement EvntDel=driver.findElement(By.xpath("//div[@class='VYTiVb']"));
        String EventDelMsg=EvntDel.getText();
        System.out.println("Event deleted msg popup is = "+EventDelMsg);

        if(EvntDel.isDisplayed())
        {
                System.out.println("Event deleted successfully");
        }
        else
        {
            System.out.println("Event NOT deleted successfully");
        }





       
       
        System.out.println("End Test case: testCase04");
        System.out.println();
    }


}
