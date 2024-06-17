import helpers.Driver;
import helpers.Listener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.CareersPage;
import pages.HomePage;
import pages.JobPage;

public class InsiderTest {
    Listener listener;
    HomePage homePage;
    CareersPage careersPage;
    JobPage jobPage;

    @BeforeTest
    public void setUp() {
        Driver.getDriver();
        homePage=new HomePage();
        careersPage=new CareersPage();
        jobPage=new JobPage();
        listener = new Listener();
    }

    @Test
    public void testInsider() {

        homePage.goToHomePage();
        homePage.checkHomePageOpened();
        homePage.acceptCookies();
        homePage.clickCompanyMenuButton();
        homePage.checkDropdownMenuOpened();
        homePage.clickCareersButton();
        homePage.checkDropdownMenuClosed();

        careersPage.checkGoToCareerPage();
        careersPage.checkOurLocationsSectionIsPresent();
        careersPage.checkFindYourCallingSectionIsPresent();
        careersPage.clickSeeAllTeamsButton();
        careersPage.checkSeeAllTeamsOpened();
        careersPage.checkLifeAtInsiderSectionIsPresent();

        jobPage.goToJobPage();
        jobPage.checkJobPageOpened();
        jobPage.clickSeeAllQAJobs();
        jobPage.selectLocation();
        jobPage.checkLocationSelected();
        jobPage.selectDepartment();
        jobPage.checkDepartmentSelected();
        jobPage.checkJobListIsPresent();
        jobPage.checkAllPositionTitle();
        jobPage.checkAllPositionLocation();
        jobPage.checkAllPositionDepartment();
        jobPage.clickFirstViewRoleButton();
        jobPage.checkApplicationFormOpenedRedirectly();
    }

    @AfterTest
    public void driverDown() {
        Driver.tearDown();
    }

    @AfterMethod
    public void screenshotCapture(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            listener.onTestFailure(result);
        }
    }
}