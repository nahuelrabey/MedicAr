package com.medicar.Medico;

import java.sql.Connection;
// import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MedicoSqlite {
    // create a database connection
    static String connection_string = "jdbc:sqlite:sample.db";

    static boolean check_if_table_exists(){
        try (
            Connection connection = DriverManager.getConnection(connection_string);
            Statement statement = connection.createStatement();) {
            statement.setQueryTimeout(30); // set timeout to 30 sec.

            ResultSet rs = statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='medicos';");
            return rs.next();
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            e.printStackTrace(System.err);
            return false;
        }
    }

    public static void create_table_db(){
        if (check_if_table_exists()) return;
        try {
            Connection connection = DriverManager.getConnection(connection_string);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30); // set timeout to 30 sec.

            statement.executeUpdate("drop table if exists medicos");
            statement.executeUpdate("""
                CREATE TABLE medicos (
                    medico_id INTEGER PRIMARY KEY,
                    name TEXT NOT NULL,
                    matricula TEXT NOT NULL
                );
                """);
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            e.printStackTrace(System.err);
        }
    }    

    public static void insert_into_db(String id, String name, String matricula){
        try {
            // Connection connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
            Connection connection = DriverManager.getConnection(connection_string);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30); // set timeout to 30 sec.

            statement.executeUpdate("insert into medicos values('" + id + "', '" + name + "', '" + matricula + "')");
            ResultSet rs = statement.executeQuery("select * from medicos");
            while (rs.next()) {
                // read the result set
                System.out.println("name = " + rs.getString("name"));
                System.out.println("id = " + rs.getInt("id"));
                System.out.println("matricula = " + rs.getString("matricula"));
            }
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            e.printStackTrace(System.err);
        }
    }

    public static void new_medico(String name, String matricula){
        try{
            Connection connection = DriverManager.getConnection(connection_string);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            
            statement.executeUpdate("insert into medicos (name, matricula) values('" + name + "', '" + matricula + "')");
            // ResultSet rs = statement.executeQ
        } catch (SQLException e){
            e.printStackTrace(System.err);
        }
    }

    public static void read_db(){
        try {
            Connection connection = DriverManager.getConnection(connection_string);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30); // set timeout to 30 sec.

            ResultSet rs = statement.executeQuery("select * from medicos");
            while (rs.next()) {
                // read the result set
                System.out.println("name = " + rs.getString("name"));
                System.out.println("id = " + rs.getInt("id"));
                System.out.println("matricula = " + rs.getString("matricula"));
            }
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            e.printStackTrace(System.err);
        }
    }

    public static void delete_table(){
        try {
            Connection connection = DriverManager.getConnection(connection_string);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30); // set timeout to 30 sec.

            statement.executeUpdate("drop table if exists medicos");
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }

}
