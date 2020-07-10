package com.dandan.test;

import com.dandan.mybatis.test.mapper.UserMapper;
import com.dandan.mybatis.test.pojo.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: tangdandan
 * @Date: 2020/7/6 14:27
 */
public class TestMybatis {
    public static void main(String[] args) {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sessionFactory = null;
        try {
          sessionFactory = builder.build(Resources.getResourceAsStream("mybatisConfig.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
     /*   SqlSession session = sessionFactory.openSession() ;
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = userMapper.selectByPrimaryKey(1L);
        session.commit();
        System.out.println(user.toString());*/

        SqlSession session1 = sessionFactory.openSession();
        UserMapper mapper = session1.getMapper(UserMapper.class);
        /*User user2 = mapper.selectByPrimaryKey(1L);
        session1.commit();
        System.out.println(user2.toString());*/
        //分页插件
      //  PageHelper.startPage(0,1);
        List<User> users = mapper.selectAll();
       // PageInfo<User> pageInfo = new PageInfo<>(users);
        System.out.println(users);

    }

}
