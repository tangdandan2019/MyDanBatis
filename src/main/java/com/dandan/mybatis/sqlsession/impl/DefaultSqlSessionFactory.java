package com.dandan.mybatis.sqlsession.impl;

import com.dandan.mybatis.configuration.Configuration;
import com.dandan.mybatis.configuration.MappedStatement;
import com.dandan.mybatis.enums.SqlCommandType;
import com.dandan.mybatis.sqlsession.SqlSession;
import com.dandan.mybatis.sqlsession.SqlSessionFactory;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @Author: tangdandan
 * @Date: 2020/6/18 15:26
 */
@Slf4j
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private final Configuration configuration = new Configuration();
    //xml文件存放位置
    public static final String MAPPER_CONFIG_LOCATION ="D:/workspace/dandan-learn-mybatis/src/main/resources/mapper";
    //数据库信息存放位置
    public static final String DB_CONFIG_FILE ="D:/workspace/dandan-learn-mybatis/src/main/resources/db.properties";

    public DefaultSqlSessionFactory(){
        //加载数据库配置信息
        loadDBInfo();
        //加载xmlmapperInfo
        loadMapperInfo();
 }

    /**
     * 加载mapper.xml信息
     */
    private void loadMapperInfo() {
     File mapper = new File(MAPPER_CONFIG_LOCATION);
     //读取文件夹下的任意文件信息
      if(mapper.isDirectory()){
          File[] files = mapper.listFiles();
          for(File file:files){
              loadMapperInfo(file);
          }
      }


    }

    /**
     * 方法重载：读取mapper文件
     * @param file
     */
    private void loadMapperInfo(File file) {
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document=reader.read(file);
        } catch (DocumentException e) {
            log.info("XML文件解析失败{}"+e);
        }
        //获取根节点
        Element rootElement = document.getRootElement();
        //获取命名空间
        String namespace = rootElement.attribute("namespace").getValue();
        List<Element> selects = rootElement.elements("select");
        List<Element> updates = rootElement.elements("update");
        List<Element> inserts = rootElement.elements("insert");
        List<Element> deletes = rootElement.elements("delete");
        List<Element> all = new ArrayList<>();
        all.addAll(deletes);
        all.addAll(selects);
        all.addAll(updates);
        all.addAll(inserts);
        for (Element ele : all){
            MappedStatement mapperStatement = new MappedStatement();
            String id = ele.attribute("id").getData().toString();
            String resultType = ele.attribute("resultType").getData().toString();
            String sql = ele.getData().toString();
            SqlCommandType sqlCommandType ;
            switch (ele.getName()){
                case "select":
                    sqlCommandType = SqlCommandType.SELECT;
                    break;
                case "update":
                    sqlCommandType = SqlCommandType.UPDATE;
                    break;
                case "insert":
                    sqlCommandType = SqlCommandType.INSERT;
                    break;
                case "delete":
                    sqlCommandType = SqlCommandType.DELETE;
                    break;
                default:
                    sqlCommandType = SqlCommandType.UNKNOWN;
            }
            mapperStatement.setSqlCommandType(sqlCommandType);
            mapperStatement.setId(namespace+"."+id);
            mapperStatement.setNamespace(namespace);
            mapperStatement.setResultType(resultType);
            mapperStatement.setSql(sql);
            configuration.getMappedStatement().put(namespace+"."+id,mapperStatement);
        }
    }

    /**
     * 加载数据库配置信息
     */
    private void loadDBInfo() {
        Properties properties = new Properties();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(DB_CONFIG_FILE));
            properties.load(bufferedReader);
            configuration.setJdbcDriver(properties.getProperty("jdbc.DriverClassName"));
            configuration.setJdbcUrl(properties.getProperty("jdbc.url"));
            configuration.setJdbcUsername(properties.getProperty("jdbc.username"));
            configuration.setJdbcPassword(properties.getProperty("jdbc.password"));
        } catch (IOException e) {
            log.error("数据库连接信息文件读取失败：{}",e.getCause() );
        }
    }

    /**
     * 返回sqlsession
     * @return
     */
    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
