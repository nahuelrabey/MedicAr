package com.medicar.Paciente;

import java.sql.Connection;
// import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PacienteSqlite {
    static String connection_string = "jdbc:sqlite:sample.db";

    static boolean check_if_table_exists(){
        try (
            Connection connection = DriverManager.getConnection(connection_string);
            Statement statement = connection.createStatement();) {
            statement.setQueryTimeout(30); // set timeout to 30 sec.

            ResultSet rs = statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='pacientes';");
            // boolean result = rs.next();
            while(rs.next()){
                System.out.println(rs.getString("name"));
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
            return false;
        }
        return false;
    }

    public static void create_table_db(){
        if (check_if_table_exists()) return;
        try {
            Connection connection = DriverManager.getConnection(connection_string);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30); // set timeout to 30 sec.

            statement.executeUpdate("drop table if exists pacientes");
            statement.executeUpdate("""
                CREATE TABLE pacientes (
                    paciente_id INTEGER PRIMARY KEY,
                    name TEXT NOT NULL,
                    dni TEXT NOT NULL
                );
                """);
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }

    public static void new_paciente(String nombre, String dni){
        try {
            Connection connection = DriverManager.getConnection(connection_string);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30); // set timeout to 30 sec.

            statement.executeUpdate("insert into pacientes (name, dni) values('"+nombre+"', '"+dni+"')");
            // ResultSet rs = statement.executeQuery("select * from paciente");
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }

    public static void delete_table(){
        try {
            Connection connection = DriverManager.getConnection(connection_string);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30); // set timeout to 30 sec.

            statement.executeUpdate("drop table if exists pacientes");
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }
}
