package myprojects.automation.assignment4;

import myprojects.automation.assignment4.pages.AdminLoginPage;
import myprojects.automation.assignment4.pages.MainAdminPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Contains main script actions that may be used in scripts.
 */
public class GeneralActions {

    private WebDriver driver;
    private WebDriverWait wait;

    public GeneralActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
    }

    /**
     * Logs in to Admin Panel.
     * @param login
     * @param password
     */
    public void login(String login, String password) {
        AdminLoginPage adminLoginPage = new AdminLoginPage(driver);
        adminLoginPage.open();
        adminLoginPage.fillEmailInput(login);
        adminLoginPage.fillPasswordInput(password);
        adminLoginPage.clickLoginButton();
        MainAdminPage mainAdminPage = new MainAdminPage(driver);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(mainAdminPage.getImgLogo())));
    }


    /**
     * Waits until page loader disappears from the page
     */
    public void waitForContentLoad() {
        // TODO implement generic method to wait until page content is loaded

        // wait.until(...);
        // ...
    }
}
