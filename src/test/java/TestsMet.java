import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class TestsMet {
    WebDriver driver;


    @DataProvider(name = "DataPickerData")
    public static Object[][] DataPickerData() {
        //
        return new Object[][] { {"1.02.2019"},{"27.12.2018"},{"today"},{"06.07.2018"},{"20.11.2017"}};
    }

    public void moveToMonth(WebDriver driver, int countMonths){
        if (countMonths>0) {
            while (countMonths > 0) {
                driver.findElement(By.cssSelector("a[title='Next']")).click();
                countMonths--;
            }
        }
        else {
            while (countMonths < 0) {
                driver.findElement(By.cssSelector("a[title='Prev']")).click();
                countMonths++;
            }
        }
    }

    public int monthInt(){

        try {
            Date date = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(driver.findElement(By.className("ui-datepicker-month")).getText());
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            return cal.get(Calendar.MONTH)+1;
        }catch (Exception e){}
        return 0;
    }


    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/drivers/chromedriver.exe");
        System.setProperty("webdriver.firefox.marionette","src/test/dricers/geckodriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();

    }
}