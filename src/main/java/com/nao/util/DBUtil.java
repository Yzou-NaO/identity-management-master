package com.nao.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @ClassName DBUtil
 * @Description Database util
 * @Author NaO
 * @Date 3/21/2019 5:37 PM
 * @Version 1.0
 **/
public class DBUtil {
    /**
     * 获取连接
     *
     * @return conn
     */
    public static Connection getConn() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://10.0.41.50:3306/otp?" +
                    "useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull" +
                    "&connectTimeout=1000&socketTimeout=6000","root","970423");
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
