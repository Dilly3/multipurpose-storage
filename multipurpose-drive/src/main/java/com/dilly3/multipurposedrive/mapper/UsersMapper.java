package com.dilly3.multipurposedrive.mapper;

import com.dilly3.multipurposedrive.model.IUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UsersMapper {
    @Select("SELECT  * FROM IUSER WHERE username = #{username}")
    IUser getUser(String username);
    @Delete("DELETE FROM IUSER WHERE username = #{username}")
    void deleteUser(String username);
    @Insert("INSERT INTO IUSER(username,firstname,lastname,password,salt) VALUES(#{username},#{firstname},#{lastname},#{password},#{salt})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    Long save(IUser user);
}
