package com.onlineDiary.logic.dao;

import com.onlineDiary.logic.beans.User;
import com.onlineDiary.logic.executor.Executor;

import java.sql.Connection;
import java.sql.ResultSet;

public class UserDAO {
    private Executor executor;

    public UserDAO(Connection connection) {
        executor = new Executor(connection);
    }

    public User getUser(String login) {
        String query = "SELECT login, password, role FROM users WHERE login = " + "\"" + login + "\"";
        return executor.execQuery(query, resultSet -> {
            resultSet.next();
            return new User(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3));
        });
    }

    public void insertUser(User user) {
        String query = "INSERT INTO users (login, password, role) " +
                "VALUES (" + "\"" + user.getLogin() + "\",\"" + user.getPassword() + "\",\"" + user.getRole() + "\")";
        executor.execUpdate(query);
    }

    public boolean isLoginExist(String login) {
        String query = "SELECT login FROM users WHERE login = " + "\"" + login + "\"";
        return executor.execQuery(query, ResultSet::wasNull);
    }

}
