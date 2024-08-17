package org.leochen.sample;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static org.leochen.utils.Util.*;

@Slf4j
public class Sample {

    public static void main(String[] args) {
        test0(false);
        test1(false);
        test2(false);
        test3(false);
        test4(false);
        test5(false);
        test6(false);
        test7(false);
        test8(true);
    }

    private static void test0(boolean runCheck){
        if(!runCheck){return;}
        log.info("测试: 綜合");
    }

    private static void test8(boolean runCheck){
        if(!runCheck){return;}
        log.info("测试: 讀取 resources 下的 properties");

        Properties prop = loadProperties("app.properties");
        prop.forEach((key, value) -> {
            log.info( "{} = {}" , key , value);
        });
    }

    private static void test7(boolean runCheck){
        if(!runCheck){return;}
        log.info("测试: Class 所在位置 & 執行目錄(user.dir)");

        log.info("getClassLocation: {}", getClassLocation());
        log.info("user.dir: {}", System.getProperty("user.dir"));
    }

    private static void test6(boolean runCheck){
        if(!runCheck){return;}
        log.info("测试: 排序存在數字的 String 內容List");

        List<String> places = Arrays.asList("123", "234", "12", "John", "88");
        log.info("sortInt: {}", sortStringList(places));
    }

    private static void test5(boolean runCheck){
        if(!runCheck){return;}
        log.info("测试: 文字轉數字");

        log.info("This text is number or not: {}", canBeNumber("Hello"));
        log.info("now process ID: {}", getPID());
    }

    private static void test1(boolean runCheck){
        if(!runCheck){return;}
        log.info("測試日誌");
    }


    private static void test2(boolean runCheck){
        if(!runCheck){return;}
        log.info("测试: 检查目录");

        String folderPath = "./db";
        makeFolderExist(folderPath);
    }

    private static void test3(boolean runCheck){
        if(!runCheck){return;}
        log.info("测试: 列出內容");

        listFiles(System.getProperty("user.dir"));
    }

    private static void test4(boolean runCheck){
        if(!runCheck){return;}
        log.info("测试 sqlite");

        Connection connection = null;
        Statement statement = null;
        try {
            // 加载SQLite驱动程序
            Class.forName("org.sqlite.JDBC");

            // 创建数据库连接
            connection = DriverManager.getConnection("jdbc:sqlite:./db/data.sqlite");

            // 创建Statement对象
            statement = connection.createStatement();

            // 创建表
            String createTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT UNIQUE, " +
                    "age INTEGER " +
                    ") "
                    ;
            statement.executeUpdate(createTableSQL);

            // 插入数据
            String insertSQL = "INSERT INTO users (name, age) VALUES ('John', 25)";
            statement.executeUpdate(insertSQL);

            log.info("数据插入成功！");
        } catch (org.sqlite.SQLiteException e) {
            // ref. https://www.sqlite.org/rescode.html#constraint
//            log.info("可能是重複了: \n{}:\n{}:\n{}", e.getMessage(), e.getErrorCode(), e.getResultCode().name());
//            e.printStackTrace();
            if( e.getErrorCode() == 19 ){
                log.info("數據重複:({}, {})", e.getErrorCode(), e.getResultCode().name());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭Statement和Connection
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
