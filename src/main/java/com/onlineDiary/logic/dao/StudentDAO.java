package com.onlineDiary.logic.dao;


import com.onlineDiary.logic.beans.Student;
import com.onlineDiary.logic.executor.Executor;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private Executor executor;

    public StudentDAO(Connection connection) {
        executor = new Executor(connection);
    }

    public List<Student> getStudents(int classId) {
        String query = "SELECT surname, name, patronymic, id FROM students WHERE class_id = " + classId;
        return executor.execQuery(query, resultSet -> {
            List<Student> students = new ArrayList<>();
            while (resultSet.next()) {
                String surname = resultSet.getString(1);
                String name = resultSet.getString(2);
                String patronymic = resultSet.getString(3);
                int id = resultSet.getInt(4);
                students.add(new Student(name, surname, patronymic, classId, id));
            }
            return students;
        });
    }

    public void addStudent(int classId, String name, String surname, String patronymic) {
        String query = "INSERT INTO students (surname, name, patronymic, class_id) VALUE " +
                "(" + "\"" + surname + "\""+"," + "\"" + name +"\"" + "," + "\"" +patronymic +"\""+ "," +
                "\"" +classId + "\"" +")";
        executor.execUpdate(query);
    }
}
