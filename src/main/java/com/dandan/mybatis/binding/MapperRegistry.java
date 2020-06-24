package com.dandan.mybatis.binding;

import com.dandan.mybatis.configuration.Configuration;
import com.dandan.mybatis.sqlsession.SqlSession;


/**
 * @Author: tangdandan
 * @Date: 2020/6/23 18:38
 */
public class MapperRegistry {
   private Configuration config;

    public MapperRegistry(Configuration config) {
        this.config = config;
    }

    public <T> T getMapper( SqlSession sqlSession) {

        return new MapperProxyFactory<T>(sqlSession).newInstance(sqlSession);
    }
}
