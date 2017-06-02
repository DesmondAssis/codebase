package com.desmond.codebase.dongge;

import ch.qos.logback.core.db.dialect.DBUtil;
import com.desmond.codebase.dongge.vo.User;
import com.desmond.codebase.jdbc.ConnectionDB;
import com.desmond.codebase.jdbc.DataBaseEnum;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Li.Xiaochuan on 17/2/21.
 */
public class UserDao {
    public Integer getIdByUser(User user) throws SQLException {
        String sql = "select id from user1 where username = '" + user.getUsername()
                +"' and password = '" + user.getPassword() + "'";
        Connection con = new ConnectionDB().getConnection(DataBaseEnum.TEST);
        Statement ps = null;
        ResultSet rs = null;
        Integer id = null;

        try {
            ps = con.createStatement();

            rs = ps.executeQuery(sql);

            if(rs.next())
                id = rs.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            con.close();
        }

        return id;
    }

    public static void main(String[] args) throws SQLException {
        User user = new User("栋哥", "123456");
        UserDao userDao = new UserDao();
        System.out.println(userDao.getIdByUser(user));
    }
}
