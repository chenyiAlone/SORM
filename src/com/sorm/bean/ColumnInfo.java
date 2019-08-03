package com.sorm.bean;

/**
 * 
 * ClassName: ColumnInfo
 * @author chenyiAlone  
 * Create Time: 2018/01/05 16:52:10
 * Description: 封装表的字段信息
 */
public class ColumnInfo {
    
    /**
     * 字段名称
     */
    private String name;
    
    /**
     * 字段类型
     */
    private String dataType;
    
    /**
     * 主键类型-->0:普通键|1:主键|2:外键
     */
    private int keyType;

    public ColumnInfo() {
    }

    public ColumnInfo(String name, String dataType, int keyType) {
        super();
        this.name = name;
        this.dataType = dataType;
        this.keyType = keyType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public int getKeyType() {
        return keyType;
    }

    public void setKeyType(int keyType) {
        this.keyType = keyType;
    }

}
