package myprojects.automation.assignment4.pages;

import myprojects.automation.assignment4.utils.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ProductPage {

    private WebDriver webDriver;

    private By catalogMenu = By.id("subtab-AdminCatalog");
    private By catalogItem = By.xpath("/html/body/nav/ul/li[4]/a/span");
    private By productItem = By.id("subtab-AdminProducts");
    private By headerProducts = By.xpath("//*[@id=\"main-div\"]/div[1]/h2");
    private By newProductBtn = By.id("page-header-desc-configuration-add");
    private By nameProduct = By.id("form_step1_name_1");
    private By qnyProduct = By.id("form_step1_qty_0_shortcut");
    private By priceProduct = By.id("form_step1_price_shortcut");
    private By toggleSwitch = By.className("switch-input ");
    private By createAndSaveBtn = By.className("js-btn-save");
    private By alertMessage = By.cssSelector("#growls > .growl");
    private By alertSuccess = By.cssSelector("#growls > .growl-notice");
    private By alertSuccessClose = By.cssSelector("#growls > .growl > .growl-close");

    public void open() {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        webDriver.get(Properties.getBaseAdminUrl());
        wait.until(ExpectedConditions.visibilityOfElementLocated(catalogMenu));
    }

    public ProductPage(WebDriver driver) {
        this.webDriver = driver;
    }

    public void clickProductSubmenu() {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        WebElement catalogElement = webDriver.findElement(catalogMenu);
        Actions actions = new Actions(webDriver);
        actions.moveToElement(catalogElement).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(productItem));
        webDriver.findElement(productItem).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(newProductBtn));
    }

    public void openNewProductPageByClick() {
        webDriver.findElement(newProductBtn).click();
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameProduct));
    }

    public void fillNameOfProduct(String name) {
        webDriver.findElement(nameProduct).sendKeys(name);
    }

    public void fillNumberOfProduct(int number) {
        webDriver.findElement(qnyProduct).sendKeys(String.valueOf(number));
    }

    public void fillPriceOfProduct(String price) {
        WebElement priceInput = webDriver.findElement(priceProduct);
        priceInput.clear();
        priceInput.sendKeys(price);
    }

    public void toggleSwitcher() {
        webDriver.findElement(toggleSwitch).click();
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(alertMessage));
    }

    public void saveButton() {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        webDriver.findElement(createAndSaveBtn).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(alertMessage));
    }

    public void closeAlert() {
        boolean staleElement = true;
        int tryCount = 0;
        while(staleElement && tryCount < 3) {
            try {
                webDriver.findElement(alertSuccessClose).click();
                staleElement = false;
            } catch (StaleElementReferenceException e) {
                tryCount++;
                staleElement = true;
            }
        }
    }

    public boolean isDisplayedHeaderProducts() {
        return webDriver.findElement(headerProducts).isDisplayed();
    }

    public boolean isSuccsessAlertDisplayed() {
        return webDriver.findElement(alertSuccess).isDisplayed();
    }

    public void allProdPageIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(catalogItem));
        webDriver.findElement(catalogItem);
        Actions actions = new Actions(webDriver);
        actions.moveToElement((WebElement) catalogItem).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(productItem));
        webDriver.findElement(productItem).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(headerProducts));
        boolean isDisplayed = webDriver.findElement(headerProducts).isDisplayed();
        Assert.assertTrue(isDisplayed, "Products header is not displayed");
    }

    public void newProdPageIsDisplayed () {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(newProductBtn));
        webDriver.findElement(newProductBtn).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameProduct));
        boolean isDisplayed = webDriver.findElement(nameProduct).isDisplayed();
        Assert.assertTrue(isDisplayed, "Input for product title is not displayed");
    }

public void newProdIsCreated () {
//    ProductData generate = ProductData.generate();
//    WebDriverWait wait = new WebDriverWait(webDriver, 10);
//    webDriver.findElement(nameProduct).sendKeys(generate.getName());
//    webDriver.findElement(qnyProduct).sendKeys(generate.getQty());
//    webDriver.findElement(priceProduct).sendKeys(generate.getPrice());
//    webDriver.findElement(toggleSwitch).click();
//    wait.until(ExpectedConditions.alertIsPresent(alertSuccess));
//    Actions actions = new Actions(webDriver);
////я не знаю как закрыть попап
//
//// actions.moveToElement((WebElement) ).build().perform();
//    webDriver.findElement().click();
////я не знаю как писать асерт

    }
}

////
//Часть А. Создание продукта:
//        1. Войти в Админ Панель.
//        2. Выбрать пункт меню Каталог -> повары и дождаться загрузки страницы продуктов.
//        3. Нажать «Новый товар» для перехода к созданию нового продукта, дождаться загрузки страницы.
//        4. Заполнить следующие свойства нового продукта: Название продукта, Количество, Цена.
//        Свойства продукта должны генерироваться случайно (случайное название продукта, количество от 1 до 100, цена от 0.1 ₴ до 100 ₴).
//        5. После заполнения полей активировать продукт используя переключатель
//        на нижней плавающей панели. После активации продукта дождаться всплывающего уведомления
//        о сохранении настроек и закрыть его.
//        6. Сохранить продукт нажав на кнопку «Сохранить». Дождаться всплывающего
//        уведомления о сохранении настроек и закрыть его.