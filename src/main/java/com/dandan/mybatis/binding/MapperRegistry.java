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

    /**
     * 获取mapper
     * @param type
     * @param sqlSession
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        MapperProxyFactory<T> mapperProxyFactory = new MapperProxyFactory<>(type);
        return mapperProxyFactory.newInstance(sqlSession);
    }
}
