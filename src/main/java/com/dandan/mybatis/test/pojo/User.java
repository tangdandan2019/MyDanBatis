package com.dandan.mybatis.test.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: tangdandan
 * @Date: 2020/6/17 18:07
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -5126983196487889541L;

    private Integer user_id;
    private String user_name;
    private Integer sex;
    private String role;
}
