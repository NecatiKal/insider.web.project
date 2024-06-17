package pages;

import helpers.Action;
import helpers.Util;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends Action {

    Util util = new Util();
    Action action = new Action();
    String baseURL = util.getProperties("homePageUrl");
    String homePageTitle = "#1 Leader in Individualized, Cross-Channel CX — Insider";
    @FindBy(id = "wt-cli-accept-all-btn")
    private WebElement acceptCookiesButton;
    @FindBy(xpath = "//a[contains(text(),'Company')]")
    private WebElement companyMenuButton;
    @FindBy(xpath = "//li[@class='nav-item dropdown show']//div[@aria-labelledby='navbarDropdownMenuLink']")
    private WebElement dropdownMenu;
    @FindBy(xpath = "//a[text()='Careers']")
    private WebElement careersButton;

    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    public void goToHomePage() {
        action.goToUrl(baseURL);
    }

    public void acceptCookies() {
        action.waitForVisibility(acceptCookiesButton);
        acceptCookiesButton.click();
    }

    public void checkHomePageOpened() {
        action.checkTitle(homePageTitle);
    }

    public void clickCompanyMenuButton() {

        action.waitForVisibility(companyMenuButton);
        companyMenuButton.click();
    }

    public void checkDropdownMenuOpened() {
        action.checkElementIsVisible(dropdownMenu);
    }

    public void clickCareersButton() {
        action.waitForVisibility(careersButton);
        careersButton.click();
    }

    public void checkDropdownMenuClosed() {
        action.checkElementIsInvisible(dropdownMenu);
    }

}
