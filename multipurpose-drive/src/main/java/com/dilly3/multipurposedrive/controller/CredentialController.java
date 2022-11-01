package com.dilly3.multipurposedrive.controller;

import com.dilly3.multipurposedrive.model.Credentials;
import com.dilly3.multipurposedrive.security.AuthenticationService;
import com.dilly3.multipurposedrive.services.CredentialService;
import com.dilly3.multipurposedrive.services.IUserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/credential")
public class CredentialController {

    private final AuthenticationService authenticationService;
    private final CredentialService credentialService;
    private final IUserService iUserService;


    public CredentialController(AuthenticationService authenticationService, CredentialService credentialService,
                                IUserService iUserService) {
        this.authenticationService = authenticationService;
        this.credentialService = credentialService;
        this.iUserService = iUserService;
    }

    private int credentialId ;
    private String url;
    private String username;
    private String credKey;
    private String password;
    private int userId;

    @PostMapping("/save")
    public String saveNote(Model model,@RequestParam("credentialId") String credentialId,@RequestParam("url") String url,
                           @RequestParam("username") String credUsername,
                           @RequestParam("password") String password, RedirectAttributes ra) {
        Credentials credentials = new Credentials();
        if (authenticationService.isUserLoggedIn()) {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            var user = iUserService.getUserByUsername(username);
          var message =  credentialService.saveCredential(credentialId,url,credUsername,password,user.getUserId());
                model.addAttribute("message", message);
                return "result";
            }
       return"redirect:/login";
    }

    @GetMapping("/delete")
    public String deletenote(@RequestParam("Id") int Id, Model model, RedirectAttributes ra) {
       var message =  credentialService.delete(Id);
        ra.addFlashAttribute("message", message);
        return "redirect:/dashboard";
    }
}
