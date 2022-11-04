package com.dilly3.multipurposedrive.services;

import com.dilly3.multipurposedrive.mapper.FilesMapper;
import com.dilly3.multipurposedrive.model.Files;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.dilly3.multipurposedrive.services.ColorConstants.LOG_ERROR;
import static com.dilly3.multipurposedrive.services.ColorConstants.LOG_INFO;


@Service public class FilesService {
    private final FilesMapper filesMapper;
    private final Logger LOGGER;

    @Autowired
    public FilesService(FilesMapper filesMapper, Logger LOGGER) {
        this.filesMapper = filesMapper;
        this.LOGGER = LOGGER;
    }

    public String uploadFile(MultipartFile multipartFile, int userId) {
        String message;
        try {
            String filename = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            var newFile = new Files();
            newFile.setContentType(multipartFile.getContentType());
            newFile.setFileSize(Integer.parseInt(String.valueOf(multipartFile.getSize())));
            newFile.setFileData(Objects.requireNonNull(multipartFile.getBytes()));
            newFile.setUserId(userId);
            newFile.setFilename(filename);

            var Id = saveFile(newFile);
            LOGGER.info(String.format(LOG_INFO, "File saved"));
            message = "upload successful";

        } catch (IOException e) {
            LOGGER.error(String.format(LOG_ERROR, e.getMessage()));
            message = e.getMessage();
        }
        LOGGER.info(String.format(LOG_INFO, "file upload successful"));
        return message;
    }

    public String deleteDoc(int fileId){
        String message = "";
        Files file;
        String filename;
        try{

           file =  getFileById(fileId);
           filename = file.getFilename();
            deleteFile(fileId);

            message = "delete successful";
            LOGGER.info(String.format(LOG_INFO, filename  + " deleted successfully"));

        }catch (Exception e){
            LOGGER.error(String.format(LOG_ERROR, e.getMessage()));
        }

       return message;
    }

    public String downloadFile(HttpServletResponse response, int fileId, String username) {
        String message;
        Files file;
        try{
            file = getFileById(fileId);
            if (Objects.isNull(file)) {
                message = "cannot find file";
            }else {
                ServletOutputStream outputStream = response.getOutputStream();
                var headerValue = "attachment; filename=" + file.getFilename();
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", headerValue);
                outputStream.write(file.getFileData());
                LOGGER.info(String.format(LOG_INFO, file.getFilename() + " downloaded by " + username ));
                message = "download successful";
            }
        }catch(Exception e){
            LOGGER.error(String.format(LOG_ERROR, e.getMessage()));
            message = e.getMessage();
        }

        return message;
    }
    public Files getFileById(int fileId){
       return filesMapper.getFileById(fileId);
    }

   public List<Files> getFilesByUserId(int userId){
        return filesMapper.getFilesByuserId(userId).stream().sorted().collect(Collectors.toList());
    }

    public int saveFile(Files file){
        return filesMapper.save(file);
    }

    public void deleteFile(int fileId){
        filesMapper.deleteFile(fileId);
    }

}