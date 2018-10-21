import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class Tests extends TestsMet {



    @Test(dataProvider = "DataPickerData")
    public void testDataPicker(String input) {

        stringToData data = new stringToData(input);

        int month = data.getMonth();
        int day = data.getDay();
        int year = data.getYear();

        driver.get("https://jqueryui.com/datepicker/#other-months");
        WebElement frame = driver.findElement(By.className("demo-frame"));
        driver.switchTo().frame(frame);
        WebElement el = driver.findElement(By.id("datepicker"));

        el.click();
        int currentMonth = monthInt();
        int currentYear = Integer.parseInt(driver.findElement(By.className("ui-datepicker-year")).getText());
        moveToMonth(driver,((year*12)+month)-((currentYear*12)+currentMonth));

        List<WebElement> daysInCalendar = driver.findElements(By.cssSelector("td[data-month='"+(month-1)+"']"));
        daysInCalendar.get(day-1).findElement(By.tagName("a")).click();

        stringToData actual = new stringToData(el.getAttribute("value"));

        Assert.assertEquals(actual.expected(),data.expected());

    }
}
