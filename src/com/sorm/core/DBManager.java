package com.sorm.core;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.sorm.bean.Configuration;

/**
 * 
 * ClassName: DBManager
 * @author chenyiAlone  
 * Create Time: 2018/01/05 16:52:10
 * Description: 数据库的配置类
 * 1. 用于从 .properties 配置文件中读取到配置信息,并封装到 Configuration 中，用于全局的使用 
 * 2. 建立 和 关闭数据库连接
 * 3. 获取 封装了 .properties 配置信息的 Configuration 实例对象
 */
public class DBManager {
    
    private static Configuration conf;
    
    static {
        Properties po = new Properties();
        try {
            po.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        conf = new Configuration();
        conf.setDriver(po.getProperty("driver"));
        conf.setUrl(po.getProperty("url"));
        conf.setPwd(po.getProperty("pwd"));
        conf.setUser(po.getProperty("user"));
        conf.setSrcPath(po.getProperty("srcPath"));
        conf.setUsingDB(po.getProperty("usingDB"));
        conf.setPoPackage(po.getProperty("poPackage"));
    }

    /**
     * 获得 Connection 连接对象
     * 
     * @return Connection 连接对象
     */
    public static Connection getConn() {
        try {
            System.out.println(conf.getDriver());
            Class.forName(conf.getDriver());
            return DriverManager.getConnection(conf.getUrl(), conf.getUser(), conf.getPwd());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 关闭数据库连接
     * 
     * @param rs
     * @param state
     * @param conn
     */
    public static void close(ResultSet rs, Statement state, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (state != null) {
            try {
                state.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获得加载完配置文件的Configuration对象
     * 
     * @return
     */
    public static Configuration getConf() {
        return conf;
    }
}
