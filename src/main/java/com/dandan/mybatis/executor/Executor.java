package com.dandan.mybatis.executor;

import com.dandan.mybatis.configuration.MappedStatement;

import java.util.List;

/**
 * @Author: tangdandan
 * @Date: 2020/6/16 11:45
 */
public interface Executor {
    /**
     * 查询接口
     * @param ms
     * @param parameter
     * @param <T>
     * @return
     */
    <T> List<T> query(MappedStatement ms, Object parameter);
}
