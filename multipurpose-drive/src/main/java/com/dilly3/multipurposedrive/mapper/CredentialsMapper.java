package com.dilly3.multipurposedrive.mapper;

import com.dilly3.multipurposedrive.model.Credentials;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper @Repository
public interface CredentialsMapper {
    @Select("SELECT  * FROM CREDENTIALS WHERE credentialId = #{credentialId}")
    Credentials getCredentialById(int CredentialId);
    @Select("SELECT  * FROM CREDENTIALS WHERE userId = #{userId}")
    List<Credentials> getCredentialsByUserId(int userId);
    @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{username}, credKey = #{credKey}, password = #{password} WHERE credentialId = #{credentialId}")
    void updateCredentialByCredentialId(Credentials credentials);
    @Delete("DELETE FROM Credentials WHERE credentialId = #{credentialId}")
    void deleteCredential(int credentialId);
    @Insert("INSERT INTO CREDENTIALS(url,username,credKey,password,userId) VALUES(#{url},#{username},#{credKey},#{password},#{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int save(Credentials credential);
}
