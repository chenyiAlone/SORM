package com.sorm.core;

/**
 * 
 * ClassName: MysqlTypeConvertor
 * @author chenyiAlone  
 * Create Time: 2018/01/05 16:52:10
 * Description: MySql 和 java 的类型转化器
 */
public class MysqlTypeConvertor implements TypeConvertor {

    @Override
    public String databaseType2JavadataType(String databaseType) {
        if ("varchar".equalsIgnoreCase(databaseType) || "char".equalsIgnoreCase(databaseType)) {
            return "String";
        } else if ("int".equalsIgnoreCase(databaseType) || "tinyint".equalsIgnoreCase(databaseType)
                || "smallint".equalsIgnoreCase(databaseType) || "integer".equalsIgnoreCase(databaseType)) {
            return "Integer";
        } else if ("bigint".equalsIgnoreCase(databaseType)) {
            return "Long";
        } else if ("double".equalsIgnoreCase(databaseType)) {
            return "Double";
        } else if ("clob".equalsIgnoreCase(databaseType)) {
            return "java.sql.Clob";
        } else if ("blob".equalsIgnoreCase(databaseType)) {
            return "java.sql.Blob";
        } else if ("date".equalsIgnoreCase(databaseType)) {
            return "java.sql.Date";
        } else if ("Time".equalsIgnoreCase(databaseType)) {
            return "java.sql.Time";
        } else if ("Timestamp".equalsIgnoreCase(databaseType)) {
            return "java.sql.Timestamp";
        } else {
            return null;
        }

    }

    @Override
    public String javadataType2DatabaseType(String javaType) {
        return null;

    }

}
