/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import database.entities.Coordinator;
import database.entities.Nurse;
import database.entities.Doctor;
import database.entities.Drug;
import database.entities.DutyTime;
import database.entities.Illness;
import database.entities.Patient;
import database.relations.OnDutyDoctors;
import database.relations.OnDutyNurses;

import java.sql.SQLException;

/**
 * Class to create the database and insert the tables needed.
 *
 * @author Manos Chatzakis (chatzakis@ics.forth.gr)
 * @author George Kokolakis (gkokol@ics.forth.gr)
 */
public class DBInitializer
{

    DBConnection conn;

    public DBInitializer() throws SQLException
    {
        conn = new DBConnection("jdbc:mysql://localhost/", "root", "");
    }

    public void buildDB() throws SQLException
    {
        createDB();
        createLogin();
        createDoctors();
        createWorkers();
        createDutyTime();
        createMedicStaff();
        createPatients();
        conn.closeDBConnection();
    }

    public void createDB() throws SQLException
    {
        String create = "CREATE DATABASE IF NOT EXISTS hospital";
        conn = new DBConnection("jdbc:mysql://localhost/", "root", "");
        conn.updateQuery(create);
    }

    public void createLogin() throws SQLException
    {
        Login login = new Login();
        login.createTable();
    }

    public void createDutyTime() throws SQLException
    {
        new DutyTime().createTable();
        new OnDutyNurses().createTable();

        new OnDutyDoctors().createTable();
        new OnDutyDoctors().createTable();
        new OnDutyDoctors().createTable();
        new OnDutyDoctors().createTable();
        new OnDutyDoctors().createTable();

    }

    public void createPatients() throws SQLException
    {
        new Patient().createTable();
    }

    public void createDoctors() throws SQLException
    {
        Doctor doctor = new Doctor();
        doctor.createTable("endocrinologists");
        doctor.createTable("gynecologists");
        doctor.createTable("pathologists");
        doctor.createTable("pulmonologists");
        doctor.createTable("cardiologists");
    }

    public void createMedicStaff() throws SQLException
    {
        new Illness().createTable();
        new Drug().createTable();
    }

    public void createWorkers() throws SQLException
    {
        new Nurse().createTable();
        new Coordinator().createTable();
    }

    public void dropDB() throws SQLException
    {
        String drop = "DROP DATABASE IF EXISTS hospital;";
        conn = new DBConnection();
        conn.updateQuery(drop);
        conn.closeDBConnection();
    }

    public void dropTable(String table) throws SQLException
    {
        conn = new DBConnection();
        String dropTable = "DROP TABLE IF EXISTS " + table + ";";
        conn.updateQuery(dropTable);
        conn.closeDBConnection();
    }
}
