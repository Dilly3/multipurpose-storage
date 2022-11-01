package com.dilly3.multipurposedrive;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private final WebDriverWait wait;
    @FindBy(id="inputUsername")
    private WebElement usernameInput;

    @FindBy(id = "inputPassword")
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement loginButton;
 @FindBy(id = "loginLink")
 private WebElement loginLink;
    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver,3);
    }

    public WebElement getUsernameInput() {
        return usernameInput;
    }

    public void setUsernameInput(WebElement usernameInput) {
        this.usernameInput = usernameInput;
    }

    public WebElement getPassword() {
        return password;
    }

    public void setPassword(WebElement password) {
        this.password = password;
    }

    public WebElement getLoginButton() {
        return loginButton;
    }

    public void setLoginButton(WebElement loginButton) {
        this.loginButton = loginButton;
    }

    public WebElement getLoginLink() {
        return loginLink;
    }

    public void setLoginLink(WebElement loginLink) {
        this.loginLink = loginLink;
    }
    public void testLogin(String usrname, String psswrd) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(usernameInput)).sendKeys(usrname);
        wait.until(ExpectedConditions.elementToBeClickable(password)).sendKeys(psswrd);

        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public void Login(String usrname, String psswrd) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(usernameInput)).sendKeys(usrname);
        wait.until(ExpectedConditions.elementToBeClickable(password)).sendKeys(psswrd);

        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();

    }
}
