package com.sorm.util;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 
 * ClassName: JDBCUtils
 * @author chenyiAlone  
 * Create Time: 2018/01/05 16:52:10
 * Description: JDBC 的工具类
 * 1. 给 PreparedStatement 设定多个 Object[] params 参数
 */
public class JDBCUtils {
    /**
     * 设置给定参数
     * 
     * @param ps        预编译的 SQL 语句的对象
     * @param params    设置给定参数
     */
    public static void handleParams(PreparedStatement ps, Object[] params) {
        for (int i = 0; i < params.length; i++) {
            try {
                ps.setObject(i + 1, params[i]);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
}
