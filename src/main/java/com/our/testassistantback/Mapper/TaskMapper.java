package com.our.testassistantback.Mapper;

import com.our.testassistantback.pojo.Task;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TaskMapper {
    @Select("select * from task where pid = #{pid}")
    Task selectAll(Integer pid);

    @Select("select * from task where id = #{id}")
    Task detailById(Integer id);

    /*@Insert("insert into task(pid, name,description) value (#{pid},#{name},#{description})")
    Task newTask(String name, String description,Integer pid);*/


    void modify(Integer id, Task task);

    @Delete("delete from task where id = #{id}")
    void deleteById(Integer id);


    int newTask(Task task);
}
