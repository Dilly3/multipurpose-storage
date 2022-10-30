package com.dilly3.multipurposedrive.controller;


import com.dilly3.multipurposedrive.mapper.UsersMapper;
import com.dilly3.multipurposedrive.security.AuthenticationService;
import com.dilly3.multipurposedrive.services.FilesService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping("/file")
public class FileController {

    private final AuthenticationService authenticationService;
    private final FilesService filesService;
    private final UsersMapper usersMapper;


    public FileController(AuthenticationService authenticationService,
                          FilesService filesService,
                          UsersMapper usersMapper) {
        this.authenticationService = authenticationService;
        this.filesService = filesService;
        this.usersMapper = usersMapper;
    }

    @PostMapping("/fileUpload")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile multipartFile, Model model, RedirectAttributes ra, final Principal principal)  {
        if (authenticationService.isUserLoggedIn() && !multipartFile.isEmpty())
            {
                String username = SecurityContextHolder.getContext().getAuthentication().getName();
                var user = usersMapper.getUser(username);
           var message = filesService.uploadFile(multipartFile,user.getUserId());
                ra.addFlashAttribute("message", message);
                return "result";
            }
            model.addAttribute("message", "empty file");
            return "redirect:/dashboard";
        }

    @GetMapping("/delete")
    public String deletedoc(@RequestParam("id") int Id, Model model,RedirectAttributes ra){
      String message =  filesService.deleteDoc(Id);
        ra.addFlashAttribute("message", message);

        return "redirect:/dashboard";
    }

    @GetMapping("/download")
    public void downloadFile(@RequestParam("id") int fileId, Model model,
                             HttpServletResponse response,
                             RedirectAttributes ra)
    {
        if (authenticationService.isUserLoggedIn()) {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            var user = usersMapper.getUser(username);
            var message = filesService.downloadFile(response, fileId, username);
        }
    }
    }
