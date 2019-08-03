package com.sorm.bean;

/**
 * 
 * ClassName: JavaFieldGetSet
 * @author chenyiAlone  
 * Create Time: 2018/01/05 16:52:10
 * Description: 封装生成的 java 源码的属性、setter|getter 方法
 */
public class JavaFieldGetSet {
    /**
     * 封装属性信息:如-->private int age;
     */
    private String fieldInfo;
    
    /**
     * 封装属性的set方法:如-->pulbic void setXxx(Object Xxx){this.Xxx=Xxx;}
     */
    private String setInfo;
    
    /**
     * 封装属性的get方法:如-->public object getXxx(){return this.Xxx;}
     */
    private String getInfo;

    public String getFieldInfo() {
        return fieldInfo;
    }

    public void setFieldInfo(String fieldInfo) {
        this.fieldInfo = fieldInfo;
    }

    public String getSetInfo() {
        return setInfo;
    }

    public void setSetInfo(String setInfo) {
        this.setInfo = setInfo;
    }

    public String getGetInfo() {
        return getInfo;
    }

    public void setGetInfo(String getInfo) {
        this.getInfo = getInfo;
    }

    public JavaFieldGetSet(String fieldInfo, String setInfo, String getInfo) {
        super();
        this.fieldInfo = fieldInfo;
        this.setInfo = setInfo;
        this.getInfo = getInfo;
    }

    public JavaFieldGetSet() {
    }

    @Override
    public String toString() {
        return this.fieldInfo + this.setInfo + this.getInfo;
    }

}
