package com.dilly3.multipurposedrive.services;

import com.dilly3.multipurposedrive.mapper.UsersMapper;
import com.dilly3.multipurposedrive.model.IUser;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IUserService {

    private final UsersMapper usersMapper;


    public IUserService(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }

    public boolean usernameExist(String username){
       var user =  usersMapper.getUser(username);

        return user != null;
    }
    public IUser getUserByUsername(String username){
        return usersMapper.getUser(username);
    }
    public void deleteUserByUsername(String username){
        usersMapper.deleteUser(username);
    }
    public Long saveUser(IUser user){
        return usersMapper.save(user);
    }


}

