package com.pori.www.database;

import com.pori.www.database.Utils.JdbcConnectUtils;
import org.apache.commons.dbutils.DbUtils;

import java.sql.CallableStatement;
import java.sql.Connection;

/**
 * 数据库测试
 *
 * @author libo
 * @date 2018/3/21 下午3:47
 */
public class DatabaseTest {
    public static void main(String[] args) {
        Connection conn = null;
        CallableStatement cs = null;

        try {
            String url = "jdbc:oracle:thin:@192.168.0.81:1521:huadongyy2qi";
            String driver = "oracle.jdbc.driver.OracleDriver";
            String user = "newsoft";
            String password = "econage";

            JdbcConnectUtils jdbcConnectUtils = new JdbcConnectUtils(url, driver, user, password);
            conn = jdbcConnectUtils.getConnection(0L);

            cs = conn.prepareCall("{call sp_test001(?)}");
            cs.setInt(1, 1);
            cs.execute();

            System.out.println("执行成功了");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);
            DbUtils.closeQuietly(cs);
        }
    }
}
