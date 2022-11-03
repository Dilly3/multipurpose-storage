package com.dilly3.multipurposedrive;

import com.dilly3.multipurposedrive.services.HashService;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CredentialsPage {
   @Autowired
   private HashService hashService;
    private final WebDriverWait wait;
    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialTab;

    @FindBy(id = "credential-url")
    private WebElement credentialURL;

    @FindBy(id = "addNewCredential")
    private WebElement addNewCredentialButton;
    @FindBy(css = "#deleteNoteButton")
    private WebElement deleteCredential;
    @FindBy(id = "credential-username")
    private WebElement credentialUsername;
    @FindBy(id = "credential-password")
    private WebElement credentialPassword;
    @FindBy(css = "#cred-table-body")
    private WebElement credTableBody;
    @FindBy(id = "cred-update-btn")
    private WebElement updateCredButton;
    @FindBy(id = "successSave-Link")
    private WebElement credentialSaveSuccessBackToHome;

    @FindBy(css = "#deleteLink")
    private WebElement credentialDeleteSuccessBackToHome;
    private final JavascriptExecutor javascriptExecutor;
    @FindBy(id = "updateLink")
    private WebElement credUpdateSuccessBackToHome;

    @FindBy(id ="successSave")
    public WebElement saveSuccessAlert;

    public CredentialsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver,3);
        javascriptExecutor = (JavascriptExecutor) driver;

    }

    public WebElement getCredentialTab() {
        return credentialTab;
    }

    public void setCredentialTab(WebElement credentialTab) {
        this.credentialTab = credentialTab;
    }

    public WebElement getCredentialURL() {
        return credentialURL;
    }

    public void setCredentialURL(WebElement credentialURL) {
        this.credentialURL = credentialURL;
    }

    public WebElement getAddNewCredentialButton() {
        return addNewCredentialButton;
    }

    public void setAddNewCredentialButton(WebElement addNewCredentialButton) {
        this.addNewCredentialButton = addNewCredentialButton;
    }

    public WebElement getDeleteCredential() {
        return deleteCredential;
    }

    public void setDeleteCredential(WebElement deleteCredential) {
        this.deleteCredential = deleteCredential;
    }

    public WebElement getCredentialUsername() {
        return credentialUsername;
    }

    public void setCredentialUsername(WebElement credentialUsername) {
        this.credentialUsername = credentialUsername;
    }

    public WebElement getCredentialPassword() {
        return credentialPassword;
    }

    public void setCredentialPassword(WebElement credentialPassword) {
        this.credentialPassword = credentialPassword;
    }

    public WebElement getCredTableBody() {
        return credTableBody;
    }

    public void setCredTableBody(WebElement credTableBody) {
        this.credTableBody = credTableBody;
    }

    public WebElement getUpdateCredButton() {
        return updateCredButton;
    }

    public void setUpdateCredButton(WebElement updateCredButton) {
        this.updateCredButton = updateCredButton;
    }

    public WebElement getCredentialSaveSuccessBackToHome() {
        return credentialSaveSuccessBackToHome;
    }

    public void setCredentialSaveSuccessBackToHome(WebElement credentialSaveSuccessBackToHome) {
        this.credentialSaveSuccessBackToHome = credentialSaveSuccessBackToHome;
    }

    public WebElement getCredentialDeleteSuccessBackToHome() {
        return credentialDeleteSuccessBackToHome;
    }

    public void setCredentialDeleteSuccessBackToHome(WebElement credentialDeleteSuccessBackToHome) {
        this.credentialDeleteSuccessBackToHome = credentialDeleteSuccessBackToHome;
    }

    public WebElement getCredUpdateSuccessBackToHome() {
        return credUpdateSuccessBackToHome;
    }

    public void setCredUpdateSuccessBackToHome(WebElement credUpdateSuccessBackToHome) {
        this.credUpdateSuccessBackToHome = credUpdateSuccessBackToHome;
    }

    public WebElement getSaveSuccessAlert() {
        return saveSuccessAlert;
    }

    public void setSaveSuccessAlert(WebElement saveSuccessAlert) {
        this.saveSuccessAlert = saveSuccessAlert;
    }

    public String testCreateNewcred(String url, String username, String password) {

        // Create a new note
        wait.until(ExpectedConditions.elementToBeClickable(addNewCredentialButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(credentialURL)).sendKeys(url);

        wait.until(ExpectedConditions.elementToBeClickable(credentialUsername)).sendKeys(username);
        wait.until(ExpectedConditions.elementToBeClickable(credentialPassword)).sendKeys(password);
        wait.until(ExpectedConditions.visibilityOf(updateCredButton)).click();

        return wait.until(ExpectedConditions.visibilityOf(saveSuccessAlert)).getText();

    }

    public List<String> testEditCredential(String url, String username, String password, int num) throws InterruptedException {

        var trx = credTableBody.findElements(By.tagName("tr")).get(num);
        var th = trx.findElement(By.tagName("th"));
        var td = trx.findElement(By.className("tdOne"));
        var btn = wait.until(ExpectedConditions.elementToBeClickable(td.findElement(By.tagName("button"))));
        btn.click();

        wait.until(ExpectedConditions.elementToBeClickable(credentialURL)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(credentialURL)).sendKeys(url);
        wait.until(ExpectedConditions.elementToBeClickable(credentialUsername)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(credentialUsername)).sendKeys(username);
        wait.until(ExpectedConditions.elementToBeClickable(credentialPassword)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(credentialPassword)).sendKeys(password);
        wait.until(ExpectedConditions.visibilityOf(updateCredButton)).click();
        wait.until(ExpectedConditions.visibilityOf(credUpdateSuccessBackToHome)).click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(credentialTab)).click();

        var  len = credTableBody.findElements(By.tagName("tr")).size();
        trx = credTableBody.findElements(By.tagName("tr")).get(num);
        var urlText = trx.findElement(By.id("tdUrl")).getText();
        var usernameText = trx.findElement(By.id("tdUsername")).getText();
        var passwordText = trx.findElement(By.id("tdPassword")).getText();
        Thread.sleep(1000);

        List<String> result = new ArrayList<>();
        result.add(urlText);
        result.add(usernameText);
        result.add(passwordText);
        Assertions.assertNotEquals(password,result.get(2));
        Assertions.assertEquals(url,result.get(0));
        Assertions.assertEquals(username,result.get(1));
        return result;

    }


    public void testDeleteCredential(int num) {
        var Tr =   credTableBody.findElements(By.tagName("tr")).get(num);
        var trx = credTableBody.findElements(By.tagName("tr")).size();
        var td = Tr.findElement(By.className("tdOne"));
        var form = td.findElement(By.tagName("form"));
        var input = form.findElement(By.id("deleteCredentialButton"));
        wait.until(ExpectedConditions.elementToBeClickable(input)).click();
        //  Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(credentialDeleteSuccessBackToHome)).click();
        //  Thread.sleep(1000);

        var len =  credTableBody.findElements(By.tagName("tr")).size();
        Assertions.assertEquals(trx-1,len);

    }
}
