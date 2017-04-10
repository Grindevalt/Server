package com.andersenlab.dao;

import com.andersenlab.entities.Student;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface for creating data access object for student entity
 *
 * @author Vlad Badilovskii
 * @version 100500
 */
public interface StudentDao {
    /**
     * Method for adding some student to database
     *
     * @param student
     * @throws SQLException
     */
    public void addStudent(Student student) throws SQLException;

    /**
     * Method for updating student data
     *
     * @param id
     * @throws SQLException
     */
    public void updateStudent(Student id) throws SQLException;


    /**
     * Method for removing some student from database
     *
     * @param student
     * @throws SQLException
     */
    public void deleteStudent(Student student) throws SQLException;

    /**
     * Method for getting some student from database by id
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public Student getStudent(long id) throws SQLException;

    /**
     *
     * @return
     * @throws SQLException
     */
    public List<Student> getStudents() throws SQLException;
}
