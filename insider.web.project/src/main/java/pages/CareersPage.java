package pages;

import dev.failsafe.internal.util.Assert;
import helpers.Action;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CareersPage extends Action{

    @FindBy(xpath = "//div[@class='job-image text-center']")
    public List<WebElement> jobImage;
    Action action = new Action();
    String careersPageTitle = "Ready to disrupt? | Insider Careers";
    Integer defaultJobSize;
    Integer openedJobSize;


    @FindBy(id = "career-our-location")
    private WebElement ourLocationsSection;

    @FindBy(id = "career-find-our-calling")
    private WebElement findYourCallingSection;

    @FindBy(xpath = "//a[text()='See all teams']")
    private WebElement seeAllTeamsButton;
    @FindBy(xpath = "//h2[text()='Life at Insider']//ancestor::section")
    private WebElement lifeAtInsiderSection;

    public CareersPage() {
        PageFactory.initElements(driver, this);
    }

    public void checkGoToCareerPage() {
        action.checkTitle(careersPageTitle);
    }

    public void checkOurLocationsSectionIsPresent() {
        action.scrollToElement(ourLocationsSection);
        action.checkElementIsVisible(ourLocationsSection);
    }

    public void checkFindYourCallingSectionIsPresent() {
        action.scrollToElement(findYourCallingSection);
        action.checkElementIsVisible(findYourCallingSection);
    }

    public void clickSeeAllTeamsButton() {
        action.scrollToElement(seeAllTeamsButton);
        defaultJobSize = jobImage.size();
        action.waitForVisibility(seeAllTeamsButton);
        action.jsClick(seeAllTeamsButton);
    }

    public void checkSeeAllTeamsOpened() {
        action.waitForSecond(3);
        openedJobSize = jobImage.size();
        Assert.isTrue(openedJobSize > defaultJobSize, "All teams don't open");
    }

    public void checkLifeAtInsiderSectionIsPresent() {
        action.scrollToElement(lifeAtInsiderSection);
        action.checkElementIsVisible(lifeAtInsiderSection);
    }
}
