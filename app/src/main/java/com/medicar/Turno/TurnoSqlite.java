package com.medicar.Turno;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TurnoSqlite {
    static String connection_string = "jdbc:sqlite:sample.db";
    
    static boolean check_if_table_exists(){
        try {
            Connection connection = DriverManager.getConnection(connection_string);
            Statement statement = connection.createStatement(); 
            statement.setQueryTimeout(30); // set timeout to 30 sec.

            ResultSet rs = statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='turnos';");
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace(System.err);
            return false;
        }
    }

    static void create_table_db(){
        if (check_if_table_exists()) return ;
        try {
            Connection connection = DriverManager.getConnection(connection_string);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30); // set timeout to 30 sec.

            statement.executeUpdate("drop table if exists turnos");
            statement.executeUpdate("""
                CREATE TABLE turnos (
                    turno_id INTEGER PRIMARY KEY,
                    medico_id INTEGER NOT NULL,
                    paciente_id INTEGER NOT NULL,
                    fecha TEXT NOT NULL,
                    FOREIGN KEY (medico_id) REFERENCES medicos(medico_id),
                    FOREIGN KEY (paciente_id) REFERENCES pacientes(paciente_id)
                );
                """);
            // return true;
        } catch (SQLException e) {
            e.printStackTrace(System.err);
            // return false;
        }
    }

    static void insert_into_db(String medico_id, String paciente_id, String fecha){
        try {
            Connection connection = DriverManager.getConnection(connection_string);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30); // set timeout to 30 sec.

            statement.executeUpdate("insert into turnos values('" + medico_id + "', '" + paciente_id + "', '" + fecha + "')");
            ResultSet rs = statement.executeQuery("select * from turnos");
            rs.next();
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }
}
