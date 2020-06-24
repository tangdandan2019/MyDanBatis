package com.dandan.mybatis.configuration;

import com.dandan.mybatis.binding.MapperRegistry;
import com.dandan.mybatis.sqlsession.SqlSession;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: tangdandan
 * @Date: 2020/6/18 11:44
 */
@Data
public class Configuration {
    private String jdbcDriver;
    private String jdbcUrl;
    private String jdbcPassword;
    private String jdbcUsername;
    private Map<String, MapperStatement> mappedStatement = new HashMap<>();
    protected MapperRegistry mapperRegistry =new MapperRegistry(this);

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return mapperRegistry.getMapper(sqlSession);
    }
}
