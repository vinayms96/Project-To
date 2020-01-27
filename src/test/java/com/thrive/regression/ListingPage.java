package com.thrive.regression;

import com.thrive.browserSetup.ProjectSetup;
import com.thrive.logger.LoggerConfig;
import com.thrive.pageModels.listing_page;
import com.thrive.pageModels.product_page;
import com.thrive.reportSetup.ExtentReports;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ListingPage extends ProjectSetup {

    @Test(description = "Open Product page", groups = {"listing.prod"})
    public void open_prod_page() {
        // Setting Extent Test and Logger
        ExtentReports.setExtentTest("Open Product Page");
        LoggerConfig.setLogger(getClass().getName());

        // Page Model References
        listing_page list = new listing_page(driver);
        product_page product = new product_page(driver);

        list.landing_page("Marketplace");
        list.listing_page("Apparel");

        String list_prod_name = list.rand_prod();
        Assert.assertEquals(list_prod_name, product.get_prod_name());

        LoggerConfig.getLogger().info("Product Page is opened");
        ExtentReports.getChildTest().pass("Product Page is opened");

    }

}
