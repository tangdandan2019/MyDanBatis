package com.dandan.mybatis.sqlsession.impl;

import com.dandan.mybatis.configuration.Configuration;
import com.dandan.mybatis.configuration.MappedStatement;
import com.dandan.mybatis.executor.Executor;
import com.dandan.mybatis.executor.SimpleExecutor;
import com.dandan.mybatis.sqlsession.SqlSession;



import java.util.List;


/**
 * @Author: tangdandan
 * @Date: 2020/6/18 15:31
 */
public class DefaultSqlSession implements SqlSession {
    private final Configuration configuration;
    private Executor executor;


    public DefaultSqlSession(Configuration configuration) {
        super();
        this.configuration =configuration;
         executor= new SimpleExecutor(configuration);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        List<T> res = this.selectList(statement, parameter);
        if(res==null||res.size()==0){
            return null;
        }
        if(res.size()==1){
            return res.get(0);
        }else{
            throw new RuntimeException("查询出的结果不只一个哟~请核对一下");
        }
    }

    @Override
    public <T> List<T> selectList(String statement, Object parameter) {
        MappedStatement ms = configuration.getMappedStatement().get(statement);
        return executor.query(ms,parameter);
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type,this);
    }

    @Override
    public Configuration getConfiguration() {
        return this.configuration;
    }
}
