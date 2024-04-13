package com.medicar.Paciente;

public class Paciente {
    public int id;
    public String name;
    public String dni;

    public Paciente(int id, String name, String dni) {
        this.id = id;
        this.name = name;
        this.dni = dni;
    }

    public void save_to_db(){
        PacienteSqlite.new_paciente(this.name, this.dni);
    }

    static void create_table_db(){
        PacienteSqlite.create_table_db();
    }
}
