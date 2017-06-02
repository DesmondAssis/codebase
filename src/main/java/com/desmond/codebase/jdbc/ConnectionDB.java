package com.desmond.codebase.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

/**
 * 数据库连接类
 * 说明:封装了 无参，有参，存储过程的调用
 * @author iflytek
 *
 */
public class ConnectionDB {

    /**
     * 数据库驱动类名称
     */
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    private static final String FILE_NAME = "/Users/gk/java/profile/jdbc-local.properties";
    private static final String WANZHOUMO_URL = "wanzhoumo.url";
    private static final String WANZHOUMO_UNAME = "wanzhoumo.username";
    private static final String WANZHOUMO_PWD = "wanzhoumo.password";

    private static final String DATACENTER_URL = "datacenter.url";
    private static final String DATACENTER_UNAME = "datacenter.username";
    private static final String DATACENTER_PWD = "datacenter.password";

    private static final String T_URL = "t.url";
    private static final String T_UNAME = "t.username";
    private static final String T_PWD = "t.password";

    /**
     * 连接字符串
     */
    private static String WZMJdbcUrl = "";
    private static String DCJdbcUrl = "";
    private static String TESTJdbcUrl = "";

    /**
     * 用户名
     */
    private static String WZMUsername = "";
    private static String DCUsername = "";
    private static String TESTUsername = "";

    /**
     * 密码
     */
    private static String WZMPwd = "";
    private static String DCPwd = "";
    private static String TESTPwd = "";

    /**
     * 创建数据库连接对象
     */
    private Connection connnection = null;

    /**
     * 创建PreparedStatement对象
     */
    private PreparedStatement preparedStatement = null;

    /**
     * 创建CallableStatement对象
     */
    private CallableStatement callableStatement = null;

    /**
     * 创建结果集对象
     */
    private ResultSet resultSet = null;

    static {
        try {
            // 加载数据库驱动程序
            Class.forName(DRIVER);

            Properties properties = new Properties();
            properties.load(new FileInputStream(new File(FILE_NAME)));

            WZMJdbcUrl = properties.getProperty(WANZHOUMO_URL);
            WZMUsername = properties.getProperty(WANZHOUMO_UNAME);
            WZMPwd = properties.getProperty(WANZHOUMO_PWD);

            DCJdbcUrl = properties.getProperty(DATACENTER_URL);
            DCUsername = properties.getProperty(DATACENTER_UNAME);
            DCPwd = properties.getProperty(DATACENTER_PWD);

            TESTJdbcUrl = properties.getProperty(T_URL);
            TESTUsername = properties.getProperty(DATACENTER_UNAME);
            TESTPwd = properties.getProperty(DATACENTER_PWD);

        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动错误");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public Connection getConnection(DataBaseEnum dataBaseEnum) {
        switch (dataBaseEnum) {
            case WANZHOUMO:
                return getWZMConnection();
            case TEST:
                return getTestConnection();
            default:
                return getDataCenterConnection();
        }
    }
    /**
     * 建立数据库连接
     * @return 数据库连接
     */
    public Connection getWZMConnection() {
        try {
            // 获取连接
            connnection = DriverManager.getConnection(WZMJdbcUrl, WZMUsername,
                    WZMPwd);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connnection;
    }
    public Connection getDataCenterConnection() {
        try {
            // 获取连接
            connnection = DriverManager.getConnection(DCJdbcUrl, DCUsername,
                    DCPwd);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connnection;
    }

    public Connection getTestConnection() {
        try {
            // 获取连接
            connnection = DriverManager.getConnection(TESTJdbcUrl, TESTUsername,
                    TESTPwd);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connnection;
    }

    /**
     * insert update delete SQL语句的执行的统一方法
     * @param sql SQL语句
     * @param params 参数数组，若没有参数则为null
     * @return 受影响的行数
     */
    public int executeUpdate(DataBaseEnum dataBaseEnum, String sql, Object[] params) {
        // 受影响的行数
        int affectedLine = 0;

        try {
            // 获得连接
            connnection = this.getConnection(dataBaseEnum);
            // 调用SQL
            preparedStatement = connnection.prepareStatement(sql);

            // 参数赋值
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i + 1, params[i]);
                }
            }

            // 执行
            affectedLine = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            // 释放资源
            closeAll();
        }
        return affectedLine;
    }

    /**
     * SQL 查询将查询结果直接放入ResultSet中
     * @param sql SQL语句
     * @param params 参数数组，若没有参数则为null
     * @return 结果集
     */
    private ResultSet executeQueryRS(DataBaseEnum dataBaseEnum, String sql, Object[] params) {
        try {
            // 获得连接
            connnection = this.getConnection(dataBaseEnum);

            // 调用SQL
            preparedStatement = connnection.prepareStatement(sql);

            // 参数赋值
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i + 1, params[i]);
                }
            }

            // 执行
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return resultSet;
    }

    /**
     * SQL 查询将查询结果：一行一列
     * @param sql SQL语句
     * @param params 参数数组，若没有参数则为null
     * @return 结果集
     */
    public Object executeQuerySingle(DataBaseEnum dataBaseEnum, String sql, Object[] params) {
        Object object = null;
        try {
            // 获得连接
            connnection = this.getConnection(dataBaseEnum);

            // 调用SQL
            preparedStatement = connnection.prepareStatement(sql);

            // 参数赋值
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i + 1, params[i]);
                }
            }

            // 执行
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                object = resultSet.getObject(1);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeAll();
        }

        return object;
    }

    /**
     * 获取结果集，并将结果放在List中
     *
     * @param sql
     *            SQL语句
     * @return List
     * 	                     结果集
     */
    public List<Map<String, Object>> excuteQuery(DataBaseEnum dataBaseEnum, String sql, Object[] params) {
        // 执行SQL获得结果集
        ResultSet rs = executeQueryRS(dataBaseEnum, sql, params);

        // 创建ResultSetMetaData对象
        ResultSetMetaData rsmd = null;

        // 结果集列数
        int columnCount = 0;
        try {
            rsmd = rs.getMetaData();

            // 获得结果集列数
            columnCount = rsmd.getColumnCount();
        } catch (SQLException e1) {
            System.out.println(e1.getMessage());
        }

        // 创建List
        List<Map<String, Object>> list = new ArrayList<>();

        try {
            // 将ResultSet的结果保存到List中
            while (rs.next()) {
                Map<String, Object> map = new HashMap<String, Object>();
                for (int i = 1; i <= columnCount; i++) {
                    map.put(rsmd.getColumnLabel(i), rs.getObject(i));
                }
                list.add(map);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            // 关闭所有资源
            closeAll();
        }

        return list;
    }

    /**
     * 存储过程带有一个输出参数的方法
     * @param sql 存储过程语句
     * @param params 参数数组
     * @param outParamPos 输出参数位置
     * @param SqlType 输出参数类型
     * @return 输出参数的值
     */
    public Object excuteQuery(DataBaseEnum dataBaseEnum, String sql, Object[] params,int outParamPos, int SqlType) {
        Object object = null;
        connnection = this.getConnection(dataBaseEnum);
        try {
            // 调用存储过程
            callableStatement = connnection.prepareCall(sql);

            // 给参数赋值
            if(params != null) {
                for(int i = 0; i < params.length; i++) {
                    callableStatement.setObject(i + 1, params[i]);
                }
            }

            // 注册输出参数
            callableStatement.registerOutParameter(outParamPos, SqlType);

            // 执行
            callableStatement.execute();

            // 得到输出参数
            object = callableStatement.getObject(outParamPos);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            // 释放资源
            closeAll();
        }

        return object;
    }

    /**
     * 关闭所有资源
     */
    private void closeAll() {
        // 关闭结果集对象
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        // 关闭PreparedStatement对象
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        // 关闭CallableStatement 对象
        if (callableStatement != null) {
            try {
                callableStatement.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        // 关闭Connection 对象
        if (connnection != null) {
            try {
                connnection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}