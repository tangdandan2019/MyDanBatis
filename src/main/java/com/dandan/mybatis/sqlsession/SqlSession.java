package com.dandan.mybatis.sqlsession;

import com.dandan.mybatis.configuration.Configuration;

import java.util.List;

/**
 * @Author: tangdandan
 * @Date: 2020/6/18 11:48
 */
public interface SqlSession {
    /**
     * 查询单个对象
     * @param statement
     * @param parameter
     * @param <T>
     * @return
     */
    <T> T selectOne(String statement,Object[] parameter);

    /**
     * 查询对象列表
     * @param statement
     * @param parameter
     * @param <T>
     * @return
     */
    <T> List<T> selectList(String statement,Object[] parameter);

    /**
     * 获取mapper
     * @param type
     * @param <T>
     * @return
     */
    <T> T getMapper(Class<T> type);

    /**
     * 获取配置
     * @return
     */
    Configuration getConfiguration();
}
