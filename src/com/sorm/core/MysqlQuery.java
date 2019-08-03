package com.sorm.core;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.po.Emp;
import com.sorm.bean.ColumnInfo;
import com.sorm.bean.TableInfo;
import com.sorm.util.JDBCUtils;
import com.sorm.util.ReflectUtils;

/**
 * 
 * ClassName: MysqlQuery
 * @author chenyiAlone  
 * Create Time: 2018/01/05 16:52:10
 * Description: Query 的实现类，负责对 MySql 数据库的操作
 * 
 */
public class MysqlQuery implements Query {

    public static void main(String[] args) {
        Emp e = new Emp();
        e.setId(1);
        e.setEmpname("sdfsfsdf");
        e.setAge(20);
        e.setSalary(60000);
        // new MysqlQuery().delete(e);
        // new MysqlQuery().insert(e);
        new MysqlQuery().insert(e);
//        new MysqlQuery().update(e, new String[] { "empname", "age", "salary" });
    }

    @Override
    public void insert(Object obj) {
        Class<?> clazz = obj.getClass();
        TableInfo tableInfo = TableContext.poClassTableMap.get(clazz);
        // 统计参数个数
        int countNotNullField = 0;
        List<Object> list = new ArrayList<Object>();
        StringBuilder sql = new StringBuilder("insert into " + tableInfo.getTname() + " (");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            Object fieldValue = ReflectUtils.invokeGet(fieldName, obj);
            if (fieldValue != null) {
                countNotNullField++;
                sql.append(fieldName + ",");
                list.add(fieldValue);
            }
        }
        sql.setCharAt(sql.length() - 1, ')');
        sql.append(" values (");
        for (int i = 0; i < countNotNullField; i++) {
            sql.append("?,");
        }
        sql.setCharAt(sql.length() - 1, ')');
        System.out.println(sql.toString());
        executeDML(sql.toString(), list.toArray());

    }

    @Override
    public void delete(Object obj) {
        TableInfo tableInfo = TableContext.poClassTableMap.get(obj.getClass());
        ColumnInfo onlyPriKey = tableInfo.getOnlyPriKey();
        Object keyValue = ReflectUtils.invokeGet(onlyPriKey.getName(), obj);
        delete(obj.getClass(), keyValue);
    }

    @Override
    public void delete(Class clazz, Object obj) {
        TableInfo tableInfo = TableContext.poClassTableMap.get(clazz);
        ColumnInfo priKey = tableInfo.getOnlyPriKey();
        String sql = new String("delete from " + tableInfo.getTname() + " where " + priKey.getName() + "=?");
        System.out.println(sql);
        executeDML(sql, new Object[] { obj });
    }

    @Override
    public Object queryValue(String sql, Object[] params) {
        Connection conn = DBManager.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Object value = null;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                value = rs.getObject(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(rs, ps, conn);
        }
        return value;

    }

    @Override
    public int update(Object obj, String[] filedName) {
        TableInfo table = TableContext.poClassTableMap.get(obj.getClass());
        ColumnInfo priKey = table.getOnlyPriKey();
        Field[] fields = obj.getClass().getDeclaredFields();
        List<Object> params = new ArrayList<Object>();
        StringBuilder sql = new StringBuilder("update " + table.getTname() + " set ");
        for (Field field : fields) {
            params.add(ReflectUtils.invokeGet(field.getName(), obj));
            sql.append(field.getName() + "=?,");
        }
        params.add(ReflectUtils.invokeGet(priKey.getName(), obj));
        sql.setCharAt(sql.length() - 1, ' ');
        sql.append("where " + priKey.getName() + "=?");
        System.out.println(sql.toString());
        return executeDML(sql.toString(), params.toArray());
    }

    @Override
    public int executeDML(String sql, Object[] params) {
        Connection conn = DBManager.getConn();
        PreparedStatement ps = null;
        int count = 0;
        try {
            ps = conn.prepareStatement(sql);
            JDBCUtils.handleParams(ps, params);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<Object> queryRows(String sql, Class clazz, Object[] params) {

        Connection conn = DBManager.getConn();
        PreparedStatement ps = null;
        ResultSetMetaData meData = null;
        ResultSet rs = null;
        List<Object> list = null;
        try {
            ps = conn.prepareStatement(sql);
            JDBCUtils.handleParams(ps, params);

            rs = ps.executeQuery();
            meData = rs.getMetaData();
            while (rs.next()) {

                if (list != null) {
                    list = new ArrayList<>();
                }
                Object rowObj = clazz.newInstance();
                for (int i = 0; i < meData.getColumnCount(); i++) {
                    String columnName = meData.getColumnLabel(i + 1);
                    Object columnValue = rs.getObject(i + 1);
                    ReflectUtils.invokeSet(columnName, rowObj, columnValue);
                }
                list.add(rowObj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(rs, ps, conn);
        }
        return list;
    }

    @Override
    public Object queryUniqueRow(String sql, Class clazz, Object[] params) {
        List<Object> list = queryRows(sql, clazz, params);
        return (list != null && list.size() > 0) ? list.get(0) : null;
    }

    @Override
    public Number queryNumber(String sql, Object[] params) {
        return (Number) queryValue(sql, params);
    }
}
