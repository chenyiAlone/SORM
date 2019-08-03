package com.sorm.util;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sorm.bean.ColumnInfo;
import com.sorm.bean.JavaFieldGetSet;
import com.sorm.bean.TableInfo;
import com.sorm.core.DBManager;
import com.sorm.core.MysqlTypeConvertor;
import com.sorm.core.TableContext;
import com.sorm.core.TypeConvertor;

/**
 * 
 * ClassName: JavaFileUtils
 * @author chenyiAlone  
 * Create Time: 2018/01/05 16:52:10
 * Description: 文件操作类
 * 1. 根据 ColumnInfo 对象来成 setter|getter 方法
 * 2. 通过 IO 在指定目录下生成对应的类
 * 
 */
public class JavaFileUtils {

    /**
     * test
     * @param args
     */
    public static void main(String[] args) {
        Map<String, TableInfo> tables = TableContext.tables;
        for (TableInfo table : tables.values()) {
            createJavaPoFile(table, new MysqlTypeConvertor());
        }
    }

    /**
     * 生成相应的 setter|getter 方法
     * 
     * @param column 字段信息
     * @param convertor 类型转换器
     * @return 封装的 setter|getter 方法
     */
    public static JavaFieldGetSet createFieldSetGetSRC(ColumnInfo column, TypeConvertor convertor) {
        // 数据类型
        String type = convertor.databaseType2JavadataType(column.getDataType());
        StringBuilder fieldSrc = new StringBuilder("\tprivate " + type + " " + column.getName() + ";\n\n");

        StringBuilder setSrc = new StringBuilder();
        // 拼接 set方法
        // public void setXxx(){this.xxx = xxx;}
        setSrc.append("\tpublic void set");
        setSrc.append(StringUtils.firstCharUpperCase(column.getName()) + " (");
        setSrc.append(type + " " + column.getName() + "){\n");
        setSrc.append("\t\tthis." + column.getName() + "=" + column.getName() + ";\n");
        setSrc.append("\t}\n\n");

        // 拼接 get方法
        // public void getXxx(){this.xxx = xxx;}
        StringBuilder getSrc = new StringBuilder();
        getSrc.append("\tpublic " + type + " get");
        getSrc.append(StringUtils.firstCharUpperCase(column.getName()) + " (){\n");
        getSrc.append("\t\treturn this." + column.getName() + ";\n");
        getSrc.append("\t}\n\n");

        return new JavaFieldGetSet(fieldSrc.toString(), setSrc.toString(), getSrc.toString());
    }

    /**
     * 生成表信息对应的类
     * 
     * @param tableInfo
     * @param convertor
     */
    public static void createJavaPoFile(TableInfo tableInfo, TypeConvertor convertor) {
        // 生成 java 源码
        String javaSrc = createJavaSrc(tableInfo, convertor);

        // 获得 srcPath
        String srcPath = DBManager.getConf().getSrcPath() + '\\';

        // 获得 po 包
        String packagePath = DBManager.getConf().getPoPackage().replace('.', '/');

        File f = new File(srcPath + packagePath);
        // 判断目录是否存在
        if (!f.exists()) {
            f.mkdir();
        }

        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(
                    f.getAbsolutePath() + '/' + StringUtils.firstCharUpperCase(tableInfo.getTname()) + ".java"));
            bw.write(javaSrc);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeIo(bw);
        }
    }

    /**
     * 生成 java 源码
     * 
     * @param tableInfo
     * @param convertor
     */
    public static String createJavaSrc(TableInfo tableInfo, TypeConvertor convertor) {
        // 类名
        String tname = tableInfo.getTname();

        // 类的全部属性-->Map
        Map<String, ColumnInfo> map = tableInfo.getColumns();

        // 存放FieldSetGet
        List<JavaFieldGetSet> list = new ArrayList<JavaFieldGetSet>();

        // 遍历Map获得其中的所有的Values
        for (ColumnInfo columnInfo : map.values()) {
            list.add(createFieldSetGetSRC(columnInfo, new MysqlTypeConvertor()));
        }

        // 拼javaSrc
        StringBuilder src = new StringBuilder();
        src.append("package com.po;\n\n");
        src.append("import java.sql.*;\n");
        src.append("import java.util.*;\n\n");
        src.append("public class " + StringUtils.firstCharUpperCase(tname) + " {\n\n\n");

        // 拼接所有的成员变量
        for (JavaFieldGetSet j : list) {
            src.append(j.getFieldInfo());
        }

        // 拼接所有的 set|get 方法
        for (JavaFieldGetSet j : list) {
            src.append(j.getGetInfo());
            src.append(j.getSetInfo());
        }
        src.append("}");
//        System.out.println(src.toString());
        return src.toString();

    }

    /**
     * close IO
     * @param ios
     */
    public static void closeIo(Closeable ... ios) {
        for (Closeable io : ios) {
            if (io != null) {
                try {
                    io.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
}
