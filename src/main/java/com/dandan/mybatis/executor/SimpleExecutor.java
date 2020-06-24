package com.dandan.mybatis.executor;

import com.dandan.mybatis.configuration.MapperStatement;

import java.util.List;

/**
 * @Author: tangdandan
 * @Date: 2020/6/18 18:05
 */
public class SimpleExecutor  implements Executor{
    @Override
    public <T> List<T> query(MapperStatement ms, Object parameter) {
        return null;
    }
}
