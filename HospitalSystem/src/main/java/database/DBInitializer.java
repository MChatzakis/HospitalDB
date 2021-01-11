package database;

import database.entities.users.User;
import database.entities.users.Coordinator;
import database.entities.users.Nurse;
import database.entities.users.Doctor;
import database.entities.medics.Drug;
import database.entities.DutyTime;
import database.entities.Examination;
import database.entities.medics.Illness;
import database.entities.Medical;
import database.entities.ReExamination;
import database.entities.users.Patient;
import database.entities.Visit;

import database.relations.OnDutyDoctors;
import database.relations.OnDutyNurses;
import database.relations.OnDutyWorkers;

import java.sql.SQLException;

/**
 * This class creates the database and the tables needed.
 *
 * @author Manos Chatzakis (chatzakis@ics.forth.gr)
 * @author George Kokolakis (gkokol@ics.forth.gr)
 */
public class DBInitializer {

    DBConnection conn;

    public DBInitializer() throws SQLException {
        conn = new DBConnection("jdbc:mysql://localhost/", "root", "");
    }

    public void buildDB() throws SQLException, ClassNotFoundException {
        createDB();
        createLogin();
        createDoctors();
        createWorkers();
        createDutyTime();
        createMedicStaff();
        createPatients();
        createVisits();
        createExaminations();

        conn.closeDBConnection();
    }

    public void createDB() throws SQLException {
        String create = "CREATE DATABASE IF NOT EXISTS hospital";
        conn = new DBConnection("jdbc:mysql://localhost/", "root", "");
        conn.updateQuery(create);
    }

    public void createLogin() throws SQLException, ClassNotFoundException {
        User login = new User();
        login.createTable();
    }

    public void createDutyTime() throws SQLException, ClassNotFoundException {
        new DutyTime().createTable();
        new OnDutyNurses().createTable();
        new OnDutyDoctors().createTable();
        new OnDutyWorkers().createTable();
    }

    public void createPatients() throws SQLException, ClassNotFoundException {
        Patient pat = new Patient();
        pat.createTable();
        pat.createTableChronicDiseases();
    }

    public void createExaminations() throws SQLException, ClassNotFoundException {
        Medical med = new Medical();
        Examination exam = new Examination();
        ReExamination reExam = new ReExamination();
        exam.createTable();
        med.createTable();
        reExam.createTable();
    }

    public void createDoctors() throws SQLException, ClassNotFoundException {
        Doctor doctor = new Doctor();
        doctor.createTable();
    }

    public void createMedicStaff() throws SQLException, ClassNotFoundException {
        new Illness().createTable();
        new Drug().createTable();
    }

    public void createWorkers() throws SQLException, ClassNotFoundException {
        new Nurse().createTable();
        new Coordinator().createTable();
    }

    public void dropDB() throws SQLException, ClassNotFoundException {
        String drop = "DROP DATABASE IF EXISTS hospital;";
        conn = new DBConnection();
        conn.updateQuery(drop);
        conn.closeDBConnection();
    }

    public void createVisits() throws SQLException, ClassNotFoundException {
        Visit vis = new Visit();
        vis.createTable();
        vis.createTableSymptoms();
    }

    public void dropTable(String table) throws SQLException, ClassNotFoundException {
        conn = new DBConnection();
        String dropTable = "DROP TABLE IF EXISTS " + table + ";";
        conn.updateQuery(dropTable);
        conn.closeDBConnection();
    }
}
