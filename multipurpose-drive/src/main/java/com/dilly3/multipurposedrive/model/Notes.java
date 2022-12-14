package com.dilly3.multipurposedrive.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity public class Notes implements Comparable<Notes> {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer noteId;
    private String noteTitle;
    private String noteDescription;
    private int userId;

    public Notes() {
    }

    public Notes(String noteTitle, String noteDescription, int userId) {
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
        this.userId = userId;
    }

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int compareTo(Notes o) {
        return noteId.compareTo(o.noteId);
    }
}
