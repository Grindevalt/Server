package com.andersenlab.dao;

import com.andersenlab.entities.Course;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface for creating data access object for course entity
 *
 * @author Vlad Badilovskii
 * @version 100500
 */
public interface CourseDao {
    /**
     * Method for adding course to database
     *
     * @param course
     * @throws SQLException
     */
    public void addCourse(Course course) throws SQLException;

    /**
     * Method for removing some course from database
     *
     * @param course
     * @throws SQLException
     */
    public void deleteCourse(Course course) throws SQLException;

    /**
     * Method for getting some course from database by id
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public Course getCourse(long id) throws SQLException;

    /**
     *
     * @return
     * @throws SQLException
     */
    public List<Course> getCouses() throws SQLException;
}
