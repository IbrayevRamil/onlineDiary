package com.onlineDiary.logic.dao;

import com.onlineDiary.logic.beans.SClass;
import com.onlineDiary.logic.executor.Executor;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class SClassDAO {
    private Executor executor;

    public SClassDAO(Connection connection) {
        executor = new Executor(connection);
    }

    public List<SClass> getAllClasses() {
        return executor.execQuery("SELECT id, year_id, letter FROM classes", resultSet -> {
            List<SClass> classes = new ArrayList<>();
            while (resultSet.next()) {
                classes.add(new SClass(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3)));
            }
            return classes;
        });
    }
}
