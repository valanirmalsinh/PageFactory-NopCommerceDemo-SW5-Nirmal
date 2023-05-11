package com.nopcommerce.demo.testsuite;

import com.nopcommerce.demo.customlisteners.CustomListeners;
import com.nopcommerce.demo.pages.*;
import com.nopcommerce.demo.testbase.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.UUID;

@Listeners(CustomListeners.class)
public class ElectronicTest extends BaseTest {
    TopMenu topMenu;
    ComputerPage computerPage;
    ShoppingCartPage shoppingCartPage;
    BillingPage billingPage;
    RegisterPage registerPage;
    SoftAssert softAssert;
    ElectronicPage electronicPage;
    BuildYourComputerPage buildYourComputerPage;
    LoginPage loginPage;

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        topMenu=new TopMenu();
        computerPage=new ComputerPage();
        shoppingCartPage=new ShoppingCartPage();
        billingPage=new BillingPage();
        registerPage = new RegisterPage();
        softAssert = new SoftAssert();
        electronicPage = new ElectronicPage();
        buildYourComputerPage=new BuildYourComputerPage();
        loginPage = new LoginPage();
    }
    @Test(groups = {"sanity","regression"})
    public void verifyUserShouldNavigateToCellPhonesPageSuccessfully() throws InterruptedException {
        topMenu.mouseHoverOnElectronic();
        topMenu.selectMenu("Cell phones");
        Thread.sleep(1000);
        softAssert(topMenu.getHeadingText(),"Cell phones","Navigation is not successful");
    }
    @Test(groups = {"smoke","regression"})
    public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfully() throws InterruptedException {
        topMenu.mouseHoverOnElectronic();
        topMenu.selectMenu("Cell phones");
        Thread.sleep(1000);
        softAssert(topMenu.getHeadingText(),"Cell phones","Navigation is not successful");
        electronicPage.selectListView();
        Thread.sleep(2000);
        electronicPage.selectPhone();
        softAssert(electronicPage.getHeading(),"Nokia Lumia 1020","navigation not successful");
        softAssert(electronicPage.getPrice(),"$349.00","Price is not correct");
        electronicPage.changeQuantity("2");
        Thread.sleep(1000);
        buildYourComputerPage.addToCart();
        Thread.sleep(1000);
        softAssert(buildYourComputerPage.getSuccessMessage(),"The product has been added to your shopping cart","Product is not added successfully");
        buildYourComputerPage.closeMessage();
        Thread.sleep(500);
        buildYourComputerPage.mouseHoverOnShoppingCart();
        buildYourComputerPage.clickOnGoToCart();
        softAssert(topMenu.getHeadingText(),"Shopping cart","Shopping cart not displayed successfully");
        Thread.sleep(2000);
        softAssert(electronicPage.getQuantity(),"2","Quantity is not correct");
        softAssert(shoppingCartPage.getPrice(),"$698.00","Price is not updated");
        shoppingCartPage.termsAndCondition();
        shoppingCartPage.goCheckout();
        Thread.sleep(1000);
        softAssert(topMenu.getHeadingText(),"Welcome, Please Sign In!","Checkout navigation not succeed");
        registerPage.clickOnRegister();
        softAssert(topMenu.getHeadingText(),"Register","Navigation not successful");
        registerPage.enterFirstname("Maitri");
        registerPage.enterLastname("Borad");
        String email= "random-" + UUID.randomUUID().toString() + "@example.com";
        registerPage.enterEmail(email);
        registerPage.enterPassword("Maitri@123");
        registerPage.enterConfirmPassword("Maitri@123");
        registerPage.clickOnRegister();
        softAssert(registerPage.getRegisterMessage(),"Your registration completed","Registration is not done");
        registerPage.clickOnContinue();
        softAssert(topMenu.getHeadingText(),"Shopping cart","Shopping cart not displayed successfully");
        registerPage.clickOnLoginLink();
        loginPage.enterEmailId(email);
        loginPage.enterPassword("Maitri@123");
        loginPage.clickOnLoginButton();
        shoppingCartPage.termsAndCondition();
        shoppingCartPage.goCheckout();
        billingPage.selectCountry("United Kingdom");
        billingPage.enterCity("London");
        billingPage.enterAddress("23, London road");
        billingPage.enterPostcode("UB123N");
        billingPage.enterPhoneNumber("7896541230");
        billingPage.clickContinue();
        billingPage.selectShipping2();
        Thread.sleep(1000);
        billingPage.clickOnContinue();
        billingPage.clickCreditCard();
        Thread.sleep(1000);
        billingPage.clickPaymentContinue();
        billingPage.selectPaymentMethod();
        billingPage.selectCard(0);
        billingPage.enterCardHolderName("Prime Testing");
        billingPage.enterCardNumber("5105105105105100");
        billingPage.selectExpiryMonth(1);
        billingPage.selectExpiryYear(2);
        billingPage.enterCVV("769");
        billingPage.clickOnPaymentContinue();
        softAssert(billingPage.getPaymentText(),"Payment Method: Credit Card","Payment method not right");
        softAssert(billingPage.getShippingText(),"Shipping Method: 2nd Day Air","Shipping method not correct");
        softAssert(billingPage.getPriceText(),"$698.00","Price not correct");
        billingPage.clickConfirm();
        Thread.sleep(1000);
        softAssert(billingPage.getHeadText(),"Thank you","Not confirmed");
        softAssert(billingPage.getSuccessText(),"Your order has been successfully processed!","Order Not done Successfully");
        billingPage.clickOnContinueButton();
        softAssert(topMenu.getWelcomeText(),"Welcome to our store","Message not displayed");
        loginPage.clickOnLogout();
        softAssert(driver.getCurrentUrl(),"https://demo.nopcommerce.com/","Log out not successful");
    }
}
