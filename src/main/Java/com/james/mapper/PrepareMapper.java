package com.james.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PrepareMapper {

    void insertFile(@Param("id") String id, @Param("fileName") String fileName);

    void deleteFile(@Param("id") String id);
}
