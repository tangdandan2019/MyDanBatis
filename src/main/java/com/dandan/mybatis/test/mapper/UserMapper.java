package com.dandan.mybatis.test.mapper;

import com.dandan.mybatis.test.pojo.User;

import java.util.List;

/**
 * @Author: tangdandan
 * @Date: 2020/6/17 18:00
 */
public interface UserMapper {
    /**
     * 根据主键查询
     * @param id
     * @return
     */
    User selectByPrimaryKey(Long id);

    /**
     * 查询所有
     * @return
     */
    List<User> selectAll();
}
