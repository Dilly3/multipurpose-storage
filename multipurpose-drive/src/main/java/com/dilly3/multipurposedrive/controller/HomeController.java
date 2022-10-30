package com.dilly3.multipurposedrive.controller;


import com.dilly3.multipurposedrive.dto.LoginDto;
import com.dilly3.multipurposedrive.mapper.CredentialsMapper;
import com.dilly3.multipurposedrive.mapper.FilesMapper;
import com.dilly3.multipurposedrive.mapper.NotesMapper;
import com.dilly3.multipurposedrive.mapper.UsersMapper;
import com.dilly3.multipurposedrive.model.Files;
import com.dilly3.multipurposedrive.model.IUser;
import com.dilly3.multipurposedrive.model.Notes;
import com.dilly3.multipurposedrive.security.AuthenticationService;
import com.dilly3.multipurposedrive.services.CredentialService;
import com.dilly3.multipurposedrive.services.HashService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    private final AuthenticationService authenticationService;
    private final UsersMapper usersMapper;
    private final HashService hashService;
    private final FilesMapper filesMapper;
    private final NotesMapper notesMapper;
    private final CredentialService credentialService;

    @Autowired public HomeController(AuthenticationService authenticationService, UsersMapper usersMapper,
                                     HashService hashService, FilesMapper filesMapper,
                                     NotesMapper notesMapper,
                                     CredentialsMapper credentialsMapper, Logger LOGGER,
                                     CredentialService credentialService) {
        this.authenticationService = authenticationService;
        this.usersMapper = usersMapper;
        this.hashService = hashService;
        this.filesMapper = filesMapper;
        this.notesMapper = notesMapper;
        this.credentialService = credentialService;
    }

    @GetMapping(value = {"/home","/"})
    public String getHomePage() {
        return "home";
    }
    @GetMapping("/logout")
    public String logout() {
        SecurityContextHolder.clearContext();
        return "/home";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model, LoginDto login) {

        model.addAttribute("Login", login);
        return "login";
    }
@GetMapping("/login-fail")
public String LoginErrorPage(Model model, LoginDto login) {
    model.addAttribute("Login", login);
    model.addAttribute("message","error");
    return "loginError";
}

    @PostMapping("/login")
    public void postLoginInfo(Model model,LoginDto loginDto, RedirectAttributes ra,
                                @RequestParam String username, @RequestParam String password) {
        var authentication = authenticationService.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
    }
    @GetMapping("/dashboard")
    public String getDashboard(Model model, Principal principal){
       IUser iuser = null;
        if(authenticationService.isUserLoggedIn()){
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            iuser = usersMapper.getUser(username);
        }
        assert iuser != null;
        List<Files> files = filesMapper.getFilesByuserId(iuser.getUserId());
        List<Notes> notes = notesMapper.getNotesByUserId(iuser.getUserId());

        var unpackedCred = credentialService.unpackCredentials(iuser.getUserId());
        model.addAttribute("files", files);
        model.addAttribute("notes", notes);
        model.addAttribute("credentials", unpackedCred);
        model.addAttribute("username",iuser.getUsername());
        return "dashboard";
    }

    @PostMapping("/signup")
    public String postSignupPage(Model model, IUser user,
                                 @RequestParam String username,
                                 @RequestParam String firstname,
                                 @RequestParam String lastname ,
                                 @RequestParam String password ) {
        var encodedSalt = hashService.getEncodedSalt();
        var hashedPassword = hashService.getHashedValue(password,encodedSalt);
        usersMapper.save(new IUser(username,firstname,lastname,hashedPassword,encodedSalt));

model.addAttribute("message", "signup successful");
model.addAttribute("Users",user);
        return "/result";
    }

    @GetMapping("/signup")
    public String getSignupPage(Model model, IUser user) {
        model.addAttribute("Users",user);
        return "signup";
    }
}
