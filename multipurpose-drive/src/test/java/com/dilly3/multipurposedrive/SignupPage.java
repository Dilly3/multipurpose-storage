package com.dilly3.multipurposedrive;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignupPage {
    private final WebDriverWait wait;
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
        wait = new WebDriverWait(driver,4);
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

    public void testSignUp(String fstname,String lstname,String usrname,String psswrd ) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(firstname)).sendKeys(fstname);
        wait.until(ExpectedConditions.elementToBeClickable(lastname)).sendKeys(lstname);
        wait.until(ExpectedConditions.elementToBeClickable(username)).sendKeys(usrname);
        wait.until(ExpectedConditions.elementToBeClickable(password)).sendKeys(psswrd);

        wait.until(ExpectedConditions.elementToBeClickable(signupButton)).click();

    }
}
