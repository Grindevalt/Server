package com.andersenlab.dao.daoImpl;

import com.andersenlab.dao.StudentDao;
import com.andersenlab.entities.Student;
import com.andersenlab.util.HibernateUtil;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO for table student in database
 *
 * @author Vlad Badilovskii
 * @version 100500
 */
public class StudentDaoImpl implements StudentDao {

    /**
     * Method for adding some student to database
     *
     * @param student - input value for method
     * @throws SQLException - if will be some problems with database
     */
    public void addStudent(Student student) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(student);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("There was an exception in the method addStudent");
        } finally {
            if (session != null) session.close();
        }
    }

    public void updateStudent(Student student) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(student);
            session.save(student);
            session.getTransaction().commit();
        } catch (Exception e){
            System.out.println("There was an exception in the method updateStudent");
        } finally {
            if (session != null) session.close();
        }
    }

    /**
     * Method for removing some student from database
     *
     * @param student - input value for method
     * @throws SQLException
     */
    public void deleteStudent(Student student) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(student);
            session.getTransaction().commit();
        } catch (Exception e) {

            System.out.println("There was an exception in the method deleteStudent");
        } finally {
            if (session != null) session.close();
        }

    }

    /**
     * Method for getting some student from database by id
     *
     * @param id - input value for method
     * @return result - object of class Student
     * @throws SQLException - if will be some problems with database
     */
    public Student getStudent(long id) throws SQLException {
        Student result = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            result = (Student) session.get(Student.class, id);
        } catch (Exception e) {
            System.out.println("There was an exception in the method getStudent");
        } finally {
            if (session != null) session.close();
        }
        return result;
    }

    public List<Student> getStudents() throws SQLException {
        List<Student> students = new ArrayList<Student>();
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            students = session.createCriteria(Student.class).list();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return students;
    }
}
