package com.dilly3.multipurposedrive.services;

import com.dilly3.multipurposedrive.mapper.NotesMapper;
import com.dilly3.multipurposedrive.model.Files;
import com.dilly3.multipurposedrive.model.Notes;
import org.apache.ibatis.annotations.*;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.dilly3.multipurposedrive.services.ColorConstants.LOG_ERROR;
import static com.dilly3.multipurposedrive.services.ColorConstants.LOG_INFO;


@Service
public class NotesService {
    private final NotesMapper notesMapper;
    private final Logger LOGGER;

    public NotesService(NotesMapper notesMapper, Logger LOGGER) {
        this.notesMapper = notesMapper;
        this.LOGGER = LOGGER;
    }

    public String saveNote(String noteId,
                           String noteDescription,
                           String noteTitle,
                           int userId){
        Notes note = new Notes();
        String message;
        try{
            if ( noteId.equals("")){
                note.setNoteDescription(noteDescription);
                note.setNoteTitle(noteTitle);
                note.setUserId(userId);
                notesMapper.save(note);
                message = "save successful";
                LOGGER.info(String.format(LOG_INFO, "Note saved"));

            }else{
                note.setNoteDescription(noteDescription);
                note.setNoteTitle(noteTitle);
                note.setNoteId(Integer.parseInt(noteId));
                notesMapper.updateNoteByNoteId(note);
                message = "update successful";
                LOGGER.info(String.format(LOG_INFO, "Note updated"));
            }
        }catch(Exception e){
            LOGGER.error(String.format(LOG_ERROR,e.getMessage()));
            message =  e.getMessage();
        }
        return message;
    }

    public String deleteNote(int noteId){
        String message;
        try{
            notesMapper.deleteNote(noteId);
            message = "delete successful";

        }catch(Exception e){
LOGGER.error(String.format(LOG_ERROR,e.getMessage()));
message = e.getMessage();
        }
        return message;
    }

    public Notes getNoteByNoteId(int noteId){
        return notesMapper.getNoteById(noteId);
    }

    public List<Notes> getNotesByUserId(int userId){
        return notesMapper.getNotesByUserId(userId);
    }

    public int saveNote(Notes note){
        return notesMapper.save(note);
    }

    public void deleteNoteById(int noteId){
        notesMapper.deleteNote(noteId);
    }

}
