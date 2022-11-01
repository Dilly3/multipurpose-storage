package com.dilly3.multipurposedrive.controller;

import com.dilly3.multipurposedrive.mapper.UsersMapper;
import com.dilly3.multipurposedrive.model.Notes;
import com.dilly3.multipurposedrive.security.AuthenticationService;
import com.dilly3.multipurposedrive.services.IUserService;
import com.dilly3.multipurposedrive.services.NotesService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/note")
public class NotesController {
    private final AuthenticationService authenticationService;
    private final IUserService iUserService;
    private final NotesService notesService;

    @Autowired
    public NotesController(AuthenticationService authenticationService,
                           NotesService notesService,
                           IUserService iUserService, Logger LOGGER) {
        this.authenticationService = authenticationService;
        this.iUserService = iUserService;
        this.notesService = notesService;
    }

    @PostMapping("/save")
    public String saveNote( Model model,@RequestParam("noteTitle") String noteTitle,@RequestParam("noteId") String noteId,
                           @RequestParam("noteDescription") String noteDescription, RedirectAttributes ra) {
        Notes note = new Notes();
        if (authenticationService.isUserLoggedIn()) {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            var user = iUserService.getUserByUsername(username);

      var message =  notesService.saveNote(noteId,noteDescription,noteTitle,user.getUserId());
            model.addAttribute("message", message);
            return "result";
        }
        return "redirect:/login";
    }

    @GetMapping("/delete")
    public String deletenote(@RequestParam("Id") int Id, Model model,RedirectAttributes ra) {
     var message =   notesService.deleteNote(Id);
        ra.addFlashAttribute("message", message);
        return "redirect:/dashboard";
    }

}
