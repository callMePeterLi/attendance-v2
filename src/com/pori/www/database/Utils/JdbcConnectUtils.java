package com.pori.www.database.Utils;

import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Jdbc获取数据库连接
 * <p>
 * 连接数据库所需要的参数
 * url：
 * oracle：jdbc:oracle:thin:@192.168.0.81:1521:zjyiyaogufen
 * sqlserver：jdbc:sqlserver://192.168.0.197:1433;databaseName=xiya-att
 * mysql：jdbc:mysql://192.168.3.10:3306/eims?useUnicode=true&characterEncoding=UTF-8
 * <p>
 * driver:
 * oracle：oracle.jdbc.driver.OracleDriver（oracle 9i版本后）
 * sqlserver：com.microsoft.sqlserver.jdbc.SQLServerDriver
 * mysql：com.mysql.jdbc.Driver
 *
 * @author libo
 * @date 2017/12/25 下午1:33
 */
public class JdbcConnectUtils {
    private static final String ACCESS_DRIVER_CONTAINS = "access";
    private static final String ACCESS_DRIVER = "com.hxtt.sql.access.AccessDriver";

    /**
     * 默认连接超时时长为5000毫秒
     */
    private long timeoutConnection = 5000L;

    private long currentTime = 0L;

    private String url;
    private String driver;
    private String user;
    private String password;

    public JdbcConnectUtils(String url, String driver, String user,
                            String password) {
        this.url = url;
        this.driver = driver;
        this.user = user;
        this.password = password;
    }

    /**
     * 获取数据库连接
     *
     * @param timeoutMillisecond 超时时长
     * @return java.sql.Connection
     * @throws Exception Exception
     */
    public Connection getConnection(Long timeoutMillisecond) throws Exception {
        if (StringUtils.isEmpty(this.url) || StringUtils.isEmpty(this.driver) || StringUtils.isEmpty(this.user) ||
                StringUtils.isEmpty(this.password)) {
            throw new NullPointerException("one of url,driver,user,password is empty!");
        }

        // 超时时间不能小于5000毫秒，若小于，则使用默认的timeoutConnection。
        if (timeoutMillisecond > this.timeoutConnection) {
            this.timeoutConnection = timeoutMillisecond;
        }

        if (this.driver.toLowerCase().contains(ACCESS_DRIVER_CONTAINS)) {
            Class.forName(ACCESS_DRIVER).newInstance();
        } else {
            Class.forName(this.driver);
        }

        this.currentTime = System.currentTimeMillis();

        Connection conn = getConnection();

        return conn;
    }

    /**
     * 如果第一次获取数据库连接异常，则根据设置的超时时间来进行连接的再次获取。
     *
     * @return Connection
     * @throws SQLException java.sql.SQLException
     */
    private Connection getConnection() throws SQLException {
        Connection conn;
        try {
            conn = DriverManager.getConnection(this.url, this.user, this.password);
        } catch (SQLException e) {
            e.printStackTrace();
            if (System.currentTimeMillis() - this.currentTime < this.timeoutConnection) {
                getConnection();
            }

            throw new SQLException(e);
        }

        return conn;
    }
}
