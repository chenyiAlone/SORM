package com.sorm.core;

import java.util.List;

/**
 * 
 * ClassName: Query
 * @author chenyiAlone  
 * Create Time: 2019/08/03 17:19:59
 * Description: 数据库操作的抽象类
 * MySql 或其他类型的数据库操作类只需要继承该类并实现素有的方法即可
 * 
 * 1. 所有的操作都是通过对象实例来进行的
 * 
 */
public interface Query {

    /**
     * 插入一条数据
     * 
     * @param obj
     */
    abstract void insert(Object obj);

    /**
     * 删除指定数据
     * 
     * @param obj
     */
    abstract void delete(Object obj);

    /**
     * 删除类+对象指定的数据
     * 
     * @param obj
     */
    abstract void delete(Class clazz, Object obj);

    /**
     * 执行一个DML语句
     * 
     * @param sql       DML语句
     * @param params    参数
     */
    abstract int executeDML(String sql, Object[] params);

    /**
     * 
     * @return 
     */
    abstract Object queryValue(String sql, Object[] params);

    /**
     * 修改数据
     * 
     * @param obj           sql参数
     * @param filedName     sql参数列表
     * @return              修改的行数
     */
    abstract int update(Object obj, String[] filedName);

    /**
     * 查找返回多行多列
     * 
     * @param sql       DML语句
     * @param clazz     class对象
     * @param params    参数
     * @return 封装了多行数据的Object的List
     */
    abstract List<Object> queryRows(String sql, Class clazz, Object[] params);

    /**
     * 查找返回单行
     * 
     * @param sql       DML语句
     * @param clazz     Class对象
     * @param params    参数
     * @return 封装了一行数据的Object对象
     */
    abstract Object queryUniqueRow(String sql, Class clazz, Object[] params);

    /**
     * 查询一行一列并返回查询到的数字
     * 
     * @param sql       DML语句
     * @param params    参数
     * @return          查询到的数字
     */
    abstract Number queryNumber(String sql, Object[] params);

}
