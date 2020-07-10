package com.dandan.mybatis.interceptor;

import com.alibaba.druid.pool.DruidDataSource;
import com.zaxxer.hikari.util.DriverDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 自定义打印sql插件
 * @Author: tangdandan
 * @Date: 2020/7/10 16:33
 */
@Intercepts({@Signature(type = Executor.class, method ="update" , args ={MappedStatement.class,Object.class} ),
        @Signature(type = Executor.class, method ="query" , args ={MappedStatement.class,Object.class,
                RowBounds.class, ResultHandler.class} )})
@Slf4j
public class SqlLogInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object parameter = null;
        if (invocation.getArgs().length>1) {
            parameter = invocation.getArgs()[1];
        }
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        Configuration configuration = mappedStatement.getConfiguration();
        PooledDataSource source = (PooledDataSource) configuration.getEnvironment().getDataSource();
        Object res = invocation.proceed();//执行原方法
        log.info("url:{},sql:{},result:{}",source.getUrl(),boundSql.getSql(),res.toString());
        return res;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }
    @Override
    public void setProperties(Properties properties) {

    }
}
