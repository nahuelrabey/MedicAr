package com.medicar.Medico;

public class Medico {
    public String id;
    public String name;
    public String matricula;
    // static MedicoSqlite sqlite = new MedicoSqlite();

    public Medico(String id, String name, String matricula) {
        this.id = id;
        this.name = name;
        this.matricula = matricula;
    }

    public void save_to_db(){
        MedicoSqlite.insert_into_db(this.id, this.name, this.matricula);
    }

}
