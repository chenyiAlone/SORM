# SORM

## 简介

> 这是在 **`2018`** 年初的时候，做的一个 `SORM` 框架

1. 根据数据库中的表来生成对应的 `java bean` 
2. 使用 **`java bean` 对象实例** 对数据库进行操作

## 项目结构

**SORM**

```
src
  │  db.properties
  │
  └─com
      │
      └─sorm
          ├─bean
          │      ColumnInfo.java
          │      Configuration.java
          │      JavaFieldGetSet.java
          │      TableInfo.java
          │
          ├─core
          │      DBManager.java
          │      MysqlQuery.java
          │      MysqlTypeConvertor.java
          │      Query.java
          │      TableContext.java
          │      TypeConvertor.java
          │
          └─util
                  JavaFileUtils.java
                  JDBCUtils.java
                  ReflectUtils.java
                  StringUtils.java
```

## 配置

> **`db.properties`** 是数据库以及 **`SORM`** 的配置类，位于 `src` 目录下

 属性       |   注释
----------- | -------
`src`       | 绝对对路径
`usingDB`   | 数据库类型
`srcPath`   | `src` 的绝对路径
`poPackage` | 生成对应类的包位置

**示例配置**

在 `src` 目录下创建 `db.properties` 配置文件，添加如下配置

```properties
driver = com.mysql.jdbc.Driver
url = jdbc\:mysql://localhost\:3306/sorm
user = root
pwd = 
usingDB = mysql
srcPath = F\:\\study\\Workspace\\SORM\\src
poPackage = com.po
```

## 常用方法

### 数据库操作

**`com.sorm.core.TableContext`** 类中的方法

#### 生成数据库对应的 java bean

> 根据数据库的表，在 `poPackage` 包下生成对应的 `java bean`，并使用类加载器对其进行加载

```java
    void updateJavaPOFile()
```

#### 加载指定包下所有的 java bean

> 将 `po` 包下所有的类的 **`Class` 对象** 以及 **对应的表信息** 保存到 `Map<Class, TableInfo>` 中

```java
    void loadPOTables()
```

### 数据库增删改查

**`com.sorm.core.Query`** 的方法

#### 插入

```java
    void insert(Object obj);
```
#### 删除

```java
    void delete(Object obj)

    void delete(Class clazz, Object obj)
```    

#### 执行 sql 语句

```java
    executeDML(String sql, Object[] params)
``` 


#### 更新    

```java
    int update(Object obj, String[] filedName)
```

#### 查询

```java

    Object queryUniqueRow(String sql, Class clazz, Object[] params)

    List<Object> queryRows(String sql, Class clazz, Object[] params)

    Number queryNumber(String sql, Object[] params)
```


## 难点

1. 数据库中表信息的封装

    > 从数据库连接中获取数据库中所有的表的集合，遍历集合，将表的所有的属性转化为 `java` 类型后，将字段名称和对应的 `java` 类型保存到 `TableInfo` 对象的 `Map<String, ColumnInfo>` 中，**表名** 和 **`TableInfo` 对象** 保存在 `Map<Class, TableInfo>` 中，用于后面的 **根据数据库生成 `java bean`** 和 **通过对象对数据库进行操作**

2. `ReflectUtils` 通过反射调用对象指定成员变量的 `setter | getter` 方法

3. 使用对象实例来完成对数据库的操作

    > `Query # executeDML` 为核心方法，建立数据库连接，通过 `PreparedStatement` 使用给定的 `Object[] args` 参数列表来和各种方法拼出来的 `sql` 语句来完成对数据的操作


