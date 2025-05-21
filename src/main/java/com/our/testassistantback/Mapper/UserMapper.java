package com.our.testassistantback.Mapper;

import com.our.testassistantback.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface UserMapper {




    @Select("select * from user where email = #{email} and password = #{password}")
    User getEmailAndgetPassword(User user);

    @Insert("insert into user(email,password) value (#{email},#{password})")
    void insert(User user);

    @Delete("delete from user where id = #{id}")
    void delete(Integer id);
}
