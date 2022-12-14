package com.dilly3.multipurposedrive;

import com.fasterxml.jackson.databind.util.ArrayBuilders;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class NotesPage {
    private final WebDriverWait wait;
  @FindBy(id = "nav-notes-tab")
    private WebElement notesTab;

  @FindBy(id = "note-title")
    private WebElement noteTitle;

    @FindBy(id = "add-new-note")
    private WebElement addNewNoteButton;
    @FindBy(css = "#deleteNoteButton")
    private WebElement deleteNote;
    @FindBy(id = "note-description")
    private WebElement noteDescription;
    @FindBy(css = "#tableBody")
    private WebElement tableBody;
    @FindBy(id = "note-update-btn")
    private WebElement updateNoteButton;
    @FindBy(id = "successSave-Link")
    private WebElement noteSaveSuccessBackToHome;

    @FindBy(css = "#deleteLink")
    private WebElement noteDeleteSuccessBackToHome;
    private final JavascriptExecutor javascriptExecutor;
    @FindBy(id = "updateLink")
    private WebElement noteUpdateSuccessBackToHome;

    @FindBy(id ="successSave")
    public WebElement successAlert;

    public NotesPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver,3);
        javascriptExecutor = (JavascriptExecutor) driver;
    }

    public WebElement getNoteSaveSuccessBackToHome() {
        return noteSaveSuccessBackToHome;
    }

    public void setNoteSaveSuccessBackToHome(WebElement noteSaveSuccessBackToHome) {
        this.noteSaveSuccessBackToHome = noteSaveSuccessBackToHome;
    }

    public WebElement getNotesTab() {
        return notesTab;
    }

    public void setNotesTab(WebElement notesTab) {
        this.notesTab = notesTab;
    }

    public WebElement getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(WebElement noteTitle) {
        this.noteTitle = noteTitle;
    }

    public WebElement getAddNewNoteButton() {
        return addNewNoteButton;
    }

    public void setAddNewNoteButton(WebElement addNewNoteButton) {
        this.addNewNoteButton = addNewNoteButton;
    }

    public WebElement getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(WebElement noteDescription) {
        this.noteDescription = noteDescription;
    }

    public WebElement getUpdateNoteButton() {
        return updateNoteButton;
    }

    public void setUpdateNoteButton(WebElement updateNoteButton) {
        this.updateNoteButton = updateNoteButton;
    }

    public WebElement getSuccessAlert() {
        return successAlert;
    }

    public void setSuccessAlert(WebElement successAlert) {
        this.successAlert = successAlert;
    }

    public String testCreateNewNote(String ntTitle, String ntDescription) {

        // Create a new note
        wait.until(ExpectedConditions.elementToBeClickable(addNewNoteButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(noteTitle)).sendKeys(ntTitle);

        wait.until(ExpectedConditions.elementToBeClickable(noteDescription)).sendKeys(ntDescription);
        wait.until(ExpectedConditions.visibilityOf(updateNoteButton)).click();

        return wait.until(ExpectedConditions.visibilityOf(successAlert)).getText();

    }

    public List<String> testEditNote(String ntTitle, String ntDescription, int num) throws InterruptedException {

        var trx = tableBody.findElements(By.tagName("tr")).get(num);
        var th = trx.findElement(By.tagName("th"));
        var td = trx.findElement(By.className("tdOne"));
        var btn = wait.until(ExpectedConditions.elementToBeClickable(td.findElement(By.tagName("button"))));
        btn.click();

        wait.until(ExpectedConditions.elementToBeClickable(noteTitle)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(noteTitle)).sendKeys(ntTitle);
        wait.until(ExpectedConditions.elementToBeClickable(noteDescription)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(noteDescription)).sendKeys(ntDescription);
        wait.until(ExpectedConditions.visibilityOf(updateNoteButton)).click();
        wait.until(ExpectedConditions.visibilityOf(noteUpdateSuccessBackToHome)).click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(notesTab)).click();

       var  len = tableBody.findElements(By.tagName("tr")).size();
        trx = tableBody.findElements(By.tagName("tr")).get(num);
        var th2 = trx.findElement(By.tagName("th"));
        var td2 = trx.findElement(By.id("tdTwo"));
        Thread.sleep(1000);
        var title = th2.getText();
        var desc = td2.getText();
        List<String> result = new ArrayList<>();
        result.add(title);
        result.add(desc);
        Assertions.assertEquals(ntDescription,result.get(1));
        Assertions.assertEquals(ntTitle,result.get(0));
        return result;

    }

    public void testDeleteNote(int num) throws InterruptedException {
      var Tr =   tableBody.findElements(By.tagName("tr")).get(num);
        var trx = tableBody.findElements(By.tagName("tr")).size();
        var td = Tr.findElement(By.className("tdOne"));
        var form = td.findElement(By.tagName("form"));
        var input = form.findElement(By.id("deleteNoteButton"));
        wait.until(ExpectedConditions.elementToBeClickable(input)).click();
      //  Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(noteDeleteSuccessBackToHome)).click();
      //  Thread.sleep(1000);

       var len =  tableBody.findElements(By.tagName("tr")).size();
       Assertions.assertEquals(trx-1,len);

    }
}
