package com.andersenlab.project;

import com.andersenlab.dao.CourseDao;
import com.andersenlab.dao.StudentDao;
import com.andersenlab.util.Config;
import com.andersenlab.util.Factory;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

/**
 * Main class for creating server application
 *
 * @author Vlad Badilovskii
 * @version 1.0
 */
public class ServerApp {
    private static final Logger LOGGER = Logger.getLogger(ServerApp.class.getSimpleName());
    private static final String LOG_PROPERTIES_FILE = "C:\\Users\\Grindevalt\\Documents\\GitHub\\Server\\src\\main\\resources\\log4j.properties";

    public static void main(String[] args) throws IOException {
        Config config = new Config(LOG_PROPERTIES_FILE);
        config.init();

        LOGGER.info("Starting the program");
        Factory factory = Factory.getInstance();
        StudentDao studentDao = factory.getStudentDao();
        CourseDao courseDao = factory.getCourseDao();
        System.out.println("Welcome to Server side");

        ServerSocket servers = null;
        Socket fromClient = null;

        try {
            servers = new ServerSocket(4444);
            LOGGER.info("ServerSocket is successfully created");
        } catch (IOException e) {
            System.out.println("Couldn't listen to port 4444");
            LOGGER.warn("Could not listen to port 4444", e);
            System.exit(-1);
        }

        try {
            System.out.print("Waiting for a client...");
            fromClient = servers.accept();
            System.out.println("Client connected");
            LOGGER.info("Client is successfully connected");
        } catch (IOException e) {
            System.out.println("Can't accept");
            LOGGER.warn("Problems with client connection", e);
            System.exit(-1);
        }

        BufferedReader requestReader = new BufferedReader(new InputStreamReader(fromClient.getInputStream()));
        LOGGER.info("BufferedReader requestReader is successfully created");
        PrintWriter answerWriter = new PrintWriter(new OutputStreamWriter(new BufferedOutputStream(fromClient.getOutputStream())));
        LOGGER.info("PrintWriter answerWriter is successfully created");

        try {
            LOGGER.info("Launching startMenu");
            Menu.startMenu(studentDao, courseDao, requestReader, answerWriter);
        } catch (SQLException e) {
            LOGGER.warn("Launching startMenu is failed", e);
        } finally {
            servers.close();
            LOGGER.info("ServerSocket is successfully closed");
            fromClient.close();
            LOGGER.info("Socket fromClient is successfully closed");
            requestReader.close();
            LOGGER.info("BufferedReader requestReader is successfully closed");
            answerWriter.close();
            LOGGER.info("PrintWriter answerWriter is successfully closed");
        }
    }
}




