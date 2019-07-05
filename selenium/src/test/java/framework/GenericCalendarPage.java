package framework;

import enumerations.CalendarView;
import enumerations.SwitchButton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public abstract class GenericCalendarPage {

    protected WebDriver webDriver;
    private By dragAndDropSwitchLocator = By.cssSelector(".sapMSwtCont *[id*=DragAndDrop]");
    private By resizeAppointmentsLocator = By.cssSelector(".sapMSwtCont *[id*=enableAppointmentsResize]");
    private By createAppointmentsLocator = By.cssSelector(".sapMSwtCont *[id*=enableAppointmentsCreate");
    private By weekViewLocator = By.xpath("//li[text()='Week']");
    private By workWeekViewLocator = By.xpath("//li[text()='Work Week']");
    private By dayViewLocator = By.xpath("//li[text()='Day']");
    private By addNewAppointmentButtonLocator = By.cssSelector("button[id*='addNewAppointment'] span");
    private By switchButtonsLocator = By.cssSelector("div.sapMSwtCont");

    GenericCalendarPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void clickOnDragAndDropSwitch() {
        WebElement dragAndDropSwitch = webDriver.findElement(dragAndDropSwitchLocator);
        dragAndDropSwitch.click();
    }

    public void clickOnResizeAppointmentsSwitch() {
        WebElement resizeAppointmentsSwitch = webDriver.findElement(resizeAppointmentsLocator);
        resizeAppointmentsSwitch.click();
    }

    public void clickOnCreateAppointmentsSwitch () {
        WebElement createAppointmentsSwitch = webDriver.findElement(createAppointmentsLocator);
        createAppointmentsSwitch.click();
    }

    public void selectCalendarView(CalendarView calendarView) {
        switch (calendarView) {

            case WORK_WEEK :
                webDriver.findElement(workWeekViewLocator).click();
                break;
            case DAY :
                webDriver.findElement(dayViewLocator).click();
                break;
            default:
                webDriver.findElement(weekViewLocator).click();
                break;
        }
    }

    public void pressAddNewAppointmentButton() {
            WebElement addNewAppointmentButton = webDriver.findElement(addNewAppointmentButtonLocator);
            addNewAppointmentButton.click();
    }

    public boolean isThereAnAppointmentWithName(String ApointmentNameToBeChecked) {
        By dynamicLocator = By.xpath("//span[text() = '" + ApointmentNameToBeChecked + "'][contains(@id,'appointment')]");
        List<WebElement> foundWebElements = webDriver.findElements(dynamicLocator);
        int numberOfFoundWebElements = foundWebElements.size();
        return (numberOfFoundWebElements != 0);
    }

    public boolean getSwitchButtonStatus(SwitchButton switchButton) {
        List<WebElement> switchButtons = webDriver.findElements(switchButtonsLocator);
        switch (switchButton) {
            case DRAG_AND_DROP:
                return (switchButtons.get(0).getAttribute("aria-checked")).equalsIgnoreCase("true");
            case RESIZE_APPOINTMENTS:
                return (switchButtons.get(1).getAttribute("aria-checked")).equalsIgnoreCase("true");
            case CREATE_APPOINTMENTS:
                return (switchButtons.get(2).getAttribute("aria-checked")).equalsIgnoreCase("true");
        }
        return false;
    }
}
