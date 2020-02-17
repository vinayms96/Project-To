package com.thrive.regression;

import com.thrive.browserSetup.ProjectSetup;
import com.thrive.logger.LoggerConfig;
import com.thrive.modules.Action;
import com.thrive.pageModels.listing_page;
import com.thrive.pageModels.mini_cart;
import com.thrive.pageModels.product_page;
import com.thrive.reportSetup.ExtentReports;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductPage extends ProjectSetup {

    @Test(description = "Add the product to cart and verify", groups = {"product.cart"})
    public void add_to_cart() {
        ExtentReports.setExtentTest("Add to Cart");
        LoggerConfig.setLogger(getClass().getName());

        product_page prod = new product_page(driver);
        listing_page list = new listing_page(driver);
        mini_cart mini = new mini_cart(driver);

        list.landing_page("Marketplace");
        list.listing_page("Apparel");

        String list_prod_name = list.rand_prod();
        Assert.assertEquals(list_prod_name, prod.get_prod_name());

        LoggerConfig.getLogger().info("Product Page is opened");
        ExtentReports.getChildTest().pass("Product Page is opened");

        prod.clk_swatches();
        prod.cart_confirmation();
        mini.clk_mini_cart();
        mini.verify_prods();
    }

}
