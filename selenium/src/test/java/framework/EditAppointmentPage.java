package framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EditAppointmentPage {

    private WebDriver webDriver;
    private By titleFieldLocator = By.cssSelector("input#dialogFrag--appTitle-inner");
    private By okButtonLocator = By.cssSelector("button#__button5");

    public EditAppointmentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void typeTitle(String newTitle) {
        WebElement titleField = webDriver.findElement(titleFieldLocator);
        titleField.clear();
        titleField.sendKeys(newTitle);
    }

    public void pressOkButton() {
        webDriver.findElement(okButtonLocator).click();
    }
}
