package com.andersenlab.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity "student" for database
 *
 * @author Vlad Badilovskii
 * @version 100500
 */
@Entity
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @Column(name = "name")
    private String studentName;

    @ManyToMany(cascade = {CascadeType.ALL},fetch = FetchType.EAGER, targetEntity = Course.class)
    @JoinTable(name = "student_course"
            , joinColumns = {@JoinColumn(name = "student_id")}
            , inverseJoinColumns = {@JoinColumn(name = "course_id")})
    private List<Course> courses = new ArrayList<Course>();


    public Student(String studentName) {
        this.studentName = studentName;
    }

    public Student() {
    }

    /**
     * Getter for parameter Id
     *
     * @return Value for property 'studentId'.
     */
    public Long getStudentId() {
        return studentId;
    }

    /**
     * Setter for parameter Id
     *
     * @param studentId
     */
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    /**
     * Getter for parameter studentName
     *
     * @return Value for property 'studentName'.
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * Setter for parameter studentName
     *
     * @param studentName
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
     * Method for getting list of courses for some student
     *
     * @return Value for property 'courses'.
     */

    public List<Course> getCourses() {
        return courses;
    }

    /**
     * Method for setting list of courses for some student
     *
     * @param courses
     */
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    /**
     * Method equals for Student class
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (studentId != null ? !studentId.equals(student.studentId) : student.studentId != null) return false;
        if (studentName != null ? !studentName.equals(student.studentName) : student.studentName != null) return false;
        return courses != null ? courses.equals(student.courses) : student.courses == null;
    }

    /**
     * Method hashCode for Student class
     *
     * @return
     */
    @Override
    public int hashCode() {
        int result = studentId != null ? studentId.hashCode() : 0;
        result = 31 * result + (studentName != null ? studentName.hashCode() : 0);
        result = 31 * result + (courses != null ? courses.hashCode() : 0);
        return result;
    }

    /**
     * Method toString for Student class
     *
     * @return
     */
    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                '}';
    }
}