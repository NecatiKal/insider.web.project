package helpers;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener extends Action implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        takeScreenShot();
    }
}