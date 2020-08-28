package org.lightweb4j.framework.helper;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.lightweb4j.framework.utils.CollectionUtil;
import org.lightweb4j.framework.utils.PropsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @Author benxin_lei
 * @Date 2020-08-27 22:21
 * @Version 1.0.0
 */
public final class DataBaseHelper {

    // 用来存放本地线程变量
    private static final ThreadLocal<Connection> CONNECTION_HOLDER;

    private static final Logger LOGGER = LoggerFactory.getLogger(DataBaseHelper.class);
    private static final QueryRunner QUERY_RUNNER;
    private static final BasicDataSource DATA_SOURCE;

    /*private static final String DRIVER;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;*/

    static {
        CONNECTION_HOLDER = new ThreadLocal<Connection>();
        QUERY_RUNNER = new QueryRunner();
        // 加载数据库配置文件
        Properties conf = PropsUtil.loadProps("config.properties");
        String driver = conf.getProperty("jdbc.driver");
        String url = conf.getProperty("jdbc.url");
        String userName = conf.getProperty("jdbc.username");
        String passWord = conf.getProperty("jdbc.password");

        DATA_SOURCE = new BasicDataSource();
        DATA_SOURCE.setDriverClassName(driver);
        DATA_SOURCE.setUrl(url);
        DATA_SOURCE.setUsername(userName);
        DATA_SOURCE.setPassword(passWord);

        /*try {
            // 注册数据库驱动
            Class.forName(DRIVER);
        }catch (ClassNotFoundException ex){
            LOGGER.error("can not load jdbc driver", ex);
        }*/
    }

    /**
     * 获取数据库连接，后续可以用数据库连接池
     * @return
     */
    public static Connection getConnection(){
        Connection conn = CONNECTION_HOLDER.get();
        if (conn == null){
            try {
                conn = DATA_SOURCE.getConnection();
            }catch (SQLException ex){
                LOGGER.error("get connection failure", ex);
                throw new RuntimeException(ex);
            }finally {
                CONNECTION_HOLDER.set(conn);
            }
        }

        return conn;
    }

    /**
     * 查询实体列表
     * @param entityClass
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    public static <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object... params){
        List<T> entityList;
        try {
            Connection conn = getConnection();
            entityList = QUERY_RUNNER.query(conn, sql,
                    new BeanListHandler<T>(entityClass), params);
        }catch (SQLException ex){
            LOGGER.error("query entity list failure", ex);
            throw new RuntimeException(ex);
        }finally {
            closeConnection();
        }

        return entityList;
    }

    /**
     * 查询单个实体
     * @param entityClass
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    public static <T> T queryEntity(Class<T> entityClass, String sql, Object... params){
        T entity;
        try {
            Connection conn = getConnection();
            entity = QUERY_RUNNER.query(conn,sql, new BeanHandler<T>(entityClass), params);

        }catch (SQLException ex){
            LOGGER.error("query entity failure", ex);
            throw new RuntimeException(ex);
        }finally {
            closeConnection();
        }

        return entity;
    }

    /**
     * 执行普通查询，非单表实体，返回List对象
     * @param sql
     * @param params
     * @return
     */
    public static List<Map<String, Object>> executeQuery(String sql, Object... params){
        List<Map<String, Object>> result;
        try {
            Connection conn = getConnection();
            result = QUERY_RUNNER.query(conn, sql, new MapListHandler(), params);
        }catch (SQLException ex){
            LOGGER.error("execute query failure", ex);
            throw new RuntimeException(ex);
        }finally {
            closeConnection();
        }

        return result;
    }

    /**
     * 执行更新语句（包括update、insert、delete）
     * @param sql
     * @param param
     * @return
     */
    public static int executeUpdate(String sql, Object... param){
        int rows = 0;
        try {
            Connection conn = getConnection();
            rows = QUERY_RUNNER.update(sql, param);
        }catch (SQLException ex){
            LOGGER.error("execute update failure", ex);
            throw new RuntimeException(ex);
        }finally {
            closeConnection();
        }

        return rows;
    }

    public static <T> boolean insertEntity(Class<T> entityClass, Map<String, Object> fieldMap){
        if (CollectionUtil.isEmpty(fieldMap)){
            LOGGER.error("can not insert entity: fieldMap is empty");
            return false;
        }

        String sql = "INSERT INTO " + getTableName(entityClass);
        StringBuilder collumns = new StringBuilder("(");
        StringBuilder values = new StringBuilder("(");
        for (String fieldName : fieldMap.keySet()){
            collumns.append(fieldName).append(", ");
            values.append("?, ");
        }
        collumns.replace(collumns.lastIndexOf(", "), collumns.length(), ")");
        values.replace(values.lastIndexOf(", "), values.length(), ")");
        sql += collumns + " VALUES " + values;

        Object[] params = fieldMap.values().toArray();
        return executeUpdate(sql, params) == 1;
    }

    /**
     * 更新单个实体
     * @param entityClass
     * @param id
     * @param fieldMap
     * @param <T>
     * @return
     */
    public static <T> boolean updateEntity(Class<T> entityClass, Long id, Map<String, Object> fieldMap){
        if (CollectionUtil.isEmpty(fieldMap)){
            LOGGER.error("can not update entity: fieldMap is empty");
            return false;
        }

        String sql = "UPDATE " + getTableName(entityClass) + " SET ";
        StringBuilder columns = new StringBuilder();
        for (String fieldName : fieldMap.keySet()) {
            columns.append(fieldName).append(" =?, ");
        }
        sql += columns.substring(0, columns.lastIndexOf(", ")) + " WHERE id = ?";

        List<Object> paramList = new ArrayList<Object>();
        paramList.addAll(fieldMap.values());
        paramList.add(id);
        Object[] params = paramList.toArray();

        return executeUpdate(sql, params) == 1;
    }

    /**
     * 删除实体
     * @param entityClass
     * @param id
     * @param <T>
     * @return
     */
    public static <T> boolean deleteEntity(Class<T> entityClass, Long id){
        String sql = " DELETE FROM " + getTableName(entityClass) + " WHERE id = ? ";
        return executeUpdate(sql, id) == 1;
    }

    /**
     * 执行sql文件，逐行执行
     * @param filePath
     */
    public static void executeSqlFile(String filePath){
        InputStream is = Thread.currentThread().getContextClassLoader().
                getResourceAsStream(filePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        try {
            String sql;
            while ((sql = reader.readLine()) != null){
                executeUpdate(sql);
            }
        }catch (Exception ex){
            LOGGER.error("Execute sql file failure", ex);
            throw new RuntimeException(ex);
        }
    }

    /**
     * 关闭数据库连接
     */
    public static void closeConnection(){
        Connection conn = CONNECTION_HOLDER.get();
        if (conn != null){
            try {
                conn.close();
            }catch (SQLException ex){
                LOGGER.error("close connection failure", ex);
            }finally {
                CONNECTION_HOLDER.remove();
            }
        }
    }

    private static String getTableName(Class<?> entityClass){
        return entityClass.getSimpleName();
    }
}
