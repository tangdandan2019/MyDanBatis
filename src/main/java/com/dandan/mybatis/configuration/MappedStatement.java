package com.dandan.mybatis.configuration;

import com.dandan.mybatis.enums.SqlCommandType;
import lombok.Data;


/**
 * @Author: tangdandan
 * @Date: 2020/6/18 11:14
 */
@Data
public class MappedStatement {
    private String namespace;
    private String id;
    private SqlCommandType sqlCommandType;
    private String resultType;
    private String sql;
}
