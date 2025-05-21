package com.our.testassistantback.Mapper;

import com.our.testassistantback.pojo.Session;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SessionMapper {

    @Select("select * from session where id = #{id}")
    Session selectById(Integer id);
}
