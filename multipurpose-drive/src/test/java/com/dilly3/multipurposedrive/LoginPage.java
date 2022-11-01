package com.dilly3.multipurposedrive;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
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
    public void testLogin() throws InterruptedException {
        Thread.sleep(1000);
        usernameInput.sendKeys("isa1238");
        password.sendKeys("0000");
       loginButton.click();
        Thread.sleep(1000);
    }

    public void Login() throws InterruptedException {
        Thread.sleep(1000);
        usernameInput.sendKeys("olisa123");
        password.sendKeys("0000");
        loginButton.click();
        Thread.sleep(1000);
    }
}
