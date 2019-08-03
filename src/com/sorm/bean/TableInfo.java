package com.sorm.bean;

import java.util.List;
import java.util.Map;

/**
 * 
 * ClassName: TableInfo
 * @author chenyiAlone  
 * Create Time: 2018/01/05 16:52:10
 * Description: 封装表的信息
 * 1. 表名
 * 2. 主键
 * 3. 字段
 * 4. 外键
 * 
 */
public class TableInfo {
    
    /**
     * 封存表名
     */
    private String tname;
    
    /**
     * 封存唯一主键
     */
    private ColumnInfo onlyPriKey;
    
    /**
     * 封存所有字段信息
     */
    private Map<String, ColumnInfo> columns;
    
    /**
     * 如果有联合主键，就存在这里
     */
    private List<ColumnInfo> priKeys;

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public Map<String, ColumnInfo> getColumns() {
        return columns;
    }

    public ColumnInfo getOnlyPriKey() {
        return onlyPriKey;
    }

    public void setOnlyPriKey(ColumnInfo onlyPriKey) {
        this.onlyPriKey = onlyPriKey;
    }

    public void setColumns(Map<String, ColumnInfo> columns) {
        this.columns = columns;
    }

    public List<ColumnInfo> getPriKeys() {
        return priKeys;
    }

    public void setPriKeys(List<ColumnInfo> priKeys) {
        this.priKeys = priKeys;
    }

    public TableInfo(String tname, List<ColumnInfo> priKeys, Map<String, ColumnInfo> columns) {
        super();
        this.tname = tname;
        this.columns = columns;
        this.priKeys = priKeys;
    }

    public TableInfo() {
        super();
    }

}
