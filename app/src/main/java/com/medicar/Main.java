package com.medicar;

// import com.medicar.Medico.Medico;
import com.medicar.Medico.MedicoSqlite;
import com.medicar.Paciente.PacienteSqlite;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        MedicoSqlite.delete_table();

        MedicoSqlite.create_table_db();
        System.out.println("medico Table created!");
        MedicoSqlite.new_medico("Rodrigo", "bc1123mpk");
        System.out.println("Inserted Rodrigo into db!");

        PacienteSqlite.delete_table();
        PacienteSqlite.create_table_db();
        System.out.println("paciente Table created!");
        PacienteSqlite.new_paciente("Jorge Macri", "25135410");
        System.out.println("paciente Jorge Macri created!");

    }
}