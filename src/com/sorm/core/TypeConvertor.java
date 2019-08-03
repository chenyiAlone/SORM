package com.sorm.core;

/**
 * 
 * ClassName: TypeConvertor
 * @author chenyiAlone  
 * Create Time: 2019/08/03 17:31:06
 * Description: 类型转化的抽象类
 * 1. java 类型    -> 数据库类型
 * 2. 数据库类型 -> java 类型
 * 
 */
public interface TypeConvertor {
    /**
     * 从数据库类型-->java 数据类型
     * 
     * @param databaseType
     */
    abstract String databaseType2JavadataType(String databaseType);

    /**
     * 从java类型-->数据库类型
     * 
     * @param javaType
     */
    abstract String javadataType2DatabaseType(String javaType);

}
