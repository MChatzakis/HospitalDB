/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import database.entities.Coordinator;
import database.entities.Doctor;
import database.entities.Drug;
import database.entities.DutyTime;
import database.entities.Illness;
import database.entities.Nurse;
import java.sql.SQLException;

/**
 *
 * @author manos
 */
public class DBDataGenerator {

    public void insertData() throws SQLException {
        insertDoctors();
        insertNurses();
        insertCoordinators();
        insertIllnesses();
        insertDrugs();
        insertDuties();
    }

    public void insertDoctors() throws SQLException {
        insertCardiologists();
        insertPathologists();
        insertEndocrinologists();
        insertGynecologists();
        insertPulmonologists();
    }

    public void insertNurses() throws SQLException{
        Nurse nurse = new Nurse();
        nurse.addNurse("5050", "Eva", "Kokolaki","unkw","eva@gmail.com","696969696");
    }
    
    public void insertCoordinators() throws SQLException{
        Coordinator coord = new Coordinator();
        coord.addCoordinator("6060", "Olimpia", "Popa", "athina","ola.popa@gmail.com","69696969696");
    }
    
    public void insertDrugs() throws SQLException{
        Drug drug = new Drug();
        drug.addDrug("1", "Depon", "Pill", "500mg", "2");
    }
    
    public void insertIllnesses() throws SQLException{
        Illness ill = new Illness();
        ill.addIllness("2", "Ponokefalos");
    }
    
    public void insertDuties() throws SQLException{
        DutyTime duty = new DutyTime();
        duty.addDutyTime("1","10/10/2010", "6060");
    }
    
    public void insertCardiologists() throws SQLException {
        Doctor doctor = new Doctor();
        doctor.addDoctor("cardiologists", "8080", "Manos", "Chatzakis", "Patmou19", "chatzakis@ics.forth.gr", "6978640371");
    }

    public void insertGynecologists() throws SQLException {
        Doctor doctor = new Doctor();
        doctor.addDoctor("gynecologists", "8081", "Nikos", "Fanourakis", "Kapoy", "nikos@ics.forth.gr", "69786403111");
    }

    public void insertPathologists() throws SQLException {
        Doctor doctor = new Doctor();
        doctor.addDoctor("pathologists", "8082", "Giwrgos", "Kokolakis", "Kapou10", "giwrgos@ics.forth.gr", "6971110371");
    }

    public void insertEndocrinologists() throws SQLException {
        Doctor doctor = new Doctor();
        doctor.addDoctor("endocrinologists", "8084", "Mirto", "Pagwmenaki", "Kapou18", "mirto@ics.forth.gr", "6978600071");
    }

    public void insertPulmonologists() throws SQLException {
        Doctor doctor = new Doctor();
        doctor.addDoctor("pulmonologists", "8083", "Giannis", "Mutakis", "Kapoy12", "giannis@ics.forth.gr", "6978644441");

    }

    
}
