package com.dilly3.multipurposedrive;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NotesPage {
  @FindBy(id = "nav-notes-tab")
    private WebElement notesTab;

  @FindBy(id = "note-title")
    private WebElement noteTitle;

    @FindBy(id = "add-new-note")
    private WebElement addNewNoteButton;

    @FindBy(id = "note-description")
    private WebElement noteDescription;

    @FindBy(id = "note-update-btn")
    private WebElement updateNoteButton;
    @FindBy(id = "successSave-Link")
    private WebElement noteSaveSuccessBackToHome;
    @FindBy(id ="successSave")
    public WebElement successAlert;

    public NotesPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
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

    public String testCreateNewNote(String ntTitle, String ntDescription) throws InterruptedException {
        // Create a new note
       addNewNoteButton.click();
        Thread.sleep(1000);
        noteTitle.sendKeys(ntTitle);
        noteDescription.sendKeys(ntDescription);
        updateNoteButton.click();
        Thread.sleep(1000);
        return successAlert.getText();

    }
}
