package com.onlineDiary.logic.dao;

import com.onlineDiary.logic.beans.Mark;
import com.onlineDiary.logic.executor.Executor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MarkDAO {
    private Executor executor;

    public MarkDAO(Connection connection) {
        executor = new Executor(connection);
    }

    public List<Mark> getMarksBySubject(int studentId, int subjectId) {
        String query = "SELECT date, mark, subjects.name FROM marks " +
                "LEFT JOIN november ON november.day=marks.date " +
                "INNER JOIN subjects ON subjects.id=marks.id_subject " +
                "WHERE (id_teacher=1 AND id_student=" + studentId + " AND id_subject=" + subjectId + ")";
        return executor.execQuery(query, resultSet -> {
            List<Mark> marks = new ArrayList<>();
            while (resultSet.next()) {
                Date date = resultSet.getDate(1);
                int mark = resultSet.getInt(2);
                String subjectName = resultSet.getString(3);
                marks.add(new Mark(subjectName, date, mark));
            }
            return marks;
        });
    }

    public void addMark(int studId, int subjId, Date date, int mark) {
        String query = "INSERT INTO marks (id_teacher, id_subject, id_student, date, mark)" +
                " VALUE (1," + subjId + "," + studId + "," + "\"" + date + "\"," + mark + ")";
        executor.execUpdate(query);
    }
}
