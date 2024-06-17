package pages;

import helpers.Action;
import helpers.Util;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class JobPage extends Action{

    @FindBy(css = "#jobs-list")
    public WebElement jobList;
    @FindBy(xpath = "//p[contains(@class,'position-title')]")
    public List<WebElement> positionTitle;
    @FindBy(xpath = "//span[contains(@class,'position-department')]")
    public List<WebElement> positionDepartment;
    @FindBy(xpath = "//div[contains(@class,'position-location')]")
    public List<WebElement> positionLocation;
    @FindBy(xpath = "(//a[contains(text(),'View Role')])[1]")
    public WebElement firstViewRoleButton;
    @FindBy(xpath = "(//a[contains(text(),'Apply for this job')])[1]")
    public WebElement applyThisJobButton;
    Util util = new Util();
    Action action = new Action();
    String jobPageURL = util.getProperties("careersQAUrl");
    String jobPageTitle = "Insider quality assurance job opportunities";
    String location = "Istanbul, Turkey";
    String department = "Quality Assurance";
    String jobTitle = "Quality Assurance";
    String jobApplicationPageUrl = "jobs.lever.co";
    @FindBy(xpath = "//span[@id='select2-filter-by-location-container']")
    private WebElement locationContainer;
    @FindBy(xpath = "//select[@id='filter-by-location']")
    private WebElement selectObjectLocation;
    @FindBy(xpath = "//span[@id='select2-filter-by-department-container']")
    private WebElement departmentContainer;
    @FindBy(xpath = "//select[@id='filter-by-department']")
    private WebElement selectObjectDepartment;

    public JobPage() {
        PageFactory.initElements(driver, this);
    }

    public void goToJobPage() {
        action.goToUrl(jobPageURL);
    }

    public void checkJobPageOpened() {
        action.checkTitle(jobPageTitle);
    }

    public void clickSeeAllQAJobs() {
        action.clickByText("See all QA jobs");
    }

    public void selectLocation() {
        action.selectByText(selectObjectLocation, location);
    }

    public void checkLocationSelected() {
        action.checkContainsText(locationContainer, location);
    }

    public void selectDepartment() {
        action.selectByText(selectObjectDepartment, department);
    }

    public void checkDepartmentSelected() {
        action.checkContainsText(departmentContainer, department);
    }

    public void checkJobListIsPresent() {
        action.checkElementIsVisible(jobList);
    }

    public void checkAllPositionTitle() {
        action.checkContainsAllElementOfList(positionTitle, jobTitle);
    }

    public void checkAllPositionDepartment() {
        action.checkContainsAllElementOfList(positionDepartment, department);
    }

    public void checkAllPositionLocation() {
        action.checkContainsAllElementOfList(positionLocation, location);
    }

    public void clickFirstViewRoleButton() {
        action.hoverOverElement(firstViewRoleButton);
        action.jsClick(firstViewRoleButton);
    }

    public void checkApplicationFormOpenedRedirectly() {
        action.switchToNewWindow();
        action.checkUrlContains(jobApplicationPageUrl);
        action.checkElementIsVisible(applyThisJobButton);
    }


}