package com.our.testassistantback.Mapper;

import com.our.testassistantback.pojo.Project;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProjectMapper {

    @Select("select * from project where uid = #{uid}")
    List<Project> selectAll(int uid);

    @Select("select * from project where id = #{id}")
    Project detailId(Integer id);

    @Insert("insert into project(name,description,uid) value (#{name},#{description},#{uid})")
    void add(String name, String description,Integer uid);


    void modify(Integer id, int uid, String name, String description);

    @Delete("delete from project where id = #{id}")
    void deleteById(Integer id);
}
