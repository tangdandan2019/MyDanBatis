package com.dandan.mybatis.sqlsession;

import java.util.List;

/**
 * @Author: tangdandan
 * @Date: 2020/6/18 11:48
 */
public interface SqlSession {
    <T> T selectOne(String statement,Object parameter);
    <T> List<T> selectList(String statement,Object parameter);
    <T> T getMapper(Class<T> type);
}
