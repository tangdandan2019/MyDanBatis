package com.dandan.mybatis.binding;

import com.dandan.mybatis.sqlsession.SqlSession;

import java.lang.reflect.Proxy;


/**
 * @Author: tangdandan
 * @Date: 2020/6/24 10:37
 */
public class MapperProxyFactory<T> {
    private final Class<T> mapperInterface;

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public T newInstance(SqlSession sqlSession) {
        final MapperProxy<T> mapperProxy = new MapperProxy<T>(sqlSession, mapperInterface);
        return newInstance(mapperProxy);
    }

    /**
     * 真正动态代理的方法
     * @param mapperProxy
     * @return
     */
    private T newInstance(MapperProxy<T> mapperProxy) {
     return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(),new Class[]{mapperInterface},mapperProxy ) ;
    }
}
