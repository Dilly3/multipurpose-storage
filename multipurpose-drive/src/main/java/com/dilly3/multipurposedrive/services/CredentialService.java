package com.dilly3.multipurposedrive.services;


import com.dilly3.multipurposedrive.dto.CredentialsDto;
import com.dilly3.multipurposedrive.mapper.CredentialsMapper;
import com.dilly3.multipurposedrive.model.Credentials;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import static com.dilly3.multipurposedrive.services.ColorConstants.LOG_ERROR;
import static com.dilly3.multipurposedrive.services.ColorConstants.LOG_INFO;


@Service
public class CredentialService {
    private final EncryptionService encryptionService;
    private final CredentialsMapper credentialsMapper;
    private final Logger LOGGER;

    public CredentialService(EncryptionService encryptionService,
                             CredentialsMapper credentialsMapper,
                             Logger LOGGER) {
        this.encryptionService = encryptionService;
        this.credentialsMapper = credentialsMapper;
        this.LOGGER = LOGGER;
    }

    public  String saveCredential(String credentialId,
                                 String url,
                                 String username,
                                 String password,
                                 int userId) {
        String message;
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[10];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        Credentials credentials = new Credentials();
        try{
            if (credentialId.equals("")) {
                credentials.setCredKey(encodedKey);
                credentials.setUrl(url);
                credentials.setUsername(username);
                credentials.setPassword(encryptionService.encryptValue(password, encodedKey));
                credentials.setUserId(userId);
                LOGGER.info(String.format(LOG_INFO, "Credential saved"));
                credentialsMapper.save(credentials);
                message =  "save successful";

            } else {
             var cred =  credentialsMapper.getCredentialById(Integer.parseInt(credentialId));
             var credKey = cred.getCredKey();
                credentials.setUrl(url);
                credentials.setUsername(username);
                credentials.setPassword(encryptionService.encryptValue(password,credKey));
                credentials.setCredKey(credKey);
                credentials.setCredentialId(Integer.parseInt(credentialId));
                LOGGER.info(String.format(LOG_INFO, "Credential updated"));
                credentialsMapper.updateCredentialByCredentialId(credentials);
                message =  "update successful";
            }
        }catch (Exception e){
            LOGGER.error(String.format(LOG_ERROR,e.getMessage()));
            message = e.getMessage();
        }
   return message;
    }

    public String delete(int credentialId){
        String message;
        try{
        credentialsMapper.deleteCredential(credentialId);
            message = "delete successful";
        }catch(Exception e){
            LOGGER.error(String.format(LOG_ERROR,e.getMessage()));
            message = e.getMessage();
        }
        LOGGER.info(String.format(LOG_ERROR,message));
        return message;
    }

    public List<CredentialsDto> unpackCredentials(int userId){
        List<Credentials> credentials = credentialsMapper.getCredentialsByUserId(userId).stream().sorted().collect(Collectors.toList());
        List<CredentialsDto> unpackedCred = new ArrayList<>();
        for (Credentials cred : credentials ){
            CredentialsDto newCredentialsDto = new CredentialsDto();
            newCredentialsDto.setCredentialId(cred.getCredentialId());
            newCredentialsDto.setUrl(cred.getUrl());
            newCredentialsDto.setUsername(cred.getUsername());
            newCredentialsDto.setEncryptedPassword(cred.getPassword());
            newCredentialsDto.setDecryptedPassword(encryptionService.decryptValue(cred.getPassword(), cred.getCredKey()));
            unpackedCred.add(newCredentialsDto);
        }
        return unpackedCred;
    }
}