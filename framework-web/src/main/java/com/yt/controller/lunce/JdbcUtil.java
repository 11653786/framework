package com.yt.controller.lunce;

/**
 * @author zhangsan
 * @version 1.0
 * @package com.yt.lunce
 * @date 2016/5/23 0023 16:05
 * @descption: 疯狂的王麻子团队!
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * JdbcUtil.java
 *
 * @version 1.0
 * @createTime JDBC获取Connection工具类
 */
public class JdbcUtil {
    private static Connection conn = null;
    private static final String URL = "jdbc:mysql://127.0.0.1/framework?autoReconnect=true&characterEncoding=utf8";
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return conn;
    }
}
