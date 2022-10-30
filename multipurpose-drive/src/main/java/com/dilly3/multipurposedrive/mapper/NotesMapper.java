package com.dilly3.multipurposedrive.mapper;

import com.dilly3.multipurposedrive.model.Notes;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper @Repository
public interface NotesMapper {
    @Select("SELECT  * FROM NOTES WHERE noteId = #{noteId}")
    Notes getNoteById(int noteId);
    @Select("SELECT  * FROM NOTES WHERE userId = #{userId}")
    List<Notes> getNotesByUserId(int userId);
    @Update("UPDATE NOTES SET noteTitle = #{noteTitle}, noteDescription = #{noteDescription} WHERE noteId = #{noteId}")
    void updateNoteByNoteId(Notes note);
    @Delete("DELETE FROM NOTES WHERE noteId = #{noteId}")
    void deleteNote(int noteId);
    @Insert("INSERT INTO NOTES(noteTitle,noteDescription,userId) VALUES(#{noteTitle},#{noteDescription},#{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int save(Notes note);
}
