package myprojects.automation.assignment4.tests;

import myprojects.automation.assignment4.BaseTest;
import myprojects.automation.assignment4.model.ProductData;
import myprojects.automation.assignment4.pages.MainClientPage;
import myprojects.automation.assignment4.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateProductTest extends BaseTest {

    private ProductData productData;

    @DataProvider
    public Object[][] getLoginData() {
        return new String[][]{
                {"webinar.test@gmail.com", "Xcg7299bnSmMuRLp9ITw"}
        };
    }

    @Test(dataProvider = "getLoginData")
    public void createNewProduct(String login, String password) {
        actions.login(login, password);
        productData = ProductData.generate();
        ProductPage productPage = new ProductPage(driver);
        productPage.open();
        productPage.clickProductSubmenu();
        Assert.assertTrue(productPage.isDisplayedHeaderProducts(), "Products header is not displayed");
        productPage.openNewProductPageByClick();
        productPage.fillNameOfProduct(productData.getName());
        productPage.fillNumberOfProduct(productData.getQty());
        productPage.fillPriceOfProduct(productData.getPrice());
        productPage.toggleSwitcher();
        Assert.assertTrue(productPage.isSuccsessAlertDisplayed(), "Failed toggle.");
        productPage.closeAlert();
        productPage.saveButton();
        Assert.assertTrue(productPage.isSuccsessAlertDisplayed(), "Failed to save");
        productPage.closeAlert();
    }

    @Test(dependsOnMethods = "createNewProduct")
    public void checkCreatedProduct() {
        MainClientPage mainClientPage = new MainClientPage(driver);
        mainClientPage.open();
        mainClientPage.clickByLinkAllProd();
        mainClientPage.search(productData);
        Assert.assertTrue(mainClientPage.isPresentProduct(productData), "Product is not present on page");
        mainClientPage.clickOnProduct(productData);
        Assert.assertEquals(productData.getName(), mainClientPage.getProductName(), "Product name are differents");
        Assert.assertTrue(mainClientPage.containProductQty(productData.getQty()), "Product number are differents");
        Assert.assertTrue(mainClientPage.containsProductPrice(productData.getPrice()), "Product price are differents");
    }
}
