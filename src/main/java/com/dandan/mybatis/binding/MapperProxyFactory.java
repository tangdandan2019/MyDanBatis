package com.dandan.mybatis.binding;

import com.dandan.mybatis.sqlsession.SqlSession;


/**
 * @Author: tangdandan
 * @Date: 2020/6/24 10:37
 */
public class MapperProxyFactory<T> {
  //  private final Class<T> mapperInterface;
    private final SqlSession sqlSession;

    public MapperProxyFactory(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public T newInstance(SqlSession sqlSession) {
     //   MapperProxy<T> mapperProxy = new MapperProxy<T>(sqlSession, mapperInterface);
        return null;
    }
}
