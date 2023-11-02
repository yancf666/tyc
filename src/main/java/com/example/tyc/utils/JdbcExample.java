package com.example.tyc.utils;

import com.example.tyc.pojo.QYSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JdbcExample {
    // JDBC连接配置
    private static final String dburl = "jdbc:mysql://127.0.0.1:3306/qcc";
    private static final String username = "root";
    private static final String password = "ycf666";

    // 方法用于保存数据
    public static void saveData(String url, String qymc, String lsxzgxf, String lsbzxr, String other,String photo,String lssxbzxr) {
        Connection connection = null;

        try {
            // 注册MySQL JDBC驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 建立数据库连接
            connection = DriverManager.getConnection(dburl, username, password);

            // 执行插入数据的SQL语句，假设你有一个名为 your_table_name 的表
            String sql = "INSERT INTO qcc.qysource (url, qymc, lsxzbgxf, lsbzxr, zjlarq,photo,lssxbzxr) VALUES (?, ?, ?, ?, ?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, url);
            statement.setString(2, qymc);
            statement.setString(3, lsxzgxf);
            statement.setString(4, lsbzxr);
            statement.setString(5, other);
            statement.setString(6, photo);
            statement.setString(7, lssxbzxr);

            // 执行SQL语句
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭数据库连接
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 方法用于查询数据
    public static ArrayList<QYSource>  queryData(int minId, int maxId) {
        Connection connection = null;

            ArrayList<QYSource> maps = new ArrayList<>();
        try {
            // 注册MySQL JDBC驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 建立数据库连接
            connection = DriverManager.getConnection(dburl, username, password);

            // 执行查询数据的SQL语句
            String sql = "SELECT * FROM qcc.qysource WHERE id>=? and id <=? and  lsbzxr>0 and lsbzxr<40 order by zjlarq desc";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, minId);
            statement.setInt(2, maxId);

            // 执行SQL查询
            ResultSet resultSet = statement.executeQuery();

            // 处理查询结果
            while (resultSet.next()) {

                QYSource qySource = new QYSource();
                qySource.setId(resultSet.getInt("id") );
                qySource.setUrl(resultSet.getString("url"));
                qySource.setQymc(resultSet.getString("qymc"));
                qySource.setLsxzbgxf(resultSet.getString("lsxzbgxf"));
                qySource.setLsbzxr(resultSet.getString("lsbzxr"));
                qySource.setZjlarq(resultSet.getString("zjlarq"));
                qySource.setPhoto(resultSet.getString("photo"));
                qySource.setLssxbzxr(resultSet.getString("lssxbzxr"));
                maps.add(qySource);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭数据库连接
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return maps;
    }


}
