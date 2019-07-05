package tests;

import enumerations.CalendarView;
import enumerations.SwitchButton;
import framework.*;
import org.junit.*;
import enumerations.Browser;
import utilities.TestManager;
import utilities.Waiter;

public class SinglePlanningCalendarDemoTestSuite {

    private TestManager testManager = new TestManager();
    private static final int ONE_SECOND = 1;
    private final static String SINGLE_PLANNING_CALENDAR_ADDRESS = "https://openui5nightly.hana.ondemand.com/#/entity/sap.m.SinglePlanningCalendar/sample/sap.m.sample.SinglePlanningCalendar";

    // In day view an appointment title is updated
    @Test
    public void updateAppointmentTitleOnDayView() throws InterruptedException{
        DayViewPage dayViewPage = new DayViewPage(testManager.provideWebDriver());
        dayViewPage.selectCalendarView(CalendarView.DAY);
        Assert.assertTrue("Drag And Drop switch is not enabled", dayViewPage.getSwitchButtonStatus(SwitchButton.DRAG_AND_DROP));
        String oldAppointmentName = "Lunch";
        dayViewPage.leftClickOnAppointment(oldAppointmentName);
        AppointmentContextPage appointmentContextPage = new AppointmentContextPage(testManager.provideWebDriver());
        Waiter.wait(ONE_SECOND);
        appointmentContextPage.pressEditButton();
        EditAppointmentPage editAppointmentPage = new EditAppointmentPage(testManager.provideWebDriver());
        String newAppointmentName = "Breakfast";
        editAppointmentPage.typeTitle(newAppointmentName);
        editAppointmentPage.pressOkButton();
        Assert.assertTrue("An appointment with name: >>>" + newAppointmentName + "<<< is missing", dayViewPage.isThereAnAppointmentWithName(newAppointmentName));
    }

    // Tests switch buttons
    @Test
    public void checkSwitckButtonsWorkCorrectlyAndIndependantly() {
        GenericCalendarPage weekViewPage= new WeekViewPage(testManager.provideWebDriver());
        weekViewPage.selectCalendarView(CalendarView.WEEK);
        Assert.assertTrue("Drag And Drop switch is enabled by default", weekViewPage.getSwitchButtonStatus(SwitchButton.DRAG_AND_DROP));
        Assert.assertTrue("Resize Appointments switch is enabled by default", weekViewPage.getSwitchButtonStatus(SwitchButton.RESIZE_APPOINTMENTS));
        Assert.assertTrue("Create Appointments switch is enabled by default", weekViewPage.getSwitchButtonStatus(SwitchButton.CREATE_APPOINTMENTS));
        weekViewPage.clickOnDragAndDropSwitch();
        Assert.assertFalse("Drag And Drop switch is disabled", weekViewPage.getSwitchButtonStatus(SwitchButton.DRAG_AND_DROP));
        Assert.assertTrue("Resize Appointments switch is enabled", weekViewPage.getSwitchButtonStatus(SwitchButton.RESIZE_APPOINTMENTS));
        Assert.assertTrue("Create Appointments switch is enabled", weekViewPage.getSwitchButtonStatus(SwitchButton.CREATE_APPOINTMENTS));
        weekViewPage.clickOnResizeAppointmentsSwitch();
        Assert.assertFalse("Drag And Drop switch is disabled", weekViewPage.getSwitchButtonStatus(SwitchButton.DRAG_AND_DROP));
        Assert.assertFalse("Resize Appointments switch is disabled", weekViewPage.getSwitchButtonStatus(SwitchButton.RESIZE_APPOINTMENTS));
        Assert.assertTrue("Create Appointments switch is enabled", weekViewPage.getSwitchButtonStatus(SwitchButton.CREATE_APPOINTMENTS));
        weekViewPage.clickOnCreateAppointmentsSwitch();
        Assert.assertFalse("Drag And Drop switch is disabled", weekViewPage.getSwitchButtonStatus(SwitchButton.DRAG_AND_DROP));
        Assert.assertFalse("Resize Appointments switch is disabled", weekViewPage.getSwitchButtonStatus(SwitchButton.RESIZE_APPOINTMENTS));
        Assert.assertFalse("Create Appointments switch is disabled", weekViewPage.getSwitchButtonStatus(SwitchButton.CREATE_APPOINTMENTS));
        weekViewPage.clickOnResizeAppointmentsSwitch();
        weekViewPage.clickOnDragAndDropSwitch();
        weekViewPage.clickOnCreateAppointmentsSwitch();
        Assert.assertTrue("Drag And Drop switch is again enabled", weekViewPage.getSwitchButtonStatus(SwitchButton.DRAG_AND_DROP));
        Assert.assertTrue("Resize Appointments switch is again enabled", weekViewPage.getSwitchButtonStatus(SwitchButton.RESIZE_APPOINTMENTS));
        Assert.assertTrue("Create Appointments switch is again enabled", weekViewPage.getSwitchButtonStatus(SwitchButton.CREATE_APPOINTMENTS));
    }

    // Test creation of a new appointment on work view
    @Test
    public void createNewAppointmentOnWeekView() {
        GenericCalendarPage workViewPage = new WorkWeekViewPage(testManager.provideWebDriver());
        workViewPage.pressAddNewAppointmentButton();
        CreateAppointmentPage createAppointmentPage = new CreateAppointmentPage(testManager.provideWebDriver());
        String testAppointmentName = "Standard appointment name";
        createAppointmentPage.enterAppointmentName(testAppointmentName);
        boolean isThereAnAppointment = workViewPage.isThereAnAppointmentWithName(testAppointmentName);
        Assert.assertTrue("An appointment with name: >>>" + testAppointmentName + "<<< is not present", isThereAnAppointment);
    }

    // Test creation of a new appointment on work week view
    @Test
    public void createNewAppointmentOnWorkWeekView() {
        GenericCalendarPage workWeekViewPage = new WorkWeekViewPage(testManager.provideWebDriver());
        workWeekViewPage.selectCalendarView(CalendarView.WORK_WEEK);
        workWeekViewPage.pressAddNewAppointmentButton();
        CreateAppointmentPage createAppointmentPage = new CreateAppointmentPage(testManager.provideWebDriver());
        String testAppointmentName = "Среща на Кирилица";
        createAppointmentPage.enterAppointmentName(testAppointmentName);
        boolean isThereAnAppointment = workWeekViewPage.isThereAnAppointmentWithName(testAppointmentName);
        Assert.assertTrue("An appointment with name: >>>" + testAppointmentName + "<<< is not present", isThereAnAppointment);
    }

    // Test creation of a new appointment on day view
    @Test
    public void createNewAppointmentOnDayView() {
        GenericCalendarPage dayViewPage = new DayViewPage(testManager.provideWebDriver());
        dayViewPage.selectCalendarView(CalendarView.DAY);
        dayViewPage.pressAddNewAppointmentButton();
        CreateAppointmentPage createAppointmentPage = new CreateAppointmentPage(testManager.provideWebDriver());
        String testAppointmentName = "0987654321!@#$%";
        createAppointmentPage.enterAppointmentName(testAppointmentName);
        boolean isThereAnAppointment = dayViewPage.isThereAnAppointmentWithName(testAppointmentName);
        Assert.assertTrue("An appointment with name: >>>" + testAppointmentName + "<<< is not present", isThereAnAppointment);
    }

    // Test which disables 'Create Appointment' switch and checks that an appointment cannot be created
    @Test
    public void appointmentIsNotCreatedOnDayView() {
        GenericCalendarPage dayViewPage = new DayViewPage(testManager.provideWebDriver());
        dayViewPage.selectCalendarView(CalendarView.DAY);
        dayViewPage.clickOnCreateAppointmentsSwitch();
        dayViewPage.pressAddNewAppointmentButton();
        CreateAppointmentPage createAppointmentPage = new CreateAppointmentPage(testManager.provideWebDriver());
        String appointmentName = "DO NOT DISPLAY";
        createAppointmentPage.enterAppointmentName(appointmentName);
        Assert.assertFalse("An appointment with name: >>>" + appointmentName + "<<< is present", dayViewPage.isThereAnAppointmentWithName(appointmentName));
    }

    // Launches browser and setup initial settings. Run before each test.
    @Before
    public void startTest() {
        testManager.startBrowser(Browser.CHROME);
//        testManager.startBrowser(Browser.FIREFOX);
        testManager.navigate(SINGLE_PLANNING_CALENDAR_ADDRESS);
    }

    // Closes browse browser. Run after each test.
    @After
    public void endTest() {
        testManager.closeBrowser();
    }
}