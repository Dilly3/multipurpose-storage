package com.dilly3.multipurposedrive;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FilePage {

    @FindBy(id ="nav-files-tab")
    private WebElement filesTab;
    private final WebDriverWait wait;
    public FilePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver,3);
    }

    public WebElement getFilesTab() {
        return filesTab;
    }

    public void setFilesTab(WebElement filesTab) {
        this.filesTab = filesTab;
    }
}
