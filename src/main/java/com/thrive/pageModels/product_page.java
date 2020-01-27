package com.thrive.pageModels;

import com.thrive.modules.Action;
import com.thrive.modules.WaitUntil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class product_page {

    @FindBy(xpath = "//h1/span")
    private WebElement prod_name;

    public product_page(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String get_prod_name() {
        return prod_name.getText();
    }

}
