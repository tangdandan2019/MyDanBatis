package com.dandan.mybatis.test;

import com.dandan.mybatis.sqlsession.SqlSession;
import com.dandan.mybatis.sqlsession.impl.DefaultSqlSessionFactory;
import com.dandan.mybatis.test.mapper.UserMapper;
import com.dandan.mybatis.test.pojo.User;

/**
 * @Author: tangdandan
 * @Date: 2020/6/29 16:42
 */
public class TestDemo {
    public static void main(String[] args) {
        DefaultSqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectByPrimaryKey(1L);
        System.out.println(user.toString());
    }
}
