package com.sorm.core;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.sorm.bean.ColumnInfo;
import com.sorm.bean.TableInfo;
import com.sorm.util.JavaFileUtils;
import com.sorm.util.StringUtils;

/**
 * 
 * ClassName: TableContext
 * @author chenyiAlone  
 * Create Time: 2018/01/05 16:52:10
 * Description: 表的操作类
 * 1. 获取数据库的所有的表信息，保存到 Map<String, TableInfo> 中
 * 2. 根据表信息在对应的包下生成响应的类
 * 3. 使用类加载器将生成的类加载
 * 
 */
public class TableContext {
    
    /**
     * 表名为key，表信息对象为value
     */
    public static Map<String, TableInfo> tables = new HashMap<String, TableInfo>();

    /**
     * 将po的class对象和表信息对象关联起来，便于重用！
     */
    public static Map<Class, TableInfo> poClassTableMap = new HashMap<Class, TableInfo>();

    private TableContext() {}

    static {
        try {
            // 初始化获得表的信息
            Connection con = DBManager.getConn();
            DatabaseMetaData dbmd = con.getMetaData();

            ResultSet tableRet = dbmd.getTables(null, "%", "%", new String[] { "TABLE" });

            while (tableRet.next()) {
                String tableName = (String) tableRet.getObject("TABLE_NAME");

                TableInfo ti = new TableInfo(tableName, new ArrayList<ColumnInfo>(), new HashMap<String, ColumnInfo>());
                tables.put(tableName, ti);

                ResultSet set = dbmd.getColumns(null, "%", tableName, "%"); // 查询表中的所有字段
                while (set.next()) {
                    ColumnInfo ci = new ColumnInfo(set.getString("COLUMN_NAME"), set.getString("TYPE_NAME"), 0);
                    ti.getColumns().put(set.getString("COLUMN_NAME"), ci);
                }

                ResultSet set2 = dbmd.getPrimaryKeys(null, "%", tableName); // 查询t_user表中的主键
                while (set2.next()) {
                    ColumnInfo ci2 = (ColumnInfo) ti.getColumns().get(set2.getObject("COLUMN_NAME"));
                    ci2.setKeyType(1); // 设置为主键类型
                    ti.getPriKeys().add(ci2);
                }

                if (ti.getPriKeys().size() > 0) { // 取唯一主键。。方便使用。如果是联合主键。则为空！
                    ti.setOnlyPriKey(ti.getPriKeys().get(0));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 更新类结构
        updateJavaPOFile();

        // 加载po包下面所有的类，便于重用，提高效率！
        loadPOTables();
    }

    /**
     * 根据表结构将配置更新到 po 包下
     */
    public static void updateJavaPOFile() {
        Map<String, TableInfo> tables = TableContext.tables;
        for (TableInfo table : tables.values()) {
            JavaFileUtils.createJavaPoFile(table, new MysqlTypeConvertor());
        }
    }

    /**
     * 加载 po 包下的类--> poClassTableMap
     */
    public static void loadPOTables() {
        for (TableInfo table : tables.values()) {
            try {
                String className = DBManager.getConf().getPoPackage() + "." + StringUtils.firstCharUpperCase(table.getTname());
//                System.out.println(className);
                Class<?> clazz = Class.forName(className);
//                Class clazz = Class.forName(
//                        DBManager.getConf().getPoPackage() + "." + StringUtils.firstCharUpperCase(table.getTname()));
                poClassTableMap.put(clazz, table);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}
