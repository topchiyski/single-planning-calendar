package framework;

import org.openqa.selenium.*;

public class CreateAppointmentPage {

    private WebDriver webDriver;
    private By titleInputLocator = By.cssSelector("input[id=dialogFrag--appTitle-inner]");
    private By okButtonLocator = By.xpath("//bdi[text() = 'OK']//../..");

    public CreateAppointmentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void enterAppointmentName(String appointmentName) {
        WebElement title = webDriver.findElement(titleInputLocator);
        title.sendKeys(appointmentName);
        WebElement okButton = webDriver.findElement(okButtonLocator);
        okButton.click();
    }
}
