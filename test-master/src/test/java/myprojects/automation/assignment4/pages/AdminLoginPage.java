package myprojects.automation.assignment4.pages;
import lombok.Getter;
import myprojects.automation.assignment4.utils.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * For login page.
 */
public class AdminLoginPage {

    private WebDriver webDriver;

    private By emailInput = By.id("email");
    private By passInput = By.id("passwd");
    private By loginBtn = By.name("submitLogin");

    @Getter
    private By logo = By.id("header_logo");

    public AdminLoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void open() {
        webDriver.get(Properties.getBaseAdminUrl());
    }

    public void fillEmailInput(String email) {
        webDriver.findElement(emailInput).sendKeys(email);
    }

    public void fillPasswordInput(String password) {
        webDriver.findElement(passInput).sendKeys(password);
    }

    public void clickLoginButton() {
        webDriver.findElement(loginBtn).click();
    }
}
