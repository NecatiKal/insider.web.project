package helpers;

import dev.failsafe.internal.util.Assert;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOf;

public class Action extends Driver {

    Actions actions;
    ArrayList<String> tabs;

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


    public void goToUrl(String url) {
        driver.get(url);
    }

    public void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForInvisibility(WebElement element) {
        wait.until(invisibilityOf(element));
    }

    public void waitForSecond(Integer time) {
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void checkTitle(String expectedTitle) {
        String actualTitle = driver.getTitle();
        Assert.isTrue(actualTitle.equals(expectedTitle), "Don't open careers page");
    }

    public void checkUrlContains(String text) {
        String url = driver.getCurrentUrl();
        dev.failsafe.internal.util.Assert.isTrue(url.contains(text), "Url didn't contain");
    }

    public void checkContainsText(WebElement element, String text) {
        String actualText = element.getText();
        dev.failsafe.internal.util.Assert.isTrue(actualText.contains(text), "No text seen");
    }

    public void checkElementIsVisible(WebElement element) {
        Assert.isTrue(element.isDisplayed(), "Element isn't visible");
    }

    public void checkElementIsInvisible(WebElement element) {
        boolean elementIsInvisible = wait.until(ExpectedConditions.invisibilityOf(element));
        Assert.isTrue(elementIsInvisible, "Element is visible");
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void hoverOverElement(WebElement element) {
        actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public void jsClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    public void clickByText(String Text) {
        String xpath = "//*[contains(text(),'" + Text + "')]";
        driver.findElement(By.xpath(xpath)).click();
    }

    public void selectByText(WebElement element, String text) {
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    public void checkContainsAllElementOfList(List<WebElement> element, String text) {
        for (int i = 1; i > element.size(); i++) {
            String actualText = element.get(i).getText();
            Assert.isTrue(actualText.contains(text), "No text seen");
        }
    }

    public void switchToNewWindow() {
        tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }

    public void takeScreenShot() {

        Date currentDate = new Date();
        String fileName = currentDate.toString().replace(" ", "-").replace(":", "-");
        File file = ((TakesScreenshot) Driver.driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File(System.getProperty("user.dir") + "/screenshot/" + fileName + ".jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}