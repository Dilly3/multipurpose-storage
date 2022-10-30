package com.dilly3.multipurposedrive.mapper;

import com.dilly3.multipurposedrive.model.IUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UsersMapper {
    @Select("SELECT  * FROM IUSER WHERE username = #{username}")
    IUser getUser(String username);
    @Insert("INSERT INTO IUSER(username,firstname,lastname,password,salt) VALUES(#{username},#{firstname},#{lastname},#{password},#{salt})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    Long save(IUser user);
}
