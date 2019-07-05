package framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AppointmentContextPage {

    private WebDriver webDriver;
    private By editButtonLocator = By.cssSelector("#__button3");

    public AppointmentContextPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void pressEditButton() {
        webDriver.findElement(editButtonLocator).click();
    }
}
