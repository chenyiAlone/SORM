package com.sorm.util;

import java.lang.reflect.Method;

import com.po.Emp;

/**
 * 
 * ClassName: ReflectUtils
 * @author chenyiAlone  
 * Create Time: 2018/01/05 16:52:10
 * Description: 对象属性操作类，使用反射来调用对象实例的
 * setter|getter 方法
 * 
 */
public class ReflectUtils {

    /**
     * 通过反射调用obj的get方法来获取其中的 fieldValue
     * 
     * @param fieldName 属性名
     * @param obj       Object对象
     * @return fieldValue
     */
    public static Object invokeGet(String fieldName, Object obj) {
        Object ret = null;
        try {
            Method m = obj.getClass().getDeclaredMethod("get" + StringUtils.firstCharUpperCase(fieldName));
            ret = m.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 通过反射调用obj的set方法来设置其中的field的fieldValue
     * 
     * @param columnName    fieldName
     * @param obj           obj对象
     * @param columnValue   filedValue
     */
    public static void invokeSet(String columnName, Object obj, Object columnValue) {
        try {
            Method m = obj.getClass().getDeclaredMethod("set" + StringUtils.firstCharUpperCase(columnName),
                                                        columnValue.getClass());
            m.invoke(obj, columnValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * test
     * @param args
     */
    public static void main(String[] args) {
        Emp e = new Emp();
        e.setEmpname("lily");
        e.setBirthday(new java.sql.Date(System.currentTimeMillis()));
        e.setAge(30);
        e.setId(1);
        ReflectUtils.invokeSet("age", e, 99);
        System.out.println(ReflectUtils.invokeGet("age", e));
    }

}
