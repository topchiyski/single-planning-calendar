package framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DayViewPage extends GenericCalendarPage {

    public DayViewPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void leftClickOnAppointment(String appointmentName) {
        By appointmentLocator = By.xpath("//span[text()='" + appointmentName + "']/../../../..");
        webDriver.findElement(appointmentLocator).click();
    }
}
