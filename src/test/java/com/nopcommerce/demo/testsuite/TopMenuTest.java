package com.nopcommerce.demo.testsuite;

import com.nopcommerce.demo.customlisteners.CustomListeners;
import com.nopcommerce.demo.pages.TopMenu;
import com.nopcommerce.demo.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(CustomListeners.class)
public class TopMenuTest extends BaseTest {
    TopMenu topMenu;
    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        topMenu =new TopMenu();
    }
    @Test(groups = {"sanity","regression"})
    public void verifyPageNavigation(){
        topMenu.selectMenu("Gift Cards");
        Assert.assertEquals(topMenu.getHeadingText(),"Gift Cards","User is not navigated successfully");
    }
}
