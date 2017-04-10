package com.andersenlab.dao.daoImpl;

import com.andersenlab.dao.CourseDao;
import com.andersenlab.entities.Course;
import com.andersenlab.util.HibernateUtil;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO for table course in database
 *
 * @author Vlad Badilovskii
 * @version 100500
 */
public class CourseDaoImpl implements CourseDao {
    /**
     * Method for adding course to database
     *
     * @param course
     * @throws SQLException
     */
    public void addCourse(Course course) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(course);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("There was an exception in the method addCourse");
        } finally {
            if (session != null) session.close();
        }

    }

    /**
     * Method for removing some course from database
     *
     * @param course
     * @throws SQLException
     */
    public void deleteCourse(Course course) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(course);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("There was an exception in the method deleteCourse");
        } finally {
            if (session != null) session.close();
        }
    }

    /**
     * Method for getting some course from database by id
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public Course getCourse(long id) throws SQLException {
        Course result = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            result = (Course) session.get(Course.class, id);
        } catch (Exception e) {
            System.out.println("There was an exception in the method getCourse");
        } finally {
            if (session != null) session.close();
        }
        return result;
    }

    public List<Course> getCouses() throws SQLException {
        List<Course> courses = new ArrayList<Course>();
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            courses = session.createCriteria(Course.class).list();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return courses;
    }
}
