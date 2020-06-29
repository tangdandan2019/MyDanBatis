package com.dandan.mybatis.binding;

import com.dandan.mybatis.configuration.Configuration;
import com.dandan.mybatis.configuration.MappedStatement;
import com.dandan.mybatis.enums.SqlCommandType;
import com.dandan.mybatis.sqlsession.SqlSession;


import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Author: tangdandan
 * @Date: 2020/6/24 11:10
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {
    private static final long serialVersionUID = -3325616703115663220L;
    private final SqlSession sqlSession;
    private final Class<T> mapperInterface;

    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return this.excute(method,args) ;
    }
    //执行方法
    private Object excute(Method method, Object[] args) {
        String statementName = mapperInterface.getName() + "." + method.getName();
        Map<String, MappedStatement> ms = sqlSession.getConfiguration().getMappedStatement();
        //查询结果
        Object result = null;
        if(SqlCommandType.SELECT==ms.get(statementName).getSqlCommandType()){
            //获取方法的返回类型
            Class<?> returnType = method.getReturnType();
            //如果返回类型是集合类型
            if(Collection.class.isAssignableFrom(returnType)){
                result = sqlSession.selectList(statementName, args);
            }else{
                result = sqlSession.selectOne(statementName,args);
            }
        }
        return result;
    }
}
