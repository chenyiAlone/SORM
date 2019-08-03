package com.sorm.bean;

/**
 * 
 * ClassName: Configuration
 * @author chenyiAlone  
 * Create Time: 2018/01/05 16:52:10
 * Description: 封装数据库配置信息
 */
public class Configuration {
    
    /**
     * 驱动类
     */
    private String driver;
    
    /**
     * JDBC 的 URL
     */
    private String url;
    
    /**
     * 数据库的用户名
     */
    private String user;
    
    /**
     * 数据库用户的密码
     */
    private String pwd;
    
    /**
     * 正在使用的数据库
     */
    private String usingDB;
    
    /**
     * SRC 的路径
     */
    private String srcPath;
    
    /**
     * 扫描生成的 java 类的包位置
     */
    private String poPackage;

    public Configuration() {
    }

    public Configuration(String driver, String url, String user, String pwd, String usingDB, String srcPath,
            String poPackage) {
        super();
        this.driver = driver;
        this.url = url;
        this.user = user;
        this.pwd = pwd;
        this.usingDB = usingDB;
        this.srcPath = srcPath;
        this.poPackage = poPackage;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUsingDB() {
        return usingDB;
    }

    public void setUsingDB(String usingDB) {
        this.usingDB = usingDB;
    }

    public String getSrcPath() {
        return srcPath;
    }

    public void setSrcPath(String srcPath) {
        this.srcPath = srcPath;
    }

    public String getPoPackage() {
        return poPackage;
    }

    public void setPoPackage(String poPackage) {
        this.poPackage = poPackage;
    }

}
