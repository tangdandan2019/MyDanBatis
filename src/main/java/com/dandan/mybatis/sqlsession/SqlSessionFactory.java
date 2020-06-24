package com.dandan.mybatis.sqlsession;

/**
 * @Author: tangdandan
 * @Date: 2020/6/18 11:48
 */
public interface SqlSessionFactory {
    SqlSession openSession();
}

