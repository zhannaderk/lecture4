package myprojects.automation.assignment4.pages;

import myprojects.automation.assignment4.model.ProductData;
import myprojects.automation.assignment4.utils.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class MainClientPage {

    private WebDriver webDriver;

    private By allProducts = By.className("all-product-link");
    private By logoImage = By.className("logo");
    private By mainPageBlock = By.xpath("//*[@id=\"main\"]/div[1]/h1");
    private By searchField = By.className("ui-autocomplete-input");
    private By searchResults = By.xpath("//*[@id=\"main\"]/h2");
    private By products = By.id("products");
    private By productName = By.cssSelector("#wrapper > div > nav > ol > li:nth-child(2) > a > span");
    private By productPrice = By.cssSelector("span[itemprop='price']");
    private By productQte = By.cssSelector("#product-details > div.product-quantities > span");

    public void open() {
        webDriver.get(Properties.getBaseUrl());
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(logoImage));
    }

    public MainClientPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void clickByLinkAllProd() {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        webDriver.findElement(allProducts).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(products));
    }

    public void search(ProductData productData) {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        webDriver.findElement(searchField).sendKeys(productData.getName());
        webDriver.findElement(searchField).submit();
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchResults));
    }

    public boolean isPresentProduct(ProductData productData) {
        return webDriver.findElement(products).getText().contains(productData.getName());
    }

    public void clickOnProduct(ProductData productData) {
        webDriver.findElement(By.linkText(productData.getName())).click();
    }

    public String getProductName() {
        return webDriver.findElement(productName).getText();
    }

    public boolean containProductQty(int number) {
        return webDriver.findElement(productQte).getText().contains(String.valueOf(number));
    }

    public boolean containsProductPrice(String price) {
        return webDriver.findElement(productPrice).getText().contains(price);
    }
}

