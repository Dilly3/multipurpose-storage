package com.dilly3.multipurposedrive.mapper;

import com.dilly3.multipurposedrive.model.Files;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FilesMapper {
    @Select("SELECT  * FROM FILES WHERE fileId = #{fileId}")
    Files getFileById(int fileId);
    @Select("SELECT  * FROM FILES WHERE userId = #{userId}")
    List<Files> getFilesByuserId(int userId);
    @Insert("INSERT INTO FILES(filename,contentType,fileSize,userId,fileData) VALUES(#{filename},#{contentType},#{fileSize},#{userId},#{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int save(Files file);

    @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
    void deleteFile(int fileId);
}
