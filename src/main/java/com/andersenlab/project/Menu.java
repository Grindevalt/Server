package com.andersenlab.project;

import com.andersenlab.dao.CourseDao;
import com.andersenlab.dao.StudentDao;
import com.andersenlab.entities.Course;
import com.andersenlab.entities.Student;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

/**
 * Class for creating commandLine application
 *
 * @author Vlad Badilovskii
 * @version 1.0
 */

public class Menu {
    private static final Logger LOGGER = Logger.getLogger(Menu.class.getSimpleName());
    private static final String END = "end";

    public static void startMenu(StudentDao studentDao, CourseDao courseDao, BufferedReader reader, PrintWriter writer) throws IOException, SQLException {
        LOGGER.info("Entering to startMenu");
        welcomeMain(writer);
        int menuChoice = Integer.parseInt(reader.readLine());
        LOGGER.info("Reading mainMenu choiceRequest from client is successful");
        switch (menuChoice) {
            case 1:
                studentMenu(studentDao, reader, courseDao, writer);
                LOGGER.info("Client chose student database");
                break;
            case 2:
                courseMenu(courseDao, reader, studentDao, writer);
                LOGGER.info("Client chose course database");
                break;
            case 3:
                System.exit(0);
                LOGGER.info("Client want to exit");
                break;
            default:
                writer.println("You pushed wrong button, please think little bit and try again");
                LOGGER.info("Client made menuChoice mistake and was returned to startMenu");
                startMenu(studentDao, courseDao, reader, writer);
                break;
        }
    }

    private static void welcomeMain(PrintWriter writer) {
        writer.println("Hello, choose the base from which you want to work");
        writer.println("1 - Students database");
        writer.println("2 - Courses database");
        writer.println("3 - Exit");
        finisher(writer);

    }

    private static void courseMenu(CourseDao courseDao, BufferedReader reader, StudentDao studentDao, PrintWriter writer) throws IOException, SQLException {
        LOGGER.info("Entering to courseMenu");
        welcomeCourse(writer);
        int courseChoice = Integer.parseInt(reader.readLine());
        LOGGER.info("Reading course choiceRequest from client is successful");
        switch (courseChoice) {
            case 1:
                showCourseTable(courseDao, reader, studentDao, writer);
                LOGGER.info("Client chose to show the list of courses");
                break;
            case 2:
                addCourse(courseDao, reader, studentDao, writer);
                LOGGER.info("Client chose to add course to database");
                break;

            case 3:
                removeCourse(courseDao, reader, studentDao, writer);
                LOGGER.info("Client chose to remove course from database");
                break;
            case 4:
                findCourse(courseDao, reader, studentDao, writer);
                break;
            case 5:
                startMenu(studentDao, courseDao, reader, writer);
                LOGGER.info("Client chose to return to mainMenu");
            default:
                writer.println("You pushed wrong button, please think a little and try again");
                LOGGER.info("Client made a mistake and was returned to courseMenu");
                finisher(writer);
                courseMenu(courseDao, reader, studentDao, writer);
                break;
        }
    }

    private static void welcomeCourse(PrintWriter writer) {
        writer.println("Welcome to Courses database");
        writer.println("Select what you want to do with the database");
        writer.println("1 - Show list of Courses");
        writer.println("2 - Add course to database");
        writer.println("3 - Remove course from database");
        writer.println("4 - Find course by id");
        writer.println("5 - Back");
        finisher(writer);
    }

    private static void showCourseTable(CourseDao courseDao, BufferedReader reader, StudentDao studentDao, PrintWriter writer) throws SQLException, IOException {
        List<Course> courses = courseDao.getCouses();
        writer.println("~~~~~~~~~TABLE COURSES~~~~~~~~~~");
        writer.println("       id       ||     name     ");
        writer.println("--------------------------------");
        for (Course course : courses) {
            writer.println("       " + course.getCourseId() + "           " + course.getCourseName());
        }
        LOGGER.info("List of courses is successfully shown");
        courseMenu(courseDao, reader, studentDao, writer);
    }

    private static void addCourse(CourseDao courseDao, BufferedReader reader, StudentDao studentDao, PrintWriter writer) throws IOException, SQLException {
        writer.println("Please enter course name");
        finisher(writer);
        String name = reader.readLine();
        Course course = new Course(name);
        courseDao.addCourse(course);
        writer.println("Course is successfully added " + course.toString());
        LOGGER.info("Course is successfully added");
        courseMenu(courseDao, reader, studentDao, writer);
    }

    private static void removeCourse(CourseDao courseDao, BufferedReader reader, StudentDao studentDao, PrintWriter writer) throws IOException, SQLException {
        writer.println("Please enter the  ID of the course you want to delete");
        finisher(writer);
        try {
            long idRemove = Long.parseLong(reader.readLine());
            Course course1 = courseDao.getCourse(idRemove);
            courseDao.deleteCourse(course1);
            writer.println("Course is successfully removed");
            LOGGER.info("Course is successfully removed");
            courseMenu(courseDao, reader, studentDao, writer);
        } catch (NumberFormatException e) {
            LOGGER.warn("Client trying to enter String format", e);
            LOGGER.info("Client was returned to courseMenu");
            writer.println("You pushed wrong button, please think a little and try again");
            finisher(writer);
            removeCourse(courseDao, reader, studentDao, writer);
        }
    }

    private static void findCourse(CourseDao courseDao, BufferedReader reader, StudentDao studentDao, PrintWriter writer) throws IOException, SQLException {
        writer.println("Please enter the  ID of the course you want to find");
        finisher(writer);
        try {
            long idFind = Long.parseLong(reader.readLine());
            Course course2 = courseDao.getCourse(idFind);
            writer.println(course2.toString());
            courseMenu(courseDao, reader, studentDao, writer);
            LOGGER.info("Course is successfully founded");
            writer.println();
        } catch (NumberFormatException e) {
            writer.println("You trying to enter string format value, please enter a number");
            LOGGER.warn("Client trying to enter String format", e);
            LOGGER.info("Client was returned to courseMenu");
            finisher(writer);
            findCourse(courseDao, reader, studentDao, writer);
        }
    }

    private static void studentMenu(StudentDao studentDao, BufferedReader reader, CourseDao courseDao, PrintWriter writer) throws IOException, SQLException {
        LOGGER.info("Entering to studentMenu");
        welcomeStudent(writer);
        int studentChoice = Integer.parseInt(reader.readLine());
        LOGGER.info("Reading student choiceRequest from client is successful");
        switch (studentChoice) {
            case 1:
                showStudentTable(studentDao, reader, courseDao, writer);
                LOGGER.info("Client chose to show the list of students");
                break;
            case 2:
                addStudent(studentDao, reader, courseDao, writer);
                LOGGER.info("Client chose to add student to database");
                break;
            case 3:
                addCourseToStudent(studentDao, reader, courseDao, writer);
                LOGGER.info("Client chose to add course to student");
                break;

            case 4:
                removeStudent(studentDao, reader, courseDao, writer);
                LOGGER.info("Client chose to remove student from database");
                break;
            case 5:
                findStudent(studentDao, reader, courseDao, writer);
                LOGGER.info("Client chose to find student");
                break;
            case 6:
                startMenu(studentDao, courseDao, reader, writer);
                LOGGER.info("Client chose to return to mainMenu");
            default:
                writer.println("You pushed wrong button, please think a little and try again");
                finisher(writer);
                LOGGER.info("Client made a mistake and was returned to studentMenu");
                studentMenu(studentDao, reader, courseDao, writer);
                break;
        }
    }

    private static void welcomeStudent(PrintWriter writer) {
        writer.println("Welcome to Students database");
        writer.println("Select what you want to do with the database");
        writer.println("1 - Show list of students");
        writer.println("2 - Add student to database");
        writer.println("3 - Add course to student");
        writer.println("4 - Remove student from database");
        writer.println("5 - Find student by id");
        writer.println("6 - Back");
        finisher(writer);
    }

    private static void addStudent(StudentDao studentDao, BufferedReader reader, CourseDao courseDao, PrintWriter writer) throws IOException, SQLException {
        writer.println("Please enter student name");
        finisher(writer);
        String name = reader.readLine();
        Student student = new Student(name);
        studentDao.addStudent(student);
        writer.println("Student is successfully added to database" + student.toString());
        LOGGER.info("Student is successfully added to database");
        studentMenu(studentDao, reader, courseDao, writer);
    }

    private static void showStudentTable(StudentDao studentDao, BufferedReader reader, CourseDao courseDao, PrintWriter writer) throws SQLException, IOException {
        List<Student> students = studentDao.getStudents();
        writer.println("~~~~~~~~TABLE STUDENTS~~~~~~~~~~");
        writer.println("       id       ||     name     ");
        writer.println("--------------------------------");
        for (Student student : students) {
            writer.println("       " + student.getStudentId() + "             " + student.getStudentName());
        }
        LOGGER.info("List of students is successfully shown");
        studentMenu(studentDao, reader, courseDao, writer);
    }

    private static void addCourseToStudent(StudentDao studentDao, BufferedReader reader, CourseDao courseDao, PrintWriter writer) throws IOException, SQLException {
        try {
            writer.println("Please enter the student ID that you want to add the course to");
            finisher(writer);

            long id3 = Long.parseLong(reader.readLine());
            Student student1 = studentDao.getStudent(id3);
            writer.println("Please enter the course that you want to add to the student");
            finisher(writer);

            String nameAddedCourse = reader.readLine();
            Course course = new Course(nameAddedCourse);
            courseDao.addCourse(course);
            student1.getCourses().add(course);
            studentDao.updateStudent(student1);

            writer.println("Course is successfully added to student");
            LOGGER.info("Course is successfully added to student");

            studentMenu(studentDao, reader, courseDao, writer);
            finisher(writer);
        } catch (NumberFormatException e) {
            writer.println("You trying to enter string format value to ID field, please enter a number");
            LOGGER.warn("Client trying to enter string format value", e);
            finisher(writer);
            LOGGER.info("Client was returned to start of the method");
            addCourseToStudent(studentDao, reader, courseDao, writer);
        }

    }


    private static void findStudent(StudentDao studentDao, BufferedReader reader, CourseDao courseDao, PrintWriter writer) throws IOException, SQLException {
        writer.println("Please enter the student ID of the student you want to find");
        finisher(writer);
        try {
            long id1 = Long.parseLong(reader.readLine());
            Student student2 = studentDao.getStudent(id1);
            writer.println(student2.toString());
            LOGGER.info("Student is successfully found");
            studentMenu(studentDao, reader, courseDao, writer);
        } catch (NumberFormatException e) {
            writer.println("You trying to enter string format value, please enter a number");
            LOGGER.warn("Client trying to enter string format value", e);
            LOGGER.info("Client was returned to start of the method");
            findStudent(studentDao, reader, courseDao, writer);
        }

    }

    private static void removeStudent(StudentDao studentDao, BufferedReader reader, CourseDao courseDao, PrintWriter writer) throws IOException, SQLException {
        writer.println("Please enter the ID of the student you want to delete");
        finisher(writer);
        try {
            long id = Long.parseLong(reader.readLine());
            Student student123 = studentDao.getStudent(id);
            studentDao.deleteStudent(student123);
            writer.println("Student is successfully removed");
            LOGGER.info("Student is successfully removed");
            studentMenu(studentDao, reader, courseDao, writer);
        } catch (NumberFormatException e) {
            writer.println("You trying to enter string format value, please enter a number");
            LOGGER.warn("Client trying to enter string format value", e);
            LOGGER.info("Client was returned to start of the removeStudent method");
            removeStudent(studentDao, reader, courseDao, writer);
        }
    }

    private static void finisher(PrintWriter writer) {
        writer.println(END);
        writer.flush();
    }

}
