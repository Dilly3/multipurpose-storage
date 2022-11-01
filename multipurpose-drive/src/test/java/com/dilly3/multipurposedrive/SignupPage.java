package com.dilly3.multipurposedrive;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {
    @FindBy(id = "inputFirstName")
    private WebElement firstname;
    @FindBy(id = "inputLastName")
    private WebElement lastname;
    @FindBy(id = "inputUsername")
    private WebElement username;

    @FindBy(id = "inputPassword")
    private WebElement password;
    @FindBy(id = "buttonSignUp")
    private WebElement signupButton;

    public SignupPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement getFirstname() {
        return firstname;
    }

    public void setFirstname(WebElement firstname) {
        this.firstname = firstname;
    }

    public WebElement getLastname() {
        return lastname;
    }

    public void setLastname(WebElement lastname) {
        this.lastname = lastname;
    }

    public WebElement getUsername() {
        return username;
    }

    public void setUsername(WebElement username) {
        this.username = username;
    }

    public WebElement getPassword() {
        return password;
    }

    public void setPassword(WebElement password) {
        this.password = password;
    }

    public WebElement getSignupButton() {
        return signupButton;
    }

    public void setSignupButton(WebElement signupButton) {
        this.signupButton = signupButton;
    }

    public void testSignUp() throws InterruptedException {
     firstname.sendKeys("mike");
        lastname.sendKeys("olisa");
        username.sendKeys("isa1238");
        password.sendKeys("0000");
        Thread.sleep(2000);
        signupButton.click();
    }
}
