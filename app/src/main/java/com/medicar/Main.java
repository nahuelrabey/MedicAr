package com.medicar;

import com.medicar.Medico.MedicoSqlite;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        MedicoSqlite.create_table_db();
        System.out.println("Table created!");
        MedicoSqlite.new_medico("Rodrigo", "bc1123mpk");
        System.out.println("Inserted into db!");
    }
}