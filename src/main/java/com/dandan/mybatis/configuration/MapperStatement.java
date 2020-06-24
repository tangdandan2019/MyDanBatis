package com.dandan.mybatis.configuration;

import lombok.Data;

/**
 * @Author: tangdandan
 * @Date: 2020/6/18 11:14
 */
@Data
public class MapperStatement {
    private String namespace;
    private String id;
    private String resultType;
    private String sql;
}
