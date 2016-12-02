package com.onlineDiary.logic.dao;

import com.onlineDiary.logic.beans.Subject;
import com.onlineDiary.logic.executor.Executor;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO {
    private Executor executor;

    public SubjectDAO(Connection connection) {
        executor = new Executor(connection);
    }

    public List<Subject> getSubjectByYear(int year) {
        String query = "SELECT subjects.name, subjects.id FROM subjects_in_classes " +
                "INNER JOIN subjects ON subject_id = subjects.id WHERE year_id = " + year;
        return executor.execQuery(query, resultSet -> {
           List<Subject> subjects = new ArrayList<>();
           while (resultSet.next()) {
               String subjectName = resultSet.getString(1);
               int subjId = resultSet.getInt(2);
               subjects.add(new Subject(subjectName, subjId));
           }
           return subjects;
        });
    }

    public String getSubjectNameById(int id) {
        String query = "SELECT name FROM subjects WHERE id=" + id;
        return executor.execQuery(query, resultSet -> {
            String subjectName;
            resultSet.next();
            subjectName = resultSet.getString(1);
            return subjectName;

        });
    }
}
