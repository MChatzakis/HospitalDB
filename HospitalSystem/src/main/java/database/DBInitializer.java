/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import database.entities.users.User;
import database.entities.users.Coordinator;
import database.entities.users.Nurse;
import database.entities.users.Doctor;
import database.entities.Drug;
import database.entities.DutyTime;
import database.entities.Examination;
import database.entities.Illness;
import database.entities.Medical;
import database.entities.users.Patient;
import database.entities.Visit;
import database.relations.OnDutyDoctors;
import database.relations.OnDutyNurses;
import database.relations.OnDutyWorkers;

import java.sql.SQLException;

/**
 * Class to create the database and insert the tables needed.
 *
 * @author Manos Chatzakis (chatzakis@ics.forth.gr)
 * @author George Kokolakis (gkokol@ics.forth.gr)
 */
public class DBInitializer {

    DBConnection conn;

    public DBInitializer() throws SQLException {
        conn = new DBConnection("jdbc:mysql://localhost/", "root", "");
    }

    public void buildDB() throws SQLException {
        createDB();
        createLogin();
        createDoctors();
        createWorkers();
        createDutyTime();
        //createMedicStaff();
        createPatients();
        createVisits();
        //createExaminations();
        conn.closeDBConnection();
    }

    public void createDB() throws SQLException {
        String create = "CREATE DATABASE IF NOT EXISTS hospital";
        conn = new DBConnection("jdbc:mysql://localhost/", "root", "");
        conn.updateQuery(create);
    }

    public void createLogin() throws SQLException {
        User login = new User();
        login.createTable();
    }

    public void createDutyTime() throws SQLException {
        new DutyTime().createTable();
        new OnDutyNurses().createTable();
        new OnDutyDoctors().createTable();
        new OnDutyWorkers().createTable();
    }

    public void createPatients() throws SQLException {
        Patient pat = new Patient();
        pat.createTable();
        pat.createTableChronicDiseases();
    }

    public void createExaminations() throws SQLException {
        Medical med = new Medical();
        Examination exam = new Examination();
        exam.createTable();
        med.createTable();
        /*med.createTable("medicals_covid_Tests");
        med.createTable("medicals_xRay_Tests");
        med.createTable("medicals_blood_tests");*/
        exam.alterTableToAddMedical();
    }

    public void createDoctors() throws SQLException {
        Doctor doctor = new Doctor();
        doctor.createTable();
        /*doctor.createTable("doctors_endocrinologists");
        doctor.createTable("doctors_gynecologists");
        doctor.createTable("doctors_pathologists");
        doctor.createTable("doctors_pulmonologists");
        doctor.createTable("doctors_cardiologists");*/
    }

    public void createMedicStaff() throws SQLException {
        new Illness().createTable();
        new Drug().createTable();
    }

    public void createWorkers() throws SQLException {
        new Nurse().createTable();
        new Coordinator().createTable();
    }

    public void dropDB() throws SQLException {
        String drop = "DROP DATABASE IF EXISTS hospital;";
        conn = new DBConnection();
        conn.updateQuery(drop);
        conn.closeDBConnection();
    }

    public void createVisits() throws SQLException {
        Visit vis = new Visit();
        vis.createTable();
        vis.createTableSymptoms();
    }

    public void dropTable(String table) throws SQLException {
        conn = new DBConnection();
        String dropTable = "DROP TABLE IF EXISTS " + table + ";";
        conn.updateQuery(dropTable);
        conn.closeDBConnection();
    }
}
