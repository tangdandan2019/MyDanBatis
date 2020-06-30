package com.dandan.mybatis.executor;

import com.dandan.mybatis.configuration.Configuration;
import com.dandan.mybatis.configuration.MappedStatement;
import org.springframework.util.ReflectionUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @Author: tangdandan
 * @Date: 2020/6/18 18:05
 */
public class SimpleExecutor  implements Executor{
    private final Configuration configuration;

    public SimpleExecutor(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> List<T> query(MappedStatement ms, Object[] parameter) {
        System.out.println(ms.getSql());
        List<T> res = new ArrayList<>();

        try {
            Class.forName(configuration.getJdbcDriver());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(configuration.getJdbcUrl(), configuration.getJdbcUsername(), configuration.getJdbcPassword());
            String regex = "#\\{([^}])*\\}";
            String sql = ms.getSql().replaceAll(regex, "?");
            preparedStatement = connection.prepareStatement(sql);
            parametersize(preparedStatement,parameter);
            resultSet = preparedStatement.executeQuery();
            handlerResultSet(resultSet,res,ms.getResultType());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                resultSet.close();
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return res;
    }

    /**
     * 结果集处理
     * @param resultSet
     * @param res
     * @param resultType
     * @param <T>
     */
    private <T> void handlerResultSet(ResultSet resultSet, List<T> res, String resultType) {
        System.out.println(resultSet);
    }

    /**
     * 处理占位符，将占位符用传入参数代替
     * @param preparedStatement
     * @param parameter
     */
    private void parametersize(PreparedStatement preparedStatement, Object[] parameter) throws SQLException {
        if(parameter[0] instanceof Integer){
            preparedStatement.setInt(1,(int)parameter[0]);
        }else if(parameter[0] instanceof  Long){
            preparedStatement.setLong(1,(Long)parameter[0]);
        }else if(parameter[0] instanceof  String){
            preparedStatement.setString(1,(String)parameter[0]);
        }
    }
}
