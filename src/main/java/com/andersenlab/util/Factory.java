package com.andersenlab.util;

import com.andersenlab.dao.CourseDao;
import com.andersenlab.dao.StudentDao;
import com.andersenlab.dao.daoImpl.CourseDaoImpl;
import com.andersenlab.dao.daoImpl.StudentDaoImpl;

/**
 * Factory pattern for creating DAO`s
 *
 * @author Vlad Badilovskii
 * @version 100500
 */
public class Factory {
    private static Factory instance = new Factory();
    private StudentDao studentDao;
    private CourseDao courseDao;

    private Factory() {
    }

    /**
     * Getter for property 'instance'.
     *
     * @return Value for property 'instance'.
     */
    public static Factory getInstance() {
        return instance;
    }

    /**
     * Getter for property 'studentDao'.
     *
     * @return Value for property 'studentDao'.
     */
    public StudentDao getStudentDao() {
        if (studentDao == null) studentDao = new StudentDaoImpl();
        return studentDao;
    }

    /**
     * Getter for property 'courseDao'.
     *
     * @return Value for property 'courseDao'.
     */
    public CourseDao getCourseDao() {
        if (courseDao == null) courseDao = new CourseDaoImpl();
        return courseDao;
    }
}
