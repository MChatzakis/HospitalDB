package database;

import database.entities.users.Coordinator;
import database.entities.users.Doctor;
import database.entities.medics.Drug;
import database.entities.DutyTime;
import database.entities.Examination;
import database.entities.medics.Illness;
import database.entities.Medical;
import database.entities.Visit;
import database.entities.users.Nurse;
import database.entities.users.Patient;
import java.sql.SQLException;

/**
 *
 * @author Manos Chatzakis (chatzakis@ics.forth.gr)
 * @author George Kokolakis (gkokol@ics.forth.gr)
 */
public class DBDataGenerator {

    public void insertData() throws SQLException {
        insertDoctors();
        insertNurses();
        insertCoordinators();
        insertIllnesses();
        insertDrugs();
        insertDuties();
        insertPatients();
        insertVisit();
        insertInitExams();
        insertMedicals();
        insertReExams();

    }

    public void insertDoctors() throws SQLException {
        insertCardiologists();
        insertPathologists();
        insertEndocrinologists();
        insertGynecologists();
        insertPulmonologists();
    }

    public void insertNurses() throws SQLException {
        Nurse nurse = new Nurse();
        nurse.addNurse("evach", "evach123", "Eva", "Chamilaki", "Hrakleio", "ch@tep.gr", "null", "AT1010");
    }

    public void insertCoordinators() throws SQLException {
        Coordinator coord = new Coordinator();
        coord.addCoordinator("alvin", "alvin123", "Alvi", "Nikola", "Hrakleio", "alvi@tep.gr", "null", "AN6768");
    }

    public void insertDrugs() throws SQLException {
        Drug drug = new Drug();
        drug.addDrug("Vaccine", "Vaccine", "500mg", "1");
        drug.addDrug("Aspirin", "Pill", "500mg", "2");
        drug.addDrug("Bandage", "Applicable", "null", "3");
        drug.addDrug("AllergyCream", "Cream", "100mg", "4");
    }

    public void insertIllnesses() throws SQLException {
        Illness ill = new Illness();
        ill.addIllness("Covid");
        ill.addIllness("Flu");
        ill.addIllness("Bone_Break");
        ill.addIllness("Allergy");
    }

    public void insertDuties() throws SQLException {
        DutyTime duty = new DutyTime();
        duty.addDutyTime("17/12/2020", "7");
    }

    public void insertCardiologists() throws SQLException {
        Doctor doctor = new Doctor();
        doctor.addDoctor("chatz", "manos123", "Manos", "Chatzakis", "Hrakleio", "chatzakis@tep.gr", "null", "cardiologist", "AT5555");
    }

    public void insertGynecologists() throws SQLException {
        Doctor doctor = new Doctor();
        doctor.addDoctor("georgegk", "george123", "George", "Kokolakis", "Hrakleio", "kokol@tep.gr", "null", "gynecologist", "KL3409");

    }

    public void insertPathologists() throws SQLException {
        Doctor doctor = new Doctor();
        doctor.addDoctor("drosos", "drosos123", "Drosos", "Drosakis", "Hrakleio", "drosos@tep.gr", "null", "pathologist", "AE2359");

    }

    public void insertEndocrinologists() throws SQLException {
        Doctor doctor = new Doctor();
        doctor.addDoctor("elvis", "elvis123", "Elvira", "Sakoudi", "Hrakleio", "sak@tep.gr", "null", "endocrinologist", "MN8866");

    }

    public void insertPulmonologists() throws SQLException {
        Doctor doctor = new Doctor();
        doctor.addDoctor("gfou", "gfou123", "Giorgos", "Fountakis", "Hrakleio", "fount@tep.gr", "null", "pulmonologist", "UT4629");

    }

    public void insertMedicals() throws SQLException {
        Medical med = new Medical();
        med.addMedical("covid", "1", "8", "1", "6", "17/12/2020");
    }

    public void insertPatients() throws SQLException {
        Patient pat = new Patient();
        pat.addPatient("nickLen", "len123", "Nikos", "Lenakis", "Hrakleio", "len@gmail.com", "null", "16/11/1990", "12066455", "IK8986", "IKA");
    }

    public void insertVisit() throws SQLException {
        Visit vis = new Visit();
        vis.addVisit("2020", "1", "8");
        vis.addSymptom("1", "Piretos");
    }

    public void insertInitExams() throws SQLException {
        Examination ex = new Examination();
        ex.addInitialExam("8", "1", "null", "null", "1", "17/12/2020");
    }

    public void insertReExams() throws SQLException {
        Examination ex = new Examination();
        ex.addReExam("8", "1", "1", "1", "1", "17/12/2020", "1", false);
    }
}
