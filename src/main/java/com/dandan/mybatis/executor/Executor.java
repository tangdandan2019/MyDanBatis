package com.dandan.mybatis.executor;

import com.dandan.mybatis.configuration.MapperStatement;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
    <T> List<T> query(MapperStatement ms, Object parameter);
}
